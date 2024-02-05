package com.facebook.friendships.payload

import com.facebook.friendships.entity.enum.Status
import java.sql.Timestamp

data class FullResponse(
    val user1 : User,
    val user2 : User,
    val status : Status,
    val requestDate: Timestamp,
    val acceptDate: Timestamp
 )
