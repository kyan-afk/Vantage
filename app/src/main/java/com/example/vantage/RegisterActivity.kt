package com.example.vantage
import com.example.vantage.R
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnRegister = findViewById<Button>(R.id.btnRegister)

        btnRegister.setOnClickListener {
            // Just show a message and close the register screen for now
            Toast.makeText(this, "Account Created! Please Login.", Toast.LENGTH_SHORT).show()
            finish() // Goes back to LoginActivity
        }
    }
}

//package com.example.vantage
//
//import android.os.Bundle
//import android.widget.ImageButton
//import android.widget.TextView
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.google.android.material.button.MaterialButton
//import com.google.android.material.textfield.TextInputEditText
//
//class RegisterActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_register)
//
//        // Updated IDs to match your new XML[cite: 1]
//        val etFullName = findViewById<TextInputEditText>(R.id.etFullName)
//        val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
//        val btnRegister = findViewById<MaterialButton>(R.id.btnRegister)
//        val btnBack = findViewById<ImageButton>(R.id.btnBack)
//        val tvGoToLogin = findViewById<TextView>(R.id.tvGoToLogin)
//
//        btnRegister.setOnClickListener {
//            val name = etFullName.text.toString()
//            Toast.makeText(this, "Welcome, $name! Account Created.", Toast.LENGTH_SHORT).show()
//            finish()
//        }
//
//        btnBack.setOnClickListener { finish() }
//        tvGoToLogin.setOnClickListener { finish() }
//    }
//}