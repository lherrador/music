package com.example.music.viewModel.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.music.domain.IMusicDomain
import com.example.music.viewModel.AlbumViewModel
import org.koin.core.KoinComponent
import org.koin.core.inject

class AlbumViewModelFactory(private val hostApplication: Application) :
		ViewModelProvider.NewInstanceFactory(), KoinComponent {

	private val musicDomain: IMusicDomain by inject()

	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel?> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(AlbumViewModel::class.java)) {
			return AlbumViewModel(hostApplication, musicDomain) as T
		}
		throw IllegalArgumentException("This factory handle only ManualEntryViewModel classes")
	}
}