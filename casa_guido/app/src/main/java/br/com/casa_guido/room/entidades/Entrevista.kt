package br.com.casa_guido.room.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "entrevista")
data class Entrevista(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val pacienteId: String = "",
    val entrevistadoId: String = "",   // Pessoa (responsável, por exemplo)
    val cidade: String = "",
    val data: String = "",             // String padronizada como ISO 8601
    val como_soube: String = "",
    val observacoes: String = "",
    val entrevistador: String = ""     // Nome livre (pode ser preenchido manualmente)
)
