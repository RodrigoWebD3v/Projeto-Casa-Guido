package br.com.casa_guido.screens.cadastro

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.casa_guido.screens.Paciente
import br.com.casa_guido.screens.Pessoa
import br.com.casa_guido.service.PacienteService
import br.com.casa_guido.service.ViaCepService
import br.com.casa_guido.util.ListaMemoria
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CadastroScreenViewModel(
    private val viaCepService: ViaCepService,
    private val pacienteService: PacienteService
) : ViewModel() {
    private val _uiState = MutableStateFlow<CadastroScreenUiState>(CadastroScreenUiState())
    val uiState = _uiState.asStateFlow()

    private val _paciente = MutableStateFlow<Paciente>(Paciente())
    val paciente = _paciente.asStateFlow()

    fun getUsuario(id: String) {
        var paciente = ListaMemoria.pacientesLista.filter { item ->
            item.id == id
        }
        if (paciente.isNotEmpty()) {
            _paciente.value = paciente.first()
        }

        _uiState.value = _uiState.value.copy(
            edicao = true
        )
    }

    fun toggleDataPickerNascimento() {
        _uiState.value = _uiState.value.copy(
            dataPickerNascimentoShow = !_uiState.value.dataPickerNascimentoShow
        )
    }

    fun toggleDataPickerCirurgia() {
        _uiState.value = _uiState.value.copy(
            dataPickerCirurgiaShow = !_uiState.value.dataPickerCirurgiaShow
        )
    }

    fun onChangeData(data: String) {
        _paciente.value = _paciente.value.copy(
            pessoa = _paciente.value.pessoa.copy(
                dataNascimento = data
            )
        )
    }

    fun onChangeNome(nome: String) {
        _paciente.value = _paciente.value.copy(
            pessoa = _paciente.value.pessoa.copy(
                nome = nome
            )
        )
    }

    fun onChangeMae(mae: String) {
        _paciente.value = _paciente.value.copy(
            nomeMae = mae
        )
    }

    fun onChangePai(pai: String) {
        _paciente.value = _paciente.value.copy(
            nomePai = pai
        )
    }

    fun onChangeOutro(outro: String) {
        _paciente.value = _paciente.value.copy(
            nomeOutro = outro
        )
    }

    fun onChangeCpf(cpf: String) {
        _paciente.value = _paciente.value.copy(
            pessoa = _paciente.value.pessoa.copy(
                cpf = cpf
            )
        )
    }

    fun onChangeRg(rg: String) {
        _paciente.value = _paciente.value.copy(
            pessoa = _paciente.value.pessoa.copy(
                rg = rg
            )
        )
    }

    fun onChangeCartaoSus(cartaoSus: String) {
        _paciente.value = _paciente.value.copy(
            cartaoSus = cartaoSus
        )

    }

    fun onChangeTelefone(telefone: String) {
        _paciente.value = _paciente.value.copy(
            pessoa = _paciente.value.pessoa.copy(
                telefone = telefone
            )
        )
    }

    fun save() {
        viewModelScope.launch {

            if (_uiState.value.edicao) {
                ListaMemoria.pacientesLista.forEachIndexed { index, paciente ->
                    if (paciente.id == _paciente.value.id) {
                        ListaMemoria.pacientesLista[index] = _paciente.value
                    }
                }
            } else {
                ListaMemoria.pacientesLista.add(_paciente.value)
                _paciente.value = Paciente()
            }

            pacienteService.createPaciente(_paciente.value)

        }
    }

    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun onChangeCep(cep: String) {
        _paciente.value = _paciente.value.copy(
            pessoa = _paciente.value.pessoa.copy(
                endereco = _paciente.value.pessoa.endereco.copy(cep = cep)
            )
        )

        if (cep.length == 8) {
            viewModelScope.launch {
                val endereco = viaCepService.buscaCep(cep)
                _paciente.value = _paciente.value.copy(
                    pessoa = _paciente.value.pessoa.copy(
                        endereco = endereco
                    )
                )
            }
        }
    }

    fun onChangeLogradouro(logradouro: String) {
        _paciente.value = _paciente.value.copy(
            pessoa = _paciente.value.pessoa.copy(
                endereco = _paciente.value.pessoa.endereco.copy(logradouro = logradouro)
            )
        )
    }

    fun onChangeComplemento(complemento: String) {
        _paciente.value = _paciente.value.copy(
            pessoa = _paciente.value.pessoa.copy(
                endereco = _paciente.value.pessoa.endereco.copy(complemento = complemento)
            )
        )
    }

    fun onChangeUnidade(unidade: String) {
        _paciente.value = _paciente.value.copy(
            pessoa = _paciente.value.pessoa.copy(
                endereco = _paciente.value.pessoa.endereco.copy(unidade = unidade)
            )
        )
    }

    fun onChangeBairro(bairro: String) {
        _paciente.value = _paciente.value.copy(
            pessoa = _paciente.value.pessoa.copy(
                endereco = _paciente.value.pessoa.endereco.copy(bairro = bairro)
            )
        )
    }

    fun onChangeLocalidade(localidade: String) {
        _paciente.value = _paciente.value.copy(
            pessoa = _paciente.value.pessoa.copy(
                endereco = _paciente.value.pessoa.endereco.copy(localidade = localidade)
            )
        )
    }

    fun onChangeUf(uf: String) {
        _paciente.value = _paciente.value.copy(
            pessoa = _paciente.value.pessoa.copy(
                endereco = _paciente.value.pessoa.endereco.copy(uf = uf)
            )
        )
    }

    fun onChangeNumero(numero: String) {
        _paciente.value = _paciente.value.copy(
            pessoa = _paciente.value.pessoa.copy(
                endereco = _paciente.value.pessoa.endereco.copy(numero = numero)
            )
        )
    }

    fun onChangeReferencia(referencia: String) {
        _paciente.value = _paciente.value.copy(
            pessoa = _paciente.value.pessoa.copy(
                endereco = _paciente.value.pessoa.endereco.copy(referencia = referencia)
            )
        )
    }

    fun onChangeEstado(estado: String) {
        _paciente.value = _paciente.value.copy(
            pessoa = _paciente.value.pessoa.copy(
                endereco = _paciente.value.pessoa.endereco.copy(estado = estado)
            )
        )
    }

    fun onChangeEscolaAno(valor: String) {
        _paciente.value = _paciente.value.copy(
            escolaAno = valor
        )
    }

    fun onChangeNomeEscola(valor: String) {
        _paciente.value = _paciente.value.copy(
            escolaNome = valor
        )
    }

    fun onChangeRemuneracao(valor: String) {
        _paciente.value = _paciente.value.copy(
            remuneracao = valor
        )
    }

    fun onChangeBpcOpt(valor: String) {
        _paciente.value = _paciente.value.copy(
            bpc = valor.toInt()
        )
    }

    fun onChangeValorBPC(valor: String) {
        _paciente.value = _paciente.value.copy(
            valorBpc = valor
        )
    }

    fun onChangeTamanhoRoupa(valor: String) {
        _paciente.value = _paciente.value.copy(
            tamRoupa = valor
        )
    }

    fun onChangeTamanhoCalcado(valor: String) {
        _paciente.value = _paciente.value.copy(
            tamCalcado = valor.toInt()
        )
    }

}