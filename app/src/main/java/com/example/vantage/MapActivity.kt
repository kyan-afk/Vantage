package com.example.vantage

import android.graphics.ColorMatrixColorFilter
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class MapActivity : AppCompatActivity() {

    private lateinit var map: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Initialize OSMDroid Configuration
        Configuration.getInstance().load(
            this, PreferenceManager.getDefaultSharedPreferences(this)
        )

        setContentView(R.layout.activity_map)

        // 2. Setup Map View and basic controls
        map = findViewById(R.id.mapView)
        map.setTileSource(TileSourceFactory.MAPNIK)
        map.setMultiTouchControls(true)

        val mapController = map.controller
        val tvTargetName = findViewById<TextView>(R.id.tvMapLocationName)

        // 3. Catch the site name passed from Dashboard/SiteDetails
        val selectedSiteName = intent.getStringExtra("siteName")
        tvTargetName.text = "Target: ${selectedSiteName ?: "Cebu Heritage"}"

        // 4. Define your Heritage Sites (Coordinates for Cebu City)
        val sites = listOf(
            Pair("Fort San Pedro", GeoPoint(10.2952, 123.9054)),
            Pair("Museo Sugbo", GeoPoint(10.3015, 123.9056)),
            Pair("Casa Gorordo Museum", GeoPoint(10.2990, 123.9038)),
            Pair("Yap-Sandiego Ancestral House", GeoPoint(10.2995, 123.9031))
        )

        // 5. Loop to add markers and focus on the selected site
        for (site in sites) {
            val marker = Marker(map)
            marker.position = site.second
            marker.title = site.first
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)

            // If this site matches the one clicked in the Dashboard
            if (site.first.equals(selectedSiteName, ignoreCase = true)) {
                mapController.setZoom(18.5)
                mapController.setCenter(site.second)
                marker.showInfoWindow() // This shows the name label automatically
            }

            map.overlays.add(marker)
        }

        // Default view if no site was selected (or if name didn't match)
        if (selectedSiteName == null) {
            mapController.setZoom(16.0)
            mapController.setCenter(GeoPoint(10.2985, 123.9045))
        }

        // 6. Handle the Return button
        findViewById<Button>(R.id.btnMapReturn).setOnClickListener {
            finish()
        }

        // 7. Apply the Vintage / Heritage Filter
        val vintageMatrix = floatArrayOf(
            0.9f, 0f, 0f, 0f, 20f,
            0f, 0.8f, 0f, 0f, 10f,
            0f, 0f, 0.7f, 0f, 0f,
            0f, 0f, 0f, 1f, 0f
        )
        map.overlayManager.tilesOverlay.setColorFilter(ColorMatrixColorFilter(vintageMatrix))
    }

    // Standard OSM lifecycle management
    override fun onResume() {
        super.onResume()
        if (::map.isInitialized) map.onResume()
    }

    override fun onPause() {
        super.onPause()
        if (::map.isInitialized) map.onPause()
    }
}