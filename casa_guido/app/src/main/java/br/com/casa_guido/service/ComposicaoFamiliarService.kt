package br.com.casa_guido.service

import br.com.casa_guido.repository.ComposicaoFamiliarRepository
import br.com.casa_guido.room.entidades.ComposicaoFamiliar
import kotlinx.coroutines.flow.first
import br.com.casa_guido.screens.ComposicaoFamiliar as ComposicaoFamiliarUI

class ComposicaoFamiliarService(
    private val composicaoFamiliarRepository: ComposicaoFamiliarRepository
) {

    suspend fun deleteComposicaoFamiliar(pacienteId: String) {
        composicaoFamiliarRepository.deleteComposicaoFamiliar(pacienteId)
    }

    suspend fun createComposicaoFamiliar(
        componenteFamiliar: ComposicaoFamiliarUI,
        pacienteId: String
    ) {
        composicaoFamiliarRepository.createComposicaoFamiliar(
            componenteFamiliar.toEntidade(
                pacienteId
            )
        )
    }

    suspend fun getComposicaoFamiliarPorPaciente(id: String): List<ComposicaoFamiliarUI> {
        return composicaoFamiliarRepository.getComposicaoFamiliarPorPaciente(id).first().map {
            it.toUI()
        }
    }


    private fun ComposicaoFamiliarUI.toEntidade(
        pacienteId: String
    ): ComposicaoFamiliar {
        return ComposicaoFamiliar(
            id = this.id,
            pacienteId = pacienteId,
            nome = this.nome,
            parentesco = this.parentesco,
            data_nascimento = this.dataNascimento,
            escolaridade = this.serie,
            trabalhaOpc = this.trabalhaOpcional,
            renda = this.renda,
            estudaOpc = this.estudaOpcional,
            idade = this.idade,
        )
    }

    private fun ComposicaoFamiliar.toUI(): ComposicaoFamiliarUI {
        return ComposicaoFamiliarUI(
            id = this.id,
            nome = this.nome ?: "",
            parentesco = this.parentesco ?: "",
            dataNascimento = this.data_nascimento ?: "",
            serie = this.escolaridade ?: "",
            trabalhaOpcional = this.trabalhaOpc ?: 0,
            renda = this.renda ?: "",
            estudaOpcional = this.estudaOpc ?: 0,
            idade = this.idade ?: 0,
        )
    }

}