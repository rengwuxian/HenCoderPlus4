package com.example.lesson

import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toolbar

import com.example.core.BaseView
import com.example.lesson.entity.Lesson
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class LessonActivity : AppCompatActivity(), BaseView<LessonPresenter>, Toolbar.OnMenuItemClickListener {

    private val lessonAdapter = LessonAdapter()

    private lateinit var refreshLayout: SwipeRefreshLayout

    override val presenter: LessonPresenter  =   LessonPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)

        findViewById<Toolbar>(R.id.toolbar).run {
            inflateMenu(R.menu.menu_lesson)
            setOnMenuItemClickListener(this@LessonActivity)
        }

        with(findViewById<RecyclerView>(R.id.list)){
            layoutManager = LinearLayoutManager(this@LessonActivity)
            adapter = lessonAdapter
            addItemDecoration(DividerItemDecoration(this@LessonActivity, LinearLayout.VERTICAL))
        }


        refreshLayout = findViewById(R.id.swipe_refresh_layout)
        refreshLayout.setOnRefreshListener { presenter.fetchData() }
        refreshLayout.isRefreshing = true

        presenter.fetchData()
    }

    internal fun showResult(lessons: List<Lesson>) {
        lessonAdapter.updateAndNotify(lessons)
        refreshLayout.isRefreshing = false
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        presenter.showPlayback()
        return false
    }
}
