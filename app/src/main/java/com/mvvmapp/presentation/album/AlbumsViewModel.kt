package com.mvvmapp.presentation.album

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvvmapp.domain.model.Album
import com.mvvmapp.domain.usecase.GetAlbumsUseCase
import javax.inject.Inject

class AlbumsViewModel @Inject constructor(private val getAlbumsUseCase: GetAlbumsUseCase) : ViewModel() {
    private val TAG = AlbumsViewModel::class.java.simpleName
    val albumsReceivedLiveData = MutableLiveData<List<Album>>()
    val isLoad = MutableLiveData<Boolean>()
    val albumData = MutableLiveData<Album>()

    init {
        isLoad.value = false
    }

    val album:  Album? get() = albumData.value

    fun set(album: Album) = run {albumData.value = album}

    fun loadAlbums() {
        getAlbumsUseCase.execute(
            onSuccess = {
                isLoad.value = true
                albumsReceivedLiveData.value = it
            },
            onError = {
                it.printStackTrace()
            }
        )
    }
}