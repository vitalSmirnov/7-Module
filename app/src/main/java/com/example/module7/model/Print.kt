package com.example.module7.model


class Print(override val position: Int, expression: String): Expression {
    private val _printable = expression

    fun getData(): String {
        return _printable
    }
}

