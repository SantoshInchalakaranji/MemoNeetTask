package com.prplmnstr.memoneettask.view.capsule

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.prplmnstr.memoneettask.R
import com.prplmnstr.memoneettask.databinding.FragmentVideoScreenBinding
import com.prplmnstr.memoneettask.viewmodel.CapsuleViewModel

class VideoScreenFragment : Fragment() {

    private lateinit var binding: FragmentVideoScreenBinding
    private val capsuleViewModel: CapsuleViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentVideoScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val videoPath = "android.resource://" + "com.prplmnstr.memoneettask" + "/" + R.raw.video
        binding.videoView.setVideoURI(Uri.parse(videoPath))
        val mediaController = android.widget.MediaController(context)
        mediaController.setAnchorView(binding.videoView)
        binding.videoView.setMediaController(mediaController)
        binding.videoView.requestFocus()
        mediaController.setMediaPlayer(binding.videoView)
        binding.videoView.start()


        //1 is the position for note fragment
        binding.bottomCard.setOnClickListener {
            val viewPager: ViewPager2? = activity?.findViewById(R.id.view_pager)
            viewPager?.currentItem = 1
        }

    }

    override fun onPause() {
        super.onPause()
        binding.videoView.pause()
        capsuleViewModel.stopPosition = binding.videoView.currentPosition

    }

    override fun onResume() {
        super.onResume()
        binding.videoView.seekTo(capsuleViewModel.stopPosition)
        binding.videoView.start()
    }


}