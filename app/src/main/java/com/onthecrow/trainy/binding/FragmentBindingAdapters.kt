package com.onthecrow.trainy.binding

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.annotation.IdRes
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.onthecrow.trainy.common.Event
import com.onthecrow.trainy.presentation.common.DataBoundListAdapter
import com.onthecrow.trainy.presentation.crop.ImageCropViewModel
import com.yalantis.ucrop.view.UCropView


class FragmentBindingAdapters constructor(
    private val applicationContext: Context
) {

    interface AfterTextChangeListener {
        fun afterTextChanged()
    }

    interface EditorActionDoneListener {
        fun onEditorDoneAction()
    }

    interface PopupMenuListener {
        fun onMenuItemClick(@IdRes menuItemId: Int)
    }

    @BindingAdapter(
        value = ["imageUrl", "imageRequestListener"],
        requireAll = false
    )
    fun bindImage(
        imageView: ImageView,
        url: String?,
        listener: RequestListener<Drawable?>?
    ) {
        url?.let {
            Glide.with(applicationContext)
                .load(url)
                .apply(
                    RequestOptions().dontTransform() // this line
                )
                .listener(listener)
                .into(imageView)
        }
    }

    @BindingAdapter(
        value = ["roundedImageUrl", "imageRequestListener"],
        requireAll = false
    )
    fun bindRoundedImage(
        imageView: ImageView,
        url: String?,
        listener: RequestListener<Drawable?>?
    ) {
        url?.let {
            Glide.with(applicationContext)
                .load(url)
                .transform(CenterCrop(), CircleCrop())
                .listener(listener)
                .into(imageView)
        }
    }

    @BindingAdapter("visibleGone")
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

    @BindingAdapter("error")
    fun error(editText: EditText, error: String?) {
        editText.error = error
    }

    @BindingAdapter("error")
    fun error(checkBox: CheckBox, error: String?) {
        checkBox.error = error
    }

    @BindingAdapter("afterTextChangeListener")
    fun doAfterTextChanged(editText: EditText, listener: AfterTextChangeListener?) {
        editText.doAfterTextChanged { listener?.afterTextChanged() }
    }

    @BindingAdapter("editorActionDoneListener")
    fun onEditorAction(editText: EditText, listener: EditorActionDoneListener?) {
        editText.setOnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_DONE) {
                listener?.onEditorDoneAction()
            }
            return@setOnEditorActionListener false
        }
    }

    @BindingAdapter("adapter")
    fun bindAdapter(recyclerView: RecyclerView, adapter: DataBoundListAdapter<*, *>) {
        recyclerView.adapter = adapter
    }

    @BindingAdapter("adapter")
    fun bindAdapter(viewPager: ViewPager2, adapter: DataBoundListAdapter<*, *>) {
        viewPager.adapter = adapter
    }

    @Suppress("UNCHECKED_CAST")
    @BindingAdapter("data")
    fun <T> bindRecyclerViewData(recyclerView: RecyclerView, data: List<T>?) {
        (recyclerView.adapter as? DataBoundListAdapter<T, *>)?.submitList(data)
    }

    @Suppress("UNCHECKED_CAST")
    @BindingAdapter("data")
    fun <T> bindRecyclerViewData(viewPager: ViewPager2, data: List<T>?) {
        (viewPager.adapter as? DataBoundListAdapter<T, *>)?.submitList(data)
    }

    @SuppressLint("SetJavaScriptEnabled")
    @BindingAdapter("url")
    fun loadUrl(webView: WebView, url: String?) {
        url?.let {
            webView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    view.loadUrl(url)
                    return true
                }
            }
            webView.settings?.apply {
                javaScriptEnabled = true
            }
            webView.loadUrl(url)
        }
    }

    @BindingAdapter("imageCropper")
    fun cropCallback(cropImageView: UCropView, cropper: ImageCropViewModel.ImageCropper) {
        cropper.cropAction = {
            @Suppress("UNNECESSARY_SAFE_CALL")
            cropImageView?.cropImageView.cropAndSaveImage(
                Bitmap.CompressFormat.JPEG,
                100,
                cropper.imageCropCallback
            )
        }
    }

    @BindingAdapter("imageToCrop")
    fun imageToCrop(cropImageView: UCropView, uri: Uri?) {
        uri?.let {
            val aspectRatio = cropImageView.cropImageView.targetAspectRatio
            cropImageView.resetCropImageView()
            cropImageView.cropImageView.setImageUri(uri, uri)
            cropImageView.cropImageView.targetAspectRatio = aspectRatio
        }
    }

    @BindingAdapter("cropAspectRatio")
    fun cropAspectRatio(cropImageView: UCropView, ratio: Float) {
        cropImageView.cropImageView.targetAspectRatio = ratio
    }

    @BindingAdapter(
        value = ["showPopup", "popupMenu", "popupMenuListener"],
        requireAll = true
    )
    fun showPopup(
        view: ImageView,
        showPopup: Event<Any>?,
        popupMenu: Int?,
        listener: PopupMenuListener?
    ) {
        showPopup?.getContentIfNotHandled()?.let {
            if (popupMenu != null && listener != null) {
                PopupMenu(view.context, view).apply {
                    setOnMenuItemClickListener { item ->
                        true.also { listener.onMenuItemClick(item.itemId) }
                    }
                    inflate(popupMenu)
                    show()
                }
            }
        }
    }

    @BindingAdapter("onClickLiveData")
    fun imageToCrop(view: View, clickLiveData: MutableLiveData<Event<Any>>) {
        view.setOnClickListener { clickLiveData.postValue(Event(Any())) }
    }

    @BindingAdapter("fitStatusBar")
    fun fitStatusBar(view: View, isFit: Boolean) {
        if (isFit) {
            (view.layoutParams as ViewGroup.MarginLayoutParams).apply {
                topMargin = view.rootWindowInsets.systemWindowInsetTop
            }
        }
    }
}

