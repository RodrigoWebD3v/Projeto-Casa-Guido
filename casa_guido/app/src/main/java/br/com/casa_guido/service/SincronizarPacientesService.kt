package br.com.casa_guido.service

import android.util.Log
import br.com.casa_guido.dto.PacientesRequest
import br.com.casa_guido.repository.SincronizarPacientesRepository

class SincronizarPacientesService(
    private val sincronizarPacientesRepository: SincronizarPacientesRepository,
    private val pacienteService: PacienteService
) {

    suspend fun SincronizarPacientes() {
        try {
            pacienteService.getPacientes().forEach {
                var dtoPaciente = PacientesRequest(
                    it
                )
                sincronizarPacientesRepository.sincronizarPacientes(dtoPaciente)
            }
        } catch (e: Exception) {
            Log.e("SincronizarPacientesService", "Erro ao sincronizar pacientes", e)
        }
    }
}