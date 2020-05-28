package com.example.music.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.music.domain.IMusicDomain
import com.example.music.ui.mapper.UiDataMapper
import com.example.mucoroutinessic.ui.uidata.AlbumContentUiData
import kotlinx..Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlbumViewModel(private val musicDomain: IMusicDomain) : ViewModel() {

	private val uiDataMapper = UiDataMapper()
	val albumLiveData = MutableLiveData<AlbumContentUiData>()
	private var job: Job? = null

	init {
	    refresh()
	}

	fun refresh() {
		job?.let {
			if (it.isActive) {
				it.cancel()
			}
		}
		job = viewModelScope.launch {
			withContext(Dispatchers.IO) {
				val result = uiDataMapper.mapAlbumListToUiData(musicDomain.findAlbum())
				withContext(Dispatchers.Main) {
					albumLiveData.value = result
				}
			}
		}
	}
}