package br.com.casa_guido.screens

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable
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
    val id: String = UUID.randomUUID().toString(),
    val endereco: Endereco = Endereco(),
    val cirurgias: List<Cirurgia> = emptyList(),
    val socioEconomico: SocioEconomico = SocioEconomico(),
)

@Serializable
data class Endereco(
    val cep: String = "",
    val logradouro: String = "",
    val numero: String = "",
    val complemento: String = "",
    val unidade: String = "",
    val bairro: String = "",
    val localidade: String = "",
    val referencia: String = "",
    val uf: String = "",
    val estado: String = "",
    val regiao: String = "",
    val ibge: String = "",
    val gia: String = "",
    val ddd: String = "",
    val siafi: String = "",
    val pais: String = "Brasil",
)

@Serializable
data class SocioEconomico(
    val remuneracao: String = "",
    val bpc: Int = 0,
    val valorBpc: String = "",
    val escolaNome: String = "",
    val escolaAno: String = "",
    val tamRoupa: Int = 0,
    val tamCalcado: Int = 0,
)


data class Cirurgia(
    val nome: String = "",
    val data: String = "",
    val quimio: Boolean = false,
    val radio: Boolean = false,
    val dataQuimioInicio: String = "",
    val dataQuimioUltima: String = "",
    val dataRadioInicio: String = "",
    val dataRadioUltima: String = "",
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