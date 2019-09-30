package com.example.app.samples

class View {
  fun setOnClickListener(action: (View) -> Unit) {

  }
}

fun main() {
  val view = View()
  // lambda
  view.setOnClickListener {
    println("被点击了")
  }

  // 匿名函数
  view.setOnClickListener(fun(_: View) {
    println("被点击了")
  })

  // 函数引用
  view.setOnClickListener(::onClick)

}

fun onClick(view: View) {
  println("被点击了")
}