package com.example.anton.fridgetracker

import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private var mDb: FoodItemDatabase? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDb = FoodItemDatabase.getInstance(this)

        val dataQuery = DatabaseQueryTask()

        dataQuery.execute()

//        viewAdapter = FoodItemAdapter(arrayOf("Hello", "this", "is"))

    }

    fun addItem(view: View) {
        val intent = Intent(this, AddItem::class.java)
        startActivity(intent)
    }

    private inner class DatabaseQueryTask: AsyncTask<Void, Void, Array<FoodItem>?>() {
        override fun doInBackground(vararg p0: Void?): Array<FoodItem>? {
            val myDataset = mDb?.foodItemDao()?.getAll()?.toTypedArray()
            return myDataset
        }

        override fun onPostExecute(result: Array<FoodItem>?) {
            super.onPostExecute(result)
            viewManager = LinearLayoutManager(this@MainActivity)
            if(result != null) {
                viewAdapter = FoodItemAdapter(result)
            }

            recyclerView.apply {
                setHasFixedSize(true)

                layoutManager = viewManager

                adapter = viewAdapter
            }
        }
    }
}
