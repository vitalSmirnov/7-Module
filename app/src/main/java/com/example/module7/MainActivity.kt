package com.example.module7

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity


const val EXTRA_MESSAGE = "com.example.module7.MESSAGE"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun transitionSecond(view: View) {
        val intent = Intent(this, TutorialActivity::class.java)
        startActivity(intent)
    }
}