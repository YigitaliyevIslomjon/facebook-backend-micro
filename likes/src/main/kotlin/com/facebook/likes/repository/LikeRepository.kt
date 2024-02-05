package com.facebook.likes.repository

import com.facebook.likes.entity.Like
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface LikeRepository : JpaRepository<Like, UUID> {
}