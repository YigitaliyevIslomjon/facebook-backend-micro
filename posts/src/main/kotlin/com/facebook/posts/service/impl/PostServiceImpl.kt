package com.facebook.posts.service.impl

import com.facebook.posts.entity.Post
import com.facebook.posts.feignclients.UserService
import com.facebook.posts.feignclients.userService
import com.facebook.posts.payload.PostDto
import com.facebook.posts.repository.PostRepository
import com.facebook.posts.payload.Result
import com.facebook.posts.service.PostService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.*

@Service
class PostServiceImpl(
    private  val postRepository: PostRepository,
    private  val userService: UserService
) : PostService {
    override fun add(dto: PostDto): Result {
        val user = userService.getOne(dto.userId)

        postRepository.save(
            Post(userId = user.id, content = dto.content, postDate = dto.postDate))

        return Result(message = "data are saved successfully")
    }

    override fun getAll(pageNo: Int, pageSize:Int, sortBy:String, sortDir:String): List<Post> {
        val sort  = if(sortDir.equals(Sort.Direction.ASC.name, ignoreCase = true)){
            Sort.by(sortBy).ascending()
        }else{
            Sort.by(sortBy).descending()
        }
        val pageable = PageRequest.of(pageNo,pageSize,sort)
        val existingPost = postRepository.findAll(pageable)
        if(existingPost.hasContent()){
            return existingPost.content.toList()
        }
        return emptyList()
    }

    override fun getOne(id: UUID): Post {
        val existingPost = postRepository.findById(id)
        if(existingPost.isEmpty){
            return Post()
        }
        return existingPost.get()
    }

    override fun edit(id: UUID, dto:PostDto): Result {
        val existingPost = postRepository.findById(id)
        if(existingPost.isEmpty){
            return Result(message = "id of user is not exist", success = false)
        }
        val updateUser = existingPost.get()
            .copy(firstName = dto.firstName,
                lastName = dto.lastName,
                email = dto.email,
                password = dto.password,
                birthdate = dto.birthdate,
                registrationDate = dto.registrationDate)

        postRepository.save((updateUser))
        return Result(message = "Data are edited", success = true)

    }

    override fun delete(id: UUID): Result {
        val existingPost = postRepository.findById(id)
        if(existingPost.isEmpty){
            return Result(message = "id of user is not exist", success = false)
        }
        postRepository.deleteById(id)
        return  Result(message = "Data are deleted", success = true)

    }
}