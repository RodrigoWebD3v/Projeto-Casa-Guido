package br.com.casa_guido.util

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import java.time.format.DateTimeFormatter
import java.time.temporal.Temporal
import java.time.temporal.TemporalAccessor

object Utils {

    fun getInitials(name: String): String {
        return name.split(" ").filter { it.isNotEmpty() }.map { it.first().uppercaseChar() }
            .joinToString("").take(2)
    }

    fun formatCPF(input: String): String {
        val digits = input.filter { it.isDigit() }.take(11)
        return digits.chunked(3).joinToString(".").let {
            if (digits.length > 9) it.replaceAfterLast(
                ".",
                it.takeLast(3) + "-" + digits.takeLast(2)
            )
            else it
        }.take(14)
    }

    fun formatRG(input: String): String {
        val digits = input.filter { it.isDigit() }.take(9)
        return buildString {
            if (digits.length >= 2) append(digits.substring(0, 2))
            if (digits.length >= 5) append(".${digits.substring(2, 5)}")
            if (digits.length >= 8) append(".${digits.substring(5, 8)}")
            if (digits.length == 9) append("-${digits.last()}")
        }
    }

    fun formatPhone(input: String): String {
        val digits = input.filter { it.isDigit() }.take(11)
        return when {
            digits.length <= 2 -> "(${digits}"
            digits.length <= 6 -> "(${digits.substring(0, 2)}) ${digits.substring(2)}"
            digits.length <= 10 -> "(${digits.substring(0, 2)}) ${
                digits.substring(
                    2,
                    7
                )
            }-${digits.substring(7)}"

            else -> "(${digits.substring(0, 2)}) ${digits.substring(2, 7)}-${
                digits.substring(
                    7,
                    11
                )
            }"
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatData(
        data: TemporalAccessor
    ): String? {
      return  DateTimeFormatter
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

}