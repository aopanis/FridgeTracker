package com.example.anton.fridgetracker

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MainFragment.OnFragmentInteractionListener, AppCompatActivity()   {

    private lateinit var mDrawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu)
        }

        mDrawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)

        nav_view.setNavigationItemSelectedListener { menuItem ->

            menuItem.isChecked = true

            mDrawerLayout.closeDrawers()

            true

        }

        if(findViewById<FrameLayout>(R.id.fragment_container) != null) {
            if(savedInstanceState != null) {
                return
            }

            val firstFragment = MainFragment()

            supportFragmentManager.beginTransaction().add(R.id.fragment_container, firstFragment)
                    .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                mDrawerLayout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onButtonClick() {
        val secondFragment = AddItemFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, secondFragment)
                .commit()
    }

}
