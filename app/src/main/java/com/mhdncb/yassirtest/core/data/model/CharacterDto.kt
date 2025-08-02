package com.mhdncb.yassirtest.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterDto(
    val id: Int,
    val name: String,
    val status : String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    val url: String
)