package com.example.lesson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.core.BaseViewHolder
import com.example.lesson.entity.Lesson

import java.util.ArrayList
import androidx.recyclerview.widget.RecyclerView

internal class LessonAdapter : RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {
  private var list: List<Lesson> = ArrayList()

  fun updateAndNotify(list: List<Lesson>) {
    this.list = list
    notifyDataSetChanged()
  }

  override fun getItemCount() = list.size

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
    return LessonViewHolder.onCreate(parent)
  }

  override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
    holder.onBind(list[position])
  }


  /**
   * 静态内部类
   */
  class LessonViewHolder internal constructor(itemView: View) : BaseViewHolder(itemView) {

    companion object {
      fun onCreate(parent: ViewGroup): LessonViewHolder {
        return LessonViewHolder(LayoutInflater
          .from(parent.context)
          .inflate(R.layout.item_lesson, parent, false))
      }
    }


    internal fun onBind(lesson: Lesson) {
      setText(R.id.tv_date, lesson.date ?: "日期待定")

      setText(R.id.tv_content, lesson.content)

      lesson.state?.let {
        setText(R.id.tv_state, it.stateName())
        val colorRes = when (it) {
          Lesson.State.PLAYBACK -> R.color.playback
          Lesson.State.LIVE -> R.color.live
          Lesson.State.WAIT -> R.color.wait
        }
        val backgroundColor = itemView.context.getColor(colorRes)
        getView<View>(R.id.tv_state).setBackgroundColor(backgroundColor)
      }
    }
  }
}
