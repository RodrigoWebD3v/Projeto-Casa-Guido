package br.com.casa_guido.room.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.util.UUID

@Entity(tableName = "paciente")
data class Paciente(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val pessoaId: String,
    val cartao_sus: String?,
    val remuneracao: String?, // manter string, mas tratar como decimal no backend
    val recebe_bpc: Boolean?,
    val bpc_valor: String?,   // idem
    val diagnostico: String?,
    val medico_responsavel: String?,
    val escola: String?,
    val tamanho_roupa: String?,
    val tamanho_calcado: String?,
    val ano_escolar: String?,
    val responsavelId: String?,
    val conjugeResponsavelId: String?,
    val outroResponsavelId: String?,
)
