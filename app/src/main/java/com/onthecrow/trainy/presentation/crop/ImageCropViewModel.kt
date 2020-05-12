package com.onthecrow.trainy.presentation.crop

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.onthecrow.trainy.base.BaseViewModel
import com.onthecrow.trainy.common.Event
import com.yalantis.ucrop.callback.BitmapCropCallback
import timber.log.Timber

class ImageCropViewModel : BaseViewModel() {

    private val mutableCroppedImage = MutableLiveData<Uri>()
    private val mutableRawImage = MutableLiveData<Uri>()
    private val mutableCropAspectRatio = MutableLiveData(1.0f)
    private val mutableOnCancelClick = MutableLiveData<Event<Any>>()

    val croppedImage: LiveData<Uri> get() = mutableCroppedImage
    val rawImage: LiveData<Uri> get() = mutableRawImage
    val cropAspectRatio: LiveData<Float> get() = mutableCropAspectRatio
    val onCancelClick: LiveData<Event<Any>> get() = mutableOnCancelClick

    val imageCropper = ImageCropper()

    fun setRawImage(uri: Uri) {
        mutableRawImage.postValue(uri)
    }

    fun crop() {
        imageCropper.cropAction?.invoke()
    }

    fun cancel() {
        mutableOnCancelClick.postValue(Event(Any()))
    }

    inner class ImageCropper {

        val imageCropCallback = object : BitmapCropCallback {
            override fun onBitmapCropped(
                resultUri: Uri,
                offsetX: Int,
                offsetY: Int,
                imageWidth: Int,
                imageHeight: Int
            ) {
                mutableCroppedImage.postValue(resultUri)
                mutableOnCancelClick.postValue(Event(Any()))
            }

            override fun onCropFailure(t: Throwable) {
                Timber.e(t)
            }
        }

        var cropAction: (() -> Unit)? = null
    }
}