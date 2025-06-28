package br.com.casa_guido.models

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
@Immutable
data class Paciente(
    val id: String = UUID.randomUUID().toString(),
    val pessoa: Pessoa = Pessoa(),
    val status: Boolean = false,
    val nomeMae: String = "",
    val nomePai: String = "",
    val nomeOutro: String = "",
    val cirurgias: List<Cirurgia> = emptyList(),
    val quimios: List<Quimio> = emptyList(),
    val radios: List<Radio> = emptyList(),
    val remuneracao: String = "",
    val bpc: Int? = null,
    val valorBpc: String = "",
    val aptoReceberBpc: Int? = null,
    val tipoEscola: Int? = null,
    val escolaNome: String = "",
    val tamRoupa: String = "",
    val tamCalcado: String = "",
    val diagnostico: String = "",
    val origen_info_ong: String = "",
    val alterado: Boolean = false,
    val ubs: Ubs = Ubs(),
    val cras: Cras = Cras(),
    val observacoes: Array<String> = emptyArray(),
    val responsavel: Pessoa = Pessoa(),
    val conjugeResponsavel: Pessoa = Pessoa(),
    val outroResponsavel: Pessoa = Pessoa(),
    val doenca: List<DoencaDTO> = emptyList(),
    val tratamento: List<Tratamento> = emptyList(),
    val profissionalResponsavel: ProfissionalResponsavel = ProfissionalResponsavel(),
    val composicaoFamiliar: List<ComposicaoFamiliar> = emptyList(),
    val historicoSaude: HistoricoSaude = HistoricoSaude(),
    val situacaoHabitacional: SituacaoHabitacional = SituacaoHabitacional(),
    val arquivos: List<Arquivo> = emptyList()
)

@Serializable
data class PacienteRequestDTO(
    val pessoa: PessoaDTO,
    val status: Boolean,
    val nomeMae: String,
    val nomePai: String,
    val nomeOutro: String,
    val cirurgias: List<CirurgiaDTO>,
    val quimios: List<QuimioDTO>,
    val radios: List<RadioDTO>,
    val remuneracao: String,
    val bpc: Int? = null,
    val valorBpc: String,
    val aptoReceberBpc: Int? = null,
    val tipoEscola: Int? = null,
    val escolaNome: String,
    val tamRoupa: String,
    val tamCalcado: String,
    val diagnostico: String,
    val origen_info_ong: String,
    val ubs: Ubs,
    val cras: Cras,
    val observacoes: Array<String>,
    val responsavel: PessoaDTO,
    val conjugeResponsavel: PessoaDTO,
    val outroResponsavel: PessoaDTO,
    val doenca: List<DoencaDTO>,
    val tratamento: List<TratamentoDTO>,
    val profissionalResponsavel: ProfissionalResponsavelDTO,
    val composicaoFamiliar: List<ComposicaoFamiliarDTO>,
    val historicoSaude: HistoricoSaudeDTO,
    val situacaoHabitacional: SituacaoHabitacionalDTO
)

fun Paciente.toRequestDTO(): PacienteRequestDTO {
    return PacienteRequestDTO(
        pessoa = pessoa.toRequestDTO(),
        status = status,
        nomeMae = nomeMae,
        nomePai = nomePai,
        nomeOutro = nomeOutro,
        cirurgias = cirurgias.map { it.toRequestDTO() },
        quimios = quimios.map { it.toRequestDTO() },
        radios = radios.map { it.toRequestDTO() },
        remuneracao = remuneracao,
        bpc = bpc,
        valorBpc = valorBpc,
        aptoReceberBpc = aptoReceberBpc,
        tipoEscola = tipoEscola,
        escolaNome = escolaNome,
        tamRoupa = tamRoupa,
        tamCalcado = tamCalcado,
        diagnostico = diagnostico,
        origen_info_ong = origen_info_ong,
        ubs = ubs,
        cras = cras,
        observacoes = observacoes,
        responsavel = responsavel.toRequestDTO(),
        conjugeResponsavel = conjugeResponsavel.toRequestDTO(),
        outroResponsavel = outroResponsavel.toRequestDTO(),
        doenca = doenca,
        tratamento = tratamento.map { it.toRequestDTO() },
        profissionalResponsavel = profissionalResponsavel.toRequestDTO(),
        composicaoFamiliar = composicaoFamiliar.map { it.toRequestDTO() },
        historicoSaude = historicoSaude.toRequestDTO(),
        situacaoHabitacional = situacaoHabitacional.toRequestDTO()
    )
}
