package com.codekul.grapprapplication.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.codekul.grapprapplication.R
import com.codekul.grapprapplication.fragments.*
import com.codekul.grapprapplication.sharedPreference.Prefs
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var fragment: Fragment? = null

    lateinit var mobileNo : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        //load home fragment
        fragment = HomeFragment()
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.contentFrame, fragment)
        ft.commit()

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        //set user mobile number
        var navigationView = findViewById(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
        var header : View = navigationView.getHeaderView(0)
        mobileNo = header.findViewById(R.id.txtNumber)
        var mn : String = Prefs.getUserMobile(this)
        Log.i("@codekul","mobileno : "+mn)
        mobileNo.text = mn

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_wallet -> showDialog("alert")
            R.id.action_refresh -> return true
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        displaySelectedScreen(item.getItemId())
        return true
    }

    private fun displaySelectedScreen(itemId: Int) {
        //initializing the fragment object which is selected
        when (itemId) {

            R.id.nav_home ->  fragment = HomeFragment()
            R.id.nav_referrals ->  fragment = ReferralsFragment()
            R.id.nav_notifications -> fragment = NotiFragment()
            R.id.nav_wallet -> showDialog("alert")
            R.id.nav_faqs -> fragment = FaqsFragment()
            R.id.nav_aboutus -> fragment = AboutusFragment()
            R.id.nav_emailus -> showDialog("")
            R.id.nav_logout -> {
                Prefs.clearUserData(applicationContext)
                startActivity(Intent(applicationContext, LoginActivity::class.java))

            }
        }

        //replacing the fragment
        if (fragment != null) {
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.contentFrame, fragment)
            ft.addToBackStack(null)
            ft.commit()
        }
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
    }

    private fun showDialog(tag: String) {
        val diafrag = Wallet_fragment()
        diafrag.show(supportFragmentManager, tag)
    }
}
