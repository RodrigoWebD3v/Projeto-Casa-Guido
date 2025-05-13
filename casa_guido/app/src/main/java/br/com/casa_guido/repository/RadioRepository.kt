package br.com.casa_guido.repository

import br.com.casa_guido.room.dao.RadioTerapiaDao
import br.com.casa_guido.room.entidades.RadioTerapia
import kotlinx.coroutines.flow.Flow

class RadioRepository(
    private val radioDao: RadioTerapiaDao
) {
    suspend fun getRadioTerapiaPorPaciente(pacienteId: String): Flow<List<RadioTerapia>> {
        return radioDao.getRadioTerapiaPorPaciente(pacienteId)
    }

    suspend fun createCirurgia(entidade: RadioTerapia) {
        radioDao.insert(entidade)
    }

    suspend fun deleteCirurgiasPorPaciente(pacienteId: String) {
        radioDao.deleteRadioTerapiaPorPaciente(pacienteId)
    }
}