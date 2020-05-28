package com.mvvmapp.di.builder

import androidx.lifecycle.ViewModel
import com.mvvmapp.di.ViewModelKey
import com.mvvmapp.presentation.album.AlbumsViewModel
import com.mvvmapp.presentation.detailphoto.PhotoDetailViewModel
import com.mvvmapp.presentation.photo.PhotosViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(AlbumsViewModel::class)
    abstract fun bindAlbumsViewModel(albumsViewModel: AlbumsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PhotosViewModel::class)
    abstract fun bindPhotosViewModel(photosViewModel: PhotosViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PhotoDetailViewModel::class)
    abstract fun bindPhotoDetailViewModel(photoDetailViewModel: PhotoDetailViewModel): ViewModel
}