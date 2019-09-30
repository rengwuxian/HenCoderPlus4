package com.example.app

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.app.entity.User
import com.example.app.widget.CodeView
import com.example.core.utils.CacheUtils
import com.example.core.utils.Utils
import com.example.core.utils.Utils.toast
import com.example.lesson.LessonActivity
import kotlin.reflect.KProperty

class Saver(var name: String) {
  operator fun getValue(any: Any?, property: KProperty<*>): String? {
    return CacheUtils.get(name)
  }

  operator fun setValue(any: Any?, property: KProperty<*>, value: String?) {
    CacheUtils.save(name, value)
  }
}

@Suppress("PrivatePropertyName")
class MainActivity : AppCompatActivity(), View.OnClickListener {

  private val usernameKey: String = "username"
  private val passwordKey: String = "password"

  private var username by Saver(usernameKey)
  private var password by Saver(passwordKey)

  private lateinit var et_username: EditText
  private lateinit var et_password: EditText
  private lateinit var et_code: EditText

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    et_username = findViewById(R.id.et_username)
    et_password = findViewById(R.id.et_password)
    et_code = findViewById(R.id.et_code)

    et_username.setText(username)
    et_password.setText(password)

    findViewById<Button>(R.id.btn_login).setOnClickListener(this)
    findViewById<CodeView>(R.id.code_view).setOnClickListener(this)
  }

  override fun onClick(v: View?) {
    when (v) {
      is CodeView -> v.updateCode()
      is Button -> login()
    }
  }

  private fun login() {
    val username = et_username.text.toString()
    val password = et_password.text.toString()
    val code = et_code.text.toString()

    val user = User(username, password, code)

    fun verify(): Boolean = when {
      user.username?.length ?: 0 < 4 -> {
        toast("用户名不合法")
        false
      }
      user.password?.length ?: 0 < 4 -> {
        toast("密码不合法")
        false
      }
      else -> true
    }


    if (verify()) {
      this.username = username
      this.password = password
      startActivity<LessonActivity>()
    }
  }

  private inline fun <reified T : Activity> Context.startActivity() = startActivity(Intent(this, T::class.java))
}