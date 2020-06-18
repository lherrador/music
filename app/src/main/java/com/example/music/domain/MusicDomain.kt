package com.example.music.domain

import com.example.music.model.local.Album
import com.example.music.model.local.AlbumWithTracks
import com.example.music.model.local.Track
import com.example.music.repository.IMusicRepository
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class MusicDomain(private val musicRepository: IMusicRepository) : IMusicDomain {
    override fun findAlbum(): Single<List<Album>> {
        return musicRepository.findAlbum()
    }

    override fun findAlbum(albumId: Long): Single<AlbumWithTracks> {
        return Single.zip(
            musicRepository.findAlbum(albumId),
            musicRepository.findTrack(albumId),
            BiFunction<Album, List<Track>, AlbumWithTracks> { album, trackList ->
                AlbumWithTracks(album, trackList)
            })
    }
}

interface IMusicDomain {
    fun findAlbum(): Single<List<Album>>
    fun findAlbum(albumId: Long): Single<AlbumWithTracks>
}