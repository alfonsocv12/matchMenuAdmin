package com.example.matchamenueadmin

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import android.os.Bundle
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
            val user = db.collection("restaurant")
                .whereEqualTo("email",email)
                .whereEqualTo("password",password)
                .get()
                .addOnSuccessListener { docs ->
                    for (doc in docs!!) {
                        Toast.makeText(this, doc.id, Toast.LENGTH_LONG).show()
                    }
                }
            //saveUidShared(user.uid)

            val sharedPreferences = getSharedPreferences("MatchaMenuAdmin", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            editor.putString("uid",user.uid)
            editor.commit()

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
