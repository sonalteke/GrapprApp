package com.codekul.grapprapplication.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.codekul.grapprapplication.R
import com.codekul.grapprapplication.domain.Category

/**
 * Created by sonal on 5/1/18.
 */
class CategoryAdapter(val list: ArrayList<Category>, val listener : (Int) -> Unit) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindItems(list[position], position, listener)

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) : ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.category_layout, parent, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val view = view
        fun bindItems(data: Category, pos: Int, listener: (Int) -> Unit) = with(itemView) {

            val textView1: TextView = itemView.findViewById(R.id.txtCategoryName)

            textView1.text = data.name

            itemView.setOnClickListener {
                listener(pos)
            }
        }
    }
}