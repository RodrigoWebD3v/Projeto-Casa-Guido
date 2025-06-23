package br.com.casa_guido.service

import android.util.Log
import br.com.casa_guido.dto.PacientesRequest
import br.com.casa_guido.repository.SincronizarPacientesRepository
import java.io.File
import br.com.casa_guido.service.ArquivoUploadService

class SincronizarPacientesService(
    private val sincronizarPacientesRepository: SincronizarPacientesRepository,
    private val pacienteService: PacienteService,
    private val arquivoUploadService: ArquivoUploadService
) {

    suspend fun SincronizarPacientes() {
        try {
            pacienteService.getPacientes().forEach {
                val dtoPaciente = PacientesRequest(it)
                sincronizarPacientesRepository.sincronizarPacientes(dtoPaciente)
                it.arquivos.forEach { arquivo ->
                    arquivo.uri?.let { path ->
                        arquivoUploadService.uploadArquivo(File(path), it.id)
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("SincronizarPacientesService", "Erro ao sincronizar pacientes", e)
        }
    }
}