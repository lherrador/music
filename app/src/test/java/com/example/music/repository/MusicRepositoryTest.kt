package com.example.music.repository

import com.example.music.help.SharedMockData
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.*
import org.amshove.kluent.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.mockito.Mockito

class MusicRepositoryTest {
    private val testCoroutineRule = TestCoroutineRule()
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
        testCoroutineRule.runBloquingTest {

            //GIVEN
            whenever(musicPersistRespository.findAlbum()).thenReturn(
                listOf(SharedMockData.album, SharedMockData.album2)
            )

            //WHEN
            val albumList = musicRepository.findAlbum()

            //THEN
            albumList.shouldNotBeNull()
            albumList.shouldNotBeEmpty()
            with(albumList.first()) {
                id.shouldEqual(SharedMockData.album.id)
                url.shouldEqual(SharedMockData.album.url)
                title.shouldEqual(SharedMockData.album.title)
                thumbnailUrl.shouldEqual(SharedMockData.album.thumbnailUrl)
                albumId.shouldEqual(SharedMockData.album.albumId)
            }

            verify(musicPersistRespository).findAlbum()
        }
    }
}

class TestCoroutineRule : TestRule {
    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher)

    override fun apply(base: Statement, description: Description)= object : Statement() {
        @Throws
        override fun evaluate() {
            Dispatchers.setMain(testCoroutineDispatcher)

            base.evaluate()

            Dispatchers.resetMain()
            testCoroutineScope.cleanupTestCoroutines()
        }
    }

    fun runBloquingTest(block: suspend TestCoroutineScope.() -> Unit) = testCoroutineScope.runBlockingTest { block() }
}
