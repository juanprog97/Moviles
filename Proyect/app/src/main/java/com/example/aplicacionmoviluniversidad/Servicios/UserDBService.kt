package com.example.aplicacionmoviluniversidad.Servicios

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.aplicacionmoviluniversidad.Modelos.Curso
import com.example.aplicacionmoviluniversidad.Modelos.HorarioApi
import com.example.aplicacionmoviluniversidad.Modelos.UserModel

class UserDBService(context: Context) : SQLiteOpenHelper(context, "UserDBService", null, 1) {
    private lateinit var user: UserModel
    override fun onCreate(db: SQLiteDatabase?) {

        val sql: String = "Create table user(nombre text, " +
                "apellido text," +
                "email text," +
                "cod text," +
                "token text, " +
                "periodo text,"+
                "password text)"
        db?.execSQL(sql)

        val horario: String = "Create table horario( id integer primary key autoincrement, " +
                "codasig text," +
                "fechaInicial text," +
                "hora text," +
                "salon text, " +
                "fechaFinal text, " +
                "doc text," +
                "dia text)"
        db?.execSQL(horario)


        val curs: String = "Create table curso(id integer primary key autoincrement  , " +
                "periodo text," +
                "coda text ,"+
                "nom text, " +
                "strm text, " +
                "ina text," +
                "notp text, " +
                "class_section text," +
                "crse_id text," +
                " foreign key(coda) references horario(codasig))"
        db?.execSQL(curs)


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun saveUser(user: UserModel) {
        this.user = user
        var localUser = ContentValues()
        localUser.put("nombre", user.nombre)
        localUser.put("apellido", user.apellido)
        localUser.put("email", user.email)
        localUser.put("cod", user.emplid)
        localUser.put("token", user.nametoken)
        localUser.put("periodo",user.periodo)
        localUser.put("password",user.value)
        this.executeModification(localUser, "user")

    }

    fun saveCurso(cursos: List<Curso>) {
        var localCurso = ContentValues()
        var localHorario = ContentValues()
        for (curs in cursos) {

            for (horario in curs.horario) {
                localHorario.put("fechaInicial", horario.feci)
                localHorario.put("codasig", curs.coda)
                localHorario.put("hora", horario.hora)
                localHorario.put("salon", horario.saln)
                localHorario.put("fechaFinal", horario.fecf)
                localHorario.put("doc", horario.doc)
                localHorario.put("dia", horario.dia)
                this.executeModification(localHorario, "horario")

            }


            localCurso.put("nom", curs.nom)
            localCurso.put("periodo", curs.peri)
            localCurso.put("coda", curs.coda)
            localCurso.put("strm", curs.Strm)
            localCurso.put("ina", curs.porci)
            localCurso.put("notp", curs.notp)
            localCurso.put("class_section", curs.class_section)
            localCurso.put("crse_id", curs.crse_id)
            this.executeModification(localCurso, "curso")

        }
    }

    fun consultCurso(): List<Curso>? {
        val sql: String = "select nom, periodo, coda, strm, ina, notp, class_section, crse_id from curso"
        val result: Cursor = this.executeQuery(sql, this.writableDatabase)
        println(result)
        var listCurso: MutableList<Curso>? = ArrayList<Curso>()
        var listHorario: MutableList<HorarioApi> = ArrayList<HorarioApi>()

        result.moveToFirst()
        val sqlHorario: String = "select codasig, fechaInicial, hora, salon, fechaFinal, doc, dia from " +
                "horario where codasig = ${result.getString(2)}"
        while (!result.isAfterLast) {
            val hor: Cursor = this.executeQuery(sqlHorario, this.writableDatabase)
            hor.moveToFirst()
           /* while (!hor.isAfterLast) {
                var horario: HorarioApi = HorarioApi(
                    hor.getString(1),
                    hor.getString(2),
                    hor.getString(3),
                    hor.getString(4),
                    hor.getString(5),
                    hor.getInt(6)
                )
                listHorario.add(horario)
            }*/


            var curso: Curso = Curso(
                result.getString(0),
                result.getString(1),
                result.getString(2),
                result.getString(3),
                result.getString(5),
                result.getString(4),
                result.getString(6),
                result.getString(7),
                listHorario.toList(),
                listOf(4.5, 5.0, 3.2),
                listOf("parcial1", "parcial2", "parcial3")
            )
            listCurso?.add(curso)
            result.moveToNext()
        }
        println(listCurso)
        return listCurso
    }

    private fun executeModification(user: ContentValues, base: String) {
        val bd = this.writableDatabase
        bd.insert(base, null, user)
        bd.close()
    }

    fun eliminateUser(){
        val bd = this.writableDatabase
        bd.execSQL("delete from "+ "user");
        bd.execSQL("delete from "+ "horario");
        bd.execSQL("delete from "+ "curso");
        bd.close()
    }


    private fun executeQuery(sql: String, bd: SQLiteDatabase): Cursor {
        val consulta: Cursor = bd.rawQuery(sql, null)
        return consulta
    }

    fun CountUser(): Int {
        val sql = "select count(nombre) from user"
        val result = this.executeQuery(sql, this.writableDatabase)
        result.moveToFirst()
        return result.getInt(0)
    }

    fun GetCurrentUser(): UserModel {
        val sql = "select password, nombre, apellido, email, cod, token, periodo  from user"
        val result = this.executeQuery(sql, this.writableDatabase)
        result.moveToFirst()

        var userMod: UserModel = UserModel(
            result.getString(1),
            "",
            "",
            result.getString(2),
            result.getString(3),
            result.getString(6),
            result.getString(4),
            result.getString(5),
            result.getString(0)
        )

        return userMod
    }
}