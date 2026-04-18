package com.example.adoptpet.data

import com.example.adoptpet.R
import com.example.adoptpet.model.PetItem

object PetRepository {

    fun getPets(): List<PetItem> {
        return listOf(
            PetItem(
                id = 1,
                name = "Milo",
                shelter = "Jakarta",
                adoptionFee = 120000,
                rating = 4.8,
                description = "Kucing Persia medium yang tenang, sehat, dan mudah akrab. Cocok untuk rumah yang hangat dan penuh perhatian.",
                imageResId = R.drawable.persiancat
            ),
            PetItem(
                id = 2,
                name = "Bella",
                shelter = "Bandung",
                adoptionFee = 135000,
                rating = 4.7,
                description = "Anjing Golden Retriever yang aktif, ramah, dan suka bermain. Sudah terbiasa berinteraksi dengan manusia.",
                imageResId = R.drawable.goldenretriever
            ),
            PetItem(
                id = 3,
                name = "Snow",
                shelter = "Tangerang",
                adoptionFee = 110000,
                rating = 4.9,
                description = "Kelinci Holland Lop yang kalem dan lucu. Cocok untuk pemilik yang ingin hewan peliharaan lembut dan jinak.",
                imageResId = R.drawable.hollandlop
            ),
            PetItem(
                id = 4,
                name = "Luna",
                shelter = "Surabaya",
                adoptionFee = 140000,
                rating = 4.8,
                description = "Kucing Domestic Short Hair yang lembut, mandiri, dan mudah beradaptasi di lingkungan baru.",
                imageResId = R.drawable.domesticshorthair
            )
        )
    }

    fun getPetById(id: Int): PetItem? = getPets().find { it.id == id }
}
