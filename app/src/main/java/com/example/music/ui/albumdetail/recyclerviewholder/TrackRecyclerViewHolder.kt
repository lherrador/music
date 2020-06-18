package com.example.music.ui.albumdetail.recyclerviewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.music.R
import com.example.music.ui.uidata.AlbumUiData
import com.example.music.ui.uidata.TrackUiData
import com.example.music.utils.bind
import com.squareup.picasso.Picasso

class TrackRecyclerViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
	private val imageView by bind<ImageView>(R.id.image)
	private val description by bind<TextView>(R.id.description)

	fun bind(trackUiData: TrackUiData, onClick: (String) -> Unit = {}) {
		view.setOnClickListener {
			onClick.invoke(trackUiData.preview)
		}
		Picasso.get()
				.load(trackUiData.image)
				.placeholder(R.drawable.emty_sate_album)
				.into(imageView)
		description.text = trackUiData.description
	}
}