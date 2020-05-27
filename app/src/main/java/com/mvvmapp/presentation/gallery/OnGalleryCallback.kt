package com.mvvmapp.presentation.gallery

import android.widget.ImageView
import com.mvvmapp.domain.model.Album

interface OnGalleryCallback {

    fun navigateToAlbumPage(album: Album)

    fun gotoDetailPageByPhotoId(imageView: ImageView, id: Long)
}