package com.micoder.dpslive.utils

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

class SP(val context: Context) {

    // Setting & Getting String values
    fun put_data(key: String, value: String) {
        val sharedPreferences = context.getSharedPreferences("UserData", AppCompatActivity.MODE_PRIVATE)
        val myEdit = sharedPreferences.edit()
        myEdit.putString(key, value)
        myEdit.apply()
    }
    fun get_data(key: String): String {
        val sh = context.getSharedPreferences("UserData", AppCompatActivity.MODE_PRIVATE)
        val value: String? = sh?.getString(key, "")
        return value.toString()
    }

}