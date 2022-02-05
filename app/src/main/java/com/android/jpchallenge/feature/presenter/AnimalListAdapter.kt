package com.android.jpchallenge.feature.presenter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.jpchallenge.MainApplication
import com.android.jpchallenge.databinding.AnimalItemAdapterBinding
import com.android.jpchallenge.feature.domain.model.Animal
import com.bumptech.glide.Glide

class AnimalListAdapter(var list : List<Animal.AnimalItem>) : RecyclerView.Adapter<AnimalListAdapter.AnimalViewHolder>() {

    inner class AnimalViewHolder(private val binding:AnimalItemAdapterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(animalItem: Animal.AnimalItem) {
            binding.txtName.text = animalItem.name
            binding.txtLatinName.text = animalItem.latin_name
            binding.txtDiet.text = "Diet : "+animalItem.diet
            if (animalItem.lifespan.toInt()>20) {
                binding.txtLifespan.text = "Lifespan : "+animalItem.lifespan + " yrs " + "(A long time)"
            } else if (animalItem.lifespan.toInt() in 11..20) {
                binding.txtLifespan.text = "Lifespan : "+animalItem.lifespan + " yrs " + "(kind of average)"
            } else {
                binding.txtLifespan.text = "Lifespan : "+animalItem.lifespan + " yrs " + "(Not very long!)"
            }
            Glide.with(MainApplication.getInstance().applicationContext)
                .load(animalItem.image_link)
                .into(binding.animalIcon)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : AnimalViewHolder
    = AnimalViewHolder(AnimalItemAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount() = list.size

    fun setItem(listOfData: List<Animal.AnimalItem>) {
        this.list = listOfData.sortedBy { list -> list.name }
        notifyDataSetChanged()
    }
}