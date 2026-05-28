package com.example.adoptpet.data

import android.content.Context
import com.example.adoptpet.R
import com.example.adoptpet.data.db.PetDao
import com.example.adoptpet.model.PetItem

class PetRepository(context: Context) {

    private val dao = PetDao(context)

    init {
        if (dao.count() == 0) dao.bulkInsert(seedData())
    }

    fun getPets(): List<PetItem> = dao.getAll()

    fun getPetById(id: Int): PetItem? = dao.getById(id)

    fun addPet(pet: PetItem): Long = dao.insert(pet)

    fun updatePet(pet: PetItem): Int = dao.update(pet)

    fun deletePet(id: Int): Int = dao.delete(id)

    private fun seedData(): List<PetItem> = listOf(
        PetItem(
            id = 0,
            name = "Milo",
            shelter = "Jakarta",
            breed = "Persia Medium",
            age = "2 Tahun",
            gender = "Jantan",
            adoptionFee = 120000,
            rating = 4.8,
            description = "Kucing Persia medium yang tenang, sehat, dan mudah akrab. Cocok untuk rumah yang hangat dan penuh perhatian.",
            imageResId = R.drawable.persiancat
        ),
        PetItem(
            id = 0,
            name = "Bella",
            shelter = "Bandung",
            breed = "Golden Retriever",
            age = "5 Bulan",
            gender = "Betina",
            adoptionFee = 135000,
            rating = 4.7,
            description = "Anjing Golden Retriever yang aktif, ramah, dan suka bermain. Sudah terbiasa berinteraksi dengan manusia.",
            imageResId = R.drawable.goldenretriever
        ),
        PetItem(
            id = 0,
            name = "Snow",
            shelter = "Tangerang",
            breed = "Holland Lop",
            age = "1 Tahun",
            gender = "Jantan",
            adoptionFee = 110000,
            rating = 4.9,
            description = "Kelinci Holland Lop yang kalem dan lucu. Cocok untuk pemilik yang ingin hewan peliharaan lembut dan jinak.",
            imageResId = R.drawable.hollandlop
        ),
        PetItem(
            id = 0,
            name = "Luna",
            shelter = "Surabaya",
            breed = "Domestic Short Hair",
            age = "3 Tahun",
            gender = "Betina",
            adoptionFee = 140000,
            rating = 4.8,
            description = "Kucing Domestic Short Hair yang lembut, mandiri, dan mudah beradaptasi di lingkungan baru.",
            imageResId = R.drawable.domesticshorthair
        )
    )
}
