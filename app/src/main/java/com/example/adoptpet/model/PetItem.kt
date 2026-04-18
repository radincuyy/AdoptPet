package com.example.adoptpet.model

data class PetItem(
    val id: Int,
    val name: String,
    val shelter: String,
    val adoptionFee: Int,
    val rating: Double,
    val description: String,
    val imageResId: Int
)
