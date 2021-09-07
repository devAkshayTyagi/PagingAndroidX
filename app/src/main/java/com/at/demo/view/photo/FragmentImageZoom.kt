package com.at.demo.view.photo

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.at.demo.R
import com.at.demo.databinding.FragmentImageZoomBinding
import com.at.demo.model.bean.PhotoItem
import com.at.demo.view.base.BaseFragment
import com.at.demo.view.extensions.loadImage

class FragmentImageZoom : BaseFragment() {
    private lateinit var binding: FragmentImageZoomBinding
    private lateinit var itemVideo: PhotoItem


    override fun getLayoutId() = R.layout.fragment_image_zoom


    companion object {
        private const val ITEM_PHOTO = "itemPhoto"
        fun create(item: PhotoItem): FragmentImageZoom {
            return FragmentImageZoom().apply {
                arguments = Bundle().apply {
                    putParcelable(ITEM_PHOTO, item)
                }
            }
        }
    }


    override fun onViewsInitialized(binding: ViewDataBinding, view: View) {
        this.binding = binding as FragmentImageZoomBinding
        itemVideo = arguments?.getParcelable(ITEM_PHOTO)!!
        initUi()

    }


    /**
     * method to bind data to ui views
     * */
    private fun initUi() {
        binding.myZoomageView.loadImage(context as MainActivity, itemVideo.imagePath)


    }
}