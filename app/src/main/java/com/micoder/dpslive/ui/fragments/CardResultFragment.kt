package com.micoder.dpslive.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.micoder.dpslive.R
import com.micoder.dpslive.databinding.FragmentCardResultBinding
import com.ramotion.foldingcell.FoldingCell

class CardResultFragment : Fragment(R.layout.fragment_card_result) {

    private lateinit var binding: FragmentCardResultBinding

    // firebase auth
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    private lateinit var fc1: FoldingCell
    private lateinit var fc2: FoldingCell
    private lateinit var fc3: FoldingCell
    private lateinit var fc4: FoldingCell
    private lateinit var title1: FrameLayout
    private lateinit var title2: FrameLayout
    private lateinit var title3: FrameLayout
    private lateinit var title4: FrameLayout

    private lateinit var uid: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCardResultBinding.bind(view)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = Firebase.database
        databaseReference = firebaseDatabase.reference
        // get current user
        val firebaseUser = firebaseAuth.currentUser
        uid = firebaseUser!!.uid

        fc1 = binding.term1fc
        fc2 = binding.term2fc
        fc3 = binding.term3fc
        fc4 = binding.term4fc
        title1 = binding.title1
        title2 = binding.title2
        title3 = binding.title3
        title4 = binding.title4

        term1()
        term2()
        term3()
        term4()

    }

    private fun term1() {
        title1.setOnClickListener {
            fc1.toggle(false)
            retrieveFirstTermMarks()
        }
        fc1.setOnClickListener {
            fc1.toggle(false)
        }
    }
    private fun term2() {
        title2.setOnClickListener {
            fc2.toggle(false)
            retrieveSecondTermMarks()
        }
        fc2.setOnClickListener {
            fc2.toggle(false)
        }
    }
    private fun term3() {
        title3.setOnClickListener {
            fc3.toggle(false)
            retrieveThirdTermMarks()
        }
        fc3.setOnClickListener {
            fc3.toggle(false)
        }
    }
    private fun term4() {
        title4.setOnClickListener {
            fc4.toggle(false)
            retrieveFourthTermMarks()
        }
        fc4.setOnClickListener {
            fc4.toggle(false)
        }
    }



    private fun retrieveFirstTermMarks() {
        databaseReference.child("users").child(uid).child("results").child("Term1").get().addOnSuccessListener {
            if (it.exists()){
                val english = it.child("english").value.toString()
                val maths = it.child("maths").value.toString()
                val cs = it.child("cs").value.toString()
                val practical = it.child("practical").value.toString()
                binding.tvEnglishT1.text = english
                binding.tvMathsT1.text = maths
                binding.tvCST1.text = cs
                binding.tvPracticalT1.text = practical

                // Avg
                val total = english.toInt() + maths.toInt() + cs.toInt() + practical.toInt()
                val totalSize = it.childrenCount
                val average: Float = total.toFloat() / totalSize
                binding.tvAvg1.text = average.toString() + "%"

                val pofEnglish = binding.pofEnglishT1
                val pofMaths = binding.pofMathsT1
                val pofCs = binding.pofCST1
                val pofPractical = binding.pofPracticalT1
                if (english.toInt() >= 35) {
                    pofEnglish.setText("Pass")
                    pofEnglish.setTextColor(resources.getColor(R.color.green,null))
                } else {
                    pofEnglish.setText("Fail")
                    pofEnglish.setTextColor(resources.getColor(R.color.red,null))
                }
                if (maths.toInt() >= 35) {
                    pofMaths.setText("Pass")
                    pofMaths.setTextColor(resources.getColor(R.color.green,null))
                } else {
                    pofMaths.setText("Fail")
                    pofMaths.setTextColor(resources.getColor(R.color.red,null))
                }
                if (cs.toInt() >= 35) {
                    pofCs.setText("Pass")
                    pofCs.setTextColor(resources.getColor(R.color.green,null))
                } else {
                    pofCs.setText("Fail")
                    pofCs.setTextColor(resources.getColor(R.color.red,null))
                }
                if (practical.toInt() >= 35) {
                    pofPractical.setText("Pass")
                    pofPractical.setTextColor(resources.getColor(R.color.green,null))
                } else {
                    pofPractical.setText("Fail")
                    pofPractical.setTextColor(resources.getColor(R.color.red,null))
                }

            }else{
                Toast.makeText(context,"No Marks Updated Yet", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            val error = it.message.toString()
            Toast.makeText(context,error, Toast.LENGTH_SHORT).show()
        }
    }
    private fun retrieveSecondTermMarks() {
        databaseReference.child("users").child(uid).child("results").child("Term2").get().addOnSuccessListener {
            if (it.exists()){
                val english = it.child("english").value.toString()
                val maths = it.child("maths").value.toString()
                val cs = it.child("cs").value.toString()
                val practical = it.child("practical").value.toString()
                binding.tvEnglishT2.text = english
                binding.tvMathsT2.text = maths
                binding.tvCST2.text = cs
                binding.tvPracticalT2.text = practical

                // Avg
                val total = english.toInt() + maths.toInt() + cs.toInt() + practical.toInt()
                val totalSize = it.childrenCount
                val average: Float = total.toFloat() / totalSize
                binding.tvAvg2.text = average.toString() + "%"

                val pofEnglish = binding.pofEnglishT2
                val pofMaths = binding.pofMathsT2
                val pofCs = binding.pofCST2
                val pofPractical = binding.pofPracticalT2
                if (english.toInt() >= 35) {
                    pofEnglish.setText("Pass")
                    pofEnglish.setTextColor(resources.getColor(R.color.green,null))
                } else {
                    pofEnglish.setText("Fail")
                    pofEnglish.setTextColor(resources.getColor(R.color.red,null))
                }
                if (maths.toInt() >= 35) {
                    pofMaths.setText("Pass")
                    pofMaths.setTextColor(resources.getColor(R.color.green,null))
                } else {
                    pofMaths.setText("Fail")
                    pofMaths.setTextColor(resources.getColor(R.color.red,null))
                }
                if (cs.toInt() >= 35) {
                    pofCs.setText("Pass")
                    pofCs.setTextColor(resources.getColor(R.color.green,null))
                } else {
                    pofCs.setText("Fail")
                    pofCs.setTextColor(resources.getColor(R.color.red,null))
                }
                if (practical.toInt() >= 35) {
                    pofPractical.setText("Pass")
                    pofPractical.setTextColor(resources.getColor(R.color.green,null))
                } else {
                    pofPractical.setText("Fail")
                    pofPractical.setTextColor(resources.getColor(R.color.red,null))
                }
            }else{
                Toast.makeText(context,"No Marks Updated Yet", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(context,"Failed to load data", Toast.LENGTH_SHORT).show()
        }
    }
    private fun retrieveThirdTermMarks() {
        databaseReference.child("users").child(uid).child("results").child("Term3").get().addOnSuccessListener {
            if (it.exists()){
                val english = it.child("english").value.toString()
                val maths = it.child("maths").value.toString()
                val cs = it.child("cs").value.toString()
                val practical = it.child("practical").value.toString()
                binding.tvEnglishT3.text = english
                binding.tvMathsT3.text = maths
                binding.tvCST3.text = cs
                binding.tvPracticalT3.text = practical

                // Avg
                val total = english.toInt() + maths.toInt() + cs.toInt() + practical.toInt()
                val totalSize = it.childrenCount
                val average: Float = total.toFloat() / totalSize
                binding.tvAvg3.text = average.toString() + "%"

                val pofEnglish = binding.pofEnglishT3
                val pofMaths = binding.pofMathsT3
                val pofCs = binding.pofCST3
                val pofPractical = binding.pofPracticalT3
                if (english.toInt() >= 35) {
                    pofEnglish.setText("Pass")
                    pofEnglish.setTextColor(resources.getColor(R.color.green,null))
                } else {
                    pofEnglish.setText("Fail")
                    pofEnglish.setTextColor(resources.getColor(R.color.red,null))
                }
                if (maths.toInt() >= 35) {
                    pofMaths.setText("Pass")
                    pofMaths.setTextColor(resources.getColor(R.color.green,null))
                } else {
                    pofMaths.setText("Fail")
                    pofMaths.setTextColor(resources.getColor(R.color.red,null))
                }
                if (cs.toInt() >= 35) {
                    pofCs.setText("Pass")
                    pofCs.setTextColor(resources.getColor(R.color.green,null))
                } else {
                    pofCs.setText("Fail")
                    pofCs.setTextColor(resources.getColor(R.color.red,null))
                }
                if (practical.toInt() >= 35) {
                    pofPractical.setText("Pass")
                    pofPractical.setTextColor(resources.getColor(R.color.green,null))
                } else {
                    pofPractical.setText("Fail")
                    pofPractical.setTextColor(resources.getColor(R.color.red,null))
                }
            }else{
                Toast.makeText(context,"No Marks Updated Yet", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(context,"Failed to load data", Toast.LENGTH_SHORT).show()
        }
    }
    private fun retrieveFourthTermMarks() {
        databaseReference.child("users").child(uid).child("results").child("Term4").get().addOnSuccessListener {
            if (it.exists()){
                val english = it.child("english").value.toString()
                val maths = it.child("maths").value.toString()
                val cs = it.child("cs").value.toString()
                val practical = it.child("practical").value.toString()
                binding.tvEnglishT4.text = english
                binding.tvMathsT4.text = maths
                binding.tvCST4.text = cs
                binding.tvPracticalT4.text = practical

                // Avg
                val total = english.toInt() + maths.toInt() + cs.toInt() + practical.toInt()
                val totalSize = it.childrenCount
                val average: Float = total.toFloat() / totalSize
                binding.tvAvg4.text = average.toString() + "%"

                val pofEnglish = binding.pofEnglishT4
                val pofMaths = binding.pofMathsT4
                val pofCs = binding.pofCST4
                val pofPractical = binding.pofPracticalT4
                if (english.toInt() >= 35) {
                    pofEnglish.setText("Pass")
                    pofEnglish.setTextColor(resources.getColor(R.color.green,null))
                } else {
                    pofEnglish.setText("Fail")
                    pofEnglish.setTextColor(resources.getColor(R.color.red,null))
                }
                if (maths.toInt() >= 35) {
                    pofMaths.setText("Pass")
                    pofMaths.setTextColor(resources.getColor(R.color.green,null))
                } else {
                    pofMaths.setText("Fail")
                    pofMaths.setTextColor(resources.getColor(R.color.red,null))
                }
                if (cs.toInt() >= 35) {
                    pofCs.setText("Pass")
                    pofCs.setTextColor(resources.getColor(R.color.green,null))
                } else {
                    pofCs.setText("Fail")
                    pofCs.setTextColor(resources.getColor(R.color.red,null))
                }
                if (practical.toInt() >= 35) {
                    pofPractical.setText("Pass")
                    pofPractical.setTextColor(resources.getColor(R.color.green,null))
                } else {
                    pofPractical.setText("Fail")
                    pofPractical.setTextColor(resources.getColor(R.color.red,null))
                }
            }else{
                Toast.makeText(context,"No Marks Updated Yet", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(context,"Failed to load data", Toast.LENGTH_SHORT).show()
        }
    }

}