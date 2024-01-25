package com.facebook.users.service.impl

import com.facebook.users.entity.User
import com.facebook.users.payload.Result
import com.facebook.users.payload.UserDto
import com.facebook.users.repository.UserRepository
import com.facebook.users.service.UserService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(private  val userRepository: UserRepository) : UserService {
    override fun add(dto: UserDto): Result {
        userRepository.save(
            User(firstName = dto.firstName,
                lastName = dto.lastName,
                email = dto.email,
                password = dto.password,
                birthdate = dto.birthdate,
                registrationDate = dto.registrationDate))
        return Result(message = "data are saved successfully")
    }

    override fun getAll(pageNo: Int, pageSize:Int, sortBy:String, sortDir:String): List<User> {
        val sort  = if(sortDir.equals(Sort.Direction.ASC.name, ignoreCase = true)){
            Sort.by(sortBy).ascending()
        }else{
            Sort.by(sortBy).descending()
        }
        val pageable = PageRequest.of(pageNo,pageSize,sort)
        val existingUser = userRepository.findAll(pageable)
        if(existingUser.hasContent()){
            return existingUser.content.toList()
        }
        return emptyList()
    }

    override fun getOne(id: UUID): User {
        val existingUser = userRepository.findById(id)
        if(existingUser.isEmpty){
/*
            throw UserNotFoundException("User not found with ID: $id")
*/
        }
        return existingUser.get()
    }

    override fun edit(id: UUID, dto:UserDto): Result {
        val existingUser = userRepository.findById(id)
        if(existingUser.isEmpty){
            return Result(message = "id of user is not exist", success = false)
        }
        val updateUser = existingUser.get()
            .copy(firstName = dto.firstName,
                lastName = dto.lastName,
                email = dto.email,
                password = dto.password,
                birthdate = dto.birthdate,
                registrationDate = dto.registrationDate)

        userRepository.save((updateUser))
        return Result(message = "Data are edited", success = true)

    }

    override fun delete(id: UUID): Result {
        val existingUser = userRepository.findById(id)
        if(existingUser.isEmpty){
            return Result(message = "id of user is not exist", success = false)
        }
        userRepository.deleteById(id)
        return  Result(message = "Data are deleted", success = true)

    }
}