package com.cblanco.marvel.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharacterModelDb(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val characterName: String,
    val description: String,
    val characterImageUrl: String
)