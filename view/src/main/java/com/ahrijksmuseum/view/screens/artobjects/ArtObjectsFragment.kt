package com.ahrijksmuseum.view.screens.artobjects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import com.ahrijksmuseum.presentation.factory.ViewModelFactory
import com.ahrijksmuseum.presentation.viewmodels.ArtObjectsViewModel
import com.ahrijksmuseum.view.R
import com.ahrijksmuseum.view.databinding.FragmentArtObjectsFragmentBinding
import com.ahrijksmuseum.view.di.ViewComponent
import com.ahrijksmuseum.view.di.ViewComponentFactoryProvider
import com.ahrijksmuseum.view.mappers.UiErrorsMapper
import com.ahrijksmuseum.view.screens.artobjects.adapters.ArtObjectsAdapter
import com.ahrijksmuseum.view.screens.artobjects.adapters.PagingLoadStateAdapter
import com.ahrijksmuseum.view.utils.clearItemDecorations
import com.ahrijksmuseum.view.utils.withCustomDrawable
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import javax.inject.Inject

class ArtObjectsFragment : Fragment() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: ArtObjectsViewModel by viewModels { viewModelFactory }

    private var _binding: FragmentArtObjectsFragmentBinding? = null
    private val binding: FragmentArtObjectsFragmentBinding
        get() = _binding!!

    private lateinit var artObjectsAdapter: ArtObjectsAdapter

    private fun getViewComponent(): ViewComponent =
        (requireContext().applicationContext as ViewComponentFactoryProvider).provideViewComponentFactory()
            .create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getViewComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArtObjectsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupListeners()
    }

    override fun onDestroyView() {
        binding.artObjectsList.clearItemDecorations()
        _binding = null
        super.onDestroyView()
    }

    @OptIn(InternalCoroutinesApi::class)
    private fun setupViews() {
        with(binding) {
            artObjectsAdapter = ArtObjectsAdapter { objectNumber ->
                findNavController().navigate(
                    ArtObjectsFragmentDirections.actionFragmentArtObjectsFragmentToArtObjectDetailsFragment(
                        objectNumber
                    )
                )
            }

            with(artObjectsList) {
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
                    .withCustomDrawable(requireContext(), R.drawable.divider)
                    .also {
                        addItemDecoration(it)
                    }

                adapter = artObjectsAdapter.withLoadStateHeaderAndFooter(
                    header = PagingLoadStateAdapter(artObjectsAdapter),
                    footer = PagingLoadStateAdapter(artObjectsAdapter)
                )
            }

            viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                artObjectsAdapter.loadStateFlow.collectLatest { loadStates ->
                    binding.swipeRefreshContainer.isRefreshing =
                        loadStates.refresh is LoadState.Loading
                }
            }

            viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                viewModel.uiState.collectLatest {
                    artObjectsAdapter.submitData(it)
                }
            }

            viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                artObjectsAdapter.loadStateFlow
                    .distinctUntilChangedBy { it.refresh }
                    .filter { it.refresh is LoadState.Error }
                    .collectLatest { loadStates ->
                        if (loadStates.refresh is LoadState.Error) {
                            Toast.makeText(
                                requireContext(),
                                UiErrorsMapper(requireContext().resources).map((loadStates.refresh as LoadState.Error).error),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            }
        }
    }

    private fun setupListeners() {
        binding.swipeRefreshContainer.setOnRefreshListener {
            artObjectsAdapter.refresh()
        }
    }

}