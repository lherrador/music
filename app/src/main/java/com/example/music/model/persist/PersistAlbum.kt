package com.example.music.model.persist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "album_table")
data class PersistAlbum(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "coverSmall") val coverSmall: String,
    @ColumnInfo(name = "coverBig") val coverBig: String
)