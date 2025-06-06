package br.com.casa_guido.room.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.util.UUID

@Entity(tableName = "paciente")
data class Paciente(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val pessoaId: String,

    val status: Boolean? = null,

    val nomeMae: String? = null,
    val nomePai: String? = null,
    val nomeOutro: String? = null,

    val recebeRemuneracao: Int? = null,
    val remuneracao: String? = null, // tratar como decimal no backend

    val recebeBpc: Int? = null,
    val valorBpc: String? = null, // tratar como decimal no backend
    val aptoReceberBpc: Int? = null,

    val diagnostico: String? = null,
    val profissionalResponsavel: String? = null,

    val escolaNome: String? = null,
    val anoEscolar: String? = null,
    val tamRoupa: String? = null,
    val tamCalcado: String? = null,

    val origenInfoOng: String? = null,
    val observacoes: Array<String>?,

    val responsavelId: String? = null,
    val conjugeResponsavelId: String? = null,
    val outroResponsavelId: String? = null,
    val tipoEscola: Int? = null, // mantido como Int para enums
    val crasMunicipio : String? = null,
    val ubsMunicipio : String? = null,
    val crasBairro: String? = null,
    val ubsBairro: String? = null,
)
