package com.example.module7


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity



const val EXTRA_MESSAGE = "com.example.module7.MESSAGE"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun transitionSecond(view: View) {
        val name = findViewById<EditText>(R.id.editText)
        val message = name.text.toString()
        val intent = Intent(this, TutorialActivity::class.java)
        startActivity(intent)
    }
}