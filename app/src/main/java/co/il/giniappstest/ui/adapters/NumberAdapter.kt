package co.il.giniappstest.ui.adapters

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.il.giniappstest.R
import co.il.giniappstest.data.models.Number
import co.il.giniappstest.databinding.ItemEqualBinding
import co.il.giniappstest.databinding.ItemRegularBinding
import co.il.giniappstest.other.Constants.EQUAL
import co.il.giniappstest.other.Constants.REGULAR

class NumberAdapter(private val numbers: List<Int>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

   private var currentNumber: Int = numbers[0]

    inner class NumberViewHolder(val binding: ItemRegularBinding) : RecyclerView.ViewHolder(binding.root)

    inner class EqualNumberViewHolder(val binding: ItemEqualBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemViewType(position: Int): Int {
        val currentNum = numbers[position]
        Log.d("NUMBER_CHECK", "currentNum = $currentNum")


        return if (currentNum < 0 && numbers.contains(0 + (currentNum * -1))) {
            Log.d("NUMBER_CHECK", "below 0 arrayContains ${0 + currentNum} = ${numbers.contains(0 + currentNum)}")
            EQUAL
        } else if (numbers.contains(0 - currentNum)) {
            EQUAL
        } else {
            Log.d("NUMBER_CHECK", "above 0 arrayContains ${0 - currentNum} = ${numbers.contains(0 - currentNum)}")
            REGULAR
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == EQUAL) {
            val view = ItemEqualBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            EqualNumberViewHolder(view)
        } else {
            val view = ItemRegularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            NumberViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return numbers.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val number = numbers[position]
        if (getItemViewType(position) == EQUAL){
            val binding = (holder as EqualNumberViewHolder).binding
            binding.tvNumber.text = number.toString()
        } else{
            val binding = (holder as NumberViewHolder).binding
            binding.tvNumber.text = number.toString()
        }
    }


}