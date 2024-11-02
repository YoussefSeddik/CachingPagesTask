package com.youssef.seddik.network

import com.youssef.seddik.data.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
    }

    @GET("ability/")
    suspend fun getAbilities(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): ApiResponse
}