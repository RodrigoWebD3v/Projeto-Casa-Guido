package br.com.casa_guido.models

import androidx.compose.runtime.Immutable
import java.util.UUID

@Immutable
data class Agendamento(
    val nome: String,
    val horario: String,
    val tipo: String,
    val status: Boolean,
    val id : String = UUID.randomUUID().toString()
)