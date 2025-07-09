package br.com.casa_guido.room.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID


@Entity(tableName = "arquivo")
data class Arquivo(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val pacienteId: String,
    val nome: String = "",
    val idServidor: String = "",
    val conteudoArquivo: ByteArray = ByteArray(0),
    val uri: String = ""

)
