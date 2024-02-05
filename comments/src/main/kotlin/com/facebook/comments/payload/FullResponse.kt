package com.facebook.comments.payload


import java.util.*

data class FullResponse(

    val user : User,
    val post : Post,
    val contentText: String,
    val createdDate: Date,
    )
