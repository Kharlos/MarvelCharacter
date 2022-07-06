package com.cblanco.marvel.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CharacterModelDb::class], version = 1)
abstract class CharacterDatabase : RoomDatabase() {

    companion object {
        fun build(context: Context) = Room.databaseBuilder(
            context,
            CharacterDatabase::class.java,
            "marvel-db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    abstract fun characterDao(): CharacterDao
}