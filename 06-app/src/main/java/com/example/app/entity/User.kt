package com.example.app.entity

data class User constructor(var username: String?, var password: String?, var code: String?) {

    constructor() : this(null, null, null)

}