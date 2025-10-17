package com.example.flixsterplus.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flixsterplus.R
import com.example.flixsterplus.model.Person

class PersonAdapter(
    private val people: List<Person>,
    private val onClick: (Person) -> Unit
) : RecyclerView.Adapter<PersonAdapter.VH>() {

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivHeadshot: ImageView = itemView.findViewById(R.id.ivHeadshot)
        private val tvName: TextView = itemView.findViewById(R.id.tvName)

        fun bind(p: Person) {
            tvName.text = p.name
            val url = p.profile_path?.let { "https://image.tmdb.org/t/p/w342$it" }
            Glide.with(itemView).load(url).into(ivHeadshot)
            itemView.setOnClickListener { onClick(p) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false)
        return VH(v)
    }

    override fun getItemCount(): Int = people.size

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(people[position])
}
