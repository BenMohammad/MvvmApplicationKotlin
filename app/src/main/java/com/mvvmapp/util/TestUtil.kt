package com.mvvmapp.util

import com.mvvmapp.domain.model.Photo

object TestUtil {

    fun createPhotoId(id: Long) = Photo (
        id = id,
        title = "",
        url = "",
        thumbnailUrl = ""
    )

    fun makePhotoList(size: Int): MutableList<Photo> {
        val list = ArrayList<Photo>(size)
        list.forEach{
            it.title = "Photo ${list.indexOf(it)}"
            it.id = (list.indexOf(it) + 1).toLong()
            list.add(it)
        }
        return list

    }

}