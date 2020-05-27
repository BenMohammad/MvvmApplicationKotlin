package com.mvvmapp.data.repository

import com.mvvmapp.data.source.local.AppDatabase
import com.mvvmapp.data.source.remote.RetrofitService
import com.mvvmapp.domain.model.Photo
import com.mvvmapp.domain.repository.PhotoRepository
import io.reactivex.Single

class PhotoRepositoryImp(private val database: AppDatabase,
                        private val retrofitService: RetrofitService) : PhotoRepository {

    override fun getPhotos(albumId: Long?): Single<List<Photo>> {
        return retrofitService.getPhotos(albumId!!)
    }

    override fun getPhotoDetails(photoId: Long?): Single<Photo> {
        return retrofitService.gtePhotoDetails(photoId!!)
    }

    override fun deletePhoto(photo: Photo) {
        database.photoDao.delete(photo)
    }

    override fun addPhoto(photo: Photo) {
        database.photoDao.insert(photo)
    }

    override fun isFavorite(photoId: Long): Boolean {
        val loadOneById = database.photoDao.loadOneByPhotoId(photoId)
        return loadOneById != null
    }
}