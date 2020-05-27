package com.mvvmapp.domain.usecase

import com.mvvmapp.domain.model.Photo
import com.mvvmapp.domain.repository.PhotoRepository
import com.mvvmapp.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(private val repository: PhotoRepository) : SingleUseCase<List<Photo>>() {

    private var albumId: Long? = null

    fun saveAlbum(id: Long) {
        albumId = id
    }

    override fun buildUseCaseSingle(): Single<List<Photo>> {
        return repository.getPhotos(albumId)
    }
}