package com.mvvmapp.presentation.album

import com.mvvmapp.domain.model.Album

interface OnAlbumsAdapterListener {

    fun showPhotos(album: Album)
}