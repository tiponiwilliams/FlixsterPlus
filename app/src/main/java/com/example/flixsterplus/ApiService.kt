package com.example.flixsterplus.network

import com.example.flixsterplus.model.PersonResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("person/popular")
    suspend fun getPopularPeople(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): PersonResponse
}
