package com.mvvmapp.domain.usecase

import com.mvvmapp.domain.model.Album
import com.mvvmapp.domain.repository.AlbumRepository
import com.mvvmapp.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetAlbumsUseCase @Inject constructor(private val repository: AlbumRepository): SingleUseCase<List<Album>>() {

    override fun buildUseCaseSingle(): Single<List<Album>> {
        return repository.getAlbums()
    }
}