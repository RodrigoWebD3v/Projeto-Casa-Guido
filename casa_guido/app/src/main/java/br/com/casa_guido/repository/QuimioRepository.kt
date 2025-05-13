package br.com.casa_guido.repository

import br.com.casa_guido.room.dao.QuimioterapiaDao
import br.com.casa_guido.room.entidades.Quimioterapia
import kotlinx.coroutines.flow.Flow

class QuimioRepository(
    private val quimioDao: QuimioterapiaDao
) {
    suspend fun getQuimiosPorPaciente(pacienteId: String): Flow<List<Quimioterapia>> {
        return quimioDao.getQuimiosByPacienteId(pacienteId)
    }

    suspend fun createQuimio(entidade: Quimioterapia) {
        quimioDao.insert(entidade)
    }

    suspend fun deleteQuimios(pacienteId: String) {
        quimioDao.deleteQuimios(pacienteId)
    }

}