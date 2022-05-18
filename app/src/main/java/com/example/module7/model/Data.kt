package com.example.module7.model

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.core.text.isDigitsOnly
import com.example.module7.SecondActivity
import com.example.module7.databinding.DialogOutputBinding
class Data(private  val context: Context){
    private val opsDictionary: Map<Char,Int> = mapOf('-' to 1, '+' to 1,'*' to 2,'/' to 2,'(' to -1,')' to -1)
    private var allVarsOfProgramm = mutableMapOf<String, String>()
    private var output = mutableListOf<String>()
    private var allOperationsOfProgramm = mutableListOf<Expression>()

    fun putData(key:String, value: String){
        allVarsOfProgramm[key] = value
    }
    fun putOperations(list: Expression){
        allOperationsOfProgramm.add(list)
    }

    fun getVarsData(key:String): String? {
        return allVarsOfProgramm[key]
    }

    fun startCompiler (){
        for (i in allOperationsOfProgramm){
            when (i){
                is Math ->{
                    countPolishString(i)
                }
                is Print -> {
                    printData(i)
                }
            }
        }
        val outputDialog = AlertDialog.Builder(context).setTitle("Output")
            .setMessage(output.toString())
        outputDialog.show()

    }
    fun printData(_printable:Print){
        val toPrint = _printable.getData()
        var check = true
        for(i in allVarsOfProgramm){
            val _var = (i.toString()).split("=")
            if (_var[0] == toPrint){
                Log.d("tst", _var[1])
                output.add(_var[0] + " " + _var[1])
                check = false
            }
        }
        if(check){
            Log.d("tst", toPrint)
            output.add(toPrint)
        }
    }

    private fun generatePolishString(input:String):MutableList<String>
    {
        var output: MutableList<String> = mutableListOf()
        var stack = ArrayDeque<Char>()

        var i = 0
        while (i < input.length) {
            var str  = ""
            if(input[i].isLetterOrDigit())
            {
                str+=input[i]
                while(i+1<input.length && input[i+1].isLetterOrDigit()){
                    i++
                    str+=input[i]
                }
                output.add(str)
            }
            else
            {
                when(input[i]) {
                    '+','-','*','/' -> {
                        while(stack.count() > 0 && opsDictionary[stack.last()]!! >= opsDictionary[input[i]]!!){
                            output.add(stack.removeLast().toString())
                        }
                        stack.addLast(input[i])
                    }
                    '(' -> {
                        stack.addLast(input[i])
                    }
                    ')' ->{
                        println(stack)
                        while (stack.count() > 0 && stack.last() != '('){
                            output.add(stack.removeLast().toString())
                        }
                        if (stack.count()>0){
                            stack.removeLast()
                        }
                    }
                }
            }
            i++
        }
        while(stack.count()>0){
            output.add(stack.removeLast().toString())
        }
        return output
    }
    fun countPolishString(_string : Math){
        val regex = "\\w+".toRegex()
        val key = _string.getExpression().first
        val string = generatePolishString(_string.getExpression().second)
        Log.d("tst", string.toString())
        var stack = ArrayDeque<String>()
        for (i in string)
        {
            if (i.isDigitsOnly())
            {
                stack.addLast(i)
                continue
            }
            if(i.matches(regex))
            {
                stack.addLast(getVarsData(i).toString()) //////переменная и её значение
                continue
            }
            val a = stack.removeLast().toInt()
            val b = stack.removeLast().toInt()
            when(i)
            {
                "+" -> {
                    stack.addLast((a+b).toString())
                }
                "-" -> {
                    stack.addLast((b-a).toString())
                }
                "*" -> {
                    stack.addLast((b*a).toString())
                }
                "/" -> {
                    stack.addLast((b/a).toString())
                }
            }
        }
        putData(key, stack.last())
    }

}
