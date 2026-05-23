package com.example.adoptpet.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.adoptpet.databinding.ItemPetCardBinding
import com.example.adoptpet.databinding.ItemPetGridBinding
import com.example.adoptpet.databinding.ItemPetListBinding
import com.example.adoptpet.model.PetItem
import java.text.NumberFormat
import java.util.Locale

class PetAdapter(
    private var items: List<PetItem>,
    private val onItemClick: (PetItem) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_LIST = 1
        const val VIEW_TYPE_GRID = 2
        const val VIEW_TYPE_CARD = 3
    }

    var viewMode: Int = VIEW_TYPE_CARD
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private val rupiah = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("id-ID"))

    fun updateData(newItems: List<PetItem>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return viewMode
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_LIST -> {
                val binding = ItemPetListBinding.inflate(inflater, parent, false)
                ListViewHolder(binding)
            }
            VIEW_TYPE_GRID -> {
                val binding = ItemPetGridBinding.inflate(inflater, parent, false)
                GridViewHolder(binding)
            }
            VIEW_TYPE_CARD -> {
                val binding = ItemPetCardBinding.inflate(inflater, parent, false)
                CardViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when (holder) {
            is ListViewHolder -> holder.bind(item)
            is GridViewHolder -> holder.bind(item)
            is CardViewHolder -> holder.bind(item)
        }
    }

    override fun getItemCount(): Int = items.size

    inner class ListViewHolder(private val binding: ItemPetListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PetItem) {
            binding.imgPet.setImageResource(item.imageResId)
            binding.tvName.text = item.name
            binding.tvBreed.text = item.breed.uppercase(Locale.getDefault())
            binding.tvLocation.text = item.shelter
            binding.tvRating.text = "⭐ ${item.rating}"
            binding.tvFee.text = rupiah.format(item.adoptionFee)
            binding.root.setOnClickListener { onItemClick(item) }
        }
    }

    inner class GridViewHolder(private val binding: ItemPetGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PetItem) {
            binding.imgPet.setImageResource(item.imageResId)
            binding.tvName.text = item.name
            binding.tvBreed.text = item.breed.uppercase(Locale.getDefault())
            binding.tvLocation.text = item.shelter
            binding.tvRating.text = "⭐ ${item.rating}"
            binding.tvFee.text = rupiah.format(item.adoptionFee)
            binding.root.setOnClickListener { onItemClick(item) }
        }
    }

    inner class CardViewHolder(private val binding: ItemPetCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PetItem) {
            binding.imgPet.setImageResource(item.imageResId)
            binding.tvName.text = item.name
            binding.tvLocation.text = item.shelter
            binding.tvBreed.text = item.breed.uppercase(Locale.getDefault())
            binding.tvRating.text = "⭐ ${item.rating}"
            binding.tvAge.text = item.age.uppercase(Locale.getDefault())
            binding.tvGender.text = item.gender.uppercase(Locale.getDefault())
            binding.tvFee.text = rupiah.format(item.adoptionFee)
            
            binding.btnFavorite.setOnClickListener {
                Toast.makeText(
                    itemView.context,
                    "${item.name} ditambahkan ke Favorit",
                    Toast.LENGTH_SHORT
                ).show()
            }
            binding.root.setOnClickListener { onItemClick(item) }
        }
    }
}
