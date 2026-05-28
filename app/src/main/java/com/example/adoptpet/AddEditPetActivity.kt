package com.example.adoptpet

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.adoptpet.data.PetRepository
import com.example.adoptpet.databinding.ActivityAddEditPetBinding
import com.example.adoptpet.model.PetItem

class AddEditPetActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PET_ID = "extra_pet_id"
        private const val NO_ID = -1
    }

    private lateinit var binding: ActivityAddEditPetBinding
    private lateinit var repository: PetRepository
    private var editingId: Int = NO_ID
    private var selectedImageRes: Int = R.drawable.persiancat

    private val imageOptions = listOf(
        "Kucing Persia" to R.drawable.persiancat,
        "Golden Retriever" to R.drawable.goldenretriever,
        "Holland Lop" to R.drawable.hollandlop,
        "Domestic Short Hair" to R.drawable.domesticshorthair
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditPetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository = PetRepository(this)
        editingId = intent.getIntExtra(EXTRA_PET_ID, NO_ID)

        setupImageSpinner()

        if (editingId != NO_ID) {
            loadExisting(editingId)
        }

        binding.btnSave.setOnClickListener { onSave() }
    }

    private fun setupImageSpinner() {
        val labels = imageOptions.map { it.first }
        binding.spImage.adapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_dropdown_item, labels
        )
        binding.spImage.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                selectedImageRes = imageOptions[position].second
                binding.imgPreview.setImageResource(selectedImageRes)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        binding.imgPreview.setImageResource(selectedImageRes)
    }

    private fun loadExisting(id: Int) {
        val pet = repository.getPetById(id) ?: run {
            Toast.makeText(this, "Pet tidak ditemukan", Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        binding.tvFormTitle.setText(R.string.form_title_edit)
        binding.etName.setText(pet.name)
        binding.etShelter.setText(pet.shelter)
        binding.etBreed.setText(pet.breed)
        binding.etAge.setText(pet.age)
        if (pet.gender.equals("Betina", ignoreCase = true)) {
            binding.rbFemale.isChecked = true
        } else {
            binding.rbMale.isChecked = true
        }
        binding.etFee.setText(pet.adoptionFee.toString())
        binding.etRating.setText(pet.rating.toString())
        binding.etDescription.setText(pet.description)

        val imgIndex = imageOptions.indexOfFirst { it.second == pet.imageResId }
            .takeIf { it >= 0 } ?: 0
        binding.spImage.setSelection(imgIndex)
        selectedImageRes = pet.imageResId
        binding.imgPreview.setImageResource(selectedImageRes)
    }

    private fun onSave() {
        val name = binding.etName.text?.toString()?.trim().orEmpty()
        val shelter = binding.etShelter.text?.toString()?.trim().orEmpty()
        val breed = binding.etBreed.text?.toString()?.trim().orEmpty()
        val age = binding.etAge.text?.toString()?.trim().orEmpty()
        val feeStr = binding.etFee.text?.toString()?.trim().orEmpty()
        val ratingStr = binding.etRating.text?.toString()?.trim().orEmpty()
        val description = binding.etDescription.text?.toString()?.trim().orEmpty()

        if (name.isEmpty() || shelter.isEmpty() || breed.isEmpty() ||
            age.isEmpty() || feeStr.isEmpty() || ratingStr.isEmpty() || description.isEmpty()
        ) {
            Toast.makeText(this, R.string.form_error_required, Toast.LENGTH_SHORT).show()
            return
        }

        val fee = feeStr.toIntOrNull() ?: 0
        val rating = ratingStr.toDoubleOrNull() ?: -1.0
        if (rating < 0.0 || rating > 5.0) {
            Toast.makeText(this, R.string.form_error_rating_range, Toast.LENGTH_SHORT).show()
            return
        }

        val gender = if (binding.rbFemale.isChecked) "Betina" else "Jantan"

        val pet = PetItem(
            id = if (editingId == NO_ID) 0 else editingId,
            name = name,
            shelter = shelter,
            breed = breed,
            age = age,
            gender = gender,
            adoptionFee = fee,
            rating = rating,
            description = description,
            imageResId = selectedImageRes
        )

        if (editingId == NO_ID) repository.addPet(pet) else repository.updatePet(pet)

        Toast.makeText(this, R.string.form_saved, Toast.LENGTH_SHORT).show()
        finish()
    }
}
