package com.example.module7

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView;


class SecondActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val container = findViewById<LinearLayout>(com.example.module7.R.id.container)
        var yDelta = 0
        var xDelta = 0
        val touchListener = OnTouchListener { view, event ->
            val x = event.rawX.toInt()
            val y = event.rawY.toInt()
            when (event.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_DOWN -> {
                    val lParams = view.layoutParams as LinearLayout.LayoutParams
                    xDelta = x - lParams.leftMargin
                    yDelta = y - lParams.topMargin
                }
                MotionEvent.ACTION_MOVE -> {
                    if (x - xDelta + view.width <= container.getWidth() && y - yDelta + view.height <= container.getHeight() && x - xDelta >= 0 && y - yDelta >= 0) {
                        val layoutParams = view.layoutParams as LinearLayout.LayoutParams
                        layoutParams.leftMargin = x - xDelta
                        layoutParams.topMargin = y - yDelta
                        layoutParams.rightMargin = 0
                        layoutParams.bottomMargin = 0
                        view.layoutParams = layoutParams
                    }
                }
            }
            container.invalidate()
            true
        }
    }

}