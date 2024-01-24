package com.facebook.users.service.impl

import com.facebook.users.entity.User
import com.facebook.users.payload.Result
import com.facebook.users.payload.UserDto
import com.facebook.users.repository.UserRepository
import com.facebook.users.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(val userRepository: UserRepository) : UserService {
    override fun add(dto: UserDto): Result {

    }

    override fun gerAll(): List<User> {
        TODO("Not yet implemented")
    }

    override fun gerOne(): User {
        TODO("Not yet implemented")
    }

    override fun edit(): Result {
        TODO("Not yet implemented")
    }

    override fun delete(): Result {
        TODO("Not yet implemented")
    }
}