package com.codekul.grapprapplication.fragments

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import com.codekul.grapprapplication.R
import com.codekul.grapprapplication.domain.CountInfo
import com.codekul.grapprapplication.rest.ApiService
import com.codekul.grapprapplication.sharedPreference.Prefs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by sonal on 29/12/17.
 */
class Wallet_fragment : DialogFragment() {

    val TAG: String = "@codekul"
    lateinit var txtInstallCount : TextView
    lateinit var txtUninstallCount : TextView

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return when(tag){
            "alert" -> customWallet()
            else -> alertEmail()
        }
    }

    private fun alertEmail() : Dialog {
        return AlertDialog.Builder(activity)
                .setTitle("Email")
                .setIcon(R.drawable.ic_launcher_background)
                .setMessage("This is Email dialog")
                .setPositiveButton("ok",{dialogInterface,btn -> dialogInterface.dismiss()})
                .show()
    }

    private fun customWallet(): Dialog {
        val vw= LayoutInflater.from(activity).inflate(R.layout.wallet_layout,null,false)

        val apiservice = ApiService.create()
        val uid = Prefs.getUserId(context)
        val call = apiservice.getUserAppsCount(uid!!)
        Log.i(TAG,"uid : "+uid)
        Log.i(TAG, "Url: " + call.request().url().toString())

        call.enqueue(object : Callback<CountInfo> {

            override fun onResponse(call: Call<CountInfo>?, response: Response<CountInfo>?) {
                val list = response?.body()

                Log.i(TAG, "install:" + list?.userInstalled)
                Log.i(TAG, "uninstall :" + list?.userUninstalled)

                txtInstallCount = vw.findViewById(R.id.txtInstallCount)
                txtUninstallCount = vw.findViewById(R.id.txtUninstallCount)

                txtInstallCount.text = list?.userInstalled
                txtUninstallCount.text = list?.userUninstalled

//                button.setOnClickListener {
//                    Log.i(TAG, "on button click...")
//                    dialog.dismiss()
//                }
            }

            override fun onFailure(call: Call<CountInfo>?, t: Throwable?) {
                Log.i(TAG, "ErrorM: " + t?.message)
                Log.i(TAG, "ErrorC: " + t?.cause)
                Log.i(TAG, "ErrorL: " + t?.localizedMessage)
            }
        })

        return AlertDialog.Builder(activity).setView(vw).create()
    }
}