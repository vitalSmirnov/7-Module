package com.example.module7.model

class Input(override val position: Int, private val _input: String): Expression  {

    fun getData() : String{
        return _input
    }
}