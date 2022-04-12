package com.ahrijksmuseum.view.screens.artobjectdetails

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ahrijksmuseum.presentation.factory.ViewModelFactory
import com.ahrijksmuseum.presentation.models.ArtObjectDetailsUiModel
import com.ahrijksmuseum.presentation.models.UiState
import com.ahrijksmuseum.presentation.viewmodels.ArtObjectDetailsViewModel
import com.ahrijksmuseum.view.databinding.FragmentArtObjectDetailsFragmentBinding
import com.ahrijksmuseum.view.di.ViewComponent
import com.ahrijksmuseum.view.di.ViewComponentFactoryProvider
import com.ahrijksmuseum.view.mappers.UiErrorsMapper
import com.ahrijksmuseum.view.utils.visible
import javax.inject.Inject


class ArtObjectDetailsFragment : Fragment() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory

    private val args: ArtObjectDetailsFragmentArgs by navArgs()

    private val viewModel: ArtObjectDetailsViewModel by viewModels { viewModelFactory }

    private var _binding: FragmentArtObjectDetailsFragmentBinding? = null
    private val binding: FragmentArtObjectDetailsFragmentBinding
        get() = _binding!!

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
        _binding = FragmentArtObjectDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupObservers()
        viewModel.start(args.objectNumber)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun setupListeners() {
        binding.content.setOnRefreshListener {
            viewModel.onRefresh()
        }
    }

    private fun setupObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is UiState.Error -> showErrorDialog(uiState.error)
                is UiState.Loaded -> showContent(uiState.data)
                is UiState.Loading -> showLoading()
            }
        }
    }

    private fun showContent(artObjectDetailsUiModel: ArtObjectDetailsUiModel) {
        with(binding) {
            content.isRefreshing = false

            artObjectDetailsTitle.text = artObjectDetailsUiModel.title
            artObjectDetailsObjectNumber.text = artObjectDetailsUiModel.objectNumber
            artObjectDetailsDating.text = artObjectDetailsUiModel.dating
            artObjectDetailsSubtitle.text = artObjectDetailsUiModel.subTitle
            artObjectDetailsLongTitle.text = artObjectDetailsUiModel.longTitle
            artObjectDetailsDescription.text = artObjectDetailsUiModel.description
            artObjectDetailsLabel.text = artObjectDetailsUiModel.scLabelLine
            artObjectDetailsPrincipalMaker.text = artObjectDetailsUiModel.principalOrFirstMaker
            artObjectDetailsMaterials.text = artObjectDetailsUiModel.materials
            artObjectDetailsTechniques.text = artObjectDetailsUiModel.techniques
            artObjectDetailsProductionPlaces.text = artObjectDetailsUiModel.productionPlaces
            artObjectDetailsDocumentation.text = artObjectDetailsUiModel.documentation

            artObjectDetailsUiModel.description.isNullOrEmpty().not().also { visible ->
                artObjectDetailsDescriptionTitle.visible(visible)
                artObjectDetailsDescription.visible(visible)
                artObjectDetailsDescriptionDivider.visible(visible)
            }
            artObjectDetailsUiModel.scLabelLine.isNullOrEmpty().not().also { visible ->
                artObjectDetailsLabelTitle.visible(visible)
                artObjectDetailsLabel.visible(visible)
                artObjectDetailsLabelDivider.visible(visible)
            }
            artObjectDetailsUiModel.materials.isNullOrEmpty().not().also { visible ->
                artObjectDetailsMaterialsTitle.visible(visible)
                artObjectDetailsMaterials.visible(visible)
                artObjectDetailsMaterialsDivider.visible(visible)
            }
            artObjectDetailsUiModel.techniques.isNullOrEmpty().not().also { visible ->
                artObjectDetailsTechniquesTitle.visible(visible)
                artObjectDetailsTechniques.visible(visible)
                artObjectDetailsTechniquesDivider.visible(visible)
            }
            artObjectDetailsUiModel.productionPlaces.isNullOrEmpty().not().also { visible ->
                artObjectDetailsProductionPlacesTitle.visible(visible)
                artObjectDetailsProductionPlaces.visible(visible)
                artObjectDetailsProductionPlacesDivider.visible(visible)
            }
            artObjectDetailsUiModel.documentation.isNullOrEmpty().not().also { visible ->
                artObjectDetailsDocumentationTitle.visible(visible)
                artObjectDetailsDocumentation.visible(visible)
                artObjectDetailsDocumentationDivider.visible(visible)
            }

            loading.visible(false)
            content.visible(true)
        }
    }

    private fun showLoading() {
        with(binding) {
            loading.visible(true)
            content.visible(false)
        }
    }

    private fun showErrorDialog(error: Throwable?) {
        with(binding) {
            loading.visible(false)
            content.visible(false)
            content.isRefreshing = false
        }

        AlertDialog.Builder(context)
            .setMessage(UiErrorsMapper(resources).map(error))
            .setPositiveButton(android.R.string.ok) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

}