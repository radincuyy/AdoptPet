package com.example.adoptpet

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adoptpet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = true
            isAppearanceLightNavigationBars = true
        }
        setContentView(binding.root)

        binding.incSearch.searchBar.setOnClickListener { }
        binding.incCategories.tvCategoryCat.setOnClickListener {
            openPetDetail(1)
        }
        binding.incCategories.tvCategoryDog.setOnClickListener {
            openPetDetail(2)
        }
        binding.incCardMilo.cardMilo.setOnClickListener { openPetDetail(1) }
        binding.incCardBella.cardBella.setOnClickListener { openPetDetail(2) }
        binding.incCardSnow.cardSnow.setOnClickListener { openPetDetail(3) }
        binding.incCardLuna.cardLuna.setOnClickListener { openPetDetail(4) }

        ViewCompat.setOnApplyWindowInsetsListener(binding.homeRoot) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun openPetDetail(id: Int) {
        val intent = Intent(this, PetDetailActivity::class.java)
        intent.putExtra(PetDetailActivity.EXTRA_PET_ID, id)
        startActivity(intent)
    }
}
