package com.mywebsite.moviesapp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @Headers("X-API-KEY: 8b7a2a60-4200-4070-9ef1-2556e8271344")
    @GET("api/v2.2/films/top")
    fun getMovies() : Call<Movies>

    @Headers("X-API-KEY: 8b7a2a60-4200-4070-9ef1-2556e8271344")
    @GET("api/v2.2/films/{id}")
    //fun getMovieDetails(@Path("kinopoiskId") kinopoiskId:Int) : Call<MovieDetails>
    //fun getMovieDetails(@Path("id") Id:Int, @Query("kinopoiskId") sort : Int) : Call<MovieDetails>
    fun getMovieDetails(@Path("id") id:Int) : Call<MovieDetails>

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