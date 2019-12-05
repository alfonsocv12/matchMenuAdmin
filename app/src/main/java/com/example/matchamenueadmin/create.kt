package com.example.matchamenueadmin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.android.synthetic.main.activity_create.*
import kotlin.collections.ArrayList

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
            uid?.let { uid ->
                db.collection("restaurant").document(uid).get()
                    .addOnCompleteListener {
                        val result = it.getResult()
                        result?.let {
                            val menu = result.get("menu")
                            if (menu is ArrayList<*>){
                                val menulisto = menu.toMutableList()
                                menulisto.add(platillo as Map<*, *>)
                                val menuListo = hashMapOf(
                                    "menu" to menulisto
                                )
                                db.collection("restaurant").document(uid).set(menuListo, SetOptions.merge())
                                    .addOnSuccessListener {
                                        Toast.makeText(this, "Se agrego nuevo platillo", Toast.LENGTH_LONG).show()
                                        startActivity(Intent(this, MainActivity::class.java))
                                    }
                                    .addOnCanceledListener {
                                        Toast.makeText(this, "no entro", Toast.LENGTH_LONG).show()
                                    }
                            }
                        }
                    }
            }
        }
    }

}
