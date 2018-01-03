package com.codekul.grapprapplication.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.codekul.grapprapplication.R
import com.codekul.grapprapplication.RecyclerAdapter
import com.codekul.grapprapplication.SliderActivity
import com.codekul.grapprapplication.domain.Apps
import com.codekul.grapprapplication.rest.ApiService
import kotlinx.android.synthetic.main.home_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by sonal on 29/12/17.
 */

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.home_layout,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity.setTitle("Offers")
        recycler()
    }

     fun recycler() {
         recyclerView.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)

         val items = ArrayList<Apps>()
         val apiservice = ApiService.create()
         val call = apiservice.getApps()
         call.enqueue(object : Callback<List<Apps>> {

             override fun onResponse(call: Call<List<Apps>>?, response: Response<List<Apps>>?) {
                 val list: List<Apps>? = response?.body()
                 Log.i("@codekul","Response:" +list)
                 list?.forEach {
                     app ->Apps(app.id,app.name,app.category,app.offer,app.image)
                      items.add(app)
                 }
                 val adapter = RecyclerAdapter(items){
                     val intent = Intent(context,SliderActivity::class.java)
                     startActivity(intent)
                 }
                 //adding the adapter to recyclerview
                 recyclerView.adapter = adapter


             }

             override fun onFailure(call: Call<List<Apps>>?, t: Throwable?) {
                Log.i("@codekul","Error" +t?.message)
             }
         })
     }


}




