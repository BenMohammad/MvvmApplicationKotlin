package com.mvvmapp.presentation.album

import android.os.Bundle
import com.mvvmapp.databinding.FragmentAlbumsBinding
import com.mvvmapp.domain.model.Album
import com.mvvmapp.presentation.gallery.OnGalleryCallback
import dagger.android.support.DaggerFragment

class AlbumsFragment : DaggerFragment(), OnAlbumsAdapterListener {

    private lateinit var fragmentAlbumsBinding: FragmentAlbumsBinding
    private var adapter: AlbumsAdapter? = null
    private var mCallback: OnGalleryCallback? = null



    override fun showPhotos(album: Album) {

    }



    companion object {
        val FRAGMENT_NAME = AlbumsFragment::class.java.name

        @JvmStatic
        fun newInstance() =
            AlbumsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}