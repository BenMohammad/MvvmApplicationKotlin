package com.mvvmapp.presentation.photo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvvmapp.domain.model.Photo

class PhotoViewModel(val photo: Photo) : ViewModel() {

    private val TAG = PhotoViewModel::class.java
    val photoData = MutableLiveData<Photo>()

    init {
        photoData.value = photo
    }
}