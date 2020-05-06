package com.example.music.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.music.R
import com.example.music.ui.uidata.AlbumUiData
import com.example.music.ui.recyclerviewholder.AlbumRecyclerViewHolder

class AlbumListAdapter :
		ListAdapter<AlbumUiData, AlbumRecyclerViewHolder>(DiffCallback()) {
	override fun onCreateViewHolder(parent: ViewGroup,
	                                viewType: Int): AlbumRecyclerViewHolder {
		val view = LayoutInflater.from(parent.context)
				.inflate(R.layout.viewholder_album_list_item, parent, false)
		return AlbumRecyclerViewHolder(view)
	}

	override fun onBindViewHolder(albumRecyclerViewHolder: AlbumRecyclerViewHolder,
								  position: Int) {
		albumRecyclerViewHolder.bind(currentList[position])
	}
}

class DiffCallback : DiffUtil.ItemCallback<AlbumUiData>() {
	override fun areItemsTheSame(oldItem: AlbumUiData, newItem: AlbumUiData): Boolean {
		return oldItem.id == newItem.id
	}

	override fun areContentsTheSame(oldItem: AlbumUiData, newItem: AlbumUiData): Boolean {
		return oldItem.description == newItem.description && oldItem.image == newItem.image
	}

}