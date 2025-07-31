package com.mhdncb.yassirtest.core.data.mapper

import com.mhdncb.yassirtest.core.data.model.CharacterDto
import com.mhdncb.yassirtest.core.domain.model.Character

fun CharacterDto.toDomain(): Character {
    return Character(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        image = image,
        url = url
    )
}