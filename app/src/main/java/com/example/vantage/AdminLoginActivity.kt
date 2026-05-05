package com.example.vantage

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AdminLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)

        val etUser = findViewById<EditText>(R.id.etAdminUsername)
        val etPass = findViewById<EditText>(R.id.etAdminPassword)
        val btnLogin = findViewById<Button>(R.id.btnAdminLogin)

        btnLogin.setOnClickListener {
            val username = etUser.text.toString()
            val password = etPass.text.toString()

            // Simple demo credentials: admin / 1234
            if (username == "admin" && password == "1234") {
                val intent = Intent(this, AdminPanelActivity::class.java)
                startActivity(intent)
                finish() // Closes the login so they can't go back to it
            } else {
                Toast.makeText(this, "Wrong Admin Credentials!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}