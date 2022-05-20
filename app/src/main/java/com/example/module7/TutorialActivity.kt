package com.example.module7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt

class TutorialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorials)
        firstTap()
    }

        private fun firstTap(){
            MaterialTapTargetPrompt.Builder(this)
                .setTarget(R.id.btn_popup)
                .setPrimaryText("Button for choosing types of code block")
                .setSecondaryText("tap here to check")
                .setPromptStateChangeListener { _, state ->
                    if (state == MaterialTapTargetPrompt.STATE_FOCAL_PRESSED)
                    {
                        secondTap()
                    }
                }
                .show()

        }

        private fun secondTap() {
            MaterialTapTargetPrompt.Builder(this)
                .setTarget(R.id.btn_start)
                .setPrimaryText("Button for start programm")
                .setSecondaryText("tap here to check")
                .setPromptStateChangeListener { _, state ->
                    if (state == MaterialTapTargetPrompt.STATE_FOCAL_PRESSED)
                    {
                        thirdTap()
                    }
                }
                .show()
        }

        private fun thirdTap() {
            MaterialTapTargetPrompt.Builder(this)
                .setTarget(R.id.btn_clear)
                .setPrimaryText("Button for clean all blocks")
                .setSecondaryText("tap here to start coding")
                .setPromptStateChangeListener { _, state ->
                    if (state == MaterialTapTargetPrompt.STATE_FOCAL_PRESSED)
                    {
                        val message = "done"
                        val intent = Intent(this, SecondActivity::class.java).apply {
                            putExtra(EXTRA_MESSAGE, message)
                        }
                        startActivity(intent)
                    }
                }
                .show()
        }

}