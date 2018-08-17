package com.example.anton.fridgetracker

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.food_item_list.view.*

class FoodItemAdapter(private val myDataset: Array<FoodItem>):
        RecyclerView.Adapter<FoodItemAdapter.ViewHolder>() {


    class ViewHolder(val textView: LinearLayout): RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemAdapter.ViewHolder{

        val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.food_item_list, parent, false) as LinearLayout

        return ViewHolder(textView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.foodType.text = myDataset[position].foodName
        holder.textView.foodQuantity.text = myDataset[position].quantity.toString() + " " +
                myDataset[position].unit
    }

    override fun getItemCount() = myDataset.size

}