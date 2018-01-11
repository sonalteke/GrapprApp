package com.codekul.grapprapplication.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.codekul.grapprapplication.R
import com.codekul.grapprapplication.domain.Apps
import com.codekul.grapprapplication.domain.AppsInfo
import com.codekul.grapprapplication.rest.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */

class AppsFragment : Fragment() {

    var al_category = ArrayList<Apps>()
    var int_position: Int = 0
    lateinit var imgAppIcon: ImageView
    lateinit var txtAppName: TextView
    lateinit var txtAppInstall: TextView
    lateinit var txtAppOffers: TextView
    lateinit var btnInstall: Button
    lateinit var view12: View
    lateinit var adapter : Adapter
    lateinit var viewPager : ViewPager

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        view12 = inflater!!.inflate(R.layout.fragment_apps, container, false)

        arraylist()
        return view12
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity.setTitle("Apps")
    }

    fun arraylist() {

        val apiservice = ApiService.create()
        val call = apiservice.getApps()
        Log.i("@codekul", "Url: " + call.request().url().toString())

        call.enqueue(object : Callback<AppsInfo> {

            override fun onResponse(call: Call<AppsInfo>?, response: Response<AppsInfo>?) {
                val list: AppsInfo? = response?.body()
                Log.i("@codekul", "Response:" + list)
                val list1 = list?.result
                list1?.forEach { app ->
                    Apps(app.id, app.appName, app.url, app.offer, app.category, app.date)
                    al_category.add(app)
                }

//                viewPager = view12.findViewById<ViewPager>(R.id.viewPager)
//                val pageAdapter = ViewPagerAdapter(al_category)
//                viewPager.adapter = pageAdapter
//                viewPager.adapter = this
//
//                val pageradapter = view12.findViewById<ViewPager>(R.id.viewPager) as ViewPager
//                pageAdapter.layoutManager =

                Log.i("@codekul", "List : " + list1)
                setData()
            }

            override fun onFailure(call: Call<AppsInfo>?, t: Throwable?) {
                Log.i("@codekul", "ErrorM: " + t?.message)
                Log.i("@codekul", "ErrorC: " + t?.cause)
                Log.i("@codekul", "ErrorL: " + t?.localizedMessage)
            }
        })
    }

    fun setData() {
        imgAppIcon = view12.findViewById(R.id.imgAppIcon) as ImageView
        txtAppName = view12.findViewById(R.id.txtAppName) as TextView
        txtAppInstall = view12.findViewById(R.id.txtAppInstall) as TextView
        txtAppOffers = view12.findViewById(R.id.txtAppOffer) as TextView
        btnInstall = view12.findViewById(R.id.btnInstall) as Button

        int_position = arguments.getInt("position")
        txtAppName.text = al_category.get(int_position).appName
        txtAppOffers.text = al_category.get(int_position).offer.toString()

        btnInstall.setOnClickListener{
            val webintent = Intent(Intent.ACTION_VIEW)
            webintent.data = Uri.parse(al_category.get(int_position).url)
            startActivity(webintent)
        }
    }// Required empty public constructor
}