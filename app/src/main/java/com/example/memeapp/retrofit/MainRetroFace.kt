package com.example.memeapp.retrofit

import com.example.memeapp.response.MemeResponse
import com.example.memeapp.util.Constants.BASE_URL

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MainRetroFace {
    @GET("gimme")
    suspend fun getMemes(): MemeResponse

    companion object{

        fun call() : MainRetroFace {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(MainRetroFace::class.java)
        }
    }

}