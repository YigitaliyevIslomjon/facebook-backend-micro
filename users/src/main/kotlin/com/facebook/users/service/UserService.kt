package com.facebook.users.service

import com.facebook.users.entity.User
import com.facebook.users.payload.Result
import com.facebook.users.payload.UserDto
import java.util.UUID

interface UserService {

    fun add(dto: UserDto) : Result
    fun edit(id: UUID, dto: UserDto) : Result
    fun delete(id: UUID): Result
    fun getOne(id: UUID) :  User?
    fun getAll(pageNo: Int, pageSize:Int, sortBy:String, sortDir:String): List<User>




}