package com.codekul.grapprapplication.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.codekul.grapprapplication.R

/**
 * Created by sonal on 29/12/17.
 */
class ReferralsFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return  inflater!!.inflate(R.layout.referrals_layout,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity.setTitle("Referrals")
    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)

        var wallet : MenuItem
        wallet = menu!!.findItem(R.id.action_wallet)
        wallet.setVisible(false)

        var settings : MenuItem
        settings = menu?.findItem(R.id.action_settings)
        settings.setVisible(false)

    }
}