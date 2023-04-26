package com.alexereh.csfbrsbackend.skrape

import com.alexereh.csfbrsbackend.model.Marks
import com.alexereh.csfbrsbackend.model.PersonData
import com.alexereh.csfbrsbackend.model.ScoringType
import com.alexereh.csfbrsbackend.model.StatisticRow
import it.skrape.core.document
import it.skrape.fetcher.*

class PersonSkraper(
	private val login: String,
	private val password: String
) {
	fun getPerson(): PersonData {
		return skrape(BrowserFetcher) {
			request {
				method = Method.POST
				url = "https://www.cs.vsu.ru/brs/login"
				timeout = 10_000
				followRedirects = true
				body {
					form {
						"login" to login
						"password" to password
						"user_type" to ""
						"button_login" to "Вход"
					}
				}
			}
			response(fun Result.(): PersonData {
				val primaryElementsList = document.findAll("dl > dd")
				val fullNameSplit = primaryElementsList[0].ownText.split(" ")
				val course = primaryElementsList[1].ownText.toInt()
				val semester = primaryElementsList[2].ownText.toInt()
				val subGroup = primaryElementsList[3]
					.ownText
					.drop(1).dropLast(1)
					.toInt()
				val group = primaryElementsList[3].children.first().ownText.toInt()
				val programmeName = primaryElementsList[4].ownText

				val gradesRowElementsList = document.findAll("tbody > tr")
				val staticRows = gradesRowElementsList.map {
					StatisticRow(
						studyYear = it.children[0].ownText,
						semesterNumber = it.children[1].ownText.toByte(),
						courseNumber = it.children[2].ownText.toByte(),
						disciplineName = it.children[3].ownText,
						scoringType = ScoringType.getTypeFromString(it.children[4].ownText),
						tutor = it.children[5].ownText,
						marks = Marks(
							firstAttestationScore = it.children[6].ownText.toByteOrNull(),
							secondAttestationScore = it.children[7].ownText.toByteOrNull(),
							thirdAttestationScore = it.children[8].ownText.toByteOrNull(),
							examScore = it.children[10].ownText.toByteOrNull(),
							additionalScore = it.children[11].ownText.toByteOrNull(),
							resultScore = it.children[12].ownText.toByteOrNull(),
						)
					)
				}
				return PersonData(
					firstName = fullNameSplit[1],
					lastName = fullNameSplit[0],
					patronymic = fullNameSplit[2],
					course = course,
					semester = semester,
					group = group,
					subGroup = subGroup,
					programmeName = programmeName,
					grades = staticRows
				)
			})
		}
	}
}