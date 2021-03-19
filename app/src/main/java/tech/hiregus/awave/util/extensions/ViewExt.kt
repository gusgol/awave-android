package tech.hiregus.awave.util.extensions

import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.text.HtmlCompat
import coil.load
import coil.transform.RoundedCornersTransformation
import tech.hiregus.awave.R

/**
 * Load an image with rounded corners using Coil.kt
 *
 * @param res resource id
 */
fun ImageView.loadWithRoundedCorners(@DrawableRes res: Int) {
    val cornerSize = context.resources.getDimension(R.dimen.cornerSize)
    this.load(res) {
        crossfade(true)
//        placeholder(R.drawable.image)
        transformations(RoundedCornersTransformation(cornerSize))
    }
}

/**
 * Set a text view text and apply html tags to it
 *
 * @param text the html
 */
@Suppress("DEPRECATION")
fun TextView.setHtmlText(text: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        this.text = Html.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY)
    } else {
        this.text = Html.fromHtml(text)
    }
}