package com.prplmnstr.memoneettask.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.prplmnstr.memoneettask.R
import com.prplmnstr.memoneettask.databinding.ActivityMainBinding
import com.prplmnstr.memoneettask.viewmodel.CapsuleViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var capsuleViewModel: CapsuleViewModel
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        capsuleViewModel = ViewModelProvider(this)[CapsuleViewModel::class.java]
        navController = findNavController(R.id.navHostFragment)
    }


}