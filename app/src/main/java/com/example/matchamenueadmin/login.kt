package com.example.matchamenueadmin

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class login : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            getUser(etEmail.text.toString(),etPass.text.toString())
        }

    }

    private  fun getUser(email: String, password: String){
        try {
            var user = db.collection("restaurant").whereEqualTo("email",email).whereEqualTo("password",password)
            Log.d("USER", user.toString())
            //saveUidShared(user.uid)
        } catch (err: Error) {
            Toast.makeText(this,"¡Correo o usuario no encontrado!", Toast.LENGTH_LONG).show()
        }
    }

    /*
    private fun saveUidShared(uid: String){
        val prefs: SharedPreferences = contex
    }
     */
}
