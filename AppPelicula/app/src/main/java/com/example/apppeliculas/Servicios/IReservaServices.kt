package com.example.apppeliculas.Servicios
import com.example.apppeliculas.Modelos.Reserva
interface IReservaServices{

    fun saveReserva(reserva: Reserva)
    fun consulReserva(reserva: Reserva) : Boolean
}