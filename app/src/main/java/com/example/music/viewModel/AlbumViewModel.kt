package com.example.music.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.music.domain.IMusicDomain
import com.example.music.ui.mapper.UiDataMapper
import com.example.music.ui.uidata.AlbumUiData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.schedulers.Schedulers

class AlbumViewModel(private val musicDomain: IMusicDomain, private val id: Long) : ViewModel() {

	private val compositeDisposable = CompositeDisposable()
	private val uiDataMapper = UiDataMapper()
	val albumUIDatasource: BehaviorProcessor<AlbumUiData> by lazy {
		BehaviorProcessor.create<AlbumUiData>()
	}

	init {
		compositeDisposable.add(musicDomain.findAlbum(id)
			.map {
				uiDataMapper.mapAlbumToUiData(it)
			}
			.subscribeOn(Schedulers.io())
			.subscribe({
				albumUIDatasource.onNext(it)
			}, {
				Log.e("TAG", it.message ?: "Error")
			}))
	}


	override fun onCleared() {
		compositeDisposable.dispose()
		super.onCleared()
	}
}