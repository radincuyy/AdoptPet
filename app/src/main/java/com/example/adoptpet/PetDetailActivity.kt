package com.example.adoptpet

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
    private lateinit var repository: PetRepository
    private var currentPetId: Int = -1
    private val rupiah = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("id-ID"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPetDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository = PetRepository(this)
        currentPetId = intent.getIntExtra(EXTRA_PET_ID, -1)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.setNavigationOnClickListener { finish() }

        renderPet()

        binding.btnAdopt.setOnClickListener {
            val name = binding.tvName.text
            Toast.makeText(this, "Permintaan adopsi dikirim untuk $name", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        renderPet()
    }

    private fun renderPet() {
        val item = repository.getPetById(currentPetId)
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
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_pet_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit -> {
                val intent = Intent(this, AddEditPetActivity::class.java)
                intent.putExtra(AddEditPetActivity.EXTRA_PET_ID, currentPetId)
                startActivity(intent)
                true
            }
            R.id.action_delete -> {
                confirmDelete()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun confirmDelete() {
        AlertDialog.Builder(this)
            .setTitle(R.string.detail_delete_title)
            .setMessage(R.string.detail_delete_message)
            .setPositiveButton(R.string.detail_delete_confirm) { _, _ ->
                repository.deletePet(currentPetId)
                Toast.makeText(this, R.string.form_deleted, Toast.LENGTH_SHORT).show()
                finish()
            }
            .setNegativeButton(R.string.detail_delete_cancel, null)
            .show()
    }
}
