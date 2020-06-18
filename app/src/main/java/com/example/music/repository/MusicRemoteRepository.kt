package com.example.music.repository

import com.example.music.api.IMusicApiClient
import com.example.music.model.local.Album
import com.example.music.model.local.Track
import com.example.music.repository.mapper.RepositoryMapper
import io.reactivex.Single

class MusicRemoteRepository(private val musicApiClient: IMusicApiClient) :
		IMusicRemoteRepository {

	private val repositoryMapper = RepositoryMapper()

	override fun findAlbum(): Single<List<Album>> {
		return musicApiClient.findAlbum()
				.map {
					repositoryMapper.mapRemoteAlbumToLocal(it)
				}
	}

	override fun findTrack(albumId: Long): Single<List<Track>> {
		return musicApiClient.findTrack(albumId)
			.map {
				repositoryMapper.mapRemoteTrackToLocal(it)
			}
	}
}

interface IMusicRemoteRepository {
	fun findAlbum(): Single<List<Album>>
	fun findTrack(albumId: Long): Single<List<Track>>
}