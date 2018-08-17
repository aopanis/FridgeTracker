package com.example.anton.fridgetracker

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.OnConflictStrategy.REPLACE

@Dao
interface FoodItemDAO {
    @Query("SELECT * from FoodItem")
    fun getAll(): List<FoodItem>

    @Insert(onConflict = REPLACE)
    fun insert(foodItem: FoodItem)

    @Query("DELETE from FoodItem")
    fun deleteAll()
}