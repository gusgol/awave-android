package tech.hiregus.awave.itineraries.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import tech.hiregus.awave.databinding.ItineraryItemBinding
import tech.hiregus.awave.itineraries.Itinerary
import tech.hiregus.awave.util.extensions.loadWithRoundedCorners

class ItinerariesAdapter(private var itineraries: List<Itinerary> = listOf()) : RecyclerView.Adapter<ItinerariesAdapter.ItineraryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItineraryViewHolder {
        val binding = ItineraryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItineraryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItineraryViewHolder, position: Int) {
        holder.bind(itineraries[position])
    }

    override fun getItemCount(): Int = itineraries.count()

    @SuppressLint("NotifyDataSetChanged")
    fun setItineraries(itineraries: List<Itinerary>) {
        this.itineraries = itineraries
        this.notifyDataSetChanged()
    }

    class ItineraryViewHolder(private val binding: ItineraryItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(itinerary: Itinerary) {
            binding.image.loadWithRoundedCorners(itinerary.city.image)
            binding.title.text = itinerary.title
            binding.description.text = "14 places selected"
        }

    }

}