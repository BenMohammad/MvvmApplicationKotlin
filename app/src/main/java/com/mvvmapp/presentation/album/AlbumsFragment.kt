package com.mvvmapp.presentation.album

import com.mvvmapp.databinding.FragmentAlbumsBinding
import com.mvvmapp.domain.model.Album
import dagger.android.support.DaggerFragment

class AlbumsFragment : DaggerFragment(), OnAlbumsAdapterListener {

    private lateinit var fragmentAlbumsBinding: FragmentAlbumsBinding
    private var adapter: AlbumsAdapter? = null


    override fun showPhotos(album: Album) {

    }
}