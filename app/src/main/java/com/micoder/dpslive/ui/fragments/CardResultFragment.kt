package com.micoder.dpslive.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.micoder.dpslive.R
import com.micoder.dpslive.databinding.FragmentCardResultBinding
import com.ramotion.foldingcell.FoldingCell

class CardResultFragment : Fragment(R.layout.fragment_card_result) {

    private lateinit var binding: FragmentCardResultBinding

    private lateinit var fc1: FoldingCell
    private lateinit var fc2: FoldingCell
    private lateinit var fc3: FoldingCell
    private lateinit var fc4: FoldingCell
    private lateinit var title1: FrameLayout
    private lateinit var title2: FrameLayout
    private lateinit var title3: FrameLayout
    private lateinit var title4: FrameLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCardResultBinding.bind(view)

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
        }
        fc1.setOnClickListener {
            fc1.toggle(false)
        }
    }


    private fun term2() {
        title2.setOnClickListener {
            fc2.toggle(false)
        }
        fc2.setOnClickListener {
            fc2.toggle(false)
        }
    }

    private fun term3() {
        title3.setOnClickListener {
            fc3.toggle(false)
        }
        fc3.setOnClickListener {
            fc3.toggle(false)
        }
    }

    private fun term4() {
        title4.setOnClickListener {
            fc4.toggle(false)
        }
        fc4.setOnClickListener {
            fc4.toggle(false)
        }
    }

}