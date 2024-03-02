package com.prplmnstr.memoneettask.view.capsule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.prplmnstr.memoneettask.Utils.Constants.Companion.ANSWERED
import com.prplmnstr.memoneettask.Utils.Constants.Companion.CORRECT_ANSWER
import com.prplmnstr.memoneettask.Utils.Constants.Companion.SCORE
import com.prplmnstr.memoneettask.Utils.Constants.Companion.UNANSWERED
import com.prplmnstr.memoneettask.Utils.Constants.Companion.WRONG_ANSWER
import com.prplmnstr.memoneettask.databinding.FragmentResultScreenBinding
import com.prplmnstr.memoneettask.viewmodel.CapsuleViewModel


class ResultScreenFragment : Fragment() {

    private lateinit var binding: FragmentResultScreenBinding
    private val capsuleViewModel: CapsuleViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultScreenBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        capsuleViewModel.ansList.observe(viewLifecycleOwner, Observer {
            val map = capsuleViewModel.getResult()

            binding.totalNumber.text = "${capsuleViewModel.questionList.size}"
            binding.answeredNumber.text = "${map.get(ANSWERED)}"
            binding.unansweredNumber.text = "${map.get(UNANSWERED)}"
            binding.correctNumber.text = "${map.get(CORRECT_ANSWER)}"
            binding.wrongNumber.text = "${map.get(WRONG_ANSWER)}"
            binding.scoreNumber.text = "${map.get(SCORE)}"

        })


    }
}