package tech.hiregus.awave.util.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import coil.load
import coil.transform.RoundedCornersTransformation
import tech.hiregus.awave.R

fun ImageView.loadWithRoundedCorners(@DrawableRes res: Int) {
    val cornerSize = context.resources.getDimension(R.dimen.cornerSize)
    this.load(res) {
        crossfade(true)
//        placeholder(R.drawable.image)
        transformations(RoundedCornersTransformation(cornerSize))
    }
}