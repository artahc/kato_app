package com.artahc.kato.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.artahc.kato.data.database.dao.CartDao
import com.artahc.kato.data.database.entity.CartEntity

@Database(entities = [CartEntity::class], version = 1)
abstract class KatoDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
}