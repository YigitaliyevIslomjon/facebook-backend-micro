package com.facebook.likes.payload

import java.sql.Timestamp
import java.util.*

data class User(
    val id : UUID,
    val firstName: String,
    val lastName: String,
    val email : String,
    val birthdate : Date,
    val registrationDate : Timestamp,
    )
