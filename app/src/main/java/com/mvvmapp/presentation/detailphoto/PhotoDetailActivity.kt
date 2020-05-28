package com.mvvmapp.presentation.detailphoto

import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.mvvmapp.R
import com.mvvmapp.databinding.ActivityPhotoDetailBinding
import com.mvvmapp.presentation.ViewModelFactory
import com.mvvmapp.presentation.loadImageFull
import dagger.android.support.DaggerAppCompatActivity
import org.jetbrains.anko.act
import javax.inject.Inject

class PhotoDetailActivity: DaggerAppCompatActivity(), OnPhotoDetailCallback {

    private val TAG = PhotoDetailActivity::class.java.name
    private lateinit var activityPhotoDetailBinding: ActivityPhotoDetailBinding
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: PhotoDetailViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(PhotoDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityPhotoDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_photo_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activityPhotoDetailBinding.photoDetailViewModel = viewModel

        val photoId = intent?.extras?.getLong(KEY_PHOTO_ID) ?: return
        viewModel.getDetail(photoId)
        viewModel.checkFavoriteStatus(photoId)

        viewModel.photoData.observe(this, Observer {
            activityPhotoDetailBinding.detailTitleTextView.text = it?.title
            activityPhotoDetailBinding.detailToolbarImageView.loadImageFull(it?.url)
        })
        viewModel.isFavorite.observe(this, Observer {
            it?.let {
                activityPhotoDetailBinding.detailFab.setImageResource(if(it) R.drawable.ic_star_full_vector else R.drawable.ic_star_empty_white_vector)
            }
        })

        activityPhotoDetailBinding.detailAppBarLayout.setOnClickListener{
            viewModel.updateFavoriteStatus()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                supportFinishAfterTransition()
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }



    companion object {
        private val KEY_PHOTO_ID = "KEY_PHOTO_ID"

    }}