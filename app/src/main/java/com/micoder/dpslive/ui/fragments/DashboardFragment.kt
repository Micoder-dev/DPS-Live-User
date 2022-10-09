package com.micoder.dpslive.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.micoder.dpslive.R
import com.micoder.dpslive.databinding.FragmentDashboardBinding
import java.util.*

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private var binding: FragmentDashboardBinding? = null

    private lateinit var currentDay: String

    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDashboardBinding.bind(view)

        firebaseDatabase = Firebase.database
        databaseReference = firebaseDatabase.reference

        binding!!.result.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_cardResultFragment)
        }

        currentDay()

        getPeriods()

    }

    private fun getPeriods() {

        val period1 = binding!!.period1
        val period2 = binding!!.period2
        val period3 = binding!!.period3
        val period4 = binding!!.period4
        val period5 = binding!!.period5
        val period6 = binding!!.period6
        val period7 = binding!!.period7

        databaseReference.child("timetable").get().addOnSuccessListener {
            if (it.exists()) {

                val t1 = it.child("timeslot1").value.toString()
                val t2 = it.child("timeslot2").value.toString()
                val t3 = it.child("timeslot3").value.toString()
                val t4 = it.child("timeslot4").value.toString()
                val t5 = it.child("timeslot5").value.toString()
                val t6 = it.child("timeslot6").value.toString()
                val t7 = it.child("timeslot7").value.toString()
                val t8 = it.child("timeslot8").value.toString()
                val t9 = it.child("timeslot9").value.toString()
                val t10 = it.child("timeslot10").value.toString()
                val t11 = it.child("timeslot11").value.toString()
                val t12 = it.child("timeslot12").value.toString()
                val t13 = it.child("timeslot13").value.toString()
                val t14 = it.child("timeslot14").value.toString()

                if (currentDay == "sun") {
                    val s1 = it.child("Sunday1").value.toString()
                    val s2 = it.child("Sunday2").value.toString()
                    val s3 = it.child("Sunday3").value.toString()
                    val s4 = it.child("Sunday4").value.toString()
                    val s5 = it.child("Sunday5").value.toString()
                    val s6 = it.child("Sunday6").value.toString()
                    val s7 = it.child("Sunday7").value.toString()

                    val st1 = it.child("Sunday1s").value.toString()
                    val st2 = it.child("Sunday2s").value.toString()
                    val st3 = it.child("Sunday3s").value.toString()
                    val st4 = it.child("Sunday4s").value.toString()
                    val st5 = it.child("Sunday5s").value.toString()
                    val st6 = it.child("Sunday6s").value.toString()
                    val st7 = it.child("Sunday7s").value.toString()

                    period1.text = "$t1 - $t2 \n $s1 \n ($st1)"
                    period2.text = "$t3 - $t4 \n $s2 \n ($st2)"
                    period3.text = "$t5 - $t6 \n $s3 \n ($st3)"
                    period4.text = "$t7 - $t8 \n $s4 \n ($st4)"
                    period5.text = "$t9 - $t10 \n $s5 \n ($st5)"
                    period6.text = "$t11 - $t12 \n $s6 \n ($st6)"
                    period7.text = "$t13 - $t14 \n $s7 \n ($st7)"
                }
                if (currentDay == "mon") {
                    val s1 = it.child("Monday1").value.toString()
                    val s2 = it.child("Monday2").value.toString()
                    val s3 = it.child("Monday3").value.toString()
                    val s4 = it.child("Monday4").value.toString()
                    val s5 = it.child("Monday5").value.toString()
                    val s6 = it.child("Monday6").value.toString()
                    val s7 = it.child("Monday7").value.toString()

                    val st1 = it.child("Monday1s").value.toString()
                    val st2 = it.child("Monday2s").value.toString()
                    val st3 = it.child("Monday3s").value.toString()
                    val st4 = it.child("Monday4s").value.toString()
                    val st5 = it.child("Monday5s").value.toString()
                    val st6 = it.child("Monday6s").value.toString()
                    val st7 = it.child("Monday7s").value.toString()

                    period1.text = "$t1 - $t2 \n $s1 \n ($st1)"
                    period2.text = "$t3 - $t4 \n $s2 \n ($st2)"
                    period3.text = "$t5 - $t6 \n $s3 \n ($st3)"
                    period4.text = "$t7 - $t8 \n $s4 \n ($st4)"
                    period5.text = "$t9 - $t10 \n $s5 \n ($st5)"
                    period6.text = "$t11 - $t12 \n $s6 \n ($st6)"
                    period7.text = "$t13 - $t14 \n $s7 \n ($st7)"
                }
                if (currentDay == "tue") {
                    val s1 = it.child("Tuesday1").value.toString()
                    val s2 = it.child("Tuesday2").value.toString()
                    val s3 = it.child("Tuesday3").value.toString()
                    val s4 = it.child("Tuesday4").value.toString()
                    val s5 = it.child("Tuesday5").value.toString()
                    val s6 = it.child("Tuesday6").value.toString()
                    val s7 = it.child("Tuesday7").value.toString()

                    val st1 = it.child("Tuesday1s").value.toString()
                    val st2 = it.child("Tuesday2s").value.toString()
                    val st3 = it.child("Tuesday3s").value.toString()
                    val st4 = it.child("Tuesday4s").value.toString()
                    val st5 = it.child("Tuesday5s").value.toString()
                    val st6 = it.child("Tuesday6s").value.toString()
                    val st7 = it.child("Tuesday7s").value.toString()

                    period1.text = "$t1 - $t2 \n $s1 \n ($st1)"
                    period2.text = "$t3 - $t4 \n $s2 \n ($st2)"
                    period3.text = "$t5 - $t6 \n $s3 \n ($st3)"
                    period4.text = "$t7 - $t8 \n $s4 \n ($st4)"
                    period5.text = "$t9 - $t10 \n $s5 \n ($st5)"
                    period6.text = "$t11 - $t12 \n $s6 \n ($st6)"
                    period7.text = "$t13 - $t14 \n $s7 \n ($st7)"
                }
                if (currentDay == "wed") {
                    val s1 = it.child("Wednesday1").value.toString()
                    val s2 = it.child("Wednesday2").value.toString()
                    val s3 = it.child("Wednesday3").value.toString()
                    val s4 = it.child("Wednesday4").value.toString()
                    val s5 = it.child("Wednesday5").value.toString()
                    val s6 = it.child("Wednesday6").value.toString()
                    val s7 = it.child("Wednesday7").value.toString()

                    val st1 = it.child("Wednesday1s").value.toString()
                    val st2 = it.child("Wednesday2s").value.toString()
                    val st3 = it.child("Wednesday3s").value.toString()
                    val st4 = it.child("Wednesday4s").value.toString()
                    val st5 = it.child("Wednesday5s").value.toString()
                    val st6 = it.child("Wednesday6s").value.toString()
                    val st7 = it.child("Wednesday7").value.toString()

                    period1.text = "$t1 - $t2 \n $s1 \n ($st1)"
                    period2.text = "$t3 - $t4 \n $s2 \n ($st2)"
                    period3.text = "$t5 - $t6 \n $s3 \n ($st3)"
                    period4.text = "$t7 - $t8 \n $s4 \n ($st4)"
                    period5.text = "$t9 - $t10 \n $s5 \n ($st5)"
                    period6.text = "$t11 - $t12 \n $s6 \n ($st6)"
                    period7.text = "$t13 - $t14 \n $s7 \n ($st7)"
                }
                if (currentDay == "thu") {
                    val s1 = it.child("Thursday1").value.toString()
                    val s2 = it.child("Thursday2").value.toString()
                    val s3 = it.child("Thursday3").value.toString()
                    val s4 = it.child("Thursday4").value.toString()
                    val s5 = it.child("Thursday5").value.toString()
                    val s6 = it.child("Thursday6").value.toString()
                    val s7 = it.child("Thursday7").value.toString()

                    val st1 = it.child("Thursday1s").value.toString()
                    val st2 = it.child("Thursday2s").value.toString()
                    val st3 = it.child("Thursday3s").value.toString()
                    val st4 = it.child("Thursday4s").value.toString()
                    val st5 = it.child("Thursday5s").value.toString()
                    val st6 = it.child("Thursday6s").value.toString()
                    val st7 = it.child("Thursday7s").value.toString()

                    period1.text = "$t1 - $t2 \n $s1 \n ($st1)"
                    period2.text = "$t3 - $t4 \n $s2 \n ($st2)"
                    period3.text = "$t5 - $t6 \n $s3 \n ($st3)"
                    period4.text = "$t7 - $t8 \n $s4 \n ($st4)"
                    period5.text = "$t9 - $t10 \n $s5 \n ($st5)"
                    period6.text = "$t11 - $t12 \n $s6 \n ($st6)"
                    period7.text = "$t13 - $t14 \n $s7 \n ($st7)"
                }
                if (currentDay == "fri") {
                    val s1 = it.child("Friday1").value.toString()
                    val s2 = it.child("Friday2").value.toString()
                    val s3 = it.child("Friday3").value.toString()
                    val s4 = it.child("Friday4").value.toString()
                    val s5 = it.child("Friday5").value.toString()
                    val s6 = it.child("Friday6").value.toString()
                    val s7 = it.child("Friday7").value.toString()

                    val st1 = it.child("Friday1s").value.toString()
                    val st2 = it.child("Friday2s").value.toString()
                    val st3 = it.child("Friday3s").value.toString()
                    val st4 = it.child("Friday4s").value.toString()
                    val st5 = it.child("Friday5s").value.toString()
                    val st6 = it.child("Friday6s").value.toString()
                    val st7 = it.child("Friday7s").value.toString()

                    period1.text = "$t1 - $t2 \n $s1 \n ($st1)"
                    period2.text = "$t3 - $t4 \n $s2 \n ($st2)"
                    period3.text = "$t5 - $t6 \n $s3 \n ($st3)"
                    period4.text = "$t7 - $t8 \n $s4 \n ($st4)"
                    period5.text = "$t9 - $t10 \n $s5 \n ($st5)"
                    period6.text = "$t11 - $t12 \n $s6 \n ($st6)"
                    period7.text = "$t13 - $t14 \n $s7 \n ($st7)"
                }
                if (currentDay == "sat") {
                    val s1 = it.child("Saturday1").value.toString()
                    val s2 = it.child("Saturday2").value.toString()
                    val s3 = it.child("Saturday3").value.toString()
                    val s4 = it.child("Saturday4").value.toString()
                    val s5 = it.child("Saturday5").value.toString()
                    val s6 = it.child("Saturday6").value.toString()
                    val s7 = it.child("Saturday7").value.toString()

                    val st1 = it.child("Saturday1s").value.toString()
                    val st2 = it.child("Saturday2s").value.toString()
                    val st3 = it.child("Saturday3s").value.toString()
                    val st4 = it.child("Saturday4s").value.toString()
                    val st5 = it.child("Saturday5s").value.toString()
                    val st6 = it.child("Saturday6s").value.toString()
                    val st7 = it.child("Saturday7s").value.toString()

                    period1.text = "$t1 - $t2 \n $s1 \n ($st1)"
                    period2.text = "$t3 - $t4 \n $s2 \n ($st2)"
                    period3.text = "$t5 - $t6 \n $s3 \n ($st3)"
                    period4.text = "$t7 - $t8 \n $s4 \n ($st4)"
                    period5.text = "$t9 - $t10 \n $s5 \n ($st5)"
                    period6.text = "$t11 - $t12 \n $s6 \n ($st6)"
                    period7.text = "$t13 - $t14 \n $s7 \n ($st7)"
                }

            }else{
                Toast.makeText(context,"No Time Table Updated", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            val error = it.message.toString()
            Toast.makeText(context,error, Toast.LENGTH_SHORT).show()
        }
    }


    fun currentDay() {
        val day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        when (day) {
            1 -> currentDay = "sun"
            2 -> currentDay = "mon"
            3 -> currentDay = "tue"
            4 -> currentDay = "wed"
            5 -> currentDay = "thu"
            6 -> currentDay = "fri"
            7 -> currentDay = "sat"
            else -> "Time has stopped"
        }
    }

}