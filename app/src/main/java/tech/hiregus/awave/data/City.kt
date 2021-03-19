package tech.hiregus.awave.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import tech.hiregus.awave.R

enum class City(@StringRes val displayName: Int, @StringRes val description: Int, @DrawableRes val image: Int) {
    Paris(R.string.title_city_paris, R.string.title_desc_paris, R.drawable.img_city_paris),
    London(R.string.title_city_london, R.string.title_desc_london, R.drawable.img_city_london),
    Berlin(R.string.title_city_berlin, R.string.title_desc_berlin, R.drawable.img_city_berlin)
}