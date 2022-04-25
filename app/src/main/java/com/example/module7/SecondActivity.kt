package com.example.module7

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.PopupMenu


class SecondActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }
    val textView: TextView = findViewById(R.id.textView)
    val button = findViewById<Button>(R.id.popMenuBtn)

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
        val showVars = findViewById<TextView>(R.id.varView)
        val varName = editNameVar.text.toString()
        val editAmountVar = findViewById<EditText>(R.id.varVal)
        val varAmount = editAmountVar.text.toString()
        v.puttingData(varName, varAmount)
        editNameVar.setText("")
        editAmountVar.setText("")
        showVars.setText("${v.numbersMap}")
    }

    val popupMenu2 = PopupMenu(this, button)
    popupMenu2

}
}



}