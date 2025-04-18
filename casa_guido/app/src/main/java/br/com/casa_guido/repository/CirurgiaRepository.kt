package br.com.casa_guido.repository

import br.com.casa_guido.room.dao.CirurgiaDao
import br.com.casa_guido.room.entidades.Cirurgia
import kotlinx.coroutines.flow.Flow

class CirurgiaRepository(
    private val cirurgiaDao: CirurgiaDao
) {
    suspend fun getCirurgiaPorPaciente(pacienteId: String): Flow<List<Cirurgia>> {
        return cirurgiaDao.getCirursgiasPorPacienteId(pacienteId)
    }

    suspend fun createCirurgia(entidade: Cirurgia) {
        cirurgiaDao.insert(entidade)
    }

    suspend fun deleteCirurgiasPorPaciente(pacienteId: String) {
        cirurgiaDao.deleteCirurgiasPorPaciente(pacienteId)
    }

}