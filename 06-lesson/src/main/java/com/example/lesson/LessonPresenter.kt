package com.example.lesson

import com.example.core.http.EntityCallback
import com.example.core.http.HttpClient
import com.example.core.utils.Utils
import com.example.lesson.entity.Lesson

const val LESSON_PATH = "lessons"

class LessonPresenter(private val activity: LessonActivity) {

  private var lessons: List<Lesson> = ArrayList()

  fun fetchData() {
    HttpClient.get<List<Lesson>>(LESSON_PATH, object : EntityCallback<List<Lesson>> {
      override fun onSuccess(entity: List<Lesson>) {
        this@LessonPresenter.lessons = entity
        activity.runOnUiThread { activity.showResult(lessons) }
      }

      override fun onFailure(message: String?) {
        activity.runOnUiThread { Utils.toast(message) }
      }
    })
  }

  fun showPlayback() {
    activity.showResult(lessons.filter {
      it.state == Lesson.State.PLAYBACK
    })
  }
}