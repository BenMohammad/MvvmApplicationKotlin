package com.mvvmapp.presentation.album

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.mvvmapp.R
import com.mvvmapp.databinding.FragmentAlbumsBinding
import com.mvvmapp.domain.model.Album
import com.mvvmapp.presentation.gallery.OnGalleryCallback
import dagger.android.support.DaggerFragment
import java.lang.ClassCastException
import javax.inject.Inject

class AlbumsFragment : DaggerFragment(), OnAlbumsAdapterListener {

    private lateinit var fragmentAlbumsBinding: FragmentAlbumsBinding
    private var adapter: AlbumsAdapter? = null
    private var mCallback: OnGalleryCallback? = null
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: AlbumsViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(AlbumsViewModel::class.java)

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is OnGalleryCallback) {
            mCallback = context
        } else throw ClassCastException(context.toString() + "must implement OnGalleryCallback")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = AlbumsAdapter(this)
        viewModel.loadAlbums()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentAlbumsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_albums, container, false)
        fragmentAlbumsBinding.albumsViewModel = viewModel
        fragmentAlbumsBinding.albumsRecyclerView.adapter = adapter

        viewModel.isLoad.observe(this, Observer {
            it?.let {
                visibility -> fragmentAlbumsBinding.albumsProgressBar.visibility = if(visibility) View.GONE else View.VISIBLE
            }
        })

        viewModel.albumsReceivedLiveData.observe(this, Observer {
            it?.let {
                initRecyclerView(it)
            }
        })

        return fragmentAlbumsBinding.root
    }

    private fun initRecyclerView(albums: List<Album>) {
        Log.i("AlbumsFragment", albums.toString())
        adapter?.addData(albums)
    }

    override fun showPhotos(album: Album) {
        mCallback?.navigateToAlbumPage(album)
    }

    override fun onDetach() {
        super.onDetach()
        adapter = null
        mCallback = null
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