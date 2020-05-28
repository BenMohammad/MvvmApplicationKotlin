package com.mvvmapp.presentation.album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.mvvmapp.R
import com.mvvmapp.databinding.HolderAlbumBinding
import com.mvvmapp.domain.model.Album

internal class AlbumsAdapter(val mListener: OnAlbumsAdapterListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TAG = AlbumsAdapter::class.java.name
    private val albums: MutableList<Album> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holderAlbumBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.holder_album, parent, false
        )
        return AlbumViewHolder(holderAlbumBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as AlbumViewHolder).onBind(getItem(position))
    }

    private fun getItem(position: Int): Album {
        return albums[position]
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    fun addData(list: List<Album>) {
        this.albums.clear()
        this.albums.addAll(list)
        notifyDataSetChanged()
    }

    inner class AlbumViewHolder(private val dataBinding: ViewDataBinding) : RecyclerView.ViewHolder(dataBinding.root) {
        fun onBind(album: Album) {
            val holderAlbumBinding = dataBinding as HolderAlbumBinding
            val albumViewModel = AlbumViewModel(album)
            holderAlbumBinding.albumViewModel = albumViewModel

            itemView.setOnClickListener {
                mListener.showPhotos(album)
            }
        }
    }
}