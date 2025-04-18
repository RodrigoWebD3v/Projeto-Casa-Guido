package br.com.casa_guido.repository

import br.com.casa_guido.room.dao.PessoaDao
import br.com.casa_guido.room.entidades.Pessoa
import kotlinx.coroutines.flow.Flow

class PessoaRepository(
    private val dao: PessoaDao
) {
    val pessoas get() = dao.getAll()

    suspend fun getById(id: String): Pessoa? {
        return dao.getById(id)
    }

    suspend fun insert(pessoa: Pessoa) {
        dao.insert(pessoa)
    }
}