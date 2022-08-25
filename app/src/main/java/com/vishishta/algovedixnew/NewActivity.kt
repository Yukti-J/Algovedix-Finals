package com.vishishta.algovedixnew

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class NewActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout


    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawer_layout)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayoutTwo)
        val navView: NavigationView = findViewById(R.id.nav_view)


        val vata:Int = intent.getIntExtra("VATA",0)
        val pitta:Int = intent.getIntExtra("PITTA",0)
        val kapha:Int = intent.getIntExtra("KAPHA",0)

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)


        navView.setNavigationItemSelectedListener {
            it.isChecked = true

            when(it.itemId){
                R.id.dashboard -> {replaceFragment(DashboardFragment(),it.title.toString())
                                   drawerLayout.closeDrawers()}
                R.id.calorie_tracker -> {replaceFragment(CalorieFragment(),it.title.toString())
                                   drawerLayout.closeDrawers()}
                R.id.steps_tracker -> {
                    replaceFragment(StepsFragment(), it.title.toString())
                    drawerLayout.closeDrawers()
                }
                R.id.vedix_store -> {replaceFragment(VedixFragment(),it.title.toString())
                     drawerLayout.closeDrawers()}
                R.id.about_us -> {replaceFragment(AboutUsFragment(),it.title.toString())
                     drawerLayout.closeDrawers()}
            }

            true
        }

    }

    private fun replaceFragment(fragment : Fragment,title: String){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,fragment)
        fragmentTransaction.commit()

        setTitle(title)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)){
            true
        }

        return super.onOptionsItemSelected(item)
    }


}

