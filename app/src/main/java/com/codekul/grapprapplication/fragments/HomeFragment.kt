package com.codekul.grapprapplication.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.widget.LinearLayout
import com.codekul.grapprapplication.activity.AppListActivity
import com.codekul.grapprapplication.R
import com.codekul.grapprapplication.adapter.CategoryAdapter
import com.codekul.grapprapplication.domain.Category
import kotlinx.android.synthetic.main.home_layout.*


/**
 * Created by sonal on 29/12/17.
 */

class HomeFragment : Fragment() {

    var fragment: Fragment? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater?.inflate(R.layout.home_layout,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity.title = "CashBolo"
        category()
    }

    private fun category(){

        recyclerView.layoutManager = LinearLayoutManager(context,LinearLayout.VERTICAL,false)

        val items = ArrayList<Category>()
        items.add(Category("Install Apps"))
        items.add(Category("Watch Videos"))
        items.add(Category("Write Reviews"))
        items.add(Category("Complete Surveys"))
        items.add(Category("Join Whatsapp Groups"))

        val adapter = CategoryAdapter(items){
            val intent = Intent(context, AppListActivity::class.java)
            startActivity(intent)
        }
        recyclerView.adapter  =  adapter
    }
}




