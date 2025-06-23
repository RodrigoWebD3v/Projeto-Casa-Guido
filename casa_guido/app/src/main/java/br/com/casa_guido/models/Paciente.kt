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
    val ubs: Ubs = Ubs(),
    val cras: Cras = Cras(),
    val observacoes: Array<String> = emptyArray(),
    val responsavel: Pessoa = Pessoa(),
    val conjugeResponsavel: Pessoa = Pessoa(),
    val outroResponsavel: Pessoa = Pessoa(),
    val doenca: List<Doenca> = emptyList(),
    val tratamento: List<Tratamento> = emptyList(),
    val profissionalResponsavel: ProfissionalResponsavel = ProfissionalResponsavel(),
    val composicaoFamiliar: List<ComposicaoFamiliar> = emptyList(),
    val historicoSaude: HistoricoSaude = HistoricoSaude(),
    val situacaoHabitacional: SituacaoHabitacional = SituacaoHabitacional(),
    val arquivos: List<Arquivo> = emptyList()
)