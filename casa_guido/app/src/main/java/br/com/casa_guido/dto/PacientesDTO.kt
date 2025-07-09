package br.com.casa_guido.dto

import br.com.casa_guido.models.PacienteRequestDTO
import br.com.casa_guido.models.PessoaDTO
import br.com.casa_guido.models.EnderecoDTO
import br.com.casa_guido.models.ProfissionalResponsavelDTO
import br.com.casa_guido.models.CirurgiaDTO
import br.com.casa_guido.models.QuimioDTO
import br.com.casa_guido.models.RadioDTO
import br.com.casa_guido.models.HistoricoSaudeDTO
import br.com.casa_guido.models.ComposicaoFamiliarDTO
import br.com.casa_guido.models.SituacaoHabitacionalDTO
import kotlinx.serialization.Serializable


@Serializable
data class PacientesRequest(
    val paciente: PacienteRequestDTO,
    val token: String
)

@Serializable
data class ArquivoRequest(
    val nome: String? = null,
    val uri: String? = null,
    val conteudoArquivo: ByteArray? = null,
)

@Serializable
data class ArquivosRequest(
    val pacienteIdBackend: String,
    val arquivos: List<ArquivoRequest>,
    val token: String
)

@Serializable
data class CreatePacienteResponse(
    val id: String,
    val nome: String
)

@Serializable
data class UpdatePacienteResponse(
    val id: String,
)

@Serializable
data class DataResponse<T>(
    val data: T,
)

@Serializable
data class ArquivoResponse(
    val message: String
)

@Serializable
data class ArquivoResponseGet(
    val nome: String,
    val base64: String,
)

@Serializable
data class ListaArquivoResponse(
    val idPaciente: String,
    val arquivos: List<ArquivoResponseGet>
)

@Serializable
data class PacienteCompletoDTO(
    val _id: String = "",
    val pessoa: PessoaDTO,
    val status: Boolean = false,
    val nomeMae: String = "",
    val nomePai: String = "",
    val nomeOutro: String = "",
    val recebeRemuneracao: Int = 0,
    val remuneracao: String = "",
    val recebeBpc: Int = 0,
    val valorBpc: String = "",
    val diagnostico: String = "",
    val profissionalResponsavel: ProfissionalResponsavelDTO? = null,
    val escolaNome: String = "",
    val anoEscolar: String = "",
    val tamRoupa: String = "",
    val tamCalcado: String = "",
    val origenInfoOng: String = "",
    val idBackend: String = "",
    val alterado: Boolean = false,
    val observacoes: List<String> = emptyList(),
    val responsavel: PessoaDTO? = null,
    val conjugeResponsavel: PessoaDTO? = null,
    val outroResponsavel: PessoaDTO? = null,
    val ubs: UbsDTO? = null,
    val cras: CrasDTO? = null,
    val cirurgias: List<CirurgiaDTO> = emptyList(),
    val quimioterapias: List<QuimioDTO> = emptyList(),
    val radioterapias: List<RadioDTO> = emptyList(),
    val historicoSaude: HistoricoSaudeDTO? = null,
    val composicaoFamiliar: List<ComposicaoFamiliarDTO> = emptyList(),
    val situacaoHabitacional: SituacaoHabitacionalDTO? = null,
    val createdAt: String = "",
    val updatedAt: String = "",
    val __v: Int = 0
)

@Serializable
data class PacientesResponseDTO(
    val data: List<PacienteCompletoDTO>
)

// DTOs auxiliares para UBS, CRAS, Quimioterapia e Radioterapia
@Serializable
data class UbsDTO(
    val municipio: String = "",
    val bairro: String = ""
)

@Serializable
data class CrasDTO(
    val municipio: String = "",
    val bairro: String = ""
)