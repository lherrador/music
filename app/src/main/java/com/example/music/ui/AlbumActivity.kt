package com.example.music.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.music.R
import com.example.music.ui.adapter.AlbumListAdapter
import com.example.music.utils.bind
import com.example.music.viewModel.AlbumViewModel
import com.example.music.viewModel.factory.AlbumViewModelFactory

class AlbumActivity : AppCompatActivity() {

	private val swipeContainer by bind<SwipeRefreshLayout>(R.id.swipeContainer)
	private val recyclerView by bind<RecyclerView>(R.id.recycler_view)
	private val albumListAdapter = AlbumListAdapter()
	private val albumViewModel by lazy {
		ViewModelProvider(this, AlbumViewModelFactory(this.application)).get(
				AlbumViewModel::class.java)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.album_list_activity_main)
		recyclerView.adapter = albumListAdapter

		albumViewModel.albumLiveData.observeForever {
			albumListAdapter.submitList(it.albumUiDataList)
			swipeContainer.isRefreshing = false
		}

		swipeContainer.setOnRefreshListener {
			albumViewModel.refresh()
		}
	}
}
