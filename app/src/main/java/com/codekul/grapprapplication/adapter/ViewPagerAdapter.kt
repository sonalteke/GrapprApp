package com.codekul.grapprapplication.adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.codekul.grapprapplication.fragments.AppsFragment
import com.codekul.grapprapplication.domain.Apps

/**
 * Created by sonal on 6/1/18.
 */
class ViewPagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {

    var position:Int = 5
    var al_category = ArrayList<Apps>()
    lateinit var imgAppIcon: ImageView
    lateinit var txtAppName: TextView
    lateinit var txtAppInstall: TextView
    lateinit var txtAppOffers: TextView
    lateinit var btnInstall: Button
    lateinit var view12: View
    lateinit var context: Context

//init {
//    this.context = view12.context
//}

    override fun getItem(position: Int): AppsFragment {

        var bundle : Bundle = Bundle()
        bundle.putInt("position",position)

        var appsFragment: AppsFragment = AppsFragment()
        appsFragment.arguments = bundle

//        imgAppIcon = view12.findViewById(R.id.imgAppIcon) as ImageView
//        txtAppName = view12.findViewById(R.id.txtAppName) as TextView
//        txtAppInstall = view12.findViewById(R.id.txtAppInstall) as TextView
//        txtAppOffers = view12.findViewById(R.id.txtAppOffer) as TextView
//        btnInstall = view12.findViewById(R.id.btnInstall) as Button
//
//        txtAppName.text = al_category.get(position).appName
//        txtAppOffers.text = al_category.get(position).offer.toString()
//
//        btnInstall.setOnClickListener{
//            Log.i("@melayer","id: " +al_category.get(position).id)
//            Log.i("@melayer","id: " +al_category.get(position).url)
//            val webintent = Intent(Intent.ACTION_VIEW)
//            webintent.data = Uri.parse(al_category.get(position).url)
//            startActivity(webintent)
//        }
        return appsFragment
    }

    override fun getCount(): Int {
        return 5
//        return AppsFragment().al_category.size

    }
}