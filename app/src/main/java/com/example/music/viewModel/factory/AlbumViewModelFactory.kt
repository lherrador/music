package com.example.music.viewModel.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.music.domain.IMusicDomain
import com.example.music.viewModel.AlbumViewModel


class AlbumViewModelFactory(private val hostApplication: Application, private val musicDomain: IMusicDomain) :
		ViewModelProvider.NewInstanceFactory() {


	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel?> create(modelClass: Class<T>): T {

		if (modelClass.isAssignableFrom(AlbumViewModel::class.java)) {
			return AlbumViewModel(hostApplication, musicDomain) as T
		}
		throw IllegalArgumentException("This factory handle only ManualEntryViewModel classes")
	}
}