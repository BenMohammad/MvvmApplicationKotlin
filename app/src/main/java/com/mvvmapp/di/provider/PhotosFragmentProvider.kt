package com.mvvmapp.di.provider

import com.mvvmapp.presentation.photo.PhotosFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PhotosFragmentProvider {

    @ContributesAndroidInjector
    abstract fun providePhotosFragment(): PhotosFragment
}