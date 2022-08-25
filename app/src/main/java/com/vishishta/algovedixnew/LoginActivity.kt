package com.vishishta.algovedixnew

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.FirebaseAuth
import com.vishishta.algovedixnew.databinding.ActivityMainBinding
import com.vishishta.algovedixnew.databinding.LoginBinding

class LoginActivity : AppCompatActivity() {

    //viewBinding
    private lateinit var binding:LoginBinding

    //ActionBar
    private lateinit var actionBar: ActionBar

    //ProgressDialog
    private lateinit var progressDialog: ProgressDialog

    //FirebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //configure actionbar
        actionBar = supportActionBar!!
        actionBar.title= "LogIn"

        //configure progress dialog
        progressDialog= ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Logging In...")
        progressDialog.setCanceledOnTouchOutside(false)

        //init firebaseAuth
        firebaseAuth= FirebaseAuth.getInstance()
        checkUser()

        //handle click, open register activity
        binding.btnSignUpOrigional.setOnClickListener{
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        //handle click, begin login
        binding.btnLogIn.setOnClickListener{
        //before Logging in validate data
            validateData()
        }
        binding.btnToPhyLogin.setOnClickListener {
            intent= Intent(this,PhysicianActivity::class.java)
            startActivity(intent)
        }

    }

    private fun validateData() {
        //get Data
        email= binding.etMobNum.editText?.text.toString().trim()
        password= binding.etPassword.editText?.text.toString().trim()

        //validate data
//        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//            //invalid email format
//            binding.etMobNum.error = "Invalid email format"
//        }
         if(TextUtils.isEmpty(password)){
           //no password
            binding.etPassword.error="Please Enter the password"
        }
        else{
            //data is validated, begin login
            firebaseLogin()
        }
    }

    private fun firebaseLogin() {
        //show progress
        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                //login Successful
                progressDialog.dismiss()
                //get user info
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this,"Logged in as $email",Toast.LENGTH_SHORT).show()

                //open take test page
                startActivity(Intent(this,ActivityTakeTest::class.java))
                finish()
            }
            .addOnFailureListener { e->
                //login failed
                progressDialog.dismiss()
                Toast.makeText(this,"Login failed due to ${e.message}",Toast.LENGTH_SHORT).show()
            }
    }

    fun checkUser() {
        //if user is already logged in go to a profile activity
        //get current user
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser!=null){
            //user is already logged in
            startActivity(Intent(this,MyProfileActivity::class.java))
            finish()
        }
    }
}

