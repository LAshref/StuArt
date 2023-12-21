package com.example.stuart.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val id: String ,
    val name: String ,
    val category: String ,
    val price: Float ,
    val offerPercentage: Float? = null,
    val description: String? = null,
    val colors: List<Int>? = emptyList(),
    val sizes: List<String>? = emptyList(),
    val images: List<String>
): Parcelable {
    // No-argument constructor
    constructor() : this("0", "", "", 0f, images = emptyList())
}