package com.example.core.http

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.io.IOException

object HttpClient : OkHttpClient() {
  val GSON = Gson()

  // 每个调用处都会将函数体中的代码复制一份，所以不能这样做
  inline fun <reified T> get(path: String?, entityCallback: EntityCallback<T>) {
    val request = Request.Builder()
      .url("https://api.hencoder.com/$path")
      .build()

    newCall(request).enqueue(object : Callback {
      override fun onFailure(call: Call, e: IOException) {
        entityCallback.onFailure("网络异常")
      }

      override fun onResponse(call: Call, response: Response) {
        when (response.code()) {
          in 200..299 -> entityCallback.onSuccess(GSON.fromJson(response.body()!!.string(), object : TypeToken<T>() {}.type))
          in 400..499 -> entityCallback.onFailure("客户端错误")
          in 500..599 -> entityCallback.onFailure("服务器错误")
          else -> entityCallback.onFailure("未知错误")
        }
      }
    })
  }
}