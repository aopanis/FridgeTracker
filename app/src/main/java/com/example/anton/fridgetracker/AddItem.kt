package com.example.anton.fridgetracker

import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_item.*

class AddItem : AppCompatActivity() {

    private var mDb: FoodItemDatabase? = null

    private lateinit var insertFood: InsertFoodTask

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        insertFood = InsertFoodTask()

        mDb = FoodItemDatabase.getInstance(this)

        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(this,
                R.array.unit_choices, android.R.layout.simple_spinner_item)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        unitSelector.adapter = adapter
    }

    fun onSave(view: View) {

        val foodItem = FoodItem()

        foodItem.foodName = foodType.text.toString()
        foodItem.quantity = quantity.text.toString().toInt()
        foodItem.unit = unitSelector.selectedItem.toString()

        insertFood.execute(foodItem)
        startActivity(Intent(this, MainActivity::class.java))

    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
    }

    private inner class InsertFoodTask: AsyncTask<FoodItem, Unit, Unit>() {

        override fun doInBackground(vararg foodItems: FoodItem?) {
            val foodItem = foodItems[0]
            if(foodItem != null) {
                mDb?.foodItemDao()?.insert(foodItem)
            } else {
                Toast.makeText(this@AddItem, "No FoodItem received",
                        Toast.LENGTH_SHORT).show()
            }
        }

    }
}
