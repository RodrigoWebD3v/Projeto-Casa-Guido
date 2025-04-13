package br.com.casa_guido.room.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(tableName = "situacao_habitacional")

data class SituacaoHabitacional(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val responsavelId: Long,
    val tipo_moradia: String?,
    val propriedade: Boolean?,
    val area: String?,
    val caracteristicas: String?,
    val eletrodomesticos: String?,
    val bens_imovel: String?,
    val meios_transporte: String?,
    val meios_comunicacao: String?,
    val possui_banheiro: Boolean?,
    val dentro_de_casa: Boolean?,
    val destino_lixo: String?,
    val tipo_agua: String?,
    val valor_total: String?
)
