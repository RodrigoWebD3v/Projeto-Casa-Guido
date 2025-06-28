package br.com.casa_guido.service

import android.content.Context
import android.util.Log
import br.com.casa_guido.configuration.SecureStorage
import br.com.casa_guido.dto.PacientesRequest
import br.com.casa_guido.models.toRequestDTO
import br.com.casa_guido.repository.SincronizarPacientesRepository
import java.io.File
import br.com.casa_guido.service.ArquivoUploadService

class SincronizarPacientesService(
    private val sincronizarPacientesRepository: SincronizarPacientesRepository,
    private val pacienteService: PacienteService,
    ) {
    suspend fun SincronizarPacientes(context: Context) {
        try {
            pacienteService.getPacientesAlterados(true)?.forEach { paciente ->
                Log.i("SincronizarPacientesService", "Sincronizando paciente: ${paciente.id}")
                var token = SecureStorage.getToken(context)
                val dtoPaciente = PacientesRequest(paciente.toRequestDTO(), token!!)
                var paciente = sincronizarPacientesRepository.sincronizarPacientes(dtoPaciente)

                Log.i("RETORNO", paciente.status.toString())
            }
        } catch (e: Exception) {
            Log.e("SincronizarPacientesService", "Erro ao sincronizar pacientes", e)
        }
    }
}