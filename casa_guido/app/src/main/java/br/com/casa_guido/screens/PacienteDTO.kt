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

data class Pessoa(
    val nome: String = "",
    val telefone: String = "",
    val dataNascimento: String = "",
    val cpf: String = "",
    val rg: String = "",
    val endereco: Endereco = Endereco(),
)

@Immutable
data class Paciente(
    val id: String = UUID.randomUUID().toString(),
    val pessoa: Pessoa = Pessoa(),
    val status: Boolean = false,
    val nomeMae: String = "",
    val nomePai: String = "",
    val nomeOutro: String = "",
    val cartaoSus: String = "",
    val cirurgias: List<Cirurgia> = emptyList(),
    val quimios: List<Quimio> = emptyList(),
    val radios: List<Radio> = emptyList(),
    val remuneracao: String = "",
    val bpc: Int = 0,
    val valorBpc: String = "",
    val escolaNome: String = "",
    val escolaAno: String = "",
    val tamRoupa: String = "",
    val tamCalcado: Int = 0,
    val diagnostico: String = "",
    val medico_responsavel: String = "",
    val origen_info_ong: String = "",
    val observacoes: String = "",
    val reponsavel: Pessoa = Pessoa(),
    val conjugeResponsavel: Pessoa = Pessoa(),
    val outroResponsavel: Pessoa = Pessoa(),
    val doenca: List<Doenca> = emptyList(),
    val tratamento: List<Tratamento> = emptyList(),
    val profissionalResponsavel: List<ProfissionalResponsavel> = emptyList(),
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

data class Cirurgia(
    val nome: String = "",
    val data: String = "",
    val id : String = UUID.randomUUID().toString()
)

data class Quimio(
    val dataInicio: String = "",
    val dataUltimaSessao: String = "",
    val id : String = UUID.randomUUID().toString()
)

data class Radio(
    val dataInicio: String = "",
    val dataUltimaSessao: String = "",
    val id : String = UUID.randomUUID().toString()
)

data class Doenca(
    val nome: String = "",
    val id : String = UUID.randomUUID().toString()
)

data class Tratamento(
    val tipo: String = "",
    val data_inicio: String = "",
    val data_ultima_sessao: String = "",
    val observacoes: String = "",
    val outros_tratamentos: String = "",
    val id : String = UUID.randomUUID().toString()
)

data class ProfissionalResponsavel(
    val tipo: String = "",
    val nome: String = "",
    val crm: String = "",
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