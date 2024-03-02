package com.prplmnstr.memoneettask.view.capsule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.prplmnstr.memoneettask.R
import com.prplmnstr.memoneettask.adapter.OptionsRecyclerAdapter
import com.prplmnstr.memoneettask.databinding.BottomSheetDialogBinding
import com.prplmnstr.memoneettask.databinding.FragmentQuizBinding
import com.prplmnstr.memoneettask.viewmodel.CapsuleViewModel


class QuizScreenFragment : Fragment() {

    private lateinit var binding: FragmentQuizBinding
    private lateinit var adapter: OptionsRecyclerAdapter
    private val capsuleViewModel: CapsuleViewModel by activityViewModels()
    private var currentQuestion = 0

    private lateinit var dialogBinding: BottomSheetDialogBinding
    private lateinit var bottomSheet: BottomSheetDialog


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initializeRecycler()
        initializeDialog()
        setQuestion(currentQuestion)


        val questionList = capsuleViewModel.questionList

        binding.nextButton.setOnClickListener {
            if (currentQuestion == questionList.size - 1) {
                val viewPager: ViewPager2? = activity?.findViewById(R.id.view_pager)
                viewPager?.currentItem = 3

            } else {
                currentQuestion++

                setQuestion(currentQuestion)
                if (currentQuestion > 0) {
                    binding.previousButton.isEnabled = true
                }

            }

        }
        binding.previousButton.isEnabled = false
        binding.previousButton.setOnClickListener {


            if (currentQuestion <= 0) {
                it.isEnabled = false
            } else {
                currentQuestion--
                it.isEnabled = true
                setQuestion(currentQuestion)
            }
        }

        binding.checkAnsButton.setOnClickListener {
            checkAnswer()
        }

    }

    private fun checkAnswer() {

        val selectedOption = capsuleViewModel.ansList.value!![currentQuestion]
        val question = capsuleViewModel.questionList[currentQuestion]

        if (selectedOption == -1) {
            Toast.makeText(context, "Please select an option", Toast.LENGTH_SHORT).show()
        } else if (selectedOption == question.ans) {
            dialogBinding.answerImage.setImageResource(R.drawable.amazing)
            bottomSheet.show()

        } else {
            dialogBinding.answerImage.setImageResource(R.drawable.oops)
            dialogBinding.answerText.text = "Correct Answer: ${question.options[question.ans]}"
            bottomSheet.show()
        }


    }

    private fun initializeDialog() {
        dialogBinding = BottomSheetDialogBinding.inflate(layoutInflater)
        bottomSheet = BottomSheetDialog(requireContext())
        bottomSheet.setContentView(dialogBinding.root)
        bottomSheet.setCancelable(true)

    }

    private fun setQuestion(questionId: Int) {
        val questionList = capsuleViewModel.questionList
        binding.questionTv.text = "Q${(questionId + 1)}.${questionList[questionId].question}"
        binding.progressText.text = "Question ${(questionId + 1)}/${questionList.size}"
        binding.progressBar.progress = ((questionId + 1) / questionList.size) * 100
        setOptions(questionId)
    }

    private fun initializeRecycler() {
        binding.optionsRecycler.layoutManager = LinearLayoutManager(requireContext())
        adapter = OptionsRecyclerAdapter { selectedOption: Int -> itemSelected(selectedOption) }
        binding.optionsRecycler.adapter = adapter

    }

    private fun setOptions(questionId: Int) {
        val selectedPosition = capsuleViewModel.ansList.value!![questionId]
        adapter.setList(capsuleViewModel.questionList[questionId].options)
        if (selectedPosition != -1) {
            adapter.setSelection(selectedPosition)
        } else {
            adapter.setSelection(RecyclerView.NO_POSITION)
        }

        adapter.notifyDataSetChanged()
    }

    private fun itemSelected(selectedOption: Int) {
        val newAnsList = capsuleViewModel.ansList.value!!.toMutableList()
        newAnsList[currentQuestion] = selectedOption
        capsuleViewModel.ansList.value = newAnsList

    }
}