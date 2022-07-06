package com.cblanco.marvel.data.mappers

import com.cblanco.domain.Character
import com.cblanco.marvel.data.database.CharacterModelDb
import com.cblanco.marvel.data.server.apiresults.publiccharacter.ResultCharacter


fun Character.toRoomDb(): CharacterModelDb = CharacterModelDb(
    this.id,
    this.characterName,
    this.description,
    this.characterImageUrl
)

fun CharacterModelDb.toDomain(): Character = Character(
    this.id,
    this.characterName,
    this.description,
    this.characterImageUrl
)

fun ResultCharacter.toDomain(): Character = Character(
    this.id,
    this.name,
    this.description,
    this.thumbnail.path +"."+ this.thumbnail.extension
)