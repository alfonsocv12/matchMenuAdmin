package com.example.matchamenueadmin

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_create.*

class create : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        saveBtn()
    }

    private fun saveBtn(){
        btnSave.setOnClickListener {
            val platillo = hashMapOf(
                "name" to nameET.text.toString(),
                "price" to priceET.text.toString().toDouble(),
                "description" to descriptionET.text.toString()
            )
            val sharedPreferences = getSharedPreferences("MatchaMenuAdmin", Context.MODE_PRIVATE)
            val uid = sharedPreferences.getString("uid","")
            uid?.let {
                db.collection("restaurant").document(it).get()
                    .addOnCompleteListener {
                        val result = it.getResult()
                        result?.let {
                            Toast.makeText(this, result.get("menu").toString(), Toast.LENGTH_LONG).show()
                        }
                    }
            }
        }
    }
}
