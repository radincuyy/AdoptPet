package com.example.adoptpet

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.adoptpet.data.PetRepository
import com.example.adoptpet.databinding.ActivityPetDetailBinding
import java.text.NumberFormat
import java.util.Locale

class PetDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PET_ID = "extra_pet_id"
    }

    private lateinit var binding: ActivityPetDetailBinding
    private val rupiah = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("id-ID"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPetDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra(EXTRA_PET_ID, -1)
        val item = PetRepository.getPetById(id)

        if (item == null) {
            Toast.makeText(this, "Data pet tidak ditemukan", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        binding.imgPet.setImageResource(item.imageResId)
        binding.tvName.text = item.name
        binding.tvMeta.text = "${item.shelter} • ⭐ ${item.rating}"
        binding.tvFee.text = rupiah.format(item.adoptionFee)
        binding.tvDesc.text = item.description

        binding.btnAdopt.setOnClickListener {
            Toast.makeText(this, "Permintaan adopsi dikirim untuk ${item.name}", Toast.LENGTH_SHORT).show()
        }
    }
}
