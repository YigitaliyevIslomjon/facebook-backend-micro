package com.facebook.likes.service.impl

import com.facebook.likes.entity.Like
import com.facebook.likes.feignclients.PostService
import com.facebook.likes.feignclients.UserService
import com.facebook.likes.payload.*
import com.facebook.likes.repository.LikeRepository
import com.facebook.likes.service.LikeService
import feign.FeignException
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.*

@Service
class LikeServiceImpl(
    private  val likeRepository: LikeRepository,
    private  val userService: UserService,
    private  val postService: PostService
) : LikeService {
    override fun add(dto: LikeDto): Result {
        try {
            val user: User = userService.getOne(dto.userId)
            val post: Post = postService.getOne(dto.postId)
            likeRepository.save(
                Like(userId = user.id, postId = post.id)
            )

            return Result(message = "data are saved successfully")
        } catch (e: FeignException.NotFound) {
            throw IllegalArgumentException(e.message)
        }
    }

    override fun getAll(pageNo: Int, pageSize:Int, sortBy:String, sortDir:String): List<Like> {
        val sort  = if(sortDir.equals(Sort.Direction.ASC.name, ignoreCase = true)){
            Sort.by(sortBy).ascending()
        }else{
            Sort.by(sortBy).descending()
        }
        val pageable = PageRequest.of(pageNo,pageSize,sort)
        val existingLike = likeRepository.findAll(pageable)
        if(existingLike.hasContent()){
            return existingLike.content.toList()
        }
        return emptyList()
    }

    override fun getOne(id: UUID): FullResponse {
        try {
            val existingLike = likeRepository.findById(id)
            val user = userService.getOne(existingLike.get().userId)
            val post: Post = postService.getOne(existingLike.get().postId)

            if (existingLike.isEmpty) {
                throw IllegalArgumentException("id is not found")
            }
            return FullResponse(user = user, post = post, createdDate = existingLike.get().createdDate)
        } catch (e: FeignException.NotFound) {
            throw IllegalArgumentException(e.message)
        }
    }

    override fun edit(id: UUID, dto:LikeDto): Result {
        try {
            val existingLike = likeRepository.findById(id)
            if(existingLike.isEmpty){
                throw NoSuchElementException("id of like is not exist")
            }

            val user = userService.getOne(existingLike.get().userId)
            val post: Post = postService.getOne(existingLike.get().postId)

            val updateLike = existingLike.get()
                .copy(userId = user.id, postId = post.id)

            likeRepository.save(updateLike)
            return Result(message = "Data are edited", success = true)
        } catch (e: FeignException.NotFound) {
            throw IllegalArgumentException(e.message)
        }
    }

    override fun delete(id: UUID): Result {
        val existingLike = likeRepository.findById(id)
        if(existingLike.isEmpty){
            throw NoSuchElementException("id of like is not exist")
        }

        likeRepository.deleteById(id)
        return  Result(message = "Data are deleted", success = true)
    }
}