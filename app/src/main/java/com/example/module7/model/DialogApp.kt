package com.example.module7.model

import android.app.Application
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.module7.R
import com.example.module7.databinding.*
import java.lang.IllegalStateException


//class DialogApp(): DialogFragment() {
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        val activity = activity ?: throw IllegalStateException("Activity can't be null")
//        when(id){
//            R.id.Vars -> {
//                addvarsDialog()
//            }
//            R.id.math -> {
//                mathDialog()
//            }
//            R.id.ifs -> {
//                conditionDialog()
//            }
//            R.id.print -> {
//                printDialog()
//            }
//            else -> {
//
//            }
//        }
//        return
//    }
//
//}