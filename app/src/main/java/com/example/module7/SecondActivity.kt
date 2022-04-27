package com.example.module7

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.app.PendingIntent.getActivity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*


class SecondActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val btnPopup = findViewById<Button>(R.id.btn_popup)
        btnPopup.setOnClickListener(){
            var popup = PopupMenu(this, btnPopup)
            popup.inflate(R.menu.popup_menu)
            popup.setOnMenuItemClickListener {
                when (it.itemId) {
                    1 -> {addVariable()
                        true}
                    else -> {addVariable()
                    true}
                }
            }
            popup.show()
        }

    }

    class CustomDialogFragment : DialogFragment() {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val builder: AlertDialog.Builder = AlertDialog.Builder(activity)

            return builder
                .setTitle("Определить переменную")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage("Для закрытия окна нажмите ОК")
                .setPositiveButton("OK", null)
                .setNegativeButton("Отмена", null)
                .create()
        }
    }

    class Vars {
        val numbersMap = mutableMapOf<String, String>()

        fun puttingData(key: String, value:String ){
            numbersMap.put(key,value)
            println(numbersMap)
        }
    }
    val vav:Vars = Vars()
    fun addVariable(){
//        val v = vav
//        val editNameVar = findViewById<EditText>(R.id.varName)
//        val showVars = findViewById<TextView>(R.id.varView)
//        val varName = editNameVar.text.toString()
//        val editAmountVar = findViewById<EditText>(R.id.varVal)
//        val varAmount = editAmountVar.text.toString()
//        v.puttingData(varName, varAmount)
//        editNameVar.setText("")
//        editAmountVar.setText("")
//        showVars.setText("${v.numbersMap}")
        val mainContainer = findViewById<LinearLayout>(R.id.codeContainer)
        val block = layoutInflater.inflate(R.layout.val_block, mainContainer, true)
    }


    fun showDialog(view: View) {
        val dialog = CustomDialogFragment();
        dialog.show(supportFragmentManager, "custom");
    }


}

