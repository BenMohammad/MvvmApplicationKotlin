package com.mvvmapp.di.module

import com.mvvmapp.di.provider.AlbumsFragmentProvider
import com.mvvmapp.di.provider.PhotosFragmentProvider
import com.mvvmapp.presentation.detailphoto.PhotoDetailActivity
import com.mvvmapp.presentation.gallery.GalleryActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [AndroidSupportInjectionModule::class])
interface ActivityModule {

    @ContributesAndroidInjector(
        modules = [
            AlbumsFragmentProvider::class,
            PhotosFragmentProvider::class
        ])

    fun galleryActivityInjector(): GalleryActivity

    @ContributesAndroidInjector
    fun photoDetailActivityInjector(): PhotoDetailActivity
}