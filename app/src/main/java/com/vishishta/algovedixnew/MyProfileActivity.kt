package com.vishishta.algovedixnew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.vishishta.algovedixnew.databinding.ActivityMyProfileBinding

class MyProfileActivity : AppCompatActivity() {

    //viewBinding
    private lateinit var binding: ActivityMyProfileBinding

    //ActionBar
    private lateinit var actionBar: ActionBar

    //FirebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth

    private var firstName = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Taking intent from previous activity
        val vataExtra = intent.getIntExtra("Vata",0)
        val pittExtra = intent.getIntExtra("Pitta",0)
        val kaphaExtra = intent.getIntExtra("cough",0)

        //configure actionbar
        actionBar = supportActionBar!!
        actionBar.title= "My Profile"

        //initialise firebase auth
        firebaseAuth=FirebaseAuth.getInstance()
        checkUser()

        //handle click of logout
      /*  binding.btnLogOut.setOnClickListener{
            firebaseAuth.signOut()
            checkUser()
        }*/

      //  TempData.energy = "Heyyy"
        //handling click on dashboard button
        binding.btnToDashboard.setOnClickListener{
            Log.d("DATAAYA", "$vataExtra $pittExtra $kaphaExtra")
            intent = Intent(this, NewActivity::class.java)
            intent.putExtra("VATA",vataExtra)
            intent.putExtra("PITTA",pittExtra)
            intent.putExtra("KAPHA",kaphaExtra)

            startActivity(intent)
        }



    }

    private fun checkUser() {
        //check user is logged in or not
        val firebaseUser =firebaseAuth.currentUser
     /*   if (firebaseUser != null){
            //user not null, user is logged in
            val email= firebaseUser.email
            binding.emailTv.text = email
        }
        else{
            //user is null, user is not logged in
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }*/
        if (firebaseUser != null){
            //user not null, user is logged in
           intent= Intent(this,NewActivity::class.java)
        }
        else {
            //not logged in
        }
    }
}

