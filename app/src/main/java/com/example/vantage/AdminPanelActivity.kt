package com.example.vantage

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AdminPanelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_panel)

        val btnAdd = findViewById<Button>(R.id.btnAddSite)
        val btnExit = findViewById<Button>(R.id.btnAdminExit)
        val etName = findViewById<EditText>(R.id.etSiteName)

        btnAdd.setOnClickListener {
            val name = etName.text.toString()
            if (name.isNotEmpty()) {
                // Just a placeholder message for now
                Toast.makeText(this, "Site $name updated successfully!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter a site name", Toast.LENGTH_SHORT).show()
            }
        }

        btnExit.setOnClickListener {
            finish() // Goes back to the previous screen
        }
    }
}