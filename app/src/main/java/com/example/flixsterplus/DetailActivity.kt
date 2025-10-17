package com.example.flixsterplus

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val ivPoster: ImageView = findViewById(R.id.ivKnownForPoster)
        val tvName: TextView = findViewById(R.id.tvPersonName)
        val tvDept: TextView = findViewById(R.id.tvDepartment)
        val tvPop: TextView = findViewById(R.id.tvPopularity)
        val tvKnownTitle: TextView = findViewById(R.id.tvKnownForTitle)
        val tvOverview: TextView = findViewById(R.id.tvOverview)

        val name = intent.getStringExtra("name") ?: ""
        val dept = intent.getStringExtra("department") ?: "—"
        val pop = intent.getDoubleExtra("popularity", 0.0)
        val knownTitle = intent.getStringExtra("known_title") ?: "—"
        val overview = intent.getStringExtra("known_overview") ?: "—"
        val posterPath = intent.getStringExtra("known_poster")

        tvName.text = name
        tvDept.text = "Department: $dept"
        tvPop.text = "Popularity: ${"%.1f".format(pop)}"
        tvKnownTitle.text = "Known for: $knownTitle"
        tvOverview.text = overview

        val posterUrl = posterPath?.let { "https://image.tmdb.org/t/p/w342$it" }
        Glide.with(this).load(posterUrl).into(ivPoster)
    }
}
