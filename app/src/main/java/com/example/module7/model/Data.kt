package com.example.module7.model

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.core.text.isDigitsOnly
import kotlin.collections.ArrayDeque


class Data(private val context: Context) {
    private val opsDictionary: Map<Char, Int> =
        mapOf('-' to 1, '+' to 1, '*' to 2, '/' to 2, '(' to -1, ')' to -1)
    private var allVarsOfProgramm = mutableMapOf<String, String>()
    private var output = mutableListOf<String>()
    private var allOperationsOfProgramm = mutableListOf<Expression>()

    fun clear() {
        allVarsOfProgramm = mutableMapOf<String, String>()
        allOperationsOfProgramm = mutableListOf<Expression>()
    }

    fun putData(key: String, value: String) {
        allVarsOfProgramm[key] = value
    }

    fun putOperations(list: Expression) {
        allOperationsOfProgramm.add(list)
    }

    fun getVarsData(key: String): String? {
        return allVarsOfProgramm[key]
    }

    fun startCompiler(first: Boolean = true, isNotCondition: Boolean = true) {
        if (first) output.clear()
        else allOperationsOfProgramm.removeAt(0)
        if (allOperationsOfProgramm.size != 0) {
            Log.d("tst", allOperationsOfProgramm.size.toString())
            if (!isNotCondition) {
                while (allOperationsOfProgramm[0] !is End) {
                    allOperationsOfProgramm.removeAt(0)
                }
            }
            compilerWrap(allOperationsOfProgramm[0])
        } else {
            endDialog()
        }
    }

    fun compilerWrap(expression: Expression) {
        when (expression) {
            is Math -> {
                countPolishString(expression)
                startCompiler(false)
            }
            is Print -> {
                printData(expression)
                startCompiler(false)
            }
            is Input -> {
                inputData(expression)
            }
            is Conditions -> {
                val isCondition = checkConditions(expression)
                startCompiler(false, isCondition)
            }
            else -> {
                startCompiler(false)
            }

        }
    }

    fun endDialog() {
        val outputDialog = AlertDialog.Builder(context).setTitle("Output")
            .setMessage(output.toString())

        outputDialog.show()
    }

    fun inputData(_input: Input) {
        val key = _input.getData()
        val input = EditText(context)
        val onCLickListener = DialogInterface.OnClickListener { _, it ->
            when (it) {
                Dialog.BUTTON_POSITIVE -> {
                    val value = input.text.toString()
                    putData(key, value)
                    startCompiler(false, true)
                }
            }
        }

        val inputDialog = AlertDialog.Builder(context).setTitle("Input -> $key")
            .setMessage(output.toString())
            .setView(input)
            .setPositiveButton("Confirm", onCLickListener)
            .setCancelable(false)

        inputDialog.show()


    }

    fun checkConditions(_condition: Conditions): Boolean {
        val expression = _condition.getData()
        var checkable = ""
        var switch = true
        var sign = ""
        var point = ""
        val size = expression.length - 1
        for (i in 0..size) {
            when (expression[i]) {
                ' ' -> {}
                '>' -> {
                    sign += expression[i]
                    switch = false
                }
                '<' -> {
                    sign += expression[i]
                    switch = false
                }
                '!' -> {
                    sign += expression[i]
                    switch = false
                }
                '=' -> {
                    sign += expression[i]
                    switch = false
                }
                else -> {
                    if (switch) {
                        checkable += expression[i]
                    } else {
                        point += expression[i]
                    }
                }
            }
        }
        val list = Math(-10, "condition", checkable)
        val checked = countPolishString(list, true)
        return isTrueOrFalse(checked.toString(), sign, point)
    }

    fun isTrueOrFalse(checked: String, sign: String, point: String): Boolean {
        when (sign) {
            ">" -> {
                return checked > point
            }
            "<" -> {
                return checked < point
            }
            ">=" -> {
                return checked >= point
            }
            "<=" -> {
                return checked <= point
            }
            "==" -> {
                return checked == point
            }
            "!=" -> {
                return checked != point
            }
            else -> {
                return false
            }
        }
    }


    fun printData(_printable: Print) {
        var toPrint = (_printable.getData()).replace("\\s".toRegex(), "")
        val sentence = toPrint.split(",")
        var check = true
        for (j in sentence) {
            for (i in allVarsOfProgramm) {
                val _var = (i.toString()).split("=")
                if (_var[0] == j) {
                    Log.d("tst", _var[1])
                    output.add(_var[0] + " " + _var[1])
                    check = false
                }
            }
            if (check) {
                Log.d("tst", j)
                output.add(j)
            }
        }

    }

    private fun generatePolishString(input: String): MutableList<String> {
        var output: MutableList<String> = mutableListOf()
        var stack = ArrayDeque<Char>()

        var i = 0
        while (i < input.length) {
            var str = ""
            if (input[i].isLetterOrDigit()) {
                str += input[i]
                while (i + 1 < input.length && input[i + 1].isLetterOrDigit()) {
                    i++
                    str += input[i]
                }
                output.add(str)
            } else {
                when (input[i]) {
                    '+', '-', '*', '/' -> {
                        while (stack.count() > 0 && opsDictionary[stack.last()]!! >= opsDictionary[input[i]]!!) {
                            output.add(stack.removeLast().toString())
                        }
                        stack.addLast(input[i])
                    }
                    '(' -> {
                        stack.addLast(input[i])
                    }
                    ')' -> {
                        println(stack)
                        while (stack.count() > 0 && stack.last() != '(') {
                            output.add(stack.removeLast().toString())
                        }
                        if (stack.count() > 0) {
                            stack.removeLast()
                        }
                    }
                }
            }
            i++
        }
        while (stack.count() > 0) {
            output.add(stack.removeLast().toString())
        }
        return output
    }

    fun countPolishString(_string: Math, isCondition: Boolean = false): Int {
        val regex = "\\w+".toRegex()
        val key = _string.getExpression().first
        val string = generatePolishString(_string.getExpression().second)
        Log.d("tst", string.toString())
        var stack = ArrayDeque<String>()
        for (i in string) {
            if (i.isDigitsOnly()) {
                stack.addLast(i)
                continue
            }
            if (i.matches(regex)) {
                stack.addLast(getVarsData(i).toString()) //////переменная и её значение
                continue
            }
            val a = stack.removeLast().toInt()
            val b = stack.removeLast().toInt()
            when (i) {
                "+" -> {
                    stack.addLast((a + b).toString())
                }
                "-" -> {
                    stack.addLast((b - a).toString())
                }
                "*" -> {
                    stack.addLast((b * a).toString())
                }
                "/" -> {
                    stack.addLast((b / a).toString())
                }
            }
        }
        if (!isCondition) {
            putData(key, stack.last())
            return 0
        } else {
            return stack.last().toInt()
        }
    }

}
