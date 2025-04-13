package br.com.casa_guido.room.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "historico_saude")
data class HistoricoSaude(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val pacienteId: Long,
    val recebe_beneficio: Boolean?,
    val beneficio_descricao: String?,
    val medicamentos_uso_continuo: String?,
    val local_procura_ajuda: String?,
    val doencas: String? // armazenar JSON como string
)

