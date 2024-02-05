package com.facebook.likes.payload

import java.sql.Timestamp
import java.util.*

data class Post(
    val id : UUID,
    val userId : UUID,
    val content: String,
    val postDate: Timestamp,
    )
