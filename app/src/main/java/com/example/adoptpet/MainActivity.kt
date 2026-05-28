package com.example.adoptpet

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adoptpet.data.PetRepository
import com.example.adoptpet.databinding.ActivityMainBinding
import com.example.adoptpet.model.PetItem
import com.example.adoptpet.ui.PetAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PetAdapter
    private lateinit var repository: PetRepository
    private var allPets: List<PetItem> = emptyList()
    private var activeCategory: String = "Semua"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = true
            isAppearanceLightNavigationBars = true
        }
        setContentView(binding.root)

        // Load data from SQLite-backed repository
        repository = PetRepository(this)
        allPets = repository.getPets()

        // Set up RecyclerView
        adapter = PetAdapter(
            allPets,
            onItemClick = { pet -> openPetDetail(pet.id) },
            onItemLongClick = { pet -> openEditForm(pet.id) }
        )

        binding.fabAdd.setOnClickListener {
            startActivity(Intent(this, AddEditPetActivity::class.java))
        }
        
        binding.rvPets.layoutManager = LinearLayoutManager(this)
        binding.rvPets.adapter = adapter

        // Setup Popup Menu for View Mode Switch
        binding.incHeader.imgMenu.setOnClickListener { view ->
            val popup = PopupMenu(this, view)
            popup.menuInflater.inflate(R.menu.menu_view_mode, popup.menu)
            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_mode_list -> {
                        adapter.viewMode = PetAdapter.VIEW_TYPE_LIST
                        binding.rvPets.layoutManager = LinearLayoutManager(this)
                        true
                    }
                    R.id.action_mode_grid -> {
                        adapter.viewMode = PetAdapter.VIEW_TYPE_GRID
                        binding.rvPets.layoutManager = GridLayoutManager(this, 2)
                        true
                    }
                    R.id.action_mode_card -> {
                        adapter.viewMode = PetAdapter.VIEW_TYPE_CARD
                        binding.rvPets.layoutManager = LinearLayoutManager(this)
                        true
                    }
                    else -> false
                }
            }
            popup.show()
        }

        // Setup Category Filtering
        binding.incCategories.tvCategoryAll.setOnClickListener {
            filterPets("Semua")
            updateCategoryUi(binding.incCategories.tvCategoryAll)
        }
        binding.incCategories.tvCategoryCat.setOnClickListener {
            filterPets("Kucing")
            updateCategoryUi(binding.incCategories.tvCategoryCat)
        }
        binding.incCategories.tvCategoryDog.setOnClickListener {
            filterPets("Anjing")
            updateCategoryUi(binding.incCategories.tvCategoryDog)
        }

        binding.incSearch.searchBar.setOnClickListener { }

        ViewCompat.setOnApplyWindowInsetsListener(binding.homeRoot) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun filterPets(category: String) {
        activeCategory = category
        val filtered = when (category) {
            "Kucing" -> allPets.filter { it.description.contains("Kucing", ignoreCase = true) }
            "Anjing" -> allPets.filter { it.description.contains("Anjing", ignoreCase = true) }
            else -> allPets
        }
        adapter.updateData(filtered)
    }

    override fun onResume() {
        super.onResume()
        allPets = repository.getPets()
        filterPets(activeCategory)
    }

    private fun openEditForm(id: Int) {
        val intent = Intent(this, AddEditPetActivity::class.java)
        intent.putExtra(AddEditPetActivity.EXTRA_PET_ID, id)
        startActivity(intent)
    }

    private fun updateCategoryUi(selectedTextView: TextView) {
        val categories = listOf(
            binding.incCategories.tvCategoryAll,
            binding.incCategories.tvCategoryCat,
            binding.incCategories.tvCategoryDog
        )
        
        categories.forEach { tv ->
            if (tv == selectedTextView) {
                tv.setBackgroundResource(R.drawable.bg_chip_active)
                tv.setTextColor(resources.getColor(android.R.color.white, theme))
            } else {
                tv.setBackgroundResource(R.drawable.bg_chip_inactive)
                tv.setTextColor(resources.getColor(R.color.home_primary, theme))
            }
        }
    }

    private fun openPetDetail(id: Int) {
        val intent = Intent(this, PetDetailActivity::class.java)
        intent.putExtra(PetDetailActivity.EXTRA_PET_ID, id)
        startActivity(intent)
    }
}
