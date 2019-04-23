package com.example.datastorage.Servicios

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.datastorage.Modelos.User

class UserDBServices(context: Context) : SQLiteOpenHelper(context, "UserDBService", null, 1), IUserServices
{
    override fun onCreate(db: SQLiteDatabase?) {
        //val sql :String = "DROP TABLE USERS;" //Primero hacer un drop table y luego el create
        val sql : String = "CREATE TABLE users(idUser int primarykey," +
                           " name text," +
                           " email text," +
                           " age integer," +
                           " password text,"+
                            " images blob);"

        println("Creado")

        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int)
    {
        TODO("Sin implementación")
    }

    override fun verifyUser(user: User) : Boolean
    {
        val sql : String = "SELECT email, password FROM users" +
                           " where email='${user.email}'"

        val result : Cursor = this.executeQuery(sql, this.writableDatabase)
        var returnValue : Boolean = false

        if(result.moveToFirst())
        {
            if (user.email.equals(result.getString(0)) && user.password.equals(result.getString(1)))
            {
                print("mo ms");
                returnValue = true
            }
        }

        this.close()
        return returnValue
    }

    override fun saveUser(user: User)
    {
        var localUser = ContentValues()
        localUser.put("name", user.name)
        localUser.put("email", user.email)
        localUser.put("age", user.age)
        localUser.put("password", user.password)
        println("aja" +user.images)
        localUser.put("images", user.images)
        this.executeModification(localUser)
    }

    override fun consultUsers(): List<User>?
    {
        val sql : String = "SELECT idUser, name, email, age, password, images FROM users"
        val result : Cursor = this.executeQuery(sql, this.writableDatabase)
        var listUsers : MutableList<User>? = ArrayList<User>()
        result.moveToFirst()

        while(!result.isAfterLast)
        {

            var user : User = User(
                result.getInt(0),
                result.getString(1),
                result.getString(2),
                result.getInt(3),
                result.getString(4),
                result.getBlob(5)
            )

            listUsers?.add(user)
            result.moveToNext()
        }

        return listUsers
    }


    private fun executeQuery(sql: String, bd : SQLiteDatabase) : Cursor
    {
        val consulta : Cursor = bd.rawQuery(sql,null)
        return consulta
    }

    private fun executeModification(user: ContentValues)
    {
        val bd = this.writableDatabase
       // bd.execSQL("INSERT INTO users VALUES (null,user.name,user.email,user.age,user.password,user.images)");
       bd.insert("users", null , user)
      /*  bd.close()
        "CREATE TABLE users(idUser int primarykey," +
                " name text," +
                " email text," +
                " age integer," +
                " password text,"+
                " images blob)"

        idUser: Int?,
        var name: String?,
        val email: String?,
        val age: Int?,
        var password: String?,
        var images: ByteArray?*/
    }
}