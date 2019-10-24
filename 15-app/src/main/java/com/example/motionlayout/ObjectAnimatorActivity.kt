package com.example.motionlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.motionlayout.util.dp

class ObjectAnimatorActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var root: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_object_animator)
        root = findViewById(R.id.root)

        findViewById<ImageView>(R.id.heart).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        v.animate()
                .translationX(200f.dp)
                .start()
    }
}
