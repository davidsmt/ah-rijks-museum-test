package com.ahrijksmuseum.view.screens.artobjects.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahrijksmuseum.view.R
import com.ahrijksmuseum.view.databinding.LayoutLoadStateItemBinding
import com.ahrijksmuseum.view.mappers.UiErrorsMapper

class PagingLoadStateAdapter(
    private val adapter: ArtObjectsAdapter
) : LoadStateAdapter<PagingLoadStateAdapter.LoadStateItemViewHolder>() {
    override fun onBindViewHolder(holder: LoadStateItemViewHolder, loadState: LoadState) {
        holder.bindTo(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadStateItemViewHolder {
        return LoadStateItemViewHolder(parent) { adapter.retry() }
    }

    class LoadStateItemViewHolder(
        parent: ViewGroup,
        private val retryCallback: () -> Unit
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.layout_load_state_item, parent, false)
    ) {
        private val binding = LayoutLoadStateItemBinding.bind(itemView)
        private val progressBar = binding.progressBar
        private val errorMsg = binding.errorMsg
        private val retry = binding.retryButton
            .also {
                it.setOnClickListener { retryCallback() }
            }

        fun bindTo(loadState: LoadState) {
            progressBar.isVisible = loadState is LoadState.Loading
            retry.isVisible = loadState is LoadState.Error
            errorMsg.isVisible = !(loadState as? LoadState.Error)?.error?.message.isNullOrBlank()
            errorMsg.text = (loadState as? LoadState.Error)?.error?.message
            errorMsg.text =
                UiErrorsMapper(itemView.resources).map((loadState as? LoadState.Error)?.error)
        }
    }

}