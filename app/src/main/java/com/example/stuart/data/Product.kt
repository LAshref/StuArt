package com.example.stuart.data

data class Product(
    val id: String = "",
    val name: String = "",
    val category: String = "",
    val price: Float = 0.0f,
    val offerPercentage: Float = 0.0f,
    val description: String = "",
    val colors: List<Int> = emptyList(),
    val sizes: List<String> = emptyList(),
    val images: List<String> = emptyList()
) {
    // No-argument constructor
    constructor() : this("", "", "", 0.0f, 0.0f, "", emptyList(), emptyList(), emptyList())
}

