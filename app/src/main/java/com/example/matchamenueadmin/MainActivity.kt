package com.example.matchamenueadmin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()
    lateinit var dishList: MutableList<Dish>
    val gson = GsonBuilder().setPrettyPrinting().create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        itemBtnAction()
    }

    private fun itemBtnAction(){
        btnCreate.setOnClickListener {
            startActivity(Intent(this, create::class.java))
        }
        val sharedPreferences = getSharedPreferences("MatchaMenuAdmin", Context.MODE_PRIVATE)
        var uid = sharedPreferences.getString("uid", "")
        dishList = mutableListOf()
        val adapter = DishAdapter(this, R.layout.dish_list, dishList)
        lvDishList.adapter = adapter
        uid?.let {
            db.collection("restaurant").document(it).get()
                .addOnCompleteListener {
                    val result = it.getResult()
                    result?.let {
                        val menu = result.get("menu").toString()
                        val menuObj = JSONArray(menu)
                        for (i in 0 until menuObj.length()) {
                            val item = menuObj.getJSONObject(i)
                            Log.d("ITEM", item.toString())
                            val dishTemp = Dish(item.getString("name"),item.getString("description"),item.getDouble("price"))
                            dishList.add(dishTemp)
                            adapter.notifyDataSetChanged()
                        }
                    }
                }
        }

    }

}
