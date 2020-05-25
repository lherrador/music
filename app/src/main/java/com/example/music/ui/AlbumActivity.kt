package com.example.music.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.music.MusicApplication
import com.example.music.R
import com.example.music.configuration.DaggerIMusicComponent
import com.example.music.domain.IMusicDomain
import com.example.music.ui.adapter.AlbumListAdapter
import com.example.music.utils.bind
import com.example.music.viewModel.AlbumViewModel
import com.example.music.viewModel.factory.AlbumViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AlbumActivity : AppCompatActivity() {

	@Inject lateinit var musicDomain : IMusicDomain

	private val swipeContainer by bind<SwipeRefreshLayout>(R.id.swipeContainer)
	private val recyclerView by bind<RecyclerView>(R.id.recycler_view)
	private val albumListAdapter = AlbumListAdapter()
	private val compositeDisposable = CompositeDisposable()
	private val albumViewModel by lazy {
		ViewModelProvider(this, AlbumViewModelFactory(this.application, musicDomain)).get(
				AlbumViewModel::class.java)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		(application as MusicApplication).findMusicComponent().inject(this)

		setContentView(R.layout.album_list_activity_main)
		recyclerView.adapter = albumListAdapter
		albumViewModel.refresh()

		swipeContainer.setOnRefreshListener {
			albumViewModel.refresh()
		}
	}

	override fun onResume() {
		compositeDisposable.add(
				albumViewModel.albumUIDatasource.observeOn(AndroidSchedulers.mainThread())
						.subscribeOn(Schedulers.io())
						.subscribe { uiData ->
							albumListAdapter.submitList(uiData.albumUiDataList)
							swipeContainer.isRefreshing = false;
						})
		super.onResume()
	}

	override fun onStop() {
		compositeDisposable.dispose()
		super.onStop()
	}
}
