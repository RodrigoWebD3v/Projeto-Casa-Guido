package br.com.casa_guido.service

import br.com.casa_guido.repository.HistoricoSaudeRepository
import br.com.casa_guido.room.entidades.HistoricoSaude
import br.com.casa_guido.screens.HistoricoSaude as HistoricoSaudeUI

class HistoricoSaudeService(
    private val historicoSaudeRepository: HistoricoSaudeRepository
) {

    suspend fun createHistoricoSaude(historicoSaude: HistoricoSaudeUI, pacienteId: String) {
        historicoSaudeRepository.create(historicoSaude.toEntidade(pacienteId))
    }

    suspend fun getHistoricoSaudeByPaciente(pacienteId: String): HistoricoSaudeUI? {
        return historicoSaudeRepository.getHistoricoSaudeByPaciente(pacienteId)?.toUI()
    }

    fun HistoricoSaudeUI.toEntidade(
        pacienteId: String
    ): HistoricoSaude {
        return HistoricoSaude(
            id = this.id,
            pacienteId = pacienteId,
            recebe_beneficio = this.recebeBeneficio,
            beneficio_descricao = this.beneficioDescricao,
            medicamentos_uso_continuo = this.medicamentosUsoContinuo,
            local_procura_atendimento = this.localProcuraAtendimento,
            doencasFamilia = this.doencasFamilia,
        )
    }

    fun HistoricoSaude.toUI(): HistoricoSaudeUI {
        return HistoricoSaudeUI(
            id = this.id,
            recebeBeneficio = this.recebe_beneficio ?: 0,
            beneficioDescricao = this.beneficio_descricao ?: "",
            medicamentosUsoContinuo = this.medicamentos_uso_continuo ?: "",
            localProcuraAtendimento = this.local_procura_atendimento ?: emptyArray(),
            doencasFamilia = this.doencasFamilia ?: emptyArray(),
        )
    }
}