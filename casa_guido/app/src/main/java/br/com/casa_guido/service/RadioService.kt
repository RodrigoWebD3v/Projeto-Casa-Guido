package br.com.casa_guido.service

import br.com.casa_guido.repository.RadioRepository
import br.com.casa_guido.room.entidades.RadioTerapia
import br.com.casa_guido.screens.Radio
import kotlinx.coroutines.flow.first

class RadioService(
    private val radioRepository: RadioRepository
) {
    suspend fun getRadiosByPaciente(pacienteId: String): List<Radio> {
        return radioRepository.getRadioTerapiaPorPaciente(pacienteId).first().map {
            it.toUi()
        }
    }

    suspend fun deleteRadio(pacienteId: String) {
        radioRepository.deleteCirurgiasPorPaciente(pacienteId)
    }

    suspend fun createRadio(radio: Radio, pacienteId: String) {
        radioRepository.createCirurgia(radio.toEntidade(pacienteId))
    }

    fun Radio.toEntidade(
        pacienteId: String
    ): RadioTerapia {
        return RadioTerapia(
            id = this.id,
            pacienteId = pacienteId,
            dataInicio = this.dataInicio,
            dataUltimaSessao = this.dataUltimaSessao
        )
    }

    fun RadioTerapia.toUi(): Radio {
        return Radio(
            id = this.id,
            dataInicio = this.dataInicio ?: "",
            dataUltimaSessao = this.dataUltimaSessao ?: ""
        )
    }
}