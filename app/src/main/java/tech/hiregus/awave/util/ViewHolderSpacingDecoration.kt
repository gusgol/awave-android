package tech.hiregus.awave.util

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import tech.hiregus.awave.R

class ViewHolderSpacingDecoration(private val space: Int, private val horizontal: Boolean = false) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        with(outRect) {
            val first = parent.getChildAdapterPosition(view) == 0
            if (horizontal) {
                top = space
                if (!first) left = space
                right = space
                bottom = space
            } else {
                if (first) top = space
                left = space
                right = space
                bottom = space
            }
        }
    }

    companion object {
        fun defaultSpacing(context: Context) = ViewHolderSpacingDecoration(context.resources.getDimension(R.dimen.keyline_4).toInt())
    }

}