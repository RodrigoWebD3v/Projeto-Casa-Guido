package br.com.casa_guido.service

import br.com.casa_guido.models.Arquivo as ArquivoUI
import br.com.casa_guido.models.Cirurgia
import br.com.casa_guido.repository.ArquivoRepository
import br.com.casa_guido.room.entidades.Arquivo
import kotlinx.coroutines.flow.first

class ArquivoService(
    private val arquivoRepository: ArquivoRepository
) {
    suspend fun getArquivosPorPaciente(pacienteId: String): List<ArquivoUI> {
        return arquivoRepository.getArquivosPorPaciente(pacienteId).first().map {
            it.toUI()
        }
    }

    suspend fun createArquivo(entidade: ArquivoUI, pacienteId: String) {
        arquivoRepository.createArquivo(entidade.toEntidade(pacienteId))
    }

    suspend fun deleteArquivosPorPaciente(pacienteId: String) {
        arquivoRepository.deleteArquivosPorPaciente(pacienteId)
    }

    private fun ArquivoUI.toEntidade(
        pacienteId: String
    ): Arquivo {
        return Arquivo(
            id = this.id,
            pacienteId = pacienteId,
            nome = this.nome,
            idServidor = this.idServidor,
            conteudoArquivo = this.conteudoArquivo,
            uri = this.uri
        )
    }

    private fun Arquivo.toUI(): ArquivoUI {
        return ArquivoUI(
            id = this.id,
            pacienteId = pacienteId,
            nome = this.nome,
            idServidor = this.idServidor,
            conteudoArquivo = this.conteudoArquivo,
            uri = this.uri
        )
    }
}