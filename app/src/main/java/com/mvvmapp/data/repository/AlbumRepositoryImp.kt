package com.mvvmapp.data.repository

import com.mvvmapp.data.source.local.AppDatabase
import com.mvvmapp.data.source.remote.RetrofitService
import com.mvvmapp.domain.model.Album
import com.mvvmapp.domain.repository.AlbumRepository
import io.reactivex.Single

class AlbumRepositoryImp(private val database: AppDatabase,
                         private val retrofitService: RetrofitService) : AlbumRepository {

    override fun getAlbums(): Single<List<Album>> {
        return retrofitService.getAlbums();
    }
}