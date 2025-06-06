package br.com.casa_guido.service

import br.com.casa_guido.models.SituacaoHabitacional as SituacaoHabitacionalUI
import br.com.casa_guido.repository.SituacaoHabitacionalRepository
import br.com.casa_guido.room.entidades.*

class SituacaoHabitacionalService (
    private val situacaoHabitacionalRepository: SituacaoHabitacionalRepository
){
    suspend fun createSituacaoHabitacional(situacaoHabitacional: SituacaoHabitacionalUI, pacienteId: String) {
        situacaoHabitacionalRepository.insert(situacaoHabitacional.toEntidade(pacienteId))
    }

    suspend fun buscaSituacaoHabitacionalPorPaciente(pacienteId: String): SituacaoHabitacionalUI? {
        return situacaoHabitacionalRepository.buscaSituacaoHabitacionalPorPaciente(pacienteId)?.toUI()
    }

    fun SituacaoHabitacionalUI.toEntidade(
        pacienteId: String
    ): SituacaoHabitacional {
        return SituacaoHabitacional(
            id = this.id,
            pacienteId = pacienteId,
            comoAdquiriuCasa = this.comoAdquiriuCasa,
            area = this.area,
            meioDeTransporte = this.meioDeTransporte,
            meioDeComunicao = this.meioDeComunicao,
            possuiBanheiros = this.possuiBanheiros,
            numeroComodos = this.numeroComodos,
            material = this.material,
            bens = this.bens,
            dentroDeCasa = this.dentroDeCasa,
        )
    }

    fun SituacaoHabitacional.toUI(): SituacaoHabitacionalUI {
        return SituacaoHabitacionalUI(
            id = this.id,
            comoAdquiriuCasa = this.comoAdquiriuCasa,
            area = this.area,
            numeroComodos = this.numeroComodos,
            material = this.material,
            bens = this.bens ?: emptyArray(),
            meioDeTransporte =  this.meioDeTransporte,
            meioDeComunicao =  this.meioDeComunicao,
            possuiBanheiros = this.possuiBanheiros
        )
    }
}