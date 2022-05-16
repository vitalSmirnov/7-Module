package com.example.module7

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.module7.databinding.*
import com.example.module7.model.Math
import com.example.module7.model.Var

private lateinit var bindingValBlock: ValBlockBinding
private lateinit var bindingMath: MathBlockBinding

class SecondActivity : AppCompatActivity() {
    private val bindingMain by lazy { ActivitySecondBinding.inflate(layoutInflater)}
    private var adapter: Adapter = Adapter(this)

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

        bindingMain.recyclerView.adapter = adapter
        bindingMain.recyclerView.setHasFixedSize(true)
        bindingMain.recyclerView.layoutManager = LinearLayoutManager(this)

    }

    fun addVariable(key: String, amount: String) {
        val list = Var(adapter.blocks.size, key, amount)
        adapter.addVars(list)
    }

    fun addMath(key: String, expression: String) {
        val list = Math(adapter.blocks.size, key, expression)
        adapter.addMath(list)
    }


    fun showsDialog(id: Int) {
        when (id) {
            R.id.Vars -> {
                addvarsDialog()
                true
            }
            R.id.math -> {
                mathDialog()
                true
            }// read the listItemPositionForPopupMenu here
            R.id.ifs -> {
                conditionDialog()
                true
            }
            else -> {
                false
            }
        }
    }

    fun addvarsDialog()  {

        val bindingDialog = DialogValBinding.inflate(layoutInflater)

        val onClickListener = DialogInterface.OnClickListener { dialog, it ->
            when (it) {
                Dialog.BUTTON_POSITIVE -> {
                    val key = bindingDialog.editValName
                    val k = key.text.toString()
                    val amount = bindingDialog.editVal
                    val a = amount.text.toString()
                    addVariable(k,a)
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

    }

    fun mathDialog()  {
        val bindingDialog = DialogMathBinding.inflate(layoutInflater)

        val onClickListener = DialogInterface.OnClickListener { dialog, it ->
            when (it) {
                Dialog.BUTTON_POSITIVE -> {
                    val key = bindingDialog.valName
                    val k = key.text.toString()
                    val amount = bindingDialog.expression
                    val a = amount.text.toString()
                    addMath(k,a)
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

//    fun setUpListVars () {
//        val listVars = vav.getData()
//        val data = (0..listVars.size).map {
//            mapOf(
//                VAR_TITTLE to it.toString(),
//                VAR_DESRIPTION to listVars[it.toString()]
//            )
//        }
//    val adapter = SimpleAdapter(
//        this,
//        data,
//        android.R.layout.simple_list_item_activated_1,
//        arrayOf(VAR_TITTLE, VAR_DESRIPTION),
//        intArrayOf(android.R.id.text1,android.R.id.text2)
//        )
//        bindingDialog.spinne
//    }
//
//
//    companion object {
//        @JvmStatic var VAR_TITTLE = ""
//        @JvmStatic var VAR_DESRIPTION = ""
//    }

}

