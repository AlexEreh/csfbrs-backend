package com.alexereh.csfbrsbackend.model

data class Marks(
	val firstAttestationScore: Byte? = null,
	val secondAttestationScore: Byte? = null,
	val thirdAttestationScore: Byte? = null,
	val examScore: Byte? = null,
	val additionalScore: Byte? = null,
	val resultScore: Byte? = null,
)