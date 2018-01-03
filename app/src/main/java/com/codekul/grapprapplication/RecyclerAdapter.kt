package com.codekul.grapprapplication

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.codekul.grapprapplication.domain.Apps

/**
 * Created by sonal on 29/12/17.
 */

class RecyclerAdapter(val list: ArrayList<Apps>, val listener : (Int) -> Unit) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    lateinit var context: Context
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindItems(list[position], position, listener)

//        holder?.itemView?.setOnClickListener(View.OnClickListener {
//            var intent = Intent(context, TabbedAppActivity::class.java)
//            startActivity(intent)
//           Log.i("@codekul","item click"+list[position])


//        })
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.recycler_layout, parent, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val view = view
        fun bindItems(data: Apps, pos: Int, listener: (Int) -> Unit) = with(itemView) {

            val textView1: TextView = itemView.findViewById(R.id.txtAppName)
            val textView2: TextView = itemView.findViewById(R.id.txtAppCategory)
            val textView3: TextView = itemView.findViewById(R.id.txtRupee)

//            val imageView:ImageView = itemView.findViewById(R.id.imgAppIcon)

            textView1.text = data.name
            textView2.text = data.category
            textView3.text = data.offer.toString()
            //imageView.setImageResource(data.imgIcon)

            itemView.setOnClickListener {
                listener(pos)

            }
        }
    }
}