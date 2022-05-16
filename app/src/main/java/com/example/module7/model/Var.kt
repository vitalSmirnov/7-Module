package com.example.module7.model

data class Var(override val position: Int, private val name: String, private var value: String) : Expression{
    private val KEY = name
    private val VALUE = value
    fun getData(): Pair<String, String> {
        return Pair(KEY, VALUE)
    }
}