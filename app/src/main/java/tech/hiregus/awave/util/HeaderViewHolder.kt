package tech.hiregus.awave.util

import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import tech.hiregus.awave.databinding.HeaderItemBinding
import tech.hiregus.awave.util.extensions.setHtmlText


class HeaderViewHolder(private val binding: HeaderItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(@StringRes title: Int) {
        binding.root.setHtmlText(itemView.context.getString(title))
    }
}