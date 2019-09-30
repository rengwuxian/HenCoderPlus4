@file:JvmName("DpUtils")

package com.example.core.utils

import android.content.res.Resources
import android.util.TypedValue
import android.widget.Toast
import com.example.core.BaseApplication


private val displayMetrics = Resources.getSystem().displayMetrics

@get:JvmName("dp2px")
val Float.dp
  get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, displayMetrics)

object Utils {

  @JvmOverloads
  @JvmStatic
  fun toast(string: String?, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(BaseApplication.currentApplication, string, duration).show()
  }
}