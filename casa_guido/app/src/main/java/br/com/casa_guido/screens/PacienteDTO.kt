package br.com.casa_guido.screens

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

@Immutable
data class Paciente(
    val nome: String = "",
    val telefone: String = "",
    val status: Boolean = false,
    val dataNascimento: String = "",
    val nomeMae: String = "",
    val nomePai: String = "",
    val nomeResponsavel: String = "",
    val cpf: String = "",
    val rg: String = "",
    val cartaoSus: String = "",
    val celular: String = "",
    val id : String = UUID.randomUUID().toString()
)


val listaAgendamentos = listOf(
    Agendamento(
        nome = "Ana Silva",
        horario = "08:00",
        tipo = "Consulta inicial",
        status = true
    ),
    Agendamento(nome = "Carlos Oliveira", horario = "09:00", tipo = "Retorno", status = false),
    Agendamento(nome = "Mariana Santos", horario = "10:00", tipo = "Exame", status = true),
    Agendamento(
        nome = "Pedro Costa",
        horario = "10:00",
        tipo = "Procedimento",
        status = true
    ),
    Agendamento(nome = "Juliana Lima", horario = "10:00", tipo = "Exame", status = false),
    Agendamento(
        nome = "Mariana Santos",
        horario = "10:00",
        tipo = "Consulta inicial",
        status = true
    ),
    Agendamento(nome = "Pedro Costa", horario = "10:00", tipo = "Exame", status = true),
    Agendamento(
        nome = "Juliana Lima",
        horario = "10:00",
        tipo = "Consulta inicial",
        status = false
    ),
    Agendamento(
        nome = "Juliana Lima",
        horario = "10:00",
        tipo = "Consulta inicial",
        status = false
    ),
    Agendamento(
        nome = "Juliana Lima",
        horario = "10:00",
        tipo = "Consulta inicial",
        status = false
    )
)