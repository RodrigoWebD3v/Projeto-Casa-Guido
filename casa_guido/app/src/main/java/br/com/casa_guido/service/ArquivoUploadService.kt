package br.com.casa_guido.service

import br.com.casa_guido.repository.ArquivoUploadRepository
import java.io.File

class ArquivoUploadService(
    private val arquivoUploadRepository: ArquivoUploadRepository
) {
    suspend fun uploadArquivo(arquivo: File, pacienteId: String) {
        try {
            arquivoUploadRepository.uploadArquivo(arquivo, pacienteId)
        } catch (e: Exception) {
            throw e
        }
    }
}
