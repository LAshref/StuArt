package com.example.stuart.data

sealed class Category (val Category : String ){

    object Handmade : Category("Handmade")
    object MusicTalents : Category("MusicTalents")
    object Needlework: Category("Needlework")
    object Painting : Category("Painting")



}