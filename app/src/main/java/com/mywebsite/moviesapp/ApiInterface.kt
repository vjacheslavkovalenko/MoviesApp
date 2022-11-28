package com.mywebsite.moviesapp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiInterface {
    @Headers("X-API-KEY: 8b7a2a60-4200-4070-9ef1-2556e8271344")
    @GET("api/v2.2/films/top")
    fun getMovies() : Call<Movies>

    companion object {

        var BASE_URL = "https://kinopoiskapiunofficial.tech/"

        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}