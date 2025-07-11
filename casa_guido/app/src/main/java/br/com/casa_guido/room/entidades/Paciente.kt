package br.com.casa_guido.room.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.util.UUID

@Entity(tableName = "paciente")
data class Paciente(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val pessoaId: String,

    val status: Boolean = false,

    val nomeMae: String = "",
    val nomePai: String = "",
    val nomeOutro: String = "",

    val recebeRemuneracao: Int? = null,
    val remuneracao: String = "", // tratar como decimal no backend

    val recebeBpc: Int? = null,
    val valorBpc: String = "", // tratar como decimal no backend
    val aptoReceberBpc: Int? = null,

    val diagnostico: String = "",
    val profissionalResponsavel: String = "",

    val escolaNome: String = "",
    val anoEscolar: String = "",
    val tamRoupa: String = "",
    val tamCalcado: String = "",

    val origenInfoOng: String = "",
    val observacoes: Array<String> = emptyArray(),

    val responsavelId: String? = "",
    val conjugeResponsavelId: String = "",
    val outroResponsavelId: String = "",
    val tipoEscola: Int? = null, // mantido como Int para enums
    val crasMunicipio : String = "",
    val ubsMunicipio : String = "",
    val crasBairro: String = "",
    val ubsBairro: String = "",
    val alterado: Boolean  = false,
    val idBackend: String = ""
)
