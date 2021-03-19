package tech.hiregus.awave.itineraries.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.hiregus.awave.R
import tech.hiregus.awave.data.City
import tech.hiregus.awave.databinding.HeaderItemBinding
import tech.hiregus.awave.databinding.ItineraryItemBinding
import tech.hiregus.awave.itineraries.Itinerary
import tech.hiregus.awave.util.HeaderViewHolder
import tech.hiregus.awave.util.extensions.loadWithRoundedCorners

class ItinerariesAdapter(private var itineraries: List<Itinerary> = listOf()) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_ITINERARY = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TYPE_HEADER else TYPE_ITINERARY
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_HEADER -> HeaderViewHolder(HeaderItemBinding.inflate(inflater, parent, false))
            else -> ItineraryViewHolder(ItineraryItemBinding.inflate(inflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> holder.bind(R.string.title_your_itineraries)
            is ItineraryViewHolder -> holder.bind(itineraries[position - 1])
        }
    }

    override fun getItemCount(): Int = itineraries.count() + 1

    @SuppressLint("NotifyDataSetChanged")
    fun setItineraries(itineraries: List<Itinerary>) {
        this.itineraries = itineraries
        this.notifyDataSetChanged()
    }

    class ItineraryViewHolder(private val binding: ItineraryItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(itinerary: Itinerary) {
            binding.image.loadWithRoundedCorners(itinerary.city.image)
            binding.title.text = itinerary.title
            binding.description.text = getDescription(itinerary.city)
        }

        private fun getDescription(city: City): String {
            return "${itemView.context.getString(city.displayName)} • ${itemView.context.getString(city.description)}"
        }

    }

}