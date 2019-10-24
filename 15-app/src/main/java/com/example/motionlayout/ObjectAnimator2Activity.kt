package com.example.motionlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class ObjectAnimator2Activity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_object_animator2)

        findViewById<View>(R.id.button_2).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        v.animate()
                .scaleY(2f)
                .scaleX(2f)
                .start()
    }
}
