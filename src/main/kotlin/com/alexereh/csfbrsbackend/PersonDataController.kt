package com.alexereh.csfbrsbackend

import com.alexereh.csfbrsbackend.model.PersonData
import com.alexereh.csfbrsbackend.skrape.PersonSkraper
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PersonDataController {
	@QueryMapping
	fun personByLoginAndPassword(
		@Argument login: String,
		@Argument password: String
	): PersonData{
		return PersonSkraper(login, password).getPerson()
	}
}