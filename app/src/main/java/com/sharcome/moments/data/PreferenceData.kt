package com.sharcome.moments.data

import android.content.Context
import android.content.SharedPreferences

class PreferenceData (private val context: Context) {

    val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPref",
        Context.MODE_PRIVATE
    )


    fun saveKey(token: String){
        sharedPreferences.edit().putString("key", token).apply()
    }

    fun getKey() : String{
        return sharedPreferences.getString("key"," ").toString()

    }
    fun saveName(name: String){
        sharedPreferences.edit().putString("name", name).apply()
    }
    fun getName(): String = sharedPreferences.getString("name","").toString()
}