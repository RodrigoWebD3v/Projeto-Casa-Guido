package br.com.casa_guido.service

import android.content.Context
import android.util.Log
import br.com.casa_guido.configuration.SecureStorage
import br.com.casa_guido.dto.ArquivoRequest
import br.com.casa_guido.dto.ArquivosRequest
import br.com.casa_guido.dto.CreatePacienteResponse
import br.com.casa_guido.dto.DataResponse
import br.com.casa_guido.dto.ListaArquivoResponse
import br.com.casa_guido.dto.PacienteCompletoDTO
import br.com.casa_guido.dto.PacientesRequest
import br.com.casa_guido.models.Cirurgia
import br.com.casa_guido.models.ComposicaoFamiliar
import br.com.casa_guido.models.Cras
import br.com.casa_guido.models.Endereco
import br.com.casa_guido.models.HistoricoSaude
import br.com.casa_guido.models.Paciente
import br.com.casa_guido.models.Pessoa
import br.com.casa_guido.models.ProfissionalResponsavel
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
                    idBackend = dataResponse.data.id, alterado = false
                )
            )

            EnviarArquivos(
                paciente.copy(
                    idBackend = dataResponse.data.id,
                ), context
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
                paciente, context
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
                })
            Log.i(
                "SincronizarPacientesService",
                "Buscando pacientes sem idBackend: ${listaIds.listaId}"
            )
            val pacientes: List<PacienteCompletoDTO>? =
                sincronizarPacientesRepository.buscarPacientesSemIdBackend(token ?: "", listaIds)
            pacientes?.forEach {
                val enderecoPessoa = Endereco(
                    logradouro = it.pessoa.endereco.logradouro,
                    numero = it.pessoa.endereco.numero,
                    bairro = it.pessoa.endereco.bairro,
                    estado = it.pessoa.endereco.estado,
                    uf = it.pessoa.endereco.uf,
                    cep = it.pessoa.endereco.cep,
                    complemento = it.pessoa.endereco.complemento,
                    unidade = it.pessoa.endereco.unidade,
                    localidade = it.pessoa.endereco.localidade,
                    referencia = it.pessoa.endereco.referencia,
                    ibge = it.pessoa.endereco.ibge,
                    gia = it.pessoa.endereco.gia,
                    ddd = it.pessoa.endereco.ddd,
                    siafi = it.pessoa.endereco.siafi,
                    pais = it.pessoa.endereco.pais,
                )

                val enderecoResponsavel = Endereco(
                    logradouro = it.responsavel?.endereco?.logradouro ?: "",
                    numero = it.responsavel?.endereco?.numero ?: "",
                    bairro = it.responsavel?.endereco?.bairro ?: "",
                    estado = it.responsavel?.endereco?.estado ?: "",
                    cep = it.responsavel?.endereco?.cep ?: "",
                    complemento = it.responsavel?.endereco?.complemento ?: "",
                    unidade = it.responsavel?.endereco?.unidade ?: "",
                    localidade = it.responsavel?.endereco?.localidade ?: "",
                    referencia = it.responsavel?.endereco?.referencia ?: "",
                    ibge = it.responsavel?.endereco?.ibge ?: "",
                    gia = it.responsavel?.endereco?.gia ?: "",
                    ddd = it.responsavel?.endereco?.ddd ?: "",
                    siafi = it.responsavel?.endereco?.siafi ?: "",
                    pais = it.responsavel?.endereco?.pais ?: "",
                    uf = it.pessoa.endereco.uf
                )

                val enderecoConjuge = Endereco(
                    logradouro = it.conjugeResponsavel?.endereco?.logradouro ?: "",
                    numero = it.conjugeResponsavel?.endereco?.numero ?: "",
                    bairro = it.conjugeResponsavel?.endereco?.bairro ?: "",
                    estado = it.conjugeResponsavel?.endereco?.estado ?: "",
                    cep = it.conjugeResponsavel?.endereco?.cep ?: "",
                    complemento = it.conjugeResponsavel?.endereco?.complemento ?: "",
                    unidade = it.conjugeResponsavel?.endereco?.unidade ?: "",
                    localidade = it.conjugeResponsavel?.endereco?.localidade ?: "",
                    referencia = it.conjugeResponsavel?.endereco?.referencia ?: "",
                    ibge = it.conjugeResponsavel?.endereco?.ibge ?: "",
                    gia = it.conjugeResponsavel?.endereco?.gia ?: "",
                    ddd = it.conjugeResponsavel?.endereco?.ddd ?: "",
                    siafi = it.conjugeResponsavel?.endereco?.siafi ?: "",
                    pais = it.conjugeResponsavel?.endereco?.pais ?: "",
                    uf = it.pessoa.endereco.uf
                )

                val enderecoOutro = Endereco(
                    logradouro = it.outroResponsavel?.endereco?.logradouro ?: "",
                    numero = it.outroResponsavel?.endereco?.numero ?: "",
                    bairro = it.outroResponsavel?.endereco?.bairro ?: "",
                    estado = it.outroResponsavel?.endereco?.estado ?: "",
                    cep = it.outroResponsavel?.endereco?.cep ?: "",
                    complemento = it.outroResponsavel?.endereco?.complemento ?: "",
                    unidade = it.outroResponsavel?.endereco?.unidade ?: "",
                    localidade = it.outroResponsavel?.endereco?.localidade ?: "",
                    referencia = it.outroResponsavel?.endereco?.referencia ?: "",
                    ibge = it.outroResponsavel?.endereco?.ibge ?: "",
                    gia = it.outroResponsavel?.endereco?.gia ?: "",
                    ddd = it.outroResponsavel?.endereco?.ddd ?: "",
                    siafi = it.outroResponsavel?.endereco?.siafi ?: "",
                    pais = it.outroResponsavel?.endereco?.pais ?: "",
                    uf = it.pessoa.endereco.uf
                )


                val pacientePessoa = Pessoa(
                    nome = it.pessoa.nome,
                    dataNascimento = it.pessoa.dataNascimento,
                    cpf = it.pessoa.cpf,
                    rg = it.pessoa.rg,
                    telefone = it.pessoa.telefone,
                    endereco = enderecoPessoa,
                    naturalidade = it.naturalidade,
                    escolaridade = it.pessoa.escolaridade,
                    serie = it.pessoa.serie,
                    estadoCivil = it.pessoa.estadoCivil,
                    situacaoProfissional = it.pessoa.situacaoProfissional,
                    salario = it.pessoa.salario,
                    cartaoSus = it.pessoa.cartaoSus,
                    profissao = it.pessoa.profissao,
                    respPrincipal = it.pessoa.respPrincipal,
                    tipoEscola = it.pessoa.tipoEscola
                )

                val responsavelPessoa = Pessoa(
                    nome = it.responsavel?.nome ?: "",
                    dataNascimento = it.responsavel?.dataNascimento ?: "",
                    cpf = it.responsavel?.cpf ?: "",
                    rg = it.responsavel?.rg ?: "",
                    telefone = it.responsavel?.telefone ?: "",
                    endereco = enderecoResponsavel,
                    naturalidade = it.responsavel?.naturalidade ?: "",
                    escolaridade = it.responsavel?.escolaridade ?: 0,
                    serie = it.responsavel?.serie ?: 0,
                    estadoCivil = it.responsavel?.estadoCivil ?: 0,
                    situacaoProfissional = it.responsavel?.situacaoProfissional ?: 0,
                    salario = it.responsavel?.salario ?: "",
                    cartaoSus = it.responsavel?.cartaoSus ?: "",
                    profissao = it.responsavel?.profissao ?: "",
                    respPrincipal = it.responsavel?.respPrincipal ?: 0,
                    tipoEscola = it.responsavel?.tipoEscola ?: 0,
                )

                val conjugeReponsavelPessoa = Pessoa(
                    nome = it.conjugeResponsavel?.nome ?: "",
                    dataNascimento = it.conjugeResponsavel?.dataNascimento ?: "",
                    cpf = it.conjugeResponsavel?.cpf ?: "",
                    rg = it.conjugeResponsavel?.rg ?: "",
                    telefone = it.conjugeResponsavel?.telefone ?: "",
                    endereco = enderecoConjuge,
                    naturalidade = it.conjugeResponsavel?.naturalidade ?: "",
                    escolaridade = it.conjugeResponsavel?.escolaridade ?: 0,
                    serie = it.conjugeResponsavel?.serie ?: 0,
                    estadoCivil = it.conjugeResponsavel?.estadoCivil ?: 0,
                    situacaoProfissional = it.conjugeResponsavel?.situacaoProfissional ?: 0,
                    salario = it.conjugeResponsavel?.salario ?: "",
                    cartaoSus = it.conjugeResponsavel?.cartaoSus ?: "",
                    profissao = it.conjugeResponsavel?.profissao ?: "",
                    respPrincipal = it.conjugeResponsavel?.respPrincipal ?: 0,
                    tipoEscola = it.conjugeResponsavel?.tipoEscola ?: 0,
                )

                val outroResponsavelPessoa = Pessoa(
                    nome = it.outroResponsavel?.nome ?: "",
                    dataNascimento = it.outroResponsavel?.dataNascimento ?: "",
                    cpf = it.outroResponsavel?.cpf ?: "",
                    rg = it.outroResponsavel?.rg ?: "",
                    telefone = it.outroResponsavel?.telefone ?: "",
                    endereco = enderecoOutro,
                    naturalidade = it.outroResponsavel?.naturalidade ?: "",
                    escolaridade = it.outroResponsavel?.escolaridade ?: 0,
                    serie = it.outroResponsavel?.serie ?: 0,
                    estadoCivil = it.outroResponsavel?.estadoCivil ?: 0,
                    situacaoProfissional = it.outroResponsavel?.situacaoProfissional ?: 0,
                    salario = it.outroResponsavel?.salario ?: "",
                    cartaoSus = it.outroResponsavel?.cartaoSus ?: "",
                    profissao = it.outroResponsavel?.profissao ?: "",
                    respPrincipal = it.outroResponsavel?.respPrincipal ?: 0,
                    tipoEscola = it.outroResponsavel?.tipoEscola ?: 0,

                    )

                val cirurgias = it.cirurgias.map { cirurgia ->
                    Cirurgia(
                        nome = cirurgia.nome,
                        data = cirurgia.data,
                        cid = cirurgia.cid,
                    )
                }

                val quimios = it.quimioterapias.map { quimioterapia ->
                    Quimio(
                        dataInicio = quimioterapia.dataInicio,
                        dataUltimaSessao = quimioterapia.dataUltimaSessao,
                    )
                }

                val radios = it.radioterapias.map { radioterapia ->
                    Radio(
                        dataInicio = radioterapia.dataInicio,
                        dataUltimaSessao = radioterapia.dataUltimaSessao,
                    )
                }

                val historicoSaude = HistoricoSaude(
                    doencasFamilia = it.historicoSaude?.doencasFamilia ?: arrayOf(),
                    medicamentosUsoContinuo = it.historicoSaude?.medicamentosUsoContinuo ?: "",
                    localProcuraAtendimento = it.historicoSaude?.localProcuraAtendimento
                        ?: arrayOf(),
                    recebeBeneficio = it.historicoSaude?.recebeBeneficio ?: 0,
                    beneficioDescricao = it.historicoSaude?.beneficioDescricao ?: "",

                    )

                val composicaoFamiliar = it.composicaoFamiliar.map { composicao ->
                    ComposicaoFamiliar(
                        nome = composicao.nome,
                        parentesco = composicao.parentesco,
                        idade = composicao.idade,
                        renda = composicao.renda,
                        dataNascimento = composicao.dataNascimento,
                        serie = composicao.serie ?: "",
                        trabalhaOpcional = composicao.trabalhaOpcional ?: 0,
                        estudaOpcional = composicao.estudaOpcional ?: 0,
                    )
                }
                val situacaoHabitacional = it.situacaoHabitacional?.let { situacao ->
                    SituacaoHabitacional(
                        comoAdquiriuCasa = situacao.comoAdquiriuCasa,
                        area = situacao.area ?: 0,
                        numeroComodos = situacao.numeroComodos ?: "",
                        material = situacao.material,
                        bens = situacao.bens,
                        meioDeTransporte = situacao.meioDeTransporte,
                        meioDeComunicao = situacao.meioDeComunicao,
                        possuiBanheiros = situacao.possuiBanheiros,
                        dentroDeCasa = situacao.dentroDeCasa,
                    )
                } ?: SituacaoHabitacional()

                val profissionalResponsavel = ProfissionalResponsavel(
                    nome = it.profissionalResponsavel?.nome ?: "Profissional Não Informado",
                    id = it.profissionalResponsavel?.id ?: "",
                    crm = it.profissionalResponsavel?.crm ?: "",

                    )

                criarPacienteService.criarPaciente(
                    Paciente(
                        idBackend = it._id,
                        pessoa = pacientePessoa,
                        responsavel = responsavelPessoa,
                        conjugeResponsavel = conjugeReponsavelPessoa,
                        outroResponsavel = outroResponsavelPessoa,
                        ubs = Ubs(
                            municipio = it.ubs?.municipio ?: "", bairro = it.ubs?.bairro ?: ""
                        ),
                        cras = Cras(
                            municipio = it.cras?.municipio ?: "", bairro = it.cras?.bairro ?: ""
                        ),
                        nomeMae = it.nomeMae,
                        nomePai = it.nomePai,
                        nomeOutro = it.nomeOutro,
                        remuneracao = it.remuneracao,
                        bpc = it.recebeBpc,
                        valorBpc = it.valorBpc,
                        alterado = false,
                        cirurgias = cirurgias,
                        quimios = quimios,
                        radios = radios,
                        historicoSaude = historicoSaude,
                        composicaoFamiliar = composicaoFamiliar,
                        situacaoHabitacional = situacaoHabitacional,
                        status = it.status,
                        escolaNome = it.escolaNome,
                        tamRoupa = it.tamRoupa,
                        tamCalcado = it.tamCalcado,
                        diagnostico = it.diagnostico,
                        observacoes = it.observacoes,
                        tipoEscola = it.tipoEscola ?: 0,
                        aptoReceberBpc = it.recebeBpc,
                        origen_info_ong = it.origenInfoOng,
                        profissionalResponsavel = profissionalResponsavel
                    ),
                )
            }
        } catch (e: Exception) {
            Log.e("SincronizarPacientesService", "Erro ao buscar pacientes", e)
        }
    }


}