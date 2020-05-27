package com.mvvmapp.domain.repository

import com.mvvmapp.domain.model.Album
import io.reactivex.Single

interface AlbumRepository {

    fun getAlbums(): Single<List<Album>>
}