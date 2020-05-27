package com.mvvmapp.presentation.photo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.mvvmapp.R
import com.mvvmapp.databinding.HolderPhotoBinding
import com.mvvmapp.domain.model.Photo
import com.mvvmapp.presentation.loadImage

internal class PhotosAdapter(val mListener: OnPhotosAdapterListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TAG = PhotosAdapter::class.java.name
    private val photos: MutableList<Photo> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holderPhotoBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.holder_photo, parent, false
        )

        return PhotoViewHolder(holderPhotoBinding)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PhotoViewHolder).onBind(getItem(position))
    }

    private fun getItem(position: Int) : Photo {
        return photos[position]
    }

    fun addData(list: List<Photo>) {
        this.photos.clear()
        this.photos.addAll(list)
        notifyDataSetChanged()

    }

    inner class PhotoViewHolder(private val dataBinding: ViewDataBinding): RecyclerView.ViewHolder(dataBinding.root) {

        fun onBind(photo: Photo) {
            val holderPhotoBinding = dataBinding as HolderPhotoBinding
            holderPhotoBinding.photoViewModel = PhotoViewModel(photo)
            holderPhotoBinding.photoImageView.loadImage(photo.url, holderPhotoBinding.photoProgressBar)

            itemView.setOnClickListener{
                mListener.gotoDetailPage(holderPhotoBinding.photoImageView, photo.id)
            }
        }
    }
}