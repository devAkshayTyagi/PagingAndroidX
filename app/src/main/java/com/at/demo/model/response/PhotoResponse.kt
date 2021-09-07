package com.at.demo.model.response

import android.os.Parcelable
import com.at.demo.model.bean.PhotoData
import com.at.demo.model.bean.PhotoItem
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhotoResponse(
    @SerializedName("photos")
    val photoData: PhotoData? = null
) : GeneralResponse(), Parcelable



