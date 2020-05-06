package com.example.music.dao

import androidx.room.*
import com.example.music.model.persist.PersistAlbum


@Dao
interface IAlbumDao {
    @Query("SELECT * from album_table")
    fun findAlbum(): List<PersistAlbum>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun create(persistAlbum: PersistAlbum)

    @Transaction
    fun create(persistAlbumList: List<PersistAlbum>) {
        persistAlbumList.forEach {
            create(it)
        }
    }

}