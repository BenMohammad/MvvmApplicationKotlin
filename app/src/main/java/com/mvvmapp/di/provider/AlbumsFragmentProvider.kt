package com.mvvmapp.di.provider

import com.mvvmapp.presentation.album.AlbumsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AlbumsFragmentProvider {

    @ContributesAndroidInjector
    abstract fun provideAlbumsFragment(): AlbumsFragment
}