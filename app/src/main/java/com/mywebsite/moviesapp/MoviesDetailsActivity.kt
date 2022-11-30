package com.mywebsite.moviesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.google.android.gms.common.internal.Constants
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesDetailsActivity : AppCompatActivity() {
    private lateinit var title: TextView
    private lateinit var releaseDate: TextView
    private lateinit var score: TextView
    private lateinit var overview: TextView
    private lateinit var banner: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_details)

        val filmId = intent.getIntExtra("filmId", 0)
Log.d("testLogs", "filmId $filmId")
        val kinopoiskId = filmId
   Log.d("testLogs", "kinopoiskId $kinopoiskId")
        title = findViewById(R.id.movies_details_title)
 //Log.d("testLogs", "title: $title")
        releaseDate = findViewById(R.id.movies_details_date)
        score = findViewById(R.id.movies_details_score)
 Log.d("testLogs", "score: $score")
        overview = findViewById(R.id.movies_details_body_overview)
        banner = findViewById(R.id.movies_details_image_banner)

//        val apiInterace = filmId?.let {ApiInterface.create().getMovieDatails(it, Constants.API_KEY)}
        val apiInterface = filmId?.let {ApiInterface.create().getMovieDetails(it)}
        apiInterface?.enqueue(object : Callback<MovieDetails> {
            override fun onResponse(call: Call<MovieDetails>?, response: Response<MovieDetails>?) {
                title.text = response?.body()?.nameRu
                releaseDate.text = response?.body()?.year.toString()
  Log.d("testLogs", "releaseDate: ${releaseDate.text}")
                score.text = response?.body()?.ratingImdb.toString()
                overview.text = response?.body()?.description

                Picasso.get().load(response?.body()?.posterUrl).into(banner)
            }

            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                Log.d("testLogs", "onFailure : ${t?.message}")
            }
        })
    }
}