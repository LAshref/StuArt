package com.example.stuart.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Address(
    val addressTitle: String,
    val fullName: String,
    val street: String,
    val phone: String,
    val city:String,
    val state: String
) : Parcelable {
    constructor():this("", "","", "", "", "")
}