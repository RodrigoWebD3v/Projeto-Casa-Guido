package br.com.casa_guido.service

import br.com.casa_guido.repository.CirurgiaRepository
import br.com.casa_guido.room.entidades.Cirurgia
import kotlinx.coroutines.flow.first
import br.com.casa_guido.screens.Cirurgia as CirurgiaUI

class CirurgiaService(
    private val cirurgiaRepository: CirurgiaRepository
) {
    suspend fun createCirurgia(cirurgia: CirurgiaUI, pacienteId: String) {
        cirurgiaRepository.createCirurgia(cirurgia.toEntidade(pacienteId))
    }

    suspend fun getCirurgiasPorPaciente(id: String): List<CirurgiaUI> {
        return cirurgiaRepository.getCirurgiaPorPaciente(id).first().map {
            it.toUI()
        }
    }

    suspend fun deleteCirurgia(pacienteId: String) {
        cirurgiaRepository.deleteCirurgiasPorPaciente(pacienteId)
    }

    private fun CirurgiaUI.toEntidade(
        pacienteId: String
    ): Cirurgia {
        return Cirurgia(
            id = this.id,
            pacienteId = pacienteId,
            nome = this.nome,
            data = this.data,
        )
    }

    private fun Cirurgia.toUI(): CirurgiaUI {
        return CirurgiaUI(
            id = this.id,
            nome = this.nome ?: "",
            data = this.data ?: "",
        )
    }
}