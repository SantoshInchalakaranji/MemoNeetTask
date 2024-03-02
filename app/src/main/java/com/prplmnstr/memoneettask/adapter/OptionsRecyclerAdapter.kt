package com.prplmnstr.memoneettask.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.prplmnstr.memoneettask.R
import com.prplmnstr.memoneettask.databinding.OptionRvItemBinding

class OptionsRecyclerAdapter(
    private val selectListener: (Int) -> Unit
) : RecyclerView.Adapter<OptionsRecyclerAdapter.ViewHolder>() {


    private val options = ArrayList<String>()
    private var selectedItemPosition: Int = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: OptionRvItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.option_rv_item, parent, false)
        return ViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return options.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(options[position], selectListener, position)
    }

    fun setList(items: List<String>) {
        options.clear()
        options.addAll(items)

    }

    fun setSelection(position: Int) {
        selectedItemPosition = position

    }

    inner class ViewHolder(val binding: OptionRvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(option: String, selectListener: (Int) -> Unit, position: Int) {
            binding.textview.text = option


            if (selectedItemPosition == position) {

                binding.textview.setBackgroundResource(R.drawable.option_selected_bg)
            } else {

                binding.textview.setBackgroundResource(R.drawable.option_bg)
            }
            binding.textview.setOnClickListener {
                // Reset background of previously selected item
                val previousSelectedItemPosition = selectedItemPosition
                selectedItemPosition = position
                notifyItemChanged(previousSelectedItemPosition)
                it.setBackgroundResource(R.drawable.option_selected_bg)

                // Notify listener about item selection
                selectListener(position)

            }
        }


    }

}