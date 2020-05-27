package com.mvvmapp.presentation.photo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvvmapp.domain.model.Photo
import com.mvvmapp.domain.usecase.GetPhotosUseCase
import javax.inject.Inject

class PhotosViewModel @Inject constructor(private val getPhotosUseCase: GetPhotosUseCase) : ViewModel() {

    private val TAG = PhotosViewModel::class.java.simpleName
    val photoListReceivedLiveData = MutableLiveData<List<Photo>>()
    val isLoad = MutableLiveData<Boolean>()

    init {
        isLoad.value = false
    }

    fun loadPhotos(id: Long?) {
        if(id == null) return
        getPhotosUseCase.saveAlbum(id)
        getPhotosUseCase.execute(
            onSuccess = {
                isLoad.value = true
                photoListReceivedLiveData.value = it
            },
            onError = {
                it.printStackTrace()
            }
        )
    }
}