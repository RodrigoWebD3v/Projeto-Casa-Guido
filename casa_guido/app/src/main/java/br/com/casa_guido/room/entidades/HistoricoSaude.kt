package br.com.casa_guido.room.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "historico_saude")
data class HistoricoSaude(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val pacienteId: String,
    val recebe_beneficio: Int?,
    val beneficio_descricao: String?,
    val medicamentos_uso_continuo: String?,
    val local_procura_atendimento: Array<Int>?,
    val doencasFamilia: Array<Int>?,
)
