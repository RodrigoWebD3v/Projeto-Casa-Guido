package br.com.casa_guido.room.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "doencas")
data class Doencas(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val pacienteId: String,
    val alcoolismo: Boolean?,
    val def_fisica: Boolean?,
    val def_intelectual: Boolean?,
    val def_auditiva: Boolean?,
    val def_visual: Boolean?,
    val cardiopatia: Boolean?,
    val bronquite: Boolean?,
    val hipertensao: Boolean?,
    val toxoplasmose: Boolean?,
    val drogadito: Boolean?,
    val epilepsia: Boolean?,
    val desnutricao: Boolean?,
    val diabetes: Boolean?,
    val cancer: Boolean?
)

