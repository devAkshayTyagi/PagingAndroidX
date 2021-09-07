package com.at.demo.view.extensions

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.at.demo.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

/** Extension to load image in imageView using Glide*/
fun ImageView.loadImage(
    context: Context,
    imageUrl: String? = "",
    placeholderResId: Int = R.color.design_default_color_primary,
    errorResId: Int = 0
) {
    val requestOptions =
        RequestOptions().apply {
            placeholder(placeholderResId)

            error(errorResId)
            dontAnimate()
            diskCacheStrategy(DiskCacheStrategy.ALL)
            override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
            dontTransform()

        }
    Glide.with(context).setDefaultRequestOptions(requestOptions)
        .load(imageUrl)
        .into(this)

}

/** Extension function to show toast */
fun Context.showToast(message: String?, duration: Int = Toast.LENGTH_SHORT) {
    message?.let {
        Toast.makeText(this, message, duration).show()
    }
}

/** Extension function to show View */
fun View.visible() {
    visibility = View.VISIBLE
}

/** Extension function to hide View */
fun View.gone() {
    visibility = View.GONE
}