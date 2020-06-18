package com.example.music.ui.albumdetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.music.R
import com.example.music.ui.albumdetail.recyclerviewholder.TrackRecyclerViewHolder
import com.example.music.ui.uidata.AlbumUiData
import com.example.music.ui.uidata.TrackUiData

class TrackListAdapter(private val onClick: (String) -> Unit = {}) :
		ListAdapter<TrackUiData, TrackRecyclerViewHolder>(DiffCallback()) {
	override fun onCreateViewHolder(parent: ViewGroup,
	                                viewType: Int): TrackRecyclerViewHolder {
		val view = LayoutInflater.from(parent.context)
				.inflate(R.layout.viewholder_album_list_item, parent, false)
		return TrackRecyclerViewHolder(view)
	}

	override fun onBindViewHolder(trackRecyclerViewHolder: TrackRecyclerViewHolder,
								  position: Int) {
		trackRecyclerViewHolder.bind(currentList[position], onClick)
	}
}

class DiffCallback : DiffUtil.ItemCallback<TrackUiData>() {
	override fun areItemsTheSame(oldItem: TrackUiData, newItem: TrackUiData): Boolean {
		return oldItem.id == newItem.id
	}

	override fun areContentsTheSame(oldItem: TrackUiData, newItem: TrackUiData): Boolean {
		return oldItem.description == newItem.description && oldItem.image == newItem.image && oldItem.preview == newItem.preview
	}

}