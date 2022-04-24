package com.example.module7

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView;


class SecondActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }

    class Vars {
        val numbersMap = mutableMapOf<String, String>()

        fun puttingData(key: String, value:String ){
            numbersMap.put(key,value)
            println(numbersMap)
        }
    }
    val vav:Vars = Vars()
    fun addVariable(view: View){
        val v = vav
        val editNameVar = findViewById<EditText>(R.id.varName)
        val varName = editNameVar.text.toString()
        val editAmountVar = findViewById<EditText>(R.id.varVal)
        val varAmount = editAmountVar.text.toString()
        v.puttingData(varName, varAmount)
        editNameVar.setText("")
        editAmountVar.setText("")
    }


}