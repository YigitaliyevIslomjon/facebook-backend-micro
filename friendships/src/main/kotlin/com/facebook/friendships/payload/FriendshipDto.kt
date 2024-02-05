package com.facebook.friendships.payload


import com.facebook.friendships.entity.enum.Status
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Past
import java.sql.Timestamp
import java.util.*

data class FriendshipDto(

    @field:NotNull
    val userId1 : UUID,

    @field:NotNull
    val userId2 : UUID,

    @field:NotNull
    val status : Status,

    @field:Past
    val requestDate: Timestamp,

    @field:Past
    val acceptDate: Timestamp
 )
