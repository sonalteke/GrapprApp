package com.codekul.grapprapplication.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.codekul.grapprapplication.R
import com.codekul.grapprapplication.domain.DtoUser
import com.codekul.grapprapplication.domain.User
import com.codekul.grapprapplication.rest.ApiService
import com.codekul.grapprapplication.sharedPreference.Prefs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationActivity : AppCompatActivity() {

    val TAG: String = "@codekul"

    lateinit var edtName: EditText
    lateinit var edtEmail: EditText
    lateinit var edtPassword: EditText
    lateinit var edtMobileNo: EditText
    lateinit var btnSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "User Registration"

        edtName = findViewById(R.id.edtName)
        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)
        edtMobileNo = findViewById(R.id.edtMobileNo)
        btnSubmit = findViewById(R.id.btnSubmit)

        btnSubmit.setOnClickListener {
            validations()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        onBackPressed()
        return true
    }
    private fun validations(){
        if ((edtName.text.isNullOrBlank()) or
                (edtEmail.text.isNullOrBlank()) or
                (edtPassword.text.isNullOrBlank()) or
                (edtMobileNo.text.isNullOrBlank())){

            val simpleAlert = AlertDialog.Builder(this@RegistrationActivity).create()
            simpleAlert.setTitle("User Registration")
            simpleAlert.setMessage("Mention all fields...")

            simpleAlert.setButton(AlertDialog.BUTTON_POSITIVE, "OK", {
                _, _ ->
            })
            simpleAlert.show()
        }else{
            postData()

        }
    }

    private fun postData() {
        val apiService = ApiService.create()

        val call = apiService.registerUser(User(edtName.text.toString(),
                edtEmail.text.toString(), edtMobileNo.text.toString(), edtPassword.text.toString()))

        Log.i(TAG, """url : ${call.request().url()}""")
        call.enqueue(object : Callback<DtoUser> {
            override fun onResponse(call: Call<DtoUser>?, response: Response<DtoUser>?) {
                val list = response?.body()

                Log.i(TAG,"user id : " +list!!.result.id)
                Log.i(TAG,"mobile no : " +edtMobileNo.text.toString())

                Prefs.saveUserId(applicationContext,list.result.id,
                        list.result.emailId,
                        list.result.password,
                        list.result.mobileNo)
                Log.i(TAG,"Response : "+response.body())
                if (response.code() == 200) {
                    Log.i(TAG, "User : " + list.result)
                    Toast.makeText(applicationContext, "Message : " + list?.msg, Toast.LENGTH_SHORT).show()
                    var intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

            override fun onFailure(call: Call<DtoUser>?, t: Throwable?) {
                Log.i(TAG, "Error :" + t)
            }
        })
    }
}