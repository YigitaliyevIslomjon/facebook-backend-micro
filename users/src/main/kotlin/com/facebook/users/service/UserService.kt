package com.facebook.users.service

import com.facebook.users.entity.User
import com.facebook.users.payload.Result
import com.facebook.users.payload.UserDto

interface UserService {

    fun add(dto: UserDto) : Result
    fun edit(id: Long, dto: UserDto) : Result
    fun delete(id: Long): Result
    fun getOne(id: Long) :  User
    fun getAll(pageNo: Int, pageSize:Int, sortBy:String, sortDir:String): List< User>




}