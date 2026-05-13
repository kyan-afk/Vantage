//package com.example.vantage
//
//import android.graphics.Color
//import android.graphics.ColorMatrixColorFilter
//import android.os.Bundle
//import android.preference.PreferenceManager
//import android.widget.Button
//import android.widget.TextView
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import org.osmdroid.bonuspack.routing.OSRMRoadManager
//import org.osmdroid.bonuspack.routing.RoadManager
//import org.osmdroid.config.Configuration
//import org.osmdroid.tileprovider.tilesource.TileSourceFactory
//import org.osmdroid.util.GeoPoint
//import org.osmdroid.views.MapView
//import org.osmdroid.views.overlay.Marker
//import org.osmdroid.views.overlay.Polyline
//import kotlin.concurrent.thread

package com.example.vantage

import android.graphics.Color
import android.graphics.ColorMatrixColorFilter
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.osmdroid.bonuspack.routing.OSRMRoadManager
import org.osmdroid.bonuspack.routing.RoadManager
import org.osmdroid.bonuspack.routing.Road
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Polyline
import kotlin.concurrent.thread

class MapActivity : AppCompatActivity() {

    private lateinit var map: MapView
    private var roadOverlay: Polyline? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Initialize OSMDroid
//        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this))
        Configuration.getInstance().userAgentValue = "VantageHeritageProject/1.0"
        setContentView(R.layout.activity_map)

        // 2. Setup MapView
        map = findViewById(R.id.mapView)
        map.setTileSource(TileSourceFactory.MAPNIK)
        map.setMultiTouchControls(true)
        val mapController = map.controller

        // 3. Define Cebu Heritage Sites
        val sites = listOf(
            Pair("Fort San Pedro", GeoPoint(10.2952, 123.9054)),
            Pair("Museo Sugbo", GeoPoint(10.3015, 123.9056)),
            Pair("Casa Gorordo Museum", GeoPoint(10.2990, 123.9038)),
            Pair("Yap-Sandiego Ancestral House", GeoPoint(10.2995, 123.9031))
        )

        // 4. Handle Data from Dashboard
        val selectedSiteName = intent.getStringExtra("siteName")
        val tvName = findViewById<TextView>(R.id.tvMapLocationName)
        tvName.text = "Target: ${selectedSiteName ?: "Heritage Site"}"

        // 5. User Starting Point (Cebu City Hall)
        val userLoc = GeoPoint(10.2929, 123.9015)
        var targetCoords: GeoPoint? = null

        // 6. Add Markers
        for (site in sites) {
            val marker = Marker(map)
            marker.position = site.second
            marker.title = site.first
            map.overlays.add(marker)

            if (site.first.equals(selectedSiteName, ignoreCase = true)) {
                targetCoords = site.second
                marker.showInfoWindow()
                mapController.setCenter(site.second)
                mapController.setZoom(17.5)
            }
        }

        // 7. Route Buttons (Driving vs. Commuting)
        val btnDrive = findViewById<Button>(R.id.btnDrive)
        val btnCommute = findViewById<Button>(R.id.btnCommute)

        btnDrive.setOnClickListener {
            if (targetCoords != null) {
                calculateRoute(userLoc, targetCoords!!, "driving")
            } else {
                Toast.makeText(this, "Select a site from Dashboard first", Toast.LENGTH_SHORT).show()
            }
        }

        btnCommute.setOnClickListener {
            if (targetCoords != null) {
                calculateRoute(userLoc, targetCoords!!, "commuting")
            } else {
                Toast.makeText(this, "Select a site from Dashboard first", Toast.LENGTH_SHORT).show()
            }
        }

        // 8. Return Button & Vintage Filter
        findViewById<Button>(R.id.btnMapReturn).setOnClickListener { finish() }
        applyVintageFilter()
    }

//    private fun calculateRoute(start: GeoPoint, end: GeoPoint, mode: String) {
//        thread {
//            // We use the OSRM Road Manager
//            val roadManager = OSRMRoadManager(this, "VantageHeritageApp/1.0")
//
//            // Use direct strings to bypass the "Unresolved Reference" error
//            if (mode == "driving") {
//                roadManager.setMean("fastest") // This is what METHOD_CAR refers to
//            } else {
//                roadManager.setMean("foot")    // This is what METHOD_PEDESTRIAN refers to
//            }
//
//            val waypoints = arrayListOf(start, end)
//            val road = roadManager.getRoad(waypoints)
//
//            if (road.mStatus == Road.STATUS_OK) {
//                runOnUiThread {
//                    roadOverlay?.let { map.overlays.remove(it) }
//
//                    roadOverlay = RoadManager.buildRoadOverlay(road)
//                    roadOverlay?.outlinePaint?.color = Color.parseColor("#4A90E2")
//                    roadOverlay?.outlinePaint?.strokeWidth = 12f
//
//                    map.overlays.add(roadOverlay)
//                    map.invalidate()
//                    Toast.makeText(this, "Route Found", Toast.LENGTH_SHORT).show()
//                }
//            } else {
//                runOnUiThread {
//                    Toast.makeText(this, "Route error: Check internet", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//    }
private fun calculateRoute(start: GeoPoint, end: GeoPoint, mode: String) {
    thread {
        val roadManager = OSRMRoadManager(this, "VantageHeritageProject/1.0")

        // Increase connection timeout for slower internet/school Wi-Fi
        roadManager.setService("https://routing.openstreetmap.de/routed-foot/route/v1/")

        if (mode == "driving") {
            roadManager.setMean("fastest")
        } else {
            roadManager.setMean("foot")
        }

        val waypoints = arrayListOf(start, end)
        val road = roadManager.getRoad(waypoints)

        runOnUiThread {
            if (road.mStatus == Road.STATUS_OK) {
                roadOverlay?.let { map.overlays.remove(it) }
                roadOverlay = RoadManager.buildRoadOverlay(road)
                roadOverlay?.outlinePaint?.color = Color.BLUE
                map.overlays.add(roadOverlay)
                map.invalidate()
                Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show()
            } else {
                // If it fails, this will show the exact error from the server
                Toast.makeText(this, "Status: ${road.mStatus}", Toast.LENGTH_LONG).show()
            }
        }
    }
}

    private fun applyVintageFilter() {
        val vintageMatrix = floatArrayOf(
            0.9f, 0f, 0f, 0f, 20f,
            0f, 0.8f, 0f, 0f, 10f,
            0f, 0f, 0.7f, 0f, 0f,
            0f, 0f, 0f, 1f, 0f
        )
        map.overlayManager.tilesOverlay.setColorFilter(ColorMatrixColorFilter(vintageMatrix))
    }

    override fun onResume() { super.onResume(); if (::map.isInitialized) map.onResume() }
    override fun onPause() { super.onPause(); if (::map.isInitialized) map.onPause() }
}