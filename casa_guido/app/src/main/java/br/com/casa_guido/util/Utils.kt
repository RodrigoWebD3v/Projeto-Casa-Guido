package br.com.casa_guido.util

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAccessor

object Utils {

    fun pegaIniciais(name: String): String {
        return name.split(" ").filter { it.isNotEmpty() }.map { it.first().uppercaseChar() }
            .joinToString("").take(2)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun formataDataPadraoBr(
        data: TemporalAccessor
    ): String? {
        return DateTimeFormatter
            .ofPattern("dd/MM/yyyy")
            .format(data)
    }

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun verificarConexao(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun buscaDiferencaEmAnos(ano: String): String {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val data = LocalDate.parse(ano, formatter)
        val hoje = LocalDate.now()
        val diferencaAnos = ChronoUnit.YEARS.between(data, hoje)

        return diferencaAnos.toString()
    }

    fun formatarTelefone(numero: String): String {
        val somenteNumeros = numero.filter { it.isDigit() }

        return if (somenteNumeros.length == 11) {
            val ddd = somenteNumeros.substring(0, 2)
            val parte1 = somenteNumeros.substring(2, 7)
            val parte2 = somenteNumeros.substring(7, 11)
            "($ddd) $parte1-$parte2"
        } else {
            numero
        }
    }


    fun converterEmByteArray(context: Context, uri: Uri): ByteArray {
        return context.contentResolver.openInputStream(uri)?.use { it.readBytes() }
            ?: throw IllegalArgumentException("Não foi possível abrir o arquivo")
    }


}