package com.example.assessment3.model

import android.provider.ContactsContract.CommonDataKinds.Email

data class User(
    val name: String = "",
    val email: String= "",
    val photoUrl: String = ""
)