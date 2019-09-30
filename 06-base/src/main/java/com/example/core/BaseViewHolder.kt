package com.example.core

import android.util.SparseArray
import android.util.SparseIntArray
import android.view.View
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  private val viewHashMap = SparseArray<View>()

  @Suppress("UNCHECKED_CAST")
  protected fun <T : View> getView(@IdRes id: Int): T {
    return when (val view = viewHashMap[id]) {
      null -> itemView.findViewById<T>(id).also {
        viewHashMap.put(id, it)
      }
      else -> view as T
    }
  }

  protected fun setText(@IdRes id: Int, text: String?) {
    getView<TextView>(id).text = text
  }
}