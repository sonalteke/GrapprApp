package com.codekul.grapprapplication.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.codekul.grapprapplication.R
import com.codekul.grapprapplication.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_app_list.*

class AppListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_list)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val obj_adapter: ViewPagerAdapter
        obj_adapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.adapter = obj_adapter
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        onBackPressed()
        return true
    }
}


