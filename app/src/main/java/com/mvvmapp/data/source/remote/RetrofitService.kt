package com.mvvmapp.data.source.remote

import com.mvvmapp.domain.model.Album
import com.mvvmapp.domain.model.Photo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("albums/")
    fun getAlbums(): Single<List<Album>>

    @GET("albums/{id}/photos")
    fun getPhotos(@Path("id") id: Long): Single<List<Photo>>

    @GET("photos/{id}")
    fun gtePhotoDetails(@Path("id") id: Long): Single<Photo>
}