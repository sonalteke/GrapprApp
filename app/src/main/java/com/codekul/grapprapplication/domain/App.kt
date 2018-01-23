package com.codekul.grapprapplication.domain

/**
 * Created by sonal on 30/12/17.
 */

class App() {
    var id : String = ""
    var appName : String = ""
    var url : String = ""
    var offer : Int = 0
    var category : String = ""
    var date : String = ""

    constructor(id : String, appName : String, url : String , offer : Int):this(){
        this.id = id
        this.appName = appName
        this.url = url
        this.offer = offer
    }
}


