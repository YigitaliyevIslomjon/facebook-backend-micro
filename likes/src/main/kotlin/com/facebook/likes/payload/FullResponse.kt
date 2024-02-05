package com.facebook.likes.payload


import java.util.*

data class FullResponse(
    val user : User,
    val post : Post,
    val createdDate: Date,
    )
