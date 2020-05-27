package com.example.music.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.music.domain.IMusicDomain
import com.example.music.ui.uidata.AlbumContentUiData
import com.example.music.ui.mapper.UiDataMapper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject

class AlbumViewModel(hostApplication: Application) : AndroidViewModel(hostApplication), KoinComponent {


	private val musicDomain: IMusicDomain by inject()

	private val compositeDisposable = CompositeDisposable()
	private val uiDataMapper = UiDataMapper()
	val albumUIDatasource: BehaviorProcessor<AlbumContentUiData> by lazy {
		BehaviorProcessor.create<AlbumContentUiData>()
	}

	fun refresh() {
		compositeDisposable.add(musicDomain.findAlbum()
				.map {
					uiDataMapper.mapAlbumListToUiData(it)
				}
				.subscribeOn(Schedulers.io())
				.subscribe({
					albumUIDatasource.onNext(it)
				}, {
					Log.e("TAG", it.message ?: "Error on api music")
				}))
	}

	override fun onCleared() {
		compositeDisposable.dispose()
		super.onCleared()
	}
}