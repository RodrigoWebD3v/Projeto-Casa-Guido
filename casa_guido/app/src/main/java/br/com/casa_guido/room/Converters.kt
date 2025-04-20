package br.com.casa_guido.room

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromArrayInt(value: Array<Int>): String {
        return value.joinToString(",")
    }

    @TypeConverter
    fun toArrayInt(value: String): Array<Int> {
        return if (value.isEmpty()) emptyArray() else value.split(",").map { it.toInt() }
            .toTypedArray()
    }

}