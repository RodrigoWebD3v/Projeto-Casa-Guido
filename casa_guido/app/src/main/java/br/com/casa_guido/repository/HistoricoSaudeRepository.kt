package br.com.casa_guido.repository

import br.com.casa_guido.room.dao.HistoricoSaudeDao
import br.com.casa_guido.room.entidades.HistoricoSaude
import kotlinx.coroutines.flow.Flow


class HistoricoSaudeRepository(
    private val historicoSaudeDao: HistoricoSaudeDao
) {

    suspend fun create(historico: HistoricoSaude) {
        historicoSaudeDao.insert(historico)
    }

    suspend fun update(historico: HistoricoSaude) {
        historicoSaudeDao.update(historico)
    }

    suspend fun getHistoricoSaudeByPaciente(pacienteId: String) : HistoricoSaude? {
        return historicoSaudeDao.getByPacienteId(pacienteId)
    }


}