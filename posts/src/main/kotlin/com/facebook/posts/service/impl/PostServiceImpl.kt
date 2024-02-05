package com.facebook.posts.service.impl

import com.facebook.posts.entity.Post
import com.facebook.posts.feignclients.UserService
import com.facebook.posts.payload.FullPostResponse
import com.facebook.posts.payload.PostDto
import com.facebook.posts.payload.Result
import com.facebook.posts.payload.User
import com.facebook.posts.repository.PostRepository
import com.facebook.posts.service.PostService
import feign.FeignException
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
        try {
            val user: User = userService.getOne(dto.userId)

            postRepository.save(
                Post(userId = user.id, content = dto.content, postDate = dto.postDate)
            )

            return Result(message = "data are saved successfully")
        } catch (e: FeignException.NotFound) {
            throw IllegalArgumentException(e.message)
        }
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
        try {
            val existingPost = postRepository.findById(id)
            val user = userService.getOne(existingPost.get().userId!!)

            if (existingPost.isEmpty) {
                throw IllegalArgumentException("id is not found")
            }

            return Post(
                id = existingPost.get().id,
                userId = user.id,
                postDate = existingPost.get().postDate,
                content = existingPost.get().content
            )
        } catch (e: FeignException.NotFound) {
            throw IllegalArgumentException(e.message)
        }
    }

    override fun edit(id: UUID, dto:PostDto): Result {
        try {
            val existingPost = postRepository.findById(id)

            if (existingPost.isEmpty) {
                throw IllegalArgumentException("id of user is not exist")
            }

            val user = userService.getOne(existingPost.get().userId!!)
            val updateUser = existingPost.get()
                .copy(
                    userId = user.id, postDate = existingPost.get().postDate!!,
                    content = existingPost.get().content!!
                )

            postRepository.save(updateUser)
            return Result(message = "Data are edited", success = true)
        } catch (e: FeignException.NotFound) {
            throw IllegalArgumentException(e.message)
        }
    }

    override fun delete(id: UUID): Result {
        val existingPost = postRepository.findById(id)

        if(existingPost.isEmpty){
            throw NoSuchElementException("id of user is not exist")
        }

        postRepository.deleteById(id)
        return  Result(message = "Data are deleted", success = true)
    }
}