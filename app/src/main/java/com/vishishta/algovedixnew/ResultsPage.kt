package com.vishishta.algovedixnew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

class ResultsPage : AppCompatActivity() {

    lateinit var progressBar1: ProgressBar
    lateinit var progressBar2: ProgressBar
    lateinit var progressBar3: ProgressBar
    lateinit var textView1: TextView
    lateinit var textView2: TextView
    lateinit var textView3: TextView
    lateinit var rectangle_3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.results_page)

        val vaat =intent.getIntExtra("vaat",0)
        val pitt =intent.getIntExtra("pitt",0)
        val cough =intent.getIntExtra("cough",0)

        progressBar1= findViewById(R.id.vata_bar)
        progressBar2= findViewById(R.id.pitta_bar)
        progressBar3= findViewById(R.id.cough_bar)
        textView1= findViewById(R.id.textView1)
        textView2= findViewById(R.id.textView2)
        textView3= findViewById(R.id.textView3)
        rectangle_3 = findViewById(R.id.rectangle_3)

        progressBar1.progress= vaat
        progressBar2.progress= pitt
        progressBar3.progress= cough

        progressBar1.max= 15
        progressBar2.max=15
        progressBar3.max=15

        textView1.text = (vaat*100/15).toString()+"%"
        textView2.text= (pitt*100/15).toString()+"%"
        textView3.text= (cough*100/15).toString()+"%"

        rectangle_3.setOnClickListener {
            intent = Intent(this@ResultsPage, MyProfileActivity::class.java)
            intent.putExtra("Vata", vaat)
            intent.putExtra("Pitt",pitt)
            intent.putExtra("cough",cough)
            startActivity(intent)
        }

    }


}