package com.example.apppeliculas.Servicios

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream




class BitMapServices(context: Context) {

    fun getBytes(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(CompressFormat.JPEG, 0, stream)
        return stream.toByteArray()
    }

    fun getImage(image: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(image, 0, image.size)
    }
}