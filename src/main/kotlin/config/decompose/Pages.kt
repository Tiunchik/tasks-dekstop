package config.decompose

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

sealed class Pages: Parcelable {

    @Parcelize
    object List: Pages()

    @Parcelize
    data class Details(val id: Long): Pages()
}