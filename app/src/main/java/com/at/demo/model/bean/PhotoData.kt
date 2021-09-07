package com.at.demo.model.bean

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhotoData(
    @SerializedName("page")
    var currentPage: Long? = null,
    @SerializedName("pages")
    var totalPages: Long? = null,
    @SerializedName("perpage")
    var perPageCount: Int? = null,
    @SerializedName("total")
    var totalItems: Long? = null,
    @SerializedName("photo")
    var photos: ArrayList<PhotoItem>? = null

): Parcelable