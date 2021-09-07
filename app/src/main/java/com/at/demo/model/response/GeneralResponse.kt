package com.at.demo.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
open class GeneralResponse : Parcelable {
    @SerializedName("stat")
    var status: String = ""

}