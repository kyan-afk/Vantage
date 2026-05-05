//package com.example.vantage
//
//import android.os.Bundle
//import android.widget.Button
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//
//class MapActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_map)
//
//        // Set the location name passed from the previous screen
//        val locationName = intent.getStringExtra("siteName") ?: "Heritage Site"
//        findViewById<TextView>(R.id.tvMapLocationName).text = "Target: $locationName"
//
//        val btnReturn = findViewById<Button>(R.id.btnMapReturn)
//        btnReturn.setOnClickListener {
//            finish() // Returns to SiteDetailsActivity
//        }
//    }
//}

package com.example.vantage

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        // Set the location name passed from the previous screen
        val locationName = intent.getStringExtra("siteName") ?: "Heritage Site"
        findViewById<TextView>(R.id.tvMapLocationName).text = "Target: $locationName"

        val btnReturn = findViewById<Button>(R.id.btnMapReturn)
        btnReturn.setOnClickListener {
            finish() // Returns to SiteDetailsActivity
        }
    }
}