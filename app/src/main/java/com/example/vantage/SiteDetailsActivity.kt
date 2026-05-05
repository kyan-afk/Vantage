package com.example.vantage
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SiteDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        // Get data from the Dashboard
        // Add this inside onCreate
        val btnReturn = findViewById<Button>(R.id.btnReturn)
        btnReturn.setOnClickListener {
            finish() // This takes you back to the Dashboard
        }
        val name = intent.getStringExtra("siteName")
        val desc = intent.getStringExtra("siteDesc")
        val hours = intent.getStringExtra("siteHours")
        val fee = intent.getStringExtra("siteFee")

        // Link to your activity_details.xml IDs
        findViewById<TextView>(R.id.tvDetailName).text = name
        findViewById<TextView>(R.id.tvDetailDesc).text = desc
        findViewById<TextView>(R.id.tvDetailHours).text = hours
        findViewById<TextView>(R.id.tvDetailFee).text = fee

        val btnViewMap = findViewById<Button>(R.id.btnViewMap)
        btnViewMap.setOnClickListener {
            val intentMap = Intent(this, MapActivity::class.java)
            // Pass the site name so the map title shows correctly
            intentMap.putExtra("siteName", intent.getStringExtra("siteName"))
            startActivity(intentMap)
        }

        val btnOpen3D = findViewById<Button>(R.id.btnOpen3D)
        btnOpen3D.setOnClickListener {
            // This will lead to your virtual walkthrough later!
            val intent3D = Intent(this, FloorplanActivity::class.java)
            startActivity(intent3D)
        }
    }
}