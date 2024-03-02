package com.prplmnstr.memoneettask.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.prplmnstr.memoneettask.view.capsule.NoteScreenFragment
import com.prplmnstr.memoneettask.view.capsule.QuizScreenFragment
import com.prplmnstr.memoneettask.view.capsule.ResultScreenFragment
import com.prplmnstr.memoneettask.view.capsule.VideoScreenFragment

class FragmentPageAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> VideoScreenFragment()
            1 -> NoteScreenFragment()
            2 -> QuizScreenFragment()
            3 -> ResultScreenFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }

    }

}