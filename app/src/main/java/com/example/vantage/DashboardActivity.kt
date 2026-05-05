package com.example.vantage

import com.example.vantage.R
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Button

class DashboardActivity : AppCompatActivity() {

    private lateinit var adapter: SiteAdapter
    private val siteList = ArrayList<HeritageSite>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val listViewSites = findViewById<ListView>(R.id.listViewSites)
        val etSearch = findViewById<EditText>(R.id.etSearch)

        // ---------------------------------------------------------
        // TO ADD MORE PLACES: Just copy and paste one of these blocks!
        // ---------------------------------------------------------
        siteList.add(HeritageSite(
            name = "Fort San Pedro",
            description = "A military defense structure built by the Spanish.",
            hours = "8:00 AM - 5:00 PM",
            fee = "Entrance: ₱30"
        ))

        siteList.add(HeritageSite(
            name = "Museo Sugbo",
            description = "The provincial museum housed in a former prison.",
            hours = "9:00 AM - 6:00 PM",
            fee = "Entrance: ₱50"
        ))

        siteList.add(HeritageSite(
            name = "Casa Gorordo Museum",
            description = "A historic house showcasing 19th-century lifestyle.",
            hours = "9:00 AM - 5:00 PM",
            fee = "Entrance: ₱120"
        ))

        siteList.add(HeritageSite(
            name = "Yap-Sandiego Ancestral House",
            description = "One of the oldest residential houses in the country.",
            hours = "8:00 AM - 7:00 PM",
            fee = "Entrance: ₱50"
        ))
        // ---------------------------------------------------------

        adapter = SiteAdapter(this, siteList)
        listViewSites.adapter = adapter

        // Click listener for when a user taps a place
//        listViewSites.setOnItemClickListener { _, _, position, _ ->
//            val selectedSite = adapter.getItem(position)
//            Toast.makeText(this, "Clicked on ${selectedSite?.name}", Toast.LENGTH_SHORT).show()
//            // In Sprint 3, this is where you'll open the 3D Floorplan activity!
//        }

        listViewSites.setOnItemClickListener { _, _, position, _ ->
            val selectedSite = adapter.getItem(position)
            val intent = Intent(this, SiteDetailsActivity::class.java)

            // Passing data to the next screen

            intent.putExtra("siteName", selectedSite?.name)
            intent.putExtra("siteDesc", selectedSite?.description)
            intent.putExtra("siteHours", selectedSite?.hours)
            intent.putExtra("siteFee", selectedSite?.fee)

            startActivity(intent)
        }

        // Simple Search Filter
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                adapter.filter.filter(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    // Custom Adapter class to link data to the item_site.xml layout
    inner class SiteAdapter(context: AppCompatActivity, private val list: ArrayList<HeritageSite>) :
        ArrayAdapter<HeritageSite>(context, 0, list) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var view = convertView
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.item_site, parent, false)
            }

            val currentSite = getItem(position)

            val tvName = view!!.findViewById<TextView>(R.id.tvSiteName)
            val tvDesc = view.findViewById<TextView>(R.id.tvSiteDesc)
            val tvHours = view.findViewById<TextView>(R.id.tvSiteHours)
            val tvFee = view.findViewById<TextView>(R.id.tvSiteFee)

            tvName.text = currentSite?.name
            tvDesc.text = currentSite?.description
            tvHours.text = currentSite?.hours
            tvFee.text = currentSite?.fee

            return view
        }
    }
}