package com.facebook.friendships.entity

import com.facebook.friendships.entity.enum.Status
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.sql.Timestamp
import java.util.*

@Entity
data class Friendship(
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    val id: UUID? = null,

    @Column(nullable = false)
    val userId1 : UUID,

    @Column(nullable = false)
    val userId2 : UUID,

    @Column(nullable = false)
    val status : Status,

    @Column(nullable = false)
    val requestDate: Timestamp,

    @Column(nullable = false)
    val acceptDate: Timestamp
)