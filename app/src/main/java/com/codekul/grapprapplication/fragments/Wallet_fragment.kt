package com.codekul.grapprapplication.fragments

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import com.codekul.grapprapplication.R
import kotlinx.android.synthetic.main.faqs_layout.view.*


/**
 * Created by sonal on 29/12/17.
 */
class Wallet_fragment : DialogFragment() {

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
        val txt=vw.textView2
        return android.app.AlertDialog.Builder(activity).setView(vw).create()
    }
}