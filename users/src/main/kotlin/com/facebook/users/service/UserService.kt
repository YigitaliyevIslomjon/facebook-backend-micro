package com.facebook.users.service

import com.facebook.users.entity.User
import com.facebook.users.payload.Result

interface UserService {

    fun postUser(): Result

    fun gerAllUser(): List<User>

    fun gerUser(): User

    fun editUser(): Result

    fun deleteUser(): Result




}