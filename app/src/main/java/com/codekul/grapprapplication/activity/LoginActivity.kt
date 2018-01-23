package com.codekul.grapprapplication.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.codekul.grapprapplication.R
import com.codekul.grapprapplication.domain.Credential
import com.codekul.grapprapplication.domain.DtoLogin
import com.codekul.grapprapplication.rest.ApiService
import com.codekul.grapprapplication.sharedPreference.Prefs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    val TAG: String = "@codekul"

    lateinit var txtSignup: TextView
    lateinit var edtLoginPassword: EditText
    lateinit var edtLoginEmailId: EditText
    lateinit var btnLogin: Button
    lateinit var context : Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (Prefs.getUserId(this)!= null){
            startActivity(Intent(applicationContext, MainActivity::class.java)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
        }

        supportActionBar?.title = "CashBolo Login"

        edtLoginEmailId = findViewById(R.id.edtLoginEmail)
        edtLoginPassword = findViewById(R.id.edtLoginPassword)
        btnLogin = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
            validations()
        }
            txtSignup = findViewById(R.id.txtSignup)
            txtSignup.setOnClickListener {
                var intent = Intent(applicationContext, RegistrationActivity::class.java)
                startActivity(intent)
            }
    }

    private fun validations(){
        if ((edtLoginEmailId.text.isNullOrBlank()) ||
                (edtLoginPassword.text.isNullOrBlank())){

            val simpleAlert = AlertDialog.Builder(this@LoginActivity).create()
            simpleAlert.setTitle("User Login")
            simpleAlert.setMessage("Mention all fields...")

            simpleAlert.setButton(AlertDialog.BUTTON_POSITIVE, "OK", {
                _, _ ->
            })
            simpleAlert.show()
        }else{
            loginUser()
        }
    }

    private fun loginUser() {
        val apiService = ApiService.create()

        val call = apiService.userLogin(Credential(edtLoginEmailId.text.toString(),edtLoginPassword.text.toString()))

        Log.i(TAG,"""url : ${call.request().url()}""")
        call.enqueue(object : Callback<DtoLogin> {
            override fun onResponse(call: Call<DtoLogin>?, response: Response<DtoLogin>?) {
                val list  = response?.body()
                if (response?.code() == 200){
                    Toast.makeText(applicationContext,"Message : "+list?.msg,Toast.LENGTH_SHORT).show()

                    Log.i(TAG,"mobileno from login : "+list?.result!!.mobileNo)

                    Prefs.saveUserId(applicationContext,list!!.result.id,
                            list.result.emailId,
                            list.result.password,
                            list.result.mobileNo)

                    Log.i(TAG,"Response : "+response.body())

                    Log.i(TAG,"UserId : "+Prefs.getUserId(applicationContext))

                    startActivity(Intent(applicationContext, MainActivity::class.java)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
                }
                else{
                    Toast.makeText(applicationContext,"Login unsuccessful...Enter valid data",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<DtoLogin>?, t: Throwable?) {
                Toast.makeText(applicationContext,"Error : " +t?.message,Toast.LENGTH_SHORT).show()
            }
        })
    }
}






