package com.facebook.friendships.repository

import com.facebook.friendships.entity.Friendship
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface FriendshipRepository : JpaRepository<Friendship, UUID> {
}