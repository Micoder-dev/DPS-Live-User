package com.micoder.dpslive.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.micoder.dpslive.R
import com.micoder.dpslive.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private var binding: FragmentDashboardBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDashboardBinding.bind(view)

        binding!!.result.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_cardResultFragment)
        }
    }

}