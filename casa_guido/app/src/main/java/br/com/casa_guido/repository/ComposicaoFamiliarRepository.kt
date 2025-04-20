package br.com.casa_guido.repository

import br.com.casa_guido.room.dao.ComposicaoFamiliarDao
import br.com.casa_guido.room.entidades.ComposicaoFamiliar
import kotlinx.coroutines.flow.Flow

class ComposicaoFamiliarRepository(
    private val composicaoFamiliarDao: ComposicaoFamiliarDao
) {

    suspend fun getComposicaoFamiliarPorPaciente(pacienteId: String): Flow<List<ComposicaoFamiliar>> {
        return composicaoFamiliarDao.getByPacienteId(pacienteId)
    }

    suspend fun createComposicaoFamiliar(entidade: ComposicaoFamiliar) {
        composicaoFamiliarDao.insert(entidade)
    }

    suspend fun deleteComposicaoFamiliar(pacienteId: String) {
        composicaoFamiliarDao.deleteByPacienteId(pacienteId)
    }

}