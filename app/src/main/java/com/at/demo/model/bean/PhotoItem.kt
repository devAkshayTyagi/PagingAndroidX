package com.at.demo.model.bean

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class PhotoItem(
    @SerializedName("id")
    val id: String?,
    @SerializedName("owner")
    val owner: String?,
    @SerializedName("secret")
    val secret: String?,
    @SerializedName("server")
    val server: String?,
    @SerializedName("farm")
    val farm: Long?,
    @SerializedName("title")
    val title: String?

) : Parcelable {


    val imagePath: String
        get() {
            return "https://live.staticflickr.com/${server}/${id}_${secret}_w.jpg"
        }

    
}


