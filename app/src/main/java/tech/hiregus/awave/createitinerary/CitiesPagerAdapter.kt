package tech.hiregus.awave.createitinerary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.hiregus.awave.data.City
import tech.hiregus.awave.databinding.CityViewBinding
import tech.hiregus.awave.util.extensions.loadWithRoundedCorners

class CitiesPagerAdapter(
    private val imagesList: List<City>
) : RecyclerView.Adapter<CitiesPagerAdapter.CityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder(
            CityViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(imagesList[position])
    }

    override fun getItemCount(): Int {
        return imagesList.size
    }

    class CityViewHolder(private val binding: CityViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(city: City) {
            binding.image.loadWithRoundedCorners(city.image)
            binding.title.text = city.name
        }
    }

}