package com.sharcome.moments.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginModel (
    var key: String? = "",
    val name: String? = "",
    val phone: String? = "",
    val birthday: String? = "",
    var username: String? = ""

): Parcelable