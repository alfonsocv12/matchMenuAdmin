package com.example.matchamenueadmin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()

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
        uid?.let {
            db.collection("restaurant")
                .document(it)
                .get()
                .addOnSuccessListener {
                    dishList.adapter = ArrayAdapter(this, R.layout.dish_list, it.name)
                }
        }

    }

}
