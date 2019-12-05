package com.example.matchamenueadmin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class DishAdapter(val mCtx: Context, val layoutResId:Int, val  dishList: List<Dish>)
    : ArrayAdapter<Dish>(mCtx,layoutResId,dishList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId,null)
        val textViewName = view.findViewById<TextView>(R.id.textViewName)
        val textViewDesc = view.findViewById<TextView>(R.id.textViewDescription)
        val textViewPrice= view.findViewById<TextView>(R.id.textViewPrice)
        val dish = dishList[position]
        textViewName.text = dish.name
        textViewDesc.text = dish.description
        textViewPrice.text = dish.price.toString()
        return view
    }

}