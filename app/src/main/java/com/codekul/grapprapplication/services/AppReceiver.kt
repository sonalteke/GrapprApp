package com.codekul.grapprapplication.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.codekul.grapprapplication.domain.*
import com.codekul.grapprapplication.rest.ApiService
import com.codekul.grapprapplication.sharedPreference.Prefs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by sonal on 16/1/18.
 */
class AppReceiver : BroadcastReceiver() {

    val TAG : String = "@codekul"

    override fun onReceive(context: Context?, intent: Intent?) {
        val uid = Prefs.getUserId(context!!)
        val appid = Prefs.getAppId(context!!)

        Log.i(TAG,"action :"+intent?.action)

        if (intent?.action!!.equals("android.intent.action.PACKAGE_ADDED"))
        {
            Log.i(TAG,"msg : app installed...")
            Log.i(TAG,"appid : "+appid)

            val user = User()
            user.id = uid!!
            val app = App()
            app.id = appid!!
            val appStack = AppStack(user,app,true)
            val apiservice = ApiService.create()
            val call = apiservice.updateUsers(appStack)
            Log.i(TAG, "Url: " + call.request().url().toString())
            call.enqueue(object : Callback<StackInfo> {

                override fun onResponse(call: Call<StackInfo>?, response: Response<StackInfo>?) {
                    val list = response?.body()
                    Log.i(TAG, "Response:" + list?.msg)
                    Log.i(TAG, "Response:" + list?.result)
                    Log.i(TAG, "Response:" + list?.status)
                }

                override fun onFailure(call: Call<StackInfo>?, t: Throwable?) {
                    Log.i(TAG, "ErrorM: " + t?.message)
                    Log.i(TAG, "ErrorC: " + t?.cause)
                    Log.i(TAG, "ErrorL: " + t?.localizedMessage)
                }
            })
        }else if (intent.action!!.equals("android.intent.action.PACKAGE_REMOVED")){

            Log.i(TAG,"msg : app uninstalled...")
            Log.i(TAG,"id : "+appid)

            val user = User()
            user.id = uid!!
            val app = App()
            app.id = appid!!
            val appStack = AppStack(user,app,true)

            val apiservice = ApiService.create()
            val call = apiservice.updateUsers(appStack)
            Log.i(TAG, "Url: " + call.request().url().toString())
            call.enqueue(object : Callback<StackInfo> {

                override fun onResponse(call: Call<StackInfo>?, response: Response<StackInfo>?) {
                    val list = response?.body()
                    Log.i(TAG, "Response:" + list)
                }

                override fun onFailure(call: Call<StackInfo>?, t: Throwable?) {
                    Log.i(TAG, "ErrorM: " + t?.message)
                    Log.i(TAG, "ErrorC: " + t?.cause)
                    Log.i(TAG, "ErrorL: " + t?.localizedMessage)
                }
            })
        }
        else{
            Log.i(TAG,"error : app not installed..")
        }
    }
}