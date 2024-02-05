package com.facebook.friendships.service

import com.facebook.friendships.entity.Friendship
import com.facebook.friendships.payload.FullResponse
import com.facebook.friendships.payload.FriendshipDto
import com.facebook.friendships.payload.Result
import java.util.UUID

interface FriendshipService {

    fun add(dto: FriendshipDto) : Result
    fun edit(id: UUID, dto: FriendshipDto) : Result
    fun delete(id: UUID): Result
    fun getOne(id: UUID) : FullResponse
    fun getAll(pageNo: Int, pageSize:Int, sortBy:String, sortDir:String): List< Friendship>

}