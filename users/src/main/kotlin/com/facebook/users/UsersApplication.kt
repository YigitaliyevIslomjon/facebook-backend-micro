package com.facebook.users

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient


@SpringBootApplication
class UsersApplication

fun main(args: Array<String>) {
	runApplication<UsersApplication>(*args)
}
