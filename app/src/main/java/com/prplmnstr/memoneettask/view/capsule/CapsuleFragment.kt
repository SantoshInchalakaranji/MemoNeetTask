package com.prplmnstr.memoneettask.view.capsule

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.prplmnstr.memoneettask.R
import com.prplmnstr.memoneettask.adapter.FragmentPageAdapter
import com.prplmnstr.memoneettask.databinding.FragmentCapsuleBinding
import com.prplmnstr.memoneettask.viewmodel.CapsuleViewModel


class CapsuleFragment : Fragment() {

    private lateinit var binding: FragmentCapsuleBinding
    private lateinit var adapter: FragmentPageAdapter

    private var secondsRemaining: Int = 600 // 10 minutes
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var runnable: Runnable

    private val capsuleViewModel: CapsuleViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCapsuleBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        adapter = FragmentPageAdapter(childFragmentManager, lifecycle)
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Video"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Note"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Quiz"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Result"))

        binding.viewPager.adapter = adapter

        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    if (tab.position == 2) {
                        binding.appbar.background = ColorDrawable(
                            ContextCompat.getColor(
                                context!!,
                                R.color.quiz_background
                            )
                        )
                        activity?.window?.statusBarColor =
                            ContextCompat.getColor(requireActivity(), R.color.quiz_background)
                        binding.tabLayout.visibility = View.GONE
                    } else {
                        binding.appbar.background =
                            ColorDrawable(ContextCompat.getColor(context!!, R.color.white))
                        activity?.window?.statusBarColor =
                            ContextCompat.getColor(requireActivity(), R.color.white)
                        binding.tabLayout.visibility = View.VISIBLE
                    }
                    binding.viewPager.currentItem = tab.position
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        binding.viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        startTimer()

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {

            findNavController().navigateUp()

            true
        }

    }


    private fun startTimer() {
        runnable = object : Runnable {
            override fun run() {
                if (secondsRemaining > 0) {
                    secondsRemaining--
                    binding.timerText.text =
                        capsuleViewModel.formatSecondsToTime(secondsRemaining) + " min"
                    handler.postDelayed(this, 1000) // Delay 1 second
                } else {
                    Toast.makeText(context, "Time Up! ", Toast.LENGTH_SHORT).show()
                    binding.timerText.text = "00:00"
                    handler.removeCallbacks(runnable)
                    findNavController().navigateUp()
                }
            }
        }
        handler.postDelayed(runnable, 1000) // Start immediately
    }

    override fun onDestroy() {
        super.onDestroy()
        // Remove callbacks to prevent memory leaks
        handler.removeCallbacks(runnable)
    }


}