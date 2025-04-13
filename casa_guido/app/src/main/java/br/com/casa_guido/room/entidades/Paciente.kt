package br.com.casa_guido.room.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(tableName = "paciente")
data class Paciente(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val pessoaId: Long,
    val cartao_sus: String?,
    val remuneracao: String?,
    val recebe_bpc: Boolean?,
    val bpc_valor: String?,
    val diagnostico: String?,
    val medico_responsavel: String?,
    val escola: String?,
    val tamanho_roupa: String?,
    val tamanho_calcado: String?,
    val ano_escolar: String?,
    val origem_info_ong: String?,
    val observacoes: String?
    )