package com.example.music.domain

import com.example.music.model.local.Album
import com.example.music.repository.IMusicRepository

class MusicDomain(private val musicRepository: IMusicRepository) : IMusicDomain {
	override suspend fun findAlbum(): List<Album> {
		return musicRepository.findAlbum()
	}
}

interface IMusicDomain {
	suspend fun findAlbum(): List<Album>
}