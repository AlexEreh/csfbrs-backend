package com.alexereh.csfbrsbackend.model

data class PersonData(
	val firstName: String,
	val lastName: String,
	val patronymic: String,
	val course: Int,
	val semester: Int,
	val group: Int,
	val subGroup: Int,
	val programmeName: String,
	val grades: List<StatisticRow>
)
