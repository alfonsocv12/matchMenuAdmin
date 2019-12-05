package com.example.matchamenueadmin

import android.content.Context
import android.content.Intent
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
        val sharedPreferences = getSharedPreferences("MatchaMenuAdmin", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        try {
            val user = db.collection("restaurant")
                .whereEqualTo("email",email)
                .whereEqualTo("password",password)
                .get()
                .addOnSuccessListener { docs ->
                    for (doc in docs!!) {
                        //Toast.makeText(this, doc.getString("email"), Toast.LENGTH_LONG).show()
                        editor.putString("email",doc.getString("email"))
                        editor.commit()
                    }
                }
            startActivity(Intent(this, MainActivity::class.java))
        } catch (err: Error) {
            Toast.makeText(this,"¡Correo o usuario no encontrado!", Toast.LENGTH_LONG).show()
        }
    }
}
