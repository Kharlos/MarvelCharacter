package com.cblanco.marvel.data.database

import androidx.room.*
import com.cblanco.domain.Character

@Dao
interface CharacterDao {

    @Query("SELECT * FROM CharacterModelDb")
    fun getAll(): List<CharacterModelDb>

    @Query("SELECT * FROM CharacterModelDb WHERE id = :id")
    fun findById(id: Int): CharacterModelDb

    @Query("SELECT COUNT(id) FROM CharacterModelDb")
    fun characterCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacters(characters: List<CharacterModelDb>)

    @Update
    fun updateCharacter(character: CharacterModelDb)
}