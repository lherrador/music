package com.example.music.repository

import com.example.music.api.IMusicApiClient
import com.example.music.model.local.Album
import com.example.music.repository.mapper.RepositoryMapper

class MusicRemoteRepository(private val musicApiClient: IMusicApiClient) :
		IMusicRemoteRepository {

	private val repositoryMapper = RepositoryMapper()

	override suspend fun findAlbum(): List<Album> {
		val albumList = musicApiClient.findAlbum()
		return repositoryMapper.mapRemoteAlbumToLocal(albumList)
	}
}

interface IMusicRemoteRepository {
	suspend fun findAlbum(): List<Album>
}