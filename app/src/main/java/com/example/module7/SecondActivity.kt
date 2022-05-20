package com.example.module7

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.module7.databinding.*
import com.example.module7.model.*


class SecondActivity : AppCompatActivity() {
    private val bindingMain by lazy { ActivitySecondBinding.inflate(layoutInflater)}
    private var adapter: Adapter = Adapter(this)
    private val _data = Data(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindingMain.root)

        val btnPopup = bindingMain.btnPopup
        btnPopup.setOnClickListener {
            val popup = PopupMenu(this, btnPopup)
            popup.inflate(R.menu.popup_menu)
            popup.setOnMenuItemClickListener {
                println(it.itemId)
                showsDialog(it.itemId)
                true
            }
            popup.show()
        }
        val btnStart = bindingMain.btnStart
        btnStart.setOnClickListener{
            startCompile()
        }

        val btnClear = bindingMain.btnClear
        btnClear.setOnClickListener{
            clearData()
        }

        bindingMain.recyclerView.adapter = adapter
        bindingMain.recyclerView.setHasFixedSize(true)
        bindingMain.recyclerView.layoutManager = LinearLayoutManager(this)

    }



    private fun addVariable(pair :Pair<String, String>) {
        val list = Var(adapter.blocks.size, pair.first, pair.second)
        _data.putData(pair.first,pair.second)
        adapter.addVars(list)
    }
    private fun addCondition(exp: String) {
        val list = Conditions(adapter.blocks.size,exp)
        _data.putOperations(list)
        adapter.addCond(list)
    }

    private fun addMath(pair :Pair<String, String>) {
        val list = Math(adapter.blocks.size, pair.first, pair.second)
        _data.putOperations(list)
        adapter.addMath(list)
    }

    private fun addPrint(print : String) {
        val list = Print(adapter.blocks.size, print)
        _data.putOperations(list)
        adapter.addPrint(list)
    }
    fun addEnd(){
        val list = End(adapter.blocks.size)
        _data.putOperations(list)
        adapter.addEnd(list)
    }
    fun addInput(_input: String){
        val list = Input(adapter.blocks.size, _input)
        _data.putOperations(list)
        adapter.addInput(list)
    }


    private fun showsDialog(id: Int) {
            when (id) {
                R.id.Vars -> {
                    addvarsDialog()
                }
                R.id.math -> {
                    mathDialog()
                }
                R.id.ifs -> {
                    conditionDialog()
                }
                R.id.print -> {
                    printDialog()
                }
                R.id.end -> {
                    addEnd()
                }
                R.id.input ->{
                    inputDialog()
                }
                else -> {

                }
            }
    }

    private fun inputDialog() {
        val binding = DialogInputBinding.inflate(layoutInflater)
        var exp = ""

        val onClickListener = DialogInterface.OnClickListener{
                dialog, it ->
            when (it) {
                Dialog.BUTTON_POSITIVE -> {
                    exp = binding.printEdit.text.toString()
                    addInput(exp)
                }
                Dialog.BUTTON_NEGATIVE -> {
                    dialog.cancel()
                }
            }
        }

        val builder = AlertDialog.Builder(this)
        builder
            .setView(binding.root)
            .setPositiveButton("Confirm", onClickListener)
            .setNegativeButton("Cancel", onClickListener)

        builder.show()
    }


    fun addvarsDialog() {

        val bindingDialog = DialogValBinding.inflate(layoutInflater)
        var k = ""
        var a = ""
        val onClickListener = DialogInterface.OnClickListener { dialog, it ->
            when (it) {
                Dialog.BUTTON_POSITIVE -> {
                    val key = bindingDialog.editValName
                    k = key.text.toString()
                    val amount = bindingDialog.editVal
                    a = amount.text.toString()
                    addVariable(Pair(k,a))
                }
                Dialog.BUTTON_NEGATIVE -> {
                    dialog.cancel()
                }
            }
        }

        val builder = AlertDialog.Builder(this)
        builder
            .setView(bindingDialog.root)
            .setPositiveButton("Confirm", onClickListener)
            .setNegativeButton("cancel", onClickListener)

        builder.show()
    }


    fun conditionDialog(){
        val binding = DialogConditionBinding.inflate(layoutInflater)
        var exp = ""
        val onClickListener = DialogInterface.OnClickListener{
                dialog, it ->
            when (it) {
                Dialog.BUTTON_POSITIVE -> {
                    exp = binding.exp.text.toString()
                    addCondition(exp)
                }
                Dialog.BUTTON_NEGATIVE -> {
                    dialog.cancel()
                }
            }
        }

        val builder = AlertDialog.Builder(this)
        builder
            .setView(binding.root)
            .setPositiveButton("Confirm", onClickListener)
            .setNegativeButton("Cancel", onClickListener)

        builder.show()
    }

    fun printDialog(){
        val binding = DialogPrintBinding.inflate(layoutInflater)
        var exp = ""

        val onClickListener = DialogInterface.OnClickListener{
                dialog, it ->
            when (it) {
                Dialog.BUTTON_POSITIVE -> {
                    exp = binding.printEdit.text.toString()
                    addPrint(exp)
                }
                Dialog.BUTTON_NEGATIVE -> {
                    dialog.cancel()
                }
            }
        }

        val builder = AlertDialog.Builder(this)
        builder
            .setView(binding.root)
            .setPositiveButton("Confirm", onClickListener)
            .setNegativeButton("Cancel", onClickListener)

        builder.show()

    }

    fun mathDialog() {
        val bindingDialog = DialogMathBinding.inflate(layoutInflater)
        var k= ""
        var a= ""
        val onClickListener = DialogInterface.OnClickListener { dialog, it ->
            when (it) {
                Dialog.BUTTON_POSITIVE -> {
                    val key = bindingDialog.valName
                    k = key.text.toString()
                    val amount = bindingDialog.expression
                    a = amount.text.toString()
                    addMath(Pair(k,a))
                }
                Dialog.BUTTON_NEGATIVE -> {
                    dialog.cancel()
                }
            }
        }

        val builder = AlertDialog.Builder(this)
        builder
            .setView(bindingDialog.root)
            .setPositiveButton("Confirm", onClickListener)
            .setNegativeButton("cancel", onClickListener)

        builder.show()
    }

    fun clearData(){
        adapter.clear()
        _data.clear()
    }

    fun startCompile(){
        _data.startCompiler()
    }

}

