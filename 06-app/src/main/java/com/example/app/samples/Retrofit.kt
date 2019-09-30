package com.example.app.samples

import android.app.Activity
import android.content.Context
import android.content.Intent
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET

interface API {
  @GET("lessons")
  fun lessons(): Call<Any>
}

fun main() {
  val retrofit = Retrofit.Builder()
    .baseUrl("https://api.hencoder.com/")
    .build()

  val api = retrofit.create<API>()
  println(api.lessons().execute().message())
}