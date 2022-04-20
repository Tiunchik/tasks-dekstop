package config.decompose

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

sealed class Pages: Parcelable {

    @Parcelize
    object TaskList: Pages()

    @Parcelize
    object TaskCreate: Pages()

    @Parcelize
    data class TaskDetails(val id: Long): Pages()
}