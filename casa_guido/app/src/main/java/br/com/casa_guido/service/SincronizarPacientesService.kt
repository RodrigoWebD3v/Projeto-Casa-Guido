package br.com.casa_guido.service

import android.content.Context
import android.util.Log
import br.com.casa_guido.configuration.SecureStorage
import br.com.casa_guido.dto.DataResponse
import br.com.casa_guido.dto.PacientesRequest
import br.com.casa_guido.models.Paciente
import br.com.casa_guido.models.toRequestDTO
import br.com.casa_guido.repository.SincronizarPacientesRepository

class SincronizarPacientesService(
    private val sincronizarPacientesRepository: SincronizarPacientesRepository,
    private val pacienteService: PacienteService,
) {
    suspend fun SincronizarPacientes(context: Context) {
        try {
            val token = SecureStorage.getToken(context)

            pacienteService.getPacientesAlterados(true)?.forEach { paciente ->
                if (paciente.idBackend.isNullOrBlank()) {
                    SincronizarCriarPaciente(context, paciente, token ?: "")
                }else{
                    SincronizarAtualizarPacientes(context, paciente, token ?: "")
                }
            }
        } catch (e: Exception) {
            Log.e("SincronizarPacientesService", "Erro ao sincronizar pacientes", e)
        }
    }

    suspend fun SincronizarCriarPaciente(context: Context, paciente: Paciente, token: String) {
        try {
            val dtoPaciente = PacientesRequest(paciente.toRequestDTO(), token)
            val dataResponse: DataResponse =
                sincronizarPacientesRepository.sincronizarCriarPacientes(dtoPaciente)

            pacienteService.atualizaPaciente(
                paciente.copy(
                    idBackend = dataResponse.data.id,
                    alterado = false
                )
            )
            val pacienteBusca = pacienteService.getById(paciente.id)
        } catch (e: Exception) {
            Log.e("SincronizarPacientesService", "Erro ao sincronizar pacientes", e)
        }
    }

    suspend fun SincronizarAtualizarPacientes(context: Context, paciente: Paciente, token: String) {
        try {

            val dtoPaciente = PacientesRequest(paciente.toRequestDTO(), token)
            sincronizarPacientesRepository.sincronizarAtualizarPacientes(dtoPaciente)

            pacienteService.atualizaPaciente(
                paciente.copy(
                    alterado = false
                )
            )

        } catch (e: Exception) {
            Log.e("SincronizarPacientesService", "Erro ao sincronizar pacientes", e)
        }
    }


}