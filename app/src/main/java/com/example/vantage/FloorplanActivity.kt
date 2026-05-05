package com.example.vantage

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FloorplanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_floorplan)

        // Find the exit button from your XML
        val btnExit = findViewById<Button>(R.id.btnExit3D)

        btnExit.setOnClickListener {
            finish() // Goes back to the details screen
        }

        // Simple Toast to show the 3D mode is "active"
        Toast.makeText(this, "Entering 3D Walkthrough...", Toast.LENGTH_SHORT).show()
    }
}
