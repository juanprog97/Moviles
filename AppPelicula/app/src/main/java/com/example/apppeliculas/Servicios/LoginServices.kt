package com.example.apppeliculas.Servicios

import android.content.Context
import com.example.apppeliculas.Modelos.User


class LoginServices(context: Context)
{
    private lateinit var user : User
    private var dbConnection : DBServices = DBServices(context)
    private var sharedConnection : UserReservedServices = UserReservedServices(context)

    fun verifyUser(user: User) : Boolean
    {
        this.user = user
        var result : Boolean = false

        /*if(sharedConnection.verifyUser(user))
        {
            result =  sharedConnection.verifyUser(this.user)
        }
        else*/
            result =  dbConnection.verifyUser(this.user)

        return result
    }
}