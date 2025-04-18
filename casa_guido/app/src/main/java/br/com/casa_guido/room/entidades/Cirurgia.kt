package br.com.casa_guido.room.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity(tableName = "cirurgia")
data class Cirurgia(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),

    val pacienteId: String, // relação com Paciente

    val nome: String? = null,      // adicionado da versão anterior
    val data: String? = null,      // manter como String, mas considere parsear para Date no backend
    val descricao: String? = null  // manter para observações extras
)

/*
data class Cirurgia(
    val nome: String = "",
    val data: String = "",
    val id : String = UUID.randomUUID().toString()
)

 */