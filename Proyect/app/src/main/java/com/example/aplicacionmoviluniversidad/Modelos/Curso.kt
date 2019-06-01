package com.example.aplicacionmoviluniversidad.Modelos

data class Curso (
    val nom: String?,
    val peri: String?,
    val coda: String?,
    val Strm: String?,
    val notp: String?,
    val porci: String?,
    val class_section: String?,
    val crse_id: String?,
    val horario: List<HorarioApi>,
    val nota: List<Int>?,
    val parciales: List<String>?
)


