package com.codekul.grapprapplication.sharedPreference

import android.content.Context
import android.content.SharedPreferences


/**
 * Created by sonal on 15/1/18.
 */
class Prefs {

    companion object {

        val PREFS_STORE_USER :String = "user"
        val USER_ID: String = "userId"
        val EMAILID: String = "emailId"
        val PASSWORD: String = "password"
        val APPID : String = "id"
        val MOBILENO : String = "mobileno"

        fun saveUserId(context: Context, userId: String, emailId: String, password: String, mobileNo: String) {
            var sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_STORE_USER, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString(USER_ID, userId).apply()
            editor.putString(EMAILID, emailId).apply()
            editor.putString(PASSWORD, password).apply()
            editor.putString(MOBILENO,mobileNo).apply()
        }

        fun saveAppId(context: Context, appId: String) {
            var sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_STORE_USER, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString(APPID, appId).apply()
        }

        fun getUserId(context: Context): String? {
            val sharedPreferences = context.getSharedPreferences(PREFS_STORE_USER, Context.MODE_PRIVATE)
            return sharedPreferences.getString(USER_ID, null)
        }

        fun getAppId(context: Context): String? {
            val sharedPreferences = context.getSharedPreferences(PREFS_STORE_USER, Context.MODE_PRIVATE)
            return sharedPreferences.getString(APPID, null)
        }

        fun getUserMobile(context: Context): String {
            val sharedPreferences = context.getSharedPreferences(PREFS_STORE_USER, Context.MODE_PRIVATE)
            return sharedPreferences.getString(MOBILENO, null)
        }

        fun clearUserData(context: Context){
            val sharedPreferences = context.getSharedPreferences(PREFS_STORE_USER,Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
        }
    }
}