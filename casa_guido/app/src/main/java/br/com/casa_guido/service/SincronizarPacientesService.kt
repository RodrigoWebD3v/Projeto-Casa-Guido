package br.com.casa_guido.service

import android.content.Context
import android.util.Log
import br.com.casa_guido.configuration.SecureStorage
import br.com.casa_guido.dto.ArquivoRequest
import br.com.casa_guido.dto.ArquivosRequest
import br.com.casa_guido.dto.CreatePacienteResponse
import br.com.casa_guido.dto.DataResponse
import br.com.casa_guido.dto.ListaArquivoResponse
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
                    SincronizarCriarPaciente(paciente, token ?: "", context)
                } else {
                    SincronizarAtualizarPacientes(paciente, token ?: "", context)
                }
            }
        } catch (e: Exception) {
            Log.e("SincronizarPacientesService", "Erro ao sincronizar pacientes", e)
        }
    }

    suspend fun SincronizarCriarPaciente(paciente: Paciente, token: String, context: Context) {
        try {
            val dtoPaciente = PacientesRequest(paciente.toRequestDTO(), token)
            val dataResponse: DataResponse<CreatePacienteResponse> =
                sincronizarPacientesRepository.SincronizarCriarPacientesRepository(dtoPaciente)

            pacienteService.atualizaPaciente(
                paciente.copy(
                    idBackend = dataResponse.data.id,
                    alterado = false
                )
            )

            EnviarArquivos(
                paciente.copy(
                    idBackend = dataResponse.data.id,
                ),
                context
            )

        } catch (e: Exception) {
            Log.e("SincronizarPacientesService", "Erro ao sincronizar pacientes", e)
            throw Exception("Erro ao sincronizar pacientes: ${e.message}")
        }
    }

    suspend fun SincronizarAtualizarPacientes(paciente: Paciente, token: String, context: Context) {
        try {

            val dtoPaciente = PacientesRequest(paciente.toRequestDTO(), token)
            sincronizarPacientesRepository.SincronizarAtualizarPacientesRepository(dtoPaciente)

            pacienteService.atualizaPaciente(
                paciente.copy(
                    alterado = false
                )
            )
            Log.i(
                "SincronizarPacientesService",
                "Paciente atualizado com sucesso: ${paciente.idBackend}"
            )
            EnviarArquivos(
                paciente,
                context
            )
        } catch (e: Exception) {
            Log.e("SincronizarPacientesService", "Erro ao sincronizar pacientes", e)
        }
    }

    suspend fun EnviarArquivos(paciente: Paciente, context: Context) {

        Log.i("Enviar Arquivos", "Enviando arquivos para o paciente: ${paciente.idBackend}")
        val token = SecureStorage.getToken(context)

        try {
            var ListaArquivos: MutableList<ArquivoRequest> = mutableListOf();

            if (paciente.arquivos.isNotEmpty()) {
                paciente.arquivos.forEach { arquivo ->

                    val dtoPaciente = ArquivoRequest(
                        nome = arquivo.nome,
                        uri = arquivo.uri,
                        conteudoArquivo = arquivo.conteudoArquivo
                    )

                    ListaArquivos.add(dtoPaciente)

                }

                val dtoArquivos = ArquivosRequest(
                    pacienteIdBackend = paciente.idBackend ?: "",
                    arquivos = ListaArquivos,
                    token = token ?: ""
                )

                sincronizarPacientesRepository.EnviarArquivosRequisicaoRepository(dtoArquivos)
            }


        } catch (e: Exception) {
            Log.e("SincronizarPacientesService", "Erro ao sincronizar paciente", e)
        }
    }

    suspend fun BuscarArquivos() {
        val dados:DataResponse<ListaArquivoResponse>? = sincronizarPacientesRepository.BuscarArquivosRepository()
        val pacientes = pacienteService.getPacientes()

        dados?.data

    }


}