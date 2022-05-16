package com.example.module7

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.module7.databinding.MathBlockBinding
import com.example.module7.databinding.ValBlockBinding
import com.example.module7.model.Expression
import com.example.module7.model.Var
import com.example.module7.model.Math

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
            val pair = expression.getData()

            textViewName.text = pair.first
            expressionView.text = pair.second
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
            else -> throw Exception()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val expression = blocks[position]
            when (holder) {
                is ValViewHolder -> holder.bind(expression as Var)
                is MathViewHolder -> holder.bind(expression as Math)
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

}