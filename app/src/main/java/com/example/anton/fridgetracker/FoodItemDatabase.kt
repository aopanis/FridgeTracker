package com.example.anton.fridgetracker

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = arrayOf(FoodItem::class), version = 1)
abstract class FoodItemDatabase: RoomDatabase() {

    abstract fun foodItemDao(): FoodItemDAO

    companion object {
        private var INSTANCE: FoodItemDatabase? = null

        fun getInstance(context: Context): FoodItemDatabase? {
            if(INSTANCE == null) {
                synchronized(FoodItemDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            FoodItemDatabase::class.java, "foodItem.db").build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}