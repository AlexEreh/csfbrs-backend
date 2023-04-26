package com.alexereh.csfbrsbackend.model

data class StatisticRow(
	val studyYear: String,
	val semesterNumber: Byte,
	val courseNumber: Byte,
	val disciplineName: String,
	val scoringType: ScoringType,
	val tutor: String,
	val marks: Marks = Marks()
)

