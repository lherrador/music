package com.example.music.ui.albumlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.music.databinding.AlbumListActivityMainBinding
import com.example.music.ui.albumdetail.AlbumActivity
import com.example.music.ui.albumlist.adapter.AlbumListAdapter
import com.example.music.viewModel.AlbumListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.android.viewmodel.ext.android.viewModel

class AlbumListActivity : AppCompatActivity() {

	private lateinit var albumListAdapter : AlbumListAdapter
	private lateinit var binding : AlbumListActivityMainBinding
	private val compositeDisposable = CompositeDisposable()
	private val albumListViewModel : AlbumListViewModel by viewModel()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = AlbumListActivityMainBinding.inflate(layoutInflater)
		albumListAdapter = AlbumListAdapter() {
			AlbumActivity.start(this, it)
		}
		binding.recyclerView.adapter = albumListAdapter

		albumListViewModel.refresh()

		binding.swipeContainer.setOnRefreshListener {
			albumListViewModel.refresh()
		}

		setContentView(binding.root)
	}

	override fun onResume() {
		compositeDisposable.add(
				albumListViewModel.albumUIDatasource.observeOn(AndroidSchedulers.mainThread())
						.subscribeOn(Schedulers.io())
						.subscribe { uiData ->
							albumListAdapter.submitList(uiData.albumUiDataList)
							binding.swipeContainer.isRefreshing = false;
						})
		super.onResume()
	}

	override fun onStop() {
		compositeDisposable.dispose()
		super.onStop()
	}
}
