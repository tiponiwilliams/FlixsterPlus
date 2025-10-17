package com.example.flixsterplus.model

data class PersonResponse(
    val page: Int,
    val results: List<Person>
)

data class Person(
    val id: Int,
    val name: String,
    val profile_path: String?,
    val known_for_department: String?,
    val popularity: Double?,
    val known_for: List<KnownFor>
)

data class KnownFor(
    val media_type: String?,
    val title: String?,   // for movies
    val name: String?,    // for TV
    val overview: String?,
    val poster_path: String?
) {
    fun displayTitle(): String = title ?: name ?: "(No title)"
}
