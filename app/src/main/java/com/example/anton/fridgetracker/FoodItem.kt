package com.example.anton.fridgetracker

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class FoodItem(@PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name = "food_name") var foodName: String,
    @ColumnInfo(name = "quantity") var quantity: Int,
    @ColumnInfo(name = "unit") var unit: String
){
    constructor():this(null, "", 0, "")
}
