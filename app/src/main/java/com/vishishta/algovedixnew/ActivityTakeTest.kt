package com.vishishta.algovedixnew


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class ActivityTakeTest : AppCompatActivity() {
    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinateLayout: CoordinatorLayout
    lateinit var toolbar: Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_test)

        drawerLayout= findViewById(R.id.drawerLayout)
        toolbar = findViewById(R.id.toolbar)
        navigationView= findViewById(R.id.navigationView)


    }
    fun toTest(view: View) {
        val intent = Intent(this, QuestionsPane::class.java)
        startActivity(intent)
    }




}