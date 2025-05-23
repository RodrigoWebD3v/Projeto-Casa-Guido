package br.com.casa_guido.screens.cadastro

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.casa_guido.configuration.Status
import br.com.casa_guido.screens.Cirurgia
import br.com.casa_guido.screens.ComposicaoFamiliar
import br.com.casa_guido.screens.Paciente
import br.com.casa_guido.screens.Quimio
import br.com.casa_guido.screens.Radio
import br.com.casa_guido.screens.cadastro.formularios.cirurgia.CamposCirurgia
import br.com.casa_guido.screens.cadastro.formularios.compFamiliar.CamposCompFamiliar
import br.com.casa_guido.screens.cadastro.formularios.conjuge.CamposConjuge
import br.com.casa_guido.screens.cadastro.formularios.conjuge.CamposOutro
import br.com.casa_guido.screens.cadastro.formularios.endereco.CamposEndereco
import br.com.casa_guido.screens.cadastro.formularios.historicoSaudePaciente.CamposHistoricoSaude
import br.com.casa_guido.screens.cadastro.formularios.identificacaoPaciente.CamposIdentificacao
import br.com.casa_guido.screens.cadastro.formularios.observacao.CamposObservacao
import br.com.casa_guido.screens.cadastro.formularios.quimio.CamposQuimio
import br.com.casa_guido.screens.cadastro.formularios.radio.CamposRadio
import br.com.casa_guido.screens.cadastro.formularios.responsavel.CamposResponsavel
import br.com.casa_guido.screens.cadastro.formularios.situacaoHabitacional.CamposSituacaoHabitacional
import br.com.casa_guido.screens.cadastro.formularios.socioEconomico.CamposSocioEconomico
import br.com.casa_guido.service.CriarPacienteService
import br.com.casa_guido.service.PacienteService
import br.com.casa_guido.service.ViaCepService
import br.com.casa_guido.util.Utils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CadastroScreenViewModel(
    private val viaCepService: ViaCepService,
    private val pacienteService: PacienteService,
    private val criarPacienteService: CriarPacienteService,
) : ViewModel() {

    private val _status = MutableStateFlow<Status>(Status.SemInteracao)
    val status = _status.asStateFlow()

    private val _uiState = MutableStateFlow<CadastroScreenUiState>(CadastroScreenUiState())
    val uiState = _uiState.asStateFlow()

    private val _paciente = MutableStateFlow<Paciente>(Paciente())
    val paciente = _paciente.asStateFlow()

    var _context: Context? = null


    fun getPaciente(id: String) {
        viewModelScope.launch {
            var paciente = pacienteService.getById(id)
            _paciente.value = paciente!!
            _uiState.value = _uiState.value.copy(
                edicao = true
            )
        }
    }

    fun save() {
        viewModelScope.launch {
            criarPacienteService.criarPaciente(_paciente.value)
        }
    }

    fun onChangeCirurgia(camposCirurgia: CamposCirurgia, valor: Cirurgia) {
        when (camposCirurgia) {
            CamposCirurgia.ADD_CIRURGIA -> {
                _paciente.value = _paciente.value.copy(
                    cirurgias = _paciente.value.cirurgias + valor
                )
            }

            CamposCirurgia.REMOVE_CIRURGIA -> {
                _paciente.value = _paciente.value.copy(
                    cirurgias = _paciente.value.cirurgias - valor
                )
            }
        }
    }

    fun onChangeSocioEconomico(camposSocioEconomico: CamposSocioEconomico, valor: String) {
        when (camposSocioEconomico) {
            CamposSocioEconomico.ESCOLARIDADE -> {
                _paciente.value = _paciente.value.copy(
                    pessoa = _paciente.value.pessoa.copy(
                        escolaridade = valor
                    )
                )
            }

            CamposSocioEconomico.SERIE -> {
                _paciente.value = _paciente.value.copy(
                    pessoa = _paciente.value.pessoa.copy(
                        serie = valor
                    )
                )
            }

            CamposSocioEconomico.NOME_ESCOLA -> {
                _paciente.value = _paciente.value.copy(
                    escolaNome = valor
                )
            }

            CamposSocioEconomico.REMUNERACAO -> {
                _paciente.value = _paciente.value.copy(
                    remuneracao = valor
                )
            }

            CamposSocioEconomico.REMUNERACAO_OPT -> {
                Log.i("CadastroScreenViewModel", "onChangeSocioEconomico REMUNERACAO_OPT: $valor")
                _paciente.value = _paciente.value.copy(
                    bpc = valor.toInt()
                )
            }

            CamposSocioEconomico.VALOR_BPC -> {
                _paciente.value = _paciente.value.copy(
                    valorBpc = valor
                )
            }

            CamposSocioEconomico.TAMANHO_ROUPA -> {
                _paciente.value = _paciente.value.copy(
                    tamRoupa = valor
                )
            }

            CamposSocioEconomico.TAMANHO_CALCADO -> {
                _paciente.value = _paciente.value.copy(
                    tamCalcado = valor
                )
            }

            CamposSocioEconomico.DIAGNOSTICO -> {
                _paciente.value = _paciente.value.copy(
                    diagnostico = valor
                )
            }

            CamposSocioEconomico.RECEBE_BPC -> {
                _paciente.value = _paciente.value.copy(
                    bpc = valor.toInt()
                )
            }


        }
    }

    fun onChangeEndereco(camposEndereco: CamposEndereco, valor: String) {
        when (camposEndereco) {
            CamposEndereco.LOGRADOURO -> {
                Log.i("CadastroScreenViewModel", "onChangeEndereco LOGRADOURO: $valor")
                _paciente.value = _paciente.value.copy(
                    pessoa = _paciente.value.pessoa.copy(
                        endereco = _paciente.value.pessoa.endereco.copy(
                            logradouro = valor
                        )
                    )
                )
            }

            CamposEndereco.NUMERO -> {
                _paciente.value = _paciente.value.copy(
                    pessoa = _paciente.value.pessoa.copy(
                        endereco = _paciente.value.pessoa.endereco.copy(
                            numero = valor
                        )
                    )
                )
            }

            CamposEndereco.COMPLEMENTO -> {
                _paciente.value = _paciente.value.copy(
                    pessoa = _paciente.value.pessoa.copy(
                        endereco = _paciente.value.pessoa.endereco.copy(
                            complemento = valor
                        )
                    )
                )
            }

            CamposEndereco.BAIRRO -> {
                Log.i("CadastroScreenViewModel", "onChangeEndereco BAIRRO: $valor")
                _paciente.value = _paciente.value.copy(
                    pessoa = _paciente.value.pessoa.copy(
                        endereco = _paciente.value.pessoa.endereco.copy(
                            bairro = valor
                        )
                    )
                )
            }

            CamposEndereco.LOCALIDADE -> {
                _paciente.value = _paciente.value.copy(
                    pessoa = _paciente.value.pessoa.copy(
                        endereco = _paciente.value.pessoa.endereco.copy(
                            localidade = valor
                        )
                    )
                )
            }

            CamposEndereco.UF -> {
                _paciente.value = _paciente.value.copy(
                    pessoa = _paciente.value.pessoa.copy(
                        endereco = _paciente.value.pessoa.endereco.copy(
                            uf = valor
                        )
                    )
                )
            }

            CamposEndereco.CEP -> {
                _paciente.value = _paciente.value.copy(
                    pessoa = _paciente.value.pessoa.copy(
                        endereco = _paciente.value.pessoa.endereco.copy(
                            cep = valor
                        )
                    )
                )

                if (valor.length == 8 && valor.all { it.isDigit() } && Utils.verificarConexao(
                        context = _context!!
                    )) {
                    try {
                        viewModelScope.launch {
                            val endereco = viaCepService.buscaCep(valor)
                            _paciente.value = _paciente.value.copy(
                                pessoa = _paciente.value.pessoa.copy(
                                    endereco = endereco!!
                                )
                            )
                            _status.value = Status.Sucesso("Endereço encontrado")
                        }
                    } catch (e: Exception) {
                        _status.value = Status.Erro("Erro ao buscar endereço")
                    }
                } else {
                    if (Utils.verificarConexao(context = _context!!)) {
                        _status.update {
                            Status.Erro("Sem conexão para buscar CEP")
                        }
                    }
                }
            }

            CamposEndereco.REFERENCIA -> {
                _paciente.value = _paciente.value.copy(
                    pessoa = _paciente.value.pessoa.copy(
                        endereco = _paciente.value.pessoa.endereco.copy(
                            referencia = valor
                        )
                    )
                )
            }
        }
    }

    fun onChangeEnderecoResponsavel(camposEndereco: CamposEndereco, valor: String) {
        when (camposEndereco) {
            CamposEndereco.LOGRADOURO -> {
                Log.i("CadastroScreenViewModel", "onChangeEndereco LOGRADOURO: $valor")
                _paciente.value = _paciente.value.copy(
                    responsavel = _paciente.value.responsavel.copy(
                        endereco = _paciente.value.responsavel.endereco.copy(
                            logradouro = valor
                        )
                    )
                )
            }

            CamposEndereco.NUMERO -> {
                _paciente.value = _paciente.value.copy(
                    responsavel = _paciente.value.responsavel.copy(
                        endereco = _paciente.value.responsavel.endereco.copy(
                            numero = valor
                        )
                    )
                )
            }

            CamposEndereco.COMPLEMENTO -> {
                _paciente.value = _paciente.value.copy(
                    responsavel = _paciente.value.responsavel.copy(
                        endereco = _paciente.value.responsavel.endereco.copy(
                            complemento = valor
                        )
                    )
                )
            }

            CamposEndereco.BAIRRO -> {
                Log.i("CadastroScreenViewModel", "onChangeEndereco BAIRRO: $valor")
                _paciente.value = _paciente.value.copy(
                    responsavel = _paciente.value.responsavel.copy(
                        endereco = _paciente.value.responsavel.endereco.copy(
                            bairro = valor
                        )
                    )
                )
            }

            CamposEndereco.LOCALIDADE -> {
                _paciente.value = _paciente.value.copy(
                    responsavel = _paciente.value.responsavel.copy(
                        endereco = _paciente.value.responsavel.endereco.copy(
                            localidade = valor
                        )
                    )
                )
            }

            CamposEndereco.UF -> {
                _paciente.value = _paciente.value.copy(
                    responsavel = _paciente.value.responsavel.copy(
                        endereco = _paciente.value.responsavel.endereco.copy(
                            uf = valor
                        )
                    )
                )
            }


            CamposEndereco.REFERENCIA -> {
                _paciente.value = _paciente.value.copy(
                    responsavel = _paciente.value.responsavel.copy(
                        endereco = _paciente.value.responsavel.endereco.copy(
                            referencia = valor
                        )
                    )
                )
            }

            CamposEndereco.CEP -> {
                _paciente.value = _paciente.value.copy(
                    responsavel = _paciente.value.responsavel.copy(
                        endereco = _paciente.value.responsavel.endereco.copy(
                            cep = valor
                        )
                    )
                )

                if (valor.length == 8 && valor.all { it.isDigit() }) {
                    try {
                        viewModelScope.launch {
                            val endereco = viaCepService.buscaCep(valor)
                            _paciente.value = _paciente.value.copy(
                                responsavel = _paciente.value.responsavel.copy(
                                    endereco = endereco!!
                                )
                            )
                            _status.value = Status.Sucesso("Endereço encontrado")
                        }
                    } catch (e: Exception) {
                        _status.value = Status.Erro("Erro ao buscar endereço")
                    }
                }
            }
        }

    }

    fun onChangeEnderecoConjuge(camposEndereco: CamposEndereco, valor: String) {
        when (camposEndereco) {
            CamposEndereco.LOGRADOURO -> {
                Log.i("CadastroScreenViewModel", "onChangeEndereco LOGRADOURO: $valor")
                _paciente.value = _paciente.value.copy(
                    conjugeResponsavel = _paciente.value.conjugeResponsavel.copy(
                        endereco = _paciente.value.conjugeResponsavel.endereco.copy(
                            logradouro = valor
                        )
                    )
                )
            }

            CamposEndereco.NUMERO -> {
                _paciente.value = _paciente.value.copy(
                    conjugeResponsavel = _paciente.value.conjugeResponsavel.copy(
                        endereco = _paciente.value.conjugeResponsavel.endereco.copy(
                            numero = valor
                        )
                    )
                )
            }

            CamposEndereco.COMPLEMENTO -> {
                _paciente.value = _paciente.value.copy(
                    conjugeResponsavel = _paciente.value.conjugeResponsavel.copy(
                        endereco = _paciente.value.conjugeResponsavel.endereco.copy(
                            complemento = valor
                        )
                    )
                )
            }

            CamposEndereco.BAIRRO -> {
                Log.i("CadastroScreenViewModel", "onChangeEndereco BAIRRO: $valor")
                _paciente.value = _paciente.value.copy(
                    conjugeResponsavel = _paciente.value.conjugeResponsavel.copy(
                        endereco = _paciente.value.conjugeResponsavel.endereco.copy(
                            bairro = valor
                        )
                    )
                )
            }

            CamposEndereco.LOCALIDADE -> {
                _paciente.value = _paciente.value.copy(
                    conjugeResponsavel = _paciente.value.conjugeResponsavel.copy(
                        endereco = _paciente.value.conjugeResponsavel.endereco.copy(
                            localidade = valor
                        )
                    )
                )
            }

            CamposEndereco.UF -> {
                _paciente.value = _paciente.value.copy(
                    conjugeResponsavel = _paciente.value.conjugeResponsavel.copy(
                        endereco = _paciente.value.conjugeResponsavel.endereco.copy(
                            uf = valor
                        )
                    )
                )
            }


            CamposEndereco.REFERENCIA -> {
                _paciente.value = _paciente.value.copy(
                    conjugeResponsavel = _paciente.value.conjugeResponsavel.copy(
                        endereco = _paciente.value.conjugeResponsavel.endereco.copy(
                            referencia = valor
                        )
                    )
                )
            }

            CamposEndereco.CEP -> {
                _paciente.value = _paciente.value.copy(
                    conjugeResponsavel = _paciente.value.conjugeResponsavel.copy(
                        endereco = _paciente.value.conjugeResponsavel.endereco.copy(
                            cep = valor
                        )
                    )
                )

                if (valor.length == 8 && valor.all { it.isDigit() }) {
                    try {
                        viewModelScope.launch {
                            val endereco = viaCepService.buscaCep(valor)
                            _paciente.value = _paciente.value.copy(
                                conjugeResponsavel = _paciente.value.conjugeResponsavel.copy(
                                    endereco = endereco!!
                                )
                            )
                            _status.value = Status.Sucesso("Endereço encontrado")
                        }
                    } catch (e: Exception) {
                        _status.value = Status.Erro("Erro ao buscar endereço")
                    }
                }
            }
        }

    }

    fun onChangeEnderecoOutro(camposEndereco: CamposEndereco, valor: String) {
        when (camposEndereco) {
            CamposEndereco.LOGRADOURO -> {
                Log.i("CadastroScreenViewModel", "onChangeEndereco LOGRADOURO: $valor")
                _paciente.value = _paciente.value.copy(
                    outroResponsavel = _paciente.value.outroResponsavel.copy(
                        endereco = _paciente.value.outroResponsavel.endereco.copy(
                            logradouro = valor
                        )
                    )
                )
            }

            CamposEndereco.NUMERO -> {
                _paciente.value = _paciente.value.copy(
                    outroResponsavel = _paciente.value.outroResponsavel.copy(
                        endereco = _paciente.value.outroResponsavel.endereco.copy(
                            numero = valor
                        )
                    )
                )
            }

            CamposEndereco.COMPLEMENTO -> {
                _paciente.value = _paciente.value.copy(
                    outroResponsavel = _paciente.value.outroResponsavel.copy(
                        endereco = _paciente.value.outroResponsavel.endereco.copy(
                            complemento = valor
                        )
                    )
                )
            }

            CamposEndereco.BAIRRO -> {
                Log.i("CadastroScreenViewModel", "onChangeEndereco BAIRRO: $valor")
                _paciente.value = _paciente.value.copy(
                    outroResponsavel = _paciente.value.outroResponsavel.copy(
                        endereco = _paciente.value.outroResponsavel.endereco.copy(
                            bairro = valor
                        )
                    )
                )
            }

            CamposEndereco.LOCALIDADE -> {
                _paciente.value = _paciente.value.copy(
                    outroResponsavel = _paciente.value.outroResponsavel.copy(
                        endereco = _paciente.value.outroResponsavel.endereco.copy(
                            localidade = valor
                        )
                    )
                )
            }

            CamposEndereco.UF -> {
                _paciente.value = _paciente.value.copy(
                    outroResponsavel = _paciente.value.outroResponsavel.copy(
                        endereco = _paciente.value.outroResponsavel.endereco.copy(
                            uf = valor
                        )
                    )
                )
            }


            CamposEndereco.REFERENCIA -> {
                _paciente.value = _paciente.value.copy(
                    outroResponsavel = _paciente.value.outroResponsavel.copy(
                        endereco = _paciente.value.outroResponsavel.endereco.copy(
                            referencia = valor
                        )
                    )
                )
            }

            CamposEndereco.CEP -> {
                _paciente.value = _paciente.value.copy(
                    outroResponsavel = _paciente.value.outroResponsavel.copy(
                        endereco = _paciente.value.outroResponsavel.endereco.copy(
                            cep = valor
                        )
                    )
                )

                if (valor.length == 8 && valor.all { it.isDigit() }) {
                    try {
                        viewModelScope.launch {
                            val endereco = viaCepService.buscaCep(valor)
                            _paciente.value = _paciente.value.copy(
                                outroResponsavel = _paciente.value.outroResponsavel.copy(
                                    endereco = endereco!!
                                )
                            )
                            _status.value = Status.Sucesso("Endereço encontrado")
                        }
                    } catch (e: Exception) {
                        _status.value = Status.Erro("Erro ao buscar endereço")
                    }
                }
            }
        }

    }

    fun onChangeIdentificacaoPaciente(camposIdentificacao: CamposIdentificacao, valor: String) {
        when (camposIdentificacao) {
            CamposIdentificacao.NOME -> {
                _paciente.value = _paciente.value.copy(
                    pessoa = _paciente.value.pessoa.copy(
                        nome = valor
                    )
                )
            }

            CamposIdentificacao.NOME_MAE -> {
                _paciente.value = _paciente.value.copy(
                    nomeMae = valor
                )
            }

            CamposIdentificacao.NOME_PAI -> {
                _paciente.value = _paciente.value.copy(
                    nomePai = valor
                )
            }

            CamposIdentificacao.CPF -> {
                _paciente.value = _paciente.value.copy(
                    pessoa = _paciente.value.pessoa.copy(
                        cpf = valor
                    )
                )
            }

            CamposIdentificacao.RG -> {
                _paciente.value = _paciente.value.copy(
                    pessoa = _paciente.value.pessoa.copy(
                        rg = valor
                    )
                )
            }

            CamposIdentificacao.CARTAO_SUS -> {
                _paciente.value = _paciente.value.copy(
                    pessoa = _paciente.value.pessoa.copy(
                        cartaoSus = valor
                    )
                )
            }

            CamposIdentificacao.DATA_NASCIMENTO -> {
                _paciente.value = _paciente.value.copy(
                    pessoa = _paciente.value.pessoa.copy(
                        dataNascimento = valor
                    )
                )
            }

            CamposIdentificacao.TELEFONE -> {
                _paciente.value = _paciente.value.copy(
                    pessoa = _paciente.value.pessoa.copy(
                        telefone = valor
                    )
                )
            }

            CamposIdentificacao.NOME_RESPONSAVEL -> {
                _paciente.value = _paciente.value.copy(
                    responsavel = _paciente.value.responsavel.copy(
                        nome = valor
                    )
                )

            }

            CamposIdentificacao.NOME_OUTRO_RESPONSAVEL -> {
                _paciente.value = _paciente.value.copy(
                    nomeOutro = valor
                )
            }
        }
    }

    fun onChangeResponsavel(tipoCampo: CamposResponsavel, valor: String) {
        when (tipoCampo) {
            CamposResponsavel.NOME_RESPONSAVEL -> {
                _paciente.value = _paciente.value.copy(
                    responsavel = _paciente.value.responsavel.copy(
                        nome = valor
                    )
                )
            }

            CamposResponsavel.CPF_RESPONSAVEL -> {
                _paciente.value = _paciente.value.copy(
                    responsavel = _paciente.value.responsavel.copy(
                        cpf = valor
                    )
                )
            }

            CamposResponsavel.TELEFONE_RESPONSAVEL -> {
                _paciente.value = _paciente.value.copy(
                    responsavel = _paciente.value.responsavel.copy(
                        telefone = valor
                    )
                )
            }

            CamposResponsavel.RG_RESPONSAVEL -> {
                _paciente.value = _paciente.value.copy(
                    responsavel = _paciente.value.responsavel.copy(
                        rg = valor
                    )
                )
            }

            CamposResponsavel.DATA_NASCIMENTO_RESPONSAVEL -> {
                _paciente.value = _paciente.value.copy(
                    responsavel = _paciente.value.responsavel.copy(
                        dataNascimento = valor
                    )
                )
            }

            CamposResponsavel.NATURALIDADE_RESPONSAVEL -> {
                _paciente.value = _paciente.value.copy(
                    responsavel = _paciente.value.responsavel.copy(
                        naturalidade = valor
                    )
                )
            }

            CamposResponsavel.ESCOLARIDADE_RESPONSAVEL -> {
                _paciente.value = _paciente.value.copy(
                    responsavel = _paciente.value.responsavel.copy(
                        escolaridade = valor
                    )
                )
            }

            CamposResponsavel.ESTADO_CIVIL_RESPONSAVEL -> {
                _paciente.value = _paciente.value.copy(
                    responsavel = _paciente.value.responsavel.copy(
                        estadoCivil = valor.toInt()
                    )
                )
            }

            CamposResponsavel.SITUACAO_PROFISSIONAL_RESPONSAVEL -> {
                _paciente.value = _paciente.value.copy(
                    responsavel = _paciente.value.responsavel.copy(
                        situacaoProfissional = valor.toInt()
                    )
                )
            }

            CamposResponsavel.SALARIO_RESPONSAVEL -> {
                _paciente.value = _paciente.value.copy(
                    responsavel = _paciente.value.responsavel.copy(
                        salario = valor
                    )
                )
            }

            CamposResponsavel.CARTAO_SUS -> {
                Log.i("CadastroScreenViewModel", "onChangeEndereco: $valor")
                _paciente.value = _paciente.value.copy(
                    responsavel = _paciente.value.responsavel.copy(
                        cartaoSus = valor
                    )
                )
            }
        }
    }

    fun onChangeConjuge(tipoCampo: CamposConjuge, valor: String) {
        when (tipoCampo) {
            CamposConjuge.NOME_CONJUGE -> {
                _paciente.value = _paciente.value.copy(
                    conjugeResponsavel = _paciente.value.conjugeResponsavel.copy(
                        nome = valor
                    )
                )
            }

            CamposConjuge.CPF_CONJUGE -> {
                _paciente.value = _paciente.value.copy(
                    conjugeResponsavel = _paciente.value.conjugeResponsavel.copy(
                        cpf = valor
                    )
                )
            }

            CamposConjuge.TELEFONE_CONJUGE -> {
                _paciente.value = _paciente.value.copy(
                    conjugeResponsavel = _paciente.value.conjugeResponsavel.copy(
                        telefone = valor
                    )
                )
            }

            CamposConjuge.RG_CONJUGE -> {
                _paciente.value = _paciente.value.copy(
                    conjugeResponsavel = _paciente.value.conjugeResponsavel.copy(
                        rg = valor
                    )
                )
            }

            CamposConjuge.DATA_NASCIMENTO_CONJUGE -> {
                _paciente.value = _paciente.value.copy(
                    conjugeResponsavel = _paciente.value.conjugeResponsavel.copy(
                        dataNascimento = valor
                    )
                )
            }

            CamposConjuge.NATURALIDADE_CONJUGE -> {
                _paciente.value = _paciente.value.copy(
                    conjugeResponsavel = _paciente.value.conjugeResponsavel.copy(
                        naturalidade = valor
                    )
                )
            }

            CamposConjuge.ESCOLARIDADE_CONJUGE -> {
                _paciente.value = _paciente.value.copy(
                    conjugeResponsavel = _paciente.value.conjugeResponsavel.copy(
                        escolaridade = valor
                    )
                )
            }

            CamposConjuge.ESTADO_CIVIL_CONJUGE -> {
                _paciente.value = _paciente.value.copy(
                    conjugeResponsavel = _paciente.value.conjugeResponsavel.copy(
                        estadoCivil = valor.toInt()
                    )
                )
            }

            CamposConjuge.SITUACAO_PROFISSIONAL_CONJUGE -> {
                _paciente.value = _paciente.value.copy(
                    conjugeResponsavel = _paciente.value.conjugeResponsavel.copy(
                        situacaoProfissional = valor.toInt()
                    )
                )
            }

            CamposConjuge.SALARIO_CONJUGE -> {
                _paciente.value = _paciente.value.copy(
                    conjugeResponsavel = _paciente.value.conjugeResponsavel.copy(
                        salario = valor
                    )
                )
            }

            CamposConjuge.CARTAO_SUS -> {
                _paciente.value = _paciente.value.copy(
                    conjugeResponsavel = _paciente.value.conjugeResponsavel.copy(
                        cartaoSus = valor
                    )
                )
            }
        }
    }

    fun onChangeOutro(tipoCampo: CamposOutro, valor: String) {
        when (tipoCampo) {
            CamposOutro.NOME_OUTRO -> {
                _paciente.value = _paciente.value.copy(
                    outroResponsavel = _paciente.value.outroResponsavel.copy(
                        nome = valor
                    )
                )
            }

            CamposOutro.CPF_OUTRO -> {
                _paciente.value = _paciente.value.copy(
                    outroResponsavel = _paciente.value.outroResponsavel.copy(
                        cpf = valor
                    )
                )
            }

            CamposOutro.TELEFONE_OUTRO -> {
                _paciente.value = _paciente.value.copy(
                    outroResponsavel = _paciente.value.outroResponsavel.copy(
                        telefone = valor
                    )
                )
            }

            CamposOutro.RG_OUTRO -> {
                _paciente.value = _paciente.value.copy(
                    outroResponsavel = _paciente.value.outroResponsavel.copy(
                        rg = valor
                    )
                )
            }

            CamposOutro.DATA_NASCIMENTO_OUTRO -> {
                _paciente.value = _paciente.value.copy(
                    outroResponsavel = _paciente.value.outroResponsavel.copy(
                        dataNascimento = valor
                    )
                )
            }

            CamposOutro.NATURALIDADE_OUTRO -> {
                _paciente.value = _paciente.value.copy(
                    outroResponsavel = _paciente.value.outroResponsavel.copy(
                        naturalidade = valor
                    )
                )
            }

            CamposOutro.ESCOLARIDADE_OUTRO -> {
                _paciente.value = _paciente.value.copy(
                    outroResponsavel = _paciente.value.outroResponsavel.copy(
                        escolaridade = valor
                    )
                )
            }

            CamposOutro.ESTADO_CIVIL_OUTRO -> {
                _paciente.value = _paciente.value.copy(
                    outroResponsavel = _paciente.value.outroResponsavel.copy(
                        estadoCivil = valor.toInt()
                    )
                )
            }

            CamposOutro.SITUACAO_PROFISSIONAL_OUTRO -> {
                _paciente.value = _paciente.value.copy(
                    outroResponsavel = _paciente.value.outroResponsavel.copy(
                        situacaoProfissional = valor.toInt()
                    )
                )
            }

            CamposOutro.SALARIO_OUTRO -> {
                _paciente.value = _paciente.value.copy(
                    outroResponsavel = _paciente.value.outroResponsavel.copy(
                        salario = valor
                    )
                )
            }

            CamposOutro.CARTAO_SUS -> {
                _paciente.value = _paciente.value.copy(
                    outroResponsavel = _paciente.value.outroResponsavel.copy(
                        cartaoSus = valor
                    )
                )
            }
        }
    }

    fun onChangeRadio(campo: CamposRadio, valor: Radio) {
        when (campo) {
            CamposRadio.ADD_RADIO -> {
                _paciente.value = _paciente.value.copy(
                    radios = _paciente.value.radios + valor
                )
            }

            CamposRadio.REMOVE_RADIO -> {
                _paciente.value = _paciente.value.copy(
                    radios = _paciente.value.radios - valor
                )
            }
        }
    }

    fun onChangeQuimio(campo: CamposQuimio, valor: Quimio) {
        when (campo) {
            CamposQuimio.ADD_QUIMIO -> {
                _paciente.value = _paciente.value.copy(
                    quimios = _paciente.value.quimios + valor
                )
            }

            CamposQuimio.REMOVE_QUIMIO -> {
                _paciente.value = _paciente.value.copy(
                    quimios = _paciente.value.quimios - valor
                )
            }
        }
    }

    fun onChangeComposicaoFamiliar(campo: CamposCompFamiliar, valor: ComposicaoFamiliar) {
        when (campo) {
            CamposCompFamiliar.ADD_COM_FAMILIA -> {
                _paciente.value = _paciente.value.copy(
                    composicaoFamiliar = _paciente.value.composicaoFamiliar + valor
                )
            }

            CamposCompFamiliar.DELETE_COM_FAMILIA -> {
                _paciente.value = _paciente.value.copy(
                    composicaoFamiliar = _paciente.value.composicaoFamiliar.filterNot {
                        it == valor
                    }
                )
            }

            CamposCompFamiliar.EDIT_COM_FAMILIA -> {
                _paciente.value = _paciente.value.copy(
                    composicaoFamiliar = _paciente.value.composicaoFamiliar.filterNot {
                        it == valor
                    }
                )

                _paciente.value = _paciente.value.copy(
                    composicaoFamiliar = _paciente.value.composicaoFamiliar + valor
                )
            }

        }
    }

    fun onChangeHistoricoSaude(campo: CamposHistoricoSaude, valor: String) {
        when (campo) {
            CamposHistoricoSaude.DOENCA_FAMILIA -> {
                if (valor.toInt() !in _paciente.value.historicoSaude.doencasFamilia) {
                    _paciente.value = _paciente.value.copy(
                        historicoSaude = _paciente.value.historicoSaude.copy(
                            doencasFamilia = _paciente.value.historicoSaude.doencasFamilia + valor.toInt()
                        )
                    )
                } else {
                    _paciente.value = _paciente.value.copy(
                        historicoSaude = _paciente.value.historicoSaude.copy(
                            doencasFamilia = _paciente.value.historicoSaude.doencasFamilia.filterNot {
                                it == valor.toInt()
                            }.toTypedArray()
                        )
                    )
                }
            }

            CamposHistoricoSaude.MEDICAMENTOS_USO_CONTINUO -> {
                _paciente.value = _paciente.value.copy(
                    historicoSaude = _paciente.value.historicoSaude.copy(
                        medicamentosUsoContinuo = valor
                    )
                )
            }

            CamposHistoricoSaude.LOCAL_PROCURA_ATENDIMENTO -> {
                if (valor.toInt() !in _paciente.value.historicoSaude.localProcuraAtendimento) {
                    _paciente.value = _paciente.value.copy(
                        historicoSaude = _paciente.value.historicoSaude.copy(
                            localProcuraAtendimento = _paciente.value.historicoSaude.localProcuraAtendimento + valor.toInt()
                        )
                    )
                } else {
                    _paciente.value = _paciente.value.copy(
                        historicoSaude = _paciente.value.historicoSaude.copy(
                            localProcuraAtendimento = _paciente.value.historicoSaude.localProcuraAtendimento.filterNot {
                                it == valor.toInt()
                            }.toTypedArray()
                        )
                    )
                }
            }

            CamposHistoricoSaude.RECEBE_BENEFICIO -> {
                _paciente.value = _paciente.value.copy(
                    historicoSaude = _paciente.value.historicoSaude.copy(
                        recebeBeneficio = valor.toInt()
                    )
                )
            }

            CamposHistoricoSaude.BENEFICIO_DESCRICAO -> {
                _paciente.value = _paciente.value.copy(
                    historicoSaude = _paciente.value.historicoSaude.copy(
                        beneficioDescricao = valor
                    )
                )
            }
        }
    }

    fun onChangeSituacaoHabitacional(campo: CamposSituacaoHabitacional, valor: String) {
        when (campo) {
            CamposSituacaoHabitacional.COMO_ADQUIRIU_CASA -> {
                _paciente.value = _paciente.value.copy(
                    situacaoHabitacional = _paciente.value.situacaoHabitacional.copy(
                        comoAdquiriuCasa = valor.toInt()
                    )
                )
            }

            CamposSituacaoHabitacional.COMP_PROPRIEDADE -> {
                _paciente.value = _paciente.value.copy(
                    situacaoHabitacional = _paciente.value.situacaoHabitacional.copy(
                        compPropriedade = valor.toInt()
                    )
                )
            }

            CamposSituacaoHabitacional.AREA -> {
                _paciente.value = _paciente.value.copy(
                    situacaoHabitacional = _paciente.value.situacaoHabitacional.copy(
                        area = valor.toInt()
                    )
                )
            }

            CamposSituacaoHabitacional.NUMERO_COMODOS -> {
                _paciente.value = _paciente.value.copy(
                    situacaoHabitacional = _paciente.value.situacaoHabitacional.copy(
                        numeroComodos = valor
                    )
                )
            }

            CamposSituacaoHabitacional.MATERIAL -> {
                _paciente.value = _paciente.value.copy(
                    situacaoHabitacional = _paciente.value.situacaoHabitacional.copy(
                        material = valor.toInt()
                    )
                )
            }

            CamposSituacaoHabitacional.ELETRODOMESTICOS -> {
                if (valor.toInt() !in _paciente.value.situacaoHabitacional.eletrodomesticos) {
                    _paciente.value = _paciente.value.copy(
                        situacaoHabitacional = _paciente.value.situacaoHabitacional.copy(
                            eletrodomesticos = _paciente.value.situacaoHabitacional.eletrodomesticos + valor.toInt()
                        )
                    )
                } else {
                    _paciente.value = _paciente.value.copy(
                        situacaoHabitacional = _paciente.value.situacaoHabitacional.copy(
                            eletrodomesticos = _paciente.value.situacaoHabitacional.eletrodomesticos.filterNot {
                                it == valor.toInt()
                            }.toTypedArray()
                        )
                    )
                }

            }

            CamposSituacaoHabitacional.BENS -> {
                if (valor.toInt() !in _paciente.value.situacaoHabitacional.bens) {
                    _paciente.value = _paciente.value.copy(
                        situacaoHabitacional = _paciente.value.situacaoHabitacional.copy(
                            bens = _paciente.value.situacaoHabitacional.bens + valor.toInt()
                        )
                    )
                } else {
                    _paciente.value = _paciente.value.copy(
                        situacaoHabitacional = _paciente.value.situacaoHabitacional.copy(
                            bens = _paciente.value.situacaoHabitacional.bens.filterNot {
                                it == valor.toInt()
                            }.toTypedArray()
                        )
                    )
                }
            }

            CamposSituacaoHabitacional.VALOR_TOTAL -> {
                _paciente.value = _paciente.value.copy(
                    situacaoHabitacional = _paciente.value.situacaoHabitacional.copy(
                        valorTotal = valor
                    )
                )
            }

            CamposSituacaoHabitacional.MEIO_TRANSPORTE -> {
                _paciente.value = _paciente.value.copy(
                    situacaoHabitacional = _paciente.value.situacaoHabitacional.copy(
                        meioDeTransporte = valor.toInt()
                    )
                )
            }

            CamposSituacaoHabitacional.MEIO_COMUNICACAO -> {
                _paciente.value = _paciente.value.copy(
                    situacaoHabitacional = _paciente.value.situacaoHabitacional.copy(
                        meioDeComunicao = valor.toInt()
                    )
                )
            }

            CamposSituacaoHabitacional.POSSUI_BANHEIRO -> {
                _paciente.value = _paciente.value.copy(
                    situacaoHabitacional = _paciente.value.situacaoHabitacional.copy(
                        possuiBanheiros = valor.toInt()
                    )
                )
            }

            CamposSituacaoHabitacional.DENTRO_DE_CASA -> {
                _paciente.value = _paciente.value.copy(
                    situacaoHabitacional = _paciente.value.situacaoHabitacional.copy(
                        dentroDeCasa = valor.toInt()
                    )
                )
            }

            CamposSituacaoHabitacional.DESCARTE_LIXO -> {
                _paciente.value = _paciente.value.copy(
                    situacaoHabitacional = _paciente.value.situacaoHabitacional.copy(
                        destinoDoLixo = valor.toInt()
                    )
                )
            }

            CamposSituacaoHabitacional.AGUA -> {
                _paciente.value = _paciente.value.copy(
                    situacaoHabitacional = _paciente.value.situacaoHabitacional.copy(
                        agua = valor.toInt()
                    )
                )
            }
        }
    }

    fun onChangeObservacao(campo: CamposObservacao, valor: String) {
        when (campo) {
            CamposObservacao.COMO_CONHECEU -> {
                _paciente.value = _paciente.value.copy(
                    origen_info_ong = valor
                )
            }

            CamposObservacao.ADD_OBSERVACAO -> {
                _paciente.value = _paciente.value.copy(
                    observacoes = _paciente.value.observacoes + valor
                )
            }

            CamposObservacao.DELETE_OBSERVACAO -> {
                _paciente.value = _paciente.value.copy(
                    observacoes = _paciente.value.observacoes
                        .filterIndexed { index, _ -> index != valor.toInt() }
                        .toTypedArray()
                )
            }
        }
    }
}