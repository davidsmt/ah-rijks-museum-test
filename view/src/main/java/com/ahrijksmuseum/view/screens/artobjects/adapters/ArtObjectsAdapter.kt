package com.ahrijksmuseum.view.screens.artobjects.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ahrijksmuseum.presentation.models.ArtObjectUiModel
import com.ahrijksmuseum.view.R
import com.ahrijksmuseum.view.databinding.LayoutArtObjectItemBinding
import com.ahrijksmuseum.view.databinding.LayoutArtistHeaderItemBinding
import com.bumptech.glide.Glide


class ArtObjectsAdapter(
    private val onItemClicked: (objectNumber: String) -> Unit
) : PagingDataAdapter<ArtObjectUiModel, RecyclerView.ViewHolder>(
    ArtObjectItemCallback()
) {

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is ArtObjectUiModel.ArtObject -> R.layout.layout_art_object_item
        is ArtObjectUiModel.ArtistHeader -> R.layout.layout_artist_header_item
        null -> throw IllegalStateException("Unknown view")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            val item = getItem(position)
            when (holder) {
                is ArtObjectHolder -> holder.bind(item as ArtObjectUiModel.ArtObject)
                is ArtistHeaderHolder -> holder.bind(item as ArtObjectUiModel.ArtistHeader)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.layout_artist_header_item -> ArtistHeaderHolder(
                binding = LayoutArtistHeaderItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> ArtObjectHolder(
                binding = LayoutArtObjectItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                onItemClicked = onItemClicked
            )
        }
    }


    class ArtObjectHolder(
        private val binding: LayoutArtObjectItemBinding,
        private val onItemClicked: (login: String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(uiModel: ArtObjectUiModel.ArtObject?) {
            with(binding) {
                when (uiModel) {
                    null -> {
                        artObjectTitle.text = itemView.resources.getString(R.string.loading)
                    }
                    else -> {
                        Glide
                            .with(binding.root.context)
                            .load(uiModel.imageUrl)
                            .into(binding.artObjectHeaderImage)

                        artObjectTitle.text = uiModel.title.orEmpty()
                        artObjectLongTitle.text = uiModel.longTitle.orEmpty()

                        root.setOnClickListener {
                            onItemClicked(uiModel.objectNumber)
                        }
                    }
                }
            }
        }
    }

    class ArtistHeaderHolder(
        private val binding: LayoutArtistHeaderItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(uiModel: ArtObjectUiModel.ArtistHeader?) {
            with(binding) {
                header.text = uiModel?.principalOrFirstMaker.orEmpty()
            }
        }
    }

    class ArtObjectItemCallback : DiffUtil.ItemCallback<ArtObjectUiModel>() {
        override fun areItemsTheSame(
            old: ArtObjectUiModel,
            new: ArtObjectUiModel
        ): Boolean =
            old is ArtObjectUiModel.ArtObject && new is ArtObjectUiModel.ArtObject && old.objectNumber == new.objectNumber

        override fun areContentsTheSame(
            old: ArtObjectUiModel,
            new: ArtObjectUiModel
        ): Boolean = old == new
    }
}