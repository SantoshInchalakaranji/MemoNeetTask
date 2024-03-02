package com.prplmnstr.memoneettask.view.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.prplmnstr.memoneettask.R
import com.prplmnstr.memoneettask.databinding.FragmentHomeScreenBinding


class HomeScreenFragment : Fragment() {

    private lateinit var binding: FragmentHomeScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bloodCard.setOnClickListener {
            findNavController().navigate(R.id.action_homeScreenFragment_to_capsuleFragment)
        }

    }


}
