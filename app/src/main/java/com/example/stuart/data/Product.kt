package com.example.stuart.data

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
) {
    // No-argument constructor
    constructor() : this("0", "", "", 0f, images = emptyList())
}