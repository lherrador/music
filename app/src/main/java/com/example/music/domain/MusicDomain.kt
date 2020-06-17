package com.example.music.domain

import com.example.music.model.local.Album
import com.example.music.repository.IMusicRepository
import io.reactivex.Single

class MusicDomain(private val musicRepository: IMusicRepository) : IMusicDomain {
	override fun findAlbum(): Single<List<Album>> {
		return musicRepository.findAlbum()
	}
	override fun findAlbum(id : Long): Single<Album> {
		return musicRepository.findAlbum(id)
	}
}

interface IMusicDomain {
	fun findAlbum(): Single<List<Album>>
	fun findAlbum(id : Long): Single<Album>
}