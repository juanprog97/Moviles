package com.example.aplicacionmoviluniversidad.Modelos

data class UserModel (
    val user: String,
    val password: String?,
    var nombre: String?,
    var apellido: String?,
    var email: String?,
    var cod: String?,
    var toke: String?
)