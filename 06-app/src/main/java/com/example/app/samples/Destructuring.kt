package com.example.app.samples

import com.example.app.entity.User

data class Response(var code: Int, var message: String, var body: User)

fun execute(): Response {
    println("正在请求网络...")

    println("网络请求成功!")

    val code = 200
    val message = "OK"
    val user = User()
    return Response(code, message, user)
}

fun main() {
    val (code, message, body) = execute()

    println(message)
    println(code)
    println(body)
}
