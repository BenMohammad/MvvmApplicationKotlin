package com.mvvmapp.presentation.photo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.mvvmapp.R
import com.mvvmapp.databinding.FragmentPhotosBinding
import com.mvvmapp.presentation.gallery.OnGalleryCallback
import dagger.android.support.DaggerFragment
import java.lang.ClassCastException
import javax.inject.Inject

class PhotosFragment : DaggerFragment(), OnPhotosAdapterListener {

    private lateinit var fragmentPhotosBinding : FragmentPhotosBinding
    private var adapter: PhotosAdapter? = null
    private var mCallback: OnGalleryCallback? = null
    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory
    private val viewModel: PhotosViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(PhotosViewModel::class.java)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is OnGalleryCallback) {
            mCallback = context
        } else throw ClassCastException(context.toString() + "must implement OnGalleryCallback!")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = PhotosAdapter(this)
        val albumId = arguments?.let {
            it.getLong(KEY_ALBUM_ID)
        }
        viewModel.loadPhotos(albumId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentPhotosBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_photos, container, false)
        fragmentPhotosBinding.photosViewModel = viewModel
        fragmentPhotosBinding.photosRecyclerView.adapter = adapter

        viewModel.isLoad.observe(this, Observer {
            it?.let {visibility ->
                fragmentPhotosBinding.photosProgressBar.visibility = if(visibility) View.GONE else View.VISIBLE
            }
        })

        viewModel.photoListReceivedLiveData.observe(this, Observer {
            it?.let {
                adapter?.addData(it)
            }
        })

        return fragmentPhotosBinding.root
    }

    override fun gotoDetailPage(imageView: ImageView, id: Long) {
        mCallback?.gotoDetailPageByPhotoId(imageView, id)
    }

    override fun onDetach() {
        super.onDetach()
        mCallback = null
        adapter = null
    }


    companion object {
        val KEY_ALBUM_ID = "KEY_ALBUM_ID"
        val FRAGMENT_NAME = PhotosFragment::class.java.name

        @JvmStatic
        fun newInstance(id: Long) =
            PhotosFragment().apply {
                arguments = Bundle().apply {
                    putLong(KEY_ALBUM_ID, id)
                }
            }
    }
}