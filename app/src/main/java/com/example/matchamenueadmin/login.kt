package com.example.matchamenueadmin

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import android.os.Bundle

class login : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }

    private  fun getUser(email: String){
        db.collection('restaurant').whereEqualTo('email',email)
    }

    private fun saveUidShared(uid: String){
        val prefs: SharedPreferences = contex
    }
}
