package com.codekul.grapprapplication.domain

/**
 * Created by sonal on 12/1/18.
 */
 class User(){
    lateinit var id : String
    lateinit var name : String
    lateinit var emailId : String
    lateinit var mobileNo : String
    lateinit var password : String
    var userInstalled : Int = 0
    var userUninstalled : Int = 0
    var userSkipped : Int = 0

    constructor(name : String,emailId : String,mobileNo : String,password : String):this(){
        this.name = name
        this.emailId = emailId
        this.mobileNo = mobileNo
        this.password = password
    }
}



