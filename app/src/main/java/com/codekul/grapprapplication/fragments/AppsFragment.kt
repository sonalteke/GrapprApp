package com.codekul.grapprapplication.fragments

import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import com.codekul.grapprapplication.R
import com.codekul.grapprapplication.domain.App
import com.codekul.grapprapplication.domain.AppsInfo
import com.codekul.grapprapplication.rest.ApiService
import com.codekul.grapprapplication.services.AppReceiver
import com.codekul.grapprapplication.sharedPreference.Prefs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.view.MotionEvent
import android.webkit.WebSettings

/**
 * A simple [Fragment] subclass.
 */

class AppsFragment : Fragment() {

    val TAG : String = "@codekul"
    val POSITION : String ="position"
    var al_category = ArrayList<App>()
    var int_position: Int = 0
    lateinit var txtAppName: TextView
    lateinit var txtAppInstall: TextView
    lateinit var txtAppOffers: TextView
    lateinit var btnInstall: Button
    lateinit var view12: View
    lateinit var appWebView : WebView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        view12 = inflater!!.inflate(R.layout.fragment_apps1, container, false)

        activity.title = "Apps"

        arraylist()
        return view12
    }

    fun arraylist(){

        val apiservice = ApiService.create()
        val call = apiservice.getApps()
        var size : Int = 0
        Log.i(TAG, "Url: " + call.request().url().toString())

        call.enqueue(object : Callback<AppsInfo> {

            override fun onResponse(call: Call<AppsInfo>?, response: Response<AppsInfo>?) {
                val list: AppsInfo? = response?.body()
                Log.i(TAG, "Response:" + list)

                size = list!!.result.size

                Log.i(TAG,"size :"+size)

                list.result.forEach {
                    app -> App(app.id, app.appName, app.url, app.offer)
                    al_category.add(app)
                }
                Log.i(TAG, "List : " + list)
                setData()
            }

            override fun onFailure(call: Call<AppsInfo>?, t: Throwable?) {
                Log.i(TAG, "ErrorM: " + t?.message)
                Log.i(TAG, "ErrorC: " + t?.cause)
                Log.i(TAG, "ErrorL: " + t?.localizedMessage)
            }
        })
    }

    fun setData() {
        txtAppName = view12.findViewById(R.id.txtAppName1) as TextView
        txtAppInstall = view12.findViewById(R.id.txtAppInstall1) as TextView
        txtAppOffers = view12.findViewById(R.id.txtAppOffer1) as TextView
        btnInstall = view12.findViewById(R.id.btnInstall1) as Button
        appWebView = view12.findViewById(R.id.webView) as WebView

        appWebView.settings.javaScriptEnabled = true
        appWebView.webViewClient = object :  WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        appWebView.loadUrl(Uri.parse(al_category.get(int_position).url).toString())

        appWebView.isHorizontalScrollBarEnabled = false
        appWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        appWebView.isVerticalScrollBarEnabled = false

//        appWebView.isScrollContainer = false
//        appWebView.setOnTouchListener(View.OnTouchListener {
//            v, event -> false
//        })

        appWebView.setOnTouchListener(View.OnTouchListener {
            v, event -> event.action == MotionEvent.ACTION_MOVE
        })


        int_position = arguments.getInt(POSITION)
        txtAppName.text = al_category.get(int_position).appName
        txtAppOffers.text = al_category.get(int_position).offer.toString()
//        imgAppIcon.setImageResource(al_category.get(int_position).image.toInt())

        btnInstall.setOnClickListener {
            val webintent = Intent(Intent.ACTION_VIEW)
            webintent.data = Uri.parse(al_category.get(int_position).url)
            startActivity(webintent)

            var intentFilter = IntentFilter()

            val appId = al_category.get(int_position).id
            Log.i(TAG,"appid = "+appId)
            Prefs.saveAppId(context,appId)

            intentFilter.addDataScheme("package")
            var appReceiver: AppReceiver = AppReceiver()

            context.registerReceiver(appReceiver,intentFilter )
        }
    }
}


