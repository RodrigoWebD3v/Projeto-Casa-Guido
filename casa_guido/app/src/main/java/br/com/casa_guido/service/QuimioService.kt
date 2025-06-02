package br.com.casa_guido.service

import br.com.casa_guido.repository.QuimioRepository
import br.com.casa_guido.room.entidades.Quimioterapia
import br.com.casa_guido.models.Quimio
import kotlinx.coroutines.flow.first

class QuimioService(
    private val quimioRepository: QuimioRepository
) {

    suspend fun deleteQuimio(pacienteId: String) {
        quimioRepository.deleteQuimios(pacienteId)
    }

    suspend fun createQuimio(quimio: Quimio, pacienteId: String) {
        quimioRepository.createQuimio(quimio.toEntidade(pacienteId))
    }

    suspend fun getQuimiosPorPaciente(id: String): List<Quimio> {
        return quimioRepository.getQuimiosPorPaciente(id).first().map {
            it.toUI()
        }
    }


    private fun Quimio.toEntidade(
        pacienteId: String
    ): Quimioterapia {
        return Quimioterapia(
            id = this.id,
            pacienteId = pacienteId,
            dataInicio = this.dataInicio,
            dataUltimaSessao = this.dataUltimaSessao,
        )
    }

    private fun Quimioterapia.toUI(): Quimio {
        return Quimio(
            id = this.id,
            dataInicio = this.dataInicio ?: "",
            dataUltimaSessao = this.dataUltimaSessao ?: "",
        )
    }

}