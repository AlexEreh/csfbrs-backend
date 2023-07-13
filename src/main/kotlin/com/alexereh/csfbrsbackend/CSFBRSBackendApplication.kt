package com.alexereh.csfbrsbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@SpringBootApplication
class CSFBRSBackendApplication

fun main(args: Array<String>) {
	runApplication<CSFBRSBackendApplication>(*args)
}

@Bean
fun corsConfigurer(): WebMvcConfigurer {
	return object : WebMvcConfigurer {
		override fun addCorsMappings(registry: CorsRegistry) {
			registry
				.addMapping("/graphql")
				.allowedOrigins("http://localhost:5173")
				.allowedMethods("GET", "POST", "HEAD", "OPTIONS")
		}
	}
}
