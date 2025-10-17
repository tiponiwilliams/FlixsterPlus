package com.example.flixsterplus

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flixsterplus.model.Person
import com.example.flixsterplus.network.RetrofitInstance
import com.example.flixsterplus.ui.PersonAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    companion object {
        private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"
    }

    private lateinit var rv: RecyclerView
    private val people = mutableListOf<Person>()
    private lateinit var adapter: PersonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // keep your insets listener
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rv = findViewById(R.id.rvPeople)
        adapter = PersonAdapter(people) { person ->
            val firstKnown = person.known_for.firstOrNull()
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("name", person.name)
                putExtra("department", person.known_for_department ?: "—")
                putExtra("popularity", person.popularity ?: 0.0)
                putExtra("known_title", firstKnown?.displayTitle() ?: "—")
                putExtra("known_overview", firstKnown?.overview ?: "No description.")
                putExtra("known_poster", firstKnown?.poster_path)
            }
            startActivity(intent)
        }
        rv.adapter = adapter
        rv.layoutManager = GridLayoutManager(this, 2)

        fetchPeople()
    }

    private fun fetchPeople() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitInstance.api.getPopularPeople(API_KEY)
                withContext(Dispatchers.Main) {
                    people.clear()
                    people.addAll(response.results)
                    adapter.notifyDataSetChanged()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // (optional) show a Toast on main thread
            }
        }
    }
}
