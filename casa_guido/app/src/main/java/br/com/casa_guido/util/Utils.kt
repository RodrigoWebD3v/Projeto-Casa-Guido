package br.com.casa_guido.util

object Utils {

    fun getInitials(name: String): String {
        return name.split(" ").filter { it.isNotEmpty() }.map { it.first().uppercaseChar() }
            .joinToString("")
    }

}