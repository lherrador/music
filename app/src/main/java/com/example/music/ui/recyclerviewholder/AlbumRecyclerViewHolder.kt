package com.example.music.ui.recyclerviewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.music.R
import com.example.music.ui.uidata.AlbumUiData
import com.example.music.utils.bind
import com.squareup.picasso.Picasso

class AlbumRecyclerViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
	private val imageView by bind<ImageView>(R.id.image)
	private val description by bind<TextView>(R.id.description)

	fun bind(albumUiData: AlbumUiData) {
		Picasso.get()
				.load(albumUiData.image)
				.placeholder(R.drawable.emty_sate_album)
				.into(imageView)
		description.text = albumUiData.description
	}
}