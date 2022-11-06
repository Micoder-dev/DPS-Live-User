package com.micoder.dpslive.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.micoder.dpslive.R
import com.micoder.dpslive.databinding.FragmentAttendanceBinding

class AttendanceFragment : Fragment(R.layout.fragment_attendance) {

    private lateinit var binding: FragmentAttendanceBinding

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAttendanceBinding.bind(view)


        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = Firebase.database
        databaseReference = firebaseDatabase.reference

        val firebaseUser = firebaseAuth.currentUser
        databaseReference.child("users").child(firebaseUser!!.uid).get().addOnSuccessListener {
            if (it.exists()) {
                if (it.child("attendance").value.toString() == "null") {
                    binding.attendanceTV.text = "Not yet updated"
                }
                else {
                    val percentage = it.child("attendance").value.toString()

                    binding.attendanceTV.text = "$percentage %"
                }
            }
        }


    }

}