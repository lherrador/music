package com.example.music.repository

import com.example.music.help.SharedMockData
import com.nhaarman.mockitokotlin2.verify
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.amshove.kluent.mock
import org.mockito.Mockito
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldNotBeEmpty
import org.amshove.kluent.shouldNotBeNull

class MusicRepositoryTest {

    private val musicRemoteRepository = mock<IMusicRemoteRepository>()
    private val musicPersistRespository = mock<IMusicPersistRepository>()

    private lateinit var musicRepository: IMusicRepository

    @Before
    fun setUp() {
        musicRepository = MusicRepository(musicRemoteRepository, musicPersistRespository)
    }

    @After
    fun tearDown() {
        Mockito.verifyNoMoreInteractions(musicRemoteRepository, musicPersistRespository)
    }

    @Test
    fun testFindAlbum_from_persist() {
        //GIVEN
        whenever(musicPersistRespository.findAlbum()).thenReturn(
            Single.just(listOf(SharedMockData.album, SharedMockData.album2))
        )

        //WHEN
        val testObserver = musicRepository.findAlbum()
            .test()
            .assertSubscribed()
            .assertNoErrors()

        //THEN
        val albumList = testObserver.values()[0]
        albumList.shouldNotBeNull()
        albumList.shouldNotBeEmpty()
        with(albumList.first()) {
            id.shouldBe(SharedMockData.album.id)
            url.shouldBe(SharedMockData.album.url)
            title.shouldBe(SharedMockData.album.title)
            thumbnailUrl.shouldBe(SharedMockData.album.thumbnailUrl)
            albumId.shouldBe(SharedMockData.album.albumId)
        }

        verify(musicPersistRespository).findAlbum()

        testObserver.dispose()
    }
}
