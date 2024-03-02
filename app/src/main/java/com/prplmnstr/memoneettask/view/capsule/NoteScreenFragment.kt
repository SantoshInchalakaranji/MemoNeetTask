package com.prplmnstr.memoneettask.view.capsule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.prplmnstr.memoneettask.R
import com.prplmnstr.memoneettask.databinding.FragmentNoteScreenBinding


class NoteScreenFragment : Fragment() {

    private lateinit var binding: FragmentNoteScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bottomCard.setOnClickListener {
            val viewPager: ViewPager2? = activity?.findViewById(R.id.view_pager)
            viewPager?.currentItem = 2
        }
    }


}