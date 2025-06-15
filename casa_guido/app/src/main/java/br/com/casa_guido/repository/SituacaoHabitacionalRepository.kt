package br.com.casa_guido.repository

import br.com.casa_guido.room.dao.SituacaoHabitacionalDao
import br.com.casa_guido.room.entidades.RadioTerapia
import br.com.casa_guido.room.entidades.SituacaoHabitacional
import kotlinx.coroutines.flow.Flow

class SituacaoHabitacionalRepository (
    private val situacaoHabitacionalDao: SituacaoHabitacionalDao
){
    suspend fun insert(situacaoHabitacional: SituacaoHabitacional) {
        situacaoHabitacionalDao.insert(situacaoHabitacional)
    }

    suspend fun update(situacaoHabitacional: SituacaoHabitacional) {
        situacaoHabitacionalDao.update(situacaoHabitacional)
    }

    val situacaoHabitacional get() = situacaoHabitacionalDao.getAll()

    suspend fun buscaSituacaoHabitacionalPorPaciente(pacienteId: String): SituacaoHabitacional?
    {
        return situacaoHabitacionalDao.buscaSituacaoHabitacionalPorPaciente(pacienteId)
    }

}