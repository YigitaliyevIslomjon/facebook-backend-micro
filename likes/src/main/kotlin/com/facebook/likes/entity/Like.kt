package com.facebook.likes.entity

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.sql.Timestamp
import java.util.*

@Entity
@Table(name = "likes")
data class Like(
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    val id: UUID? = null,

    @Column(nullable = false)
    val userId : UUID,

    @Column(nullable = false)
    val postId : UUID,

    @Column(nullable = false)
    val createdDate: Timestamp = Timestamp(System.currentTimeMillis()),
)