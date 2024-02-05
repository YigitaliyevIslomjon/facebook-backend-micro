package com.facebook.friendships

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
class FriendshipsApplication

fun main(args: Array<String>) {
	runApplication<FriendshipsApplication>(*args)
}
