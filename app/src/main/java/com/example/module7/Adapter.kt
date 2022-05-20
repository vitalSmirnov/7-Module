package com.example.module7

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.module7.databinding.*
import com.example.module7.model.*

class Adapter(private val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var blocks = mutableListOf<Expression>()

    private companion object {
        val DIFF = object : DiffUtil.ItemCallback<Var>() {
            override fun areItemsTheSame(oldItem: Var, newItem: Var) = oldItem.position == newItem.position
            override fun areContentsTheSame(oldItem: Var, newItem: Var) = oldItem == newItem
        }
    }

    inner class ValViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ValBlockBinding.bind(view)

        fun bind(expression: Var) = with(binding) {
            val pair = expression.getData()

            textViewName.text = pair.first
            textViewVal.text = pair.second
        }
    }
    inner class MathViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = MathBlockBinding.bind(view)

        fun bind(expression: Math) = with(binding) {
            val pair = expression.getExpression()

            textViewName.text = pair.first
            expressionView.text = pair.second
        }
    }

    inner class ConditionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ConditionBlockBinding.bind(view)

        fun bind(expression: Conditions) = with(binding) {
            val _exp = expression.getData()
            exp.text =_exp
        }
    }

    inner class PrintViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = PrintBlockBinding.bind(view)

        fun bind(expression: Print) = with(binding) {
            val printable = expression.getData()
            printText.text = printable
        }
    }

    inner class InputViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = InputBlockBinding.bind(view)

        fun bind(expression: Input) = with(binding) {
            val data = expression.getData()
            inputText.text = data
        }
    }


    inner class EndViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = EndBlockBinding.bind(view)
        fun bind(expression: End) = with(binding) {
            Log.d("tst", expression.toString())
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.val_block-> {
                ValViewHolder(view)
            }
            R.layout.math_block-> {
                MathViewHolder(view)
            }
            R.layout.condition_block->{
                ConditionViewHolder(view)
            }
            R.layout.print_block ->{
                PrintViewHolder(view)
            }
            R.layout.end_block ->{
                EndViewHolder(view)
            }
            R.layout.input_block -> {
                InputViewHolder(view)
            }
            else -> throw Exception()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val expression = blocks[position]
            when (holder) {
                is ValViewHolder -> holder.bind(expression as Var)
                is MathViewHolder -> holder.bind(expression as Math)
                is ConditionViewHolder -> holder.bind(expression as Conditions)
                is PrintViewHolder -> holder.bind(expression as Print)
                is EndViewHolder -> holder.bind(expression as End)
                is InputViewHolder -> holder.bind(expression as Input)
                else -> throw Exception()
            }
        }


    override fun getItemCount(): Int {
        return blocks.size
    }

    override fun getItemViewType(position: Int): Int {
        val expression = blocks[position]
        return when (expression) {
            is Var -> R.layout.val_block
            is Math -> R.layout.math_block
            is Conditions -> R.layout.condition_block
            is Print -> R.layout.print_block
            is End -> R.layout.end_block
            is Input -> R.layout.input_block
            else -> throw Exception()
        }
    }

    fun addVars(variable :Expression) {
        blocks.add(variable)
        notifyDataSetChanged()
    }
    fun addMath(math: Expression) {
        blocks.add(math)
        notifyDataSetChanged()
    }
    fun addCond(cond: Expression) {
        blocks.add(cond)
        notifyDataSetChanged()
    }
    fun addPrint(print : Expression) {
        blocks.add(print)
        notifyDataSetChanged()
    }
    fun addEnd(end: Expression) {
        blocks.add(end)
        notifyDataSetChanged()
    }
    fun addInput(_input : Expression){
        blocks.add(_input)
        notifyDataSetChanged()
    }
    fun clear(){
        blocks = mutableListOf<Expression>()
        notifyDataSetChanged()
    }
}