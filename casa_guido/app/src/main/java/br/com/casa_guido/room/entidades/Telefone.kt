package br.com.casa_guido.room.entidades


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity(tableName = "telefone")
data class Telefone(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val pessoaId: String,
    val numero: String?,
    val tipo: String?
)
