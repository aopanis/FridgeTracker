package com.example.anton.fridgetracker

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_add_item.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AddItemFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AddItemFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class AddItemFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var mDb: FoodItemDatabase? = null

    private lateinit var insertFood: InsertFoodTask
//    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        insertFood = InsertFoodTask()

        val currentContext = context

        if(currentContext!= null) {
            mDb = FoodItemDatabase.getInstance(currentContext)

            val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(currentContext,
                    R.array.unit_choices, android.R.layout.simple_spinner_item)

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        unitSelector.adapter = adapter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_item, container, false)
    }




    fun onSave(view: View) {

        val foodItem = FoodItem()

        foodItem.foodName = foodType.text.toString()
        foodItem.quantity = quantity.text.toString().toInt()
        foodItem.unit = unitSelector.selectedItem.toString()

        insertFood.execute(foodItem)
        startActivity(Intent(context, MainActivity::class.java))

    }


    private inner class InsertFoodTask: AsyncTask<FoodItem, Unit, Unit>() {

        override fun doInBackground(vararg foodItems: FoodItem?) {
            val foodItem = foodItems[0]
            if(foodItem != null) {
                mDb?.foodItemDao()?.insert(foodItem)
            } else {
                Toast.makeText(context, "No FoodItem received",
                        Toast.LENGTH_SHORT).show()
            }
        }

    }

    // TODO: Rename method, update argument and hook method into UI event
//    fun onButtonPressed(uri: Uri) {
//        listener?.onFragmentInteraction(uri)
//    }
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is OnFragmentInteractionListener) {
//            listener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
//        }
//    }
//
//    override fun onDetach() {
//        super.onDetach()
//        listener = null
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
//    interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        fun onFragmentInteraction(uri: Uri)
//    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddItemFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                AddItemFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
