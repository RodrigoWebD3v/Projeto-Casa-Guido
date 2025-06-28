package br.com.casa_guido.repository

import br.com.casa_guido.room.dao.PacienteDao
import br.com.casa_guido.room.entidades.Paciente as PacienteEntidade

class PacienteRepository(
    private val dao: PacienteDao
) {
    suspend fun getById(id: String): PacienteEntidade? {
        return dao.getById(id)
    }

    suspend fun insert(paciente: PacienteEntidade) {
        dao.insert(paciente)
    }

    val pacientes get() = dao.getAll()

    suspend fun getAlterado(status: Boolean): List<PacienteEntidade>? {
        return dao.getAlterado(status)
    }

}