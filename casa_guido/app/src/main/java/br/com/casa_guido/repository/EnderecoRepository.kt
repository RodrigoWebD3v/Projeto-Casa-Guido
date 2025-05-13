package br.com.casa_guido.repository

import android.util.Log
import br.com.casa_guido.room.dao.EnderecoDao
import br.com.casa_guido.room.entidades.Endereco as EnderecoEntidade

class EnderecoRepository(
    private val dao: EnderecoDao
) {
    suspend fun insert(endereco: EnderecoEntidade) {
        dao.insert(endereco)
        val test = dao.getById(endereco.id)
        Log.d("EnderecoRepository", "Confirmando insert: $test")
    }

    val enderecos get() = dao.getAll()

    suspend fun getById(id: String): EnderecoEntidade? {
        return dao.getById(id)
    }

    suspend fun delete(endereco: EnderecoEntidade) {
        dao.delete(endereco)
    }
}