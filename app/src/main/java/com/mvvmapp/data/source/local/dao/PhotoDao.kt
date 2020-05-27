package com.mvvmapp.data.source.local.dao

import androidx.room.*
import com.mvvmapp.domain.model.Photo

@Dao
interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(photo: Photo): Long

    @Query("SELECT * FROM Photo")
    fun loadAll(): MutableList<Photo>

    @Delete
    fun delete(photo: Photo)

    @Query("DELETE FROM Photo")
    fun deleteAll()

    @Query("SELECT * FROM Photo Where id = :photoId")
    fun loadOneByPhotoId(photoId: Long): Photo?

    @Query("SELECT * FROM Photo Where title = :photoTitle")
    fun loadOneByPhotoTitleId(photoTitle: String): Photo

    @Update
    fun update(photo: Photo)
}