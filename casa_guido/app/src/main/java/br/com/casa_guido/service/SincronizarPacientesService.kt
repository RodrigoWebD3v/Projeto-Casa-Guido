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
import br.com.casa_guido.models.Cirurgia
import br.com.casa_guido.models.ComposicaoFamiliar
import br.com.casa_guido.models.Cras
import br.com.casa_guido.models.Endereco
import br.com.casa_guido.models.HistoricoSaude
import br.com.casa_guido.models.Paciente
import br.com.casa_guido.models.Pessoa
import br.com.casa_guido.models.Quimio
import br.com.casa_guido.models.Radio
import br.com.casa_guido.models.SituacaoHabitacional
import br.com.casa_guido.models.Ubs
import br.com.casa_guido.models.toRequestDTO
import br.com.casa_guido.repository.SincronizarPacientesRepository
import kotlinx.serialization.Serializable

class SincronizarPacientesService(
    private val sincronizarPacientesRepository: SincronizarPacientesRepository,
    private val pacienteService: PacienteService,
    private val criarPacienteService: CriarPacienteService
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

            baixarPacientesSemIdBackend(context)
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
        val dados: DataResponse<ListaArquivoResponse>? =
            sincronizarPacientesRepository.BuscarArquivosRepository()
        val pacientes = pacienteService.getPacientes()

        dados?.data

    }

    @Serializable
    data class ListaIds(
        val listaId: List<String>,
    )

    suspend fun baixarPacientesSemIdBackend(
        context: Context
    ) {
        try {
            val token = SecureStorage.getToken(context)
            val listaIds = ListaIds(
                listaId = pacienteService.getPacientes().map {
                    it.idBackend
                }
            )
            Log.i(
                "SincronizarPacientesService",
                "Buscando pacientes sem idBackend: ${listaIds.listaId}"
            )
            val pacientes =
                sincronizarPacientesRepository.buscarPacientesSemIdBackend(token ?: "", listaIds)
            pacientes?.forEach {
                val pacientePessoa = Pessoa(
                    nome = it.pessoa.nome,
                    dataNascimento = it.pessoa.dataNascimento,
                    cpf = it.pessoa.cpf,
                    rg = it.pessoa.rg,
                    telefone = it.pessoa.telefone,
                    endereco = Endereco(
                        logradouro = it.pessoa.endereco.logradouro,
                        numero = it.pessoa.endereco.numero,
                        bairro = it.pessoa.endereco.bairro,
                        estado = it.pessoa.endereco.estado,
                        cep = it.pessoa.endereco.cep
                    )
                )
                val responsavelPessoa = Pessoa(
                    nome = it.responsavel?.nome ?: "",
                    dataNascimento = it.responsavel?.dataNascimento ?: "",
                    cpf = it.responsavel?.cpf ?: "",
                    rg = it.responsavel?.rg ?: "",
                    telefone = it.responsavel?.telefone ?: "",
                    endereco = Endereco(
                        logradouro = it.responsavel?.endereco?.logradouro ?: "",
                        numero = it.responsavel?.endereco?.numero ?: "",
                        bairro = it.responsavel?.endereco?.bairro ?: "",
                        estado = it.responsavel?.endereco?.estado ?: "",
                        cep = it.responsavel?.endereco?.cep ?: ""
                    )
                )

                val conjugeReponsavelPessoa = Pessoa(
                    nome = it.conjugeResponsavel?.nome ?: "",
                    dataNascimento = it.conjugeResponsavel?.dataNascimento ?: "",
                    cpf = it.conjugeResponsavel?.cpf ?: "",
                    rg = it.conjugeResponsavel?.rg ?: "",
                    telefone = it.conjugeResponsavel?.telefone ?: "",
                    endereco = Endereco(
                        logradouro = it.conjugeResponsavel?.endereco?.logradouro ?: "",
                        numero = it.conjugeResponsavel?.endereco?.numero ?: "",
                        bairro = it.conjugeResponsavel?.endereco?.bairro ?: "",
                        estado = it.conjugeResponsavel?.endereco?.estado ?: "",
                        cep = it.conjugeResponsavel?.endereco?.cep ?: ""
                    )
                )
                val outroResponsavelPessoa = Pessoa(
                    nome = it.outroResponsavel?.nome ?: "",
                    dataNascimento = it.outroResponsavel?.dataNascimento ?: "",
                    cpf = it.outroResponsavel?.cpf ?: "",
                    rg = it.outroResponsavel?.rg ?: "",
                    telefone = it.outroResponsavel?.telefone ?: "",
                    endereco = Endereco(
                        logradouro = it.outroResponsavel?.endereco?.logradouro ?: "",
                        numero = it.outroResponsavel?.endereco?.numero ?: "",
                        bairro = it.outroResponsavel?.endereco?.bairro ?: "",
                        estado = it.outroResponsavel?.endereco?.estado ?: "",
                        cep = it.outroResponsavel?.endereco?.cep ?: ""
                    )
                )

                criarPacienteService.criarPaciente(
                    Paciente(
                        idBackend = it._id,
                        pessoa = pacientePessoa,
                        responsavel = responsavelPessoa,
                        conjugeResponsavel = conjugeReponsavelPessoa,
                        outroResponsavel = outroResponsavelPessoa,
                        ubs = Ubs(
                            municipio = it.ubs?.municipio ?: "",
                            bairro = it.ubs?.bairro ?: ""
                        ),
                        cras = Cras(
                            municipio = it.cras?.municipio ?: "",
                            bairro = it.cras?.bairro ?: ""
                        ),
                        nomeMae = it.nomeMae,
                        nomePai = it.nomePai,
                        nomeOutro = it.nomeOutro,
                        remuneracao = it.remuneracao,
                        bpc = it.recebeBpc,
                        valorBpc = it.valorBpc,
                        alterado = false,
                        cirurgias = it.cirurgias.map { cirurgia ->
                            Cirurgia(
                                nome = cirurgia.nome,
                                data = cirurgia.data,
                                cid = cirurgia.cid,
                            )
                        },
                        quimios = it.quimioterapias.map { quimioterapia ->
                            Quimio(
                                dataInicio = quimioterapia.dataInicio,
                                dataUltimaSessao = quimioterapia.dataUltimaSessao,
                            )
                        },
                        radios = it.radioterapias.map { radioterapia ->
                            Radio(
                                dataInicio = radioterapia.dataInicio,
                                dataUltimaSessao = radioterapia.dataUltimaSessao,
                            )
                        },
                        historicoSaude = HistoricoSaude(
                            doencasFamilia = it.historicoSaude?.doencasFamilia ?: arrayOf(),
                            medicamentosUsoContinuo = it.historicoSaude?.medicamentosUsoContinuo
                                ?: "",
                            localProcuraAtendimento = it.historicoSaude?.localProcuraAtendimento
                                ?: arrayOf(),
                            recebeBeneficio = it.historicoSaude?.recebeBeneficio ?: 0,
                            beneficioDescricao = it.historicoSaude?.beneficioDescricao ?: "",
                        ),
                        composicaoFamiliar = it.composicaoFamiliar.map { composicao ->
                            ComposicaoFamiliar(
                                nome = composicao.nome,
                                parentesco = composicao.parentesco,
                                idade = composicao.idade,
                                renda = composicao.renda,
                            )
                        },
                        situacaoHabitacional = it.situacaoHabitacional?.let { situacao ->
                            SituacaoHabitacional(
                                comoAdquiriuCasa = situacao.comoAdquiriuCasa,
                                area = situacao.area ?: 0,
                                numeroComodos = situacao.numeroComodos,
                                material = situacao.material,
                                bens = situacao.bens,
                                meioDeTransporte = situacao.meioDeTransporte,
                                meioDeComunicao = situacao.meioDeComunicao,
                                possuiBanheiros = situacao.possuiBanheiros,
                            )
                        } ?: SituacaoHabitacional(),
                        status = it.status,
                        escolaNome = it.escolaNome ?: "",
                        tamRoupa = it.tamRoupa ?: "",
                        tamCalcado = it.tamCalcado ?: "",
                        diagnostico = it.diagnostico ?: "",
                    ),
                )
            }
        } catch (e: Exception) {
            Log.e("SincronizarPacientesService", "Erro ao buscar pacientes", e)
        }
    }


}