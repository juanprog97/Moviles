package com.example.apppeliculas.Servicios

import com.example.apppeliculas.Modelos.User

interface IUserServices
{
    fun verifyUser(user: User) : Boolean
    fun saveUser(user: User)
    fun consultId(user : User) : Int
    fun consultUsers() : List<User>?
}