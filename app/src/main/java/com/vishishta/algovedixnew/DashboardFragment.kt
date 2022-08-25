package com.vishishta.algovedixnew

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.vishishta.algovedixnew.databinding.FragmentDashboardBinding
import java.util.*

class DashboardFragment : Fragment() {



    private var _binding: FragmentDashboardBinding? = null
    private val binding : FragmentDashboardBinding get() = _binding!!
    lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDashboardBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        database = Firebase.database.reference

        setupCalendar()
        onCliclkListner()
    }

    private fun onCliclkListner() {
        binding.monBtn.setOnClickListener {
            callDatabase(kpd = StringKeys.KAPHA_PITH_DITE, day = "Monday")
        }
        binding.tueBtn.setOnClickListener {
            callDatabase( kpd= StringKeys.KAPHA_PITH_DITE, "Tuesday")
        }
        binding.wedBtn.setOnClickListener {
            callDatabase( kpd= StringKeys.KAPHA_PITH_DITE, "Wednesday")
        }
        binding.thuBtn.setOnClickListener {
            callDatabase( kpd= StringKeys.KAPHA_PITH_DITE, "Thursday")
        }
        binding.friBtn.setOnClickListener {
            callDatabase( kpd= StringKeys.KAPHA_PITH_DITE, "Friday")
        }
        binding.satBtn.setOnClickListener {
            callDatabase( kpd= StringKeys.KAPHA_PITH_DITE, "Saturday")
        }
        binding.sunBtn.setOnClickListener {
            callDatabase( kpd= StringKeys.KAPHA_PITH_DITE, "Sunday")
        }
    }

    private fun setupCalendar() {
        val calendar: Calendar = Calendar.getInstance()
        val day: Int = calendar.get(Calendar.DAY_OF_WEEK)
        var dayString = "monday"
        when (day) {
            Calendar.SUNDAY -> { dayString = "Sunday" }
            Calendar.MONDAY -> { dayString = "Monday" }
            Calendar.TUESDAY -> { dayString = "Tuesday" }
            Calendar.WEDNESDAY -> {dayString = "Wednesday"}
            Calendar.THURSDAY -> {dayString = "Thursday"}
            Calendar.FRIDAY -> {dayString = "Friday"}
            Calendar.SATURDAY -> {dayString = "Saturday"}
        }
        callDatabase(StringKeys.KAPHA_PITH_DITE, dayString)

    }

    private fun callDatabase(kpd: String, day: String) {
        database.child(kpd).child(day).addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("firebaseData", snapshot.toString())
                updateUI(
                    snapshot.child("Breakfast(8:30am)").value.toString(),
                    snapshot.child("Dinner(7:30pm)").value.toString(),
                    snapshot.child("Early Morning").value.toString(),
                    snapshot.child("Lunch(1:00pm)").value.toString(),
                    snapshot.child("Snacks(4:00pm)").value.toString()
                )
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
    }

    private fun updateUI(breakfast: String, dinner: String, morning: String,lunch: String, snacks: String) {
        binding.dietBreakfast.text = breakfast
        binding.dietDinner.text= dinner
        binding.dietLunch.text = lunch
        binding.dietMorning.text= morning
        binding.dietSnacks.text = snacks
    }
}