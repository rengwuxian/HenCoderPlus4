package com.example.core.utils

import android.annotation.SuppressLint
import android.content.Context
import com.example.core.BaseApplication
import com.example.core.R

@SuppressLint("StaticFieldLeak")
object CacheUtils {
  private val context = BaseApplication.currentApplication
  private val SP = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

  fun save(key: String?, value: String?) = SP.edit().putString(key, value).apply()

  fun get(key: String?): String? = SP.getString(key, null)
}