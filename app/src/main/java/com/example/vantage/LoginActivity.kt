package com.example.vantage
import com.example.vantage.R
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvGoToRegister = findViewById<TextView>(R.id.tvGoToRegister)
        val tvAdmin = findViewById<TextView>(R.id.tvAdminAccess)

        // Go to Dashboard when login is clicked (Skipping auth for Sprint 1 UI)
        btnLogin.setOnClickListener {
            Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish() // Prevents going back to login screen
        }

        // Go to Register Screen
        tvGoToRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        tvAdmin.setOnClickListener {
            val intent = Intent(this, AdminLoginActivity::class.java)
            startActivity(intent)
        }
    }
}

//package com.example.vantage
//
//import android.content.Intent
//import android.os.Bundle
//import android.widget.TextView
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.google.android.material.button.MaterialButton
//
//class LoginActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
//
//        val btnLogin = findViewById<MaterialButton>(R.id.btnLogin)
//        val tvGoToRegister = findViewById<TextView>(R.id.tvGoToRegister)
//
//        btnLogin.setOnClickListener {
//            Toast.makeText(this, "Exploring Heritage...", Toast.LENGTH_SHORT).show()
//            val intent = Intent(this, DashboardActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
//
//        tvGoToRegister.setOnClickListener {
//            val intent = Intent(this, RegisterActivity::class.java)
//            startActivity(intent)
//        }
//    }
//}

//package com.example.vantage
//
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Button
//import android.widget.EditText
//import android.widget.TextView
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//
//class LoginActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
//
//        // 1. Initialize UI Elements
//        // Ensure these IDs match your activity_login.xml exactly
//        val etUsername = findViewById<EditText>(R.id.etUsername)
//        val etPassword = findViewById<EditText>(R.id.etPassword)
//        val btnLogin = findViewById<Button>(R.id.btnLogin)
//        val tvRegister = findViewById<TextView>(R.id.tvRegisterLink) // This one was red in your screenshot
//        val tvAdminAccess = findViewById<TextView>(R.id.tvAdminAccess)
//        // 2. User Login Logic
//        btnLogin.setOnClickListener {
//            val user = etUsername.text.toString()
//            val pass = etPassword.text.toString()
//
//            // Basic check for student project demo
//            if (user.isNotEmpty() && pass.isNotEmpty()) {
//                val intent = Intent(this, DashboardActivity::class.java)
//                startActivity(intent)
//                finish() // User is logged in, close the login screen
//            } else {
//                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        // 3. Link to Register Screen
//        tvRegister.setOnClickListener {
//            val intent = Intent(this, RegisterActivity::class.java)
//            startActivity(intent)
//        }
//
//        // 4. Link to Admin Login Screen
//        tvAdminAccess.setOnClickListener {
//            val intent = Intent(this, AdminLoginActivity::class.java)
//            startActivity(intent)
//        }
//    }
//}