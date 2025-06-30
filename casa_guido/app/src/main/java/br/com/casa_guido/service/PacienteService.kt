package br.com.casa_guido.service

import android.util.Log
import br.com.casa_guido.models.Cras
import br.com.casa_guido.repository.PacienteRepository
import br.com.casa_guido.models.HistoricoSaude
import br.com.casa_guido.models.Paciente
import br.com.casa_guido.models.ProfissionalResponsavel
import br.com.casa_guido.models.SituacaoHabitacional
import br.com.casa_guido.models.Ubs
import kotlinx.coroutines.flow.first
import br.com.casa_guido.room.entidades.Paciente as PacienteEntidade
import br.com.casa_guido.models.Paciente as PacienteUI
import br.com.casa_guido.models.Pessoa as PessoaUI

class PacienteService(
    private val pacienteRepository: PacienteRepository,
    private val pessoaService: PessoaService,
    private val quimioService: QuimioService,
    private val cirurgiaService: CirurgiaService,
    private val radioService: RadioService,
    private val composicaoFamiliarService: ComposicaoFamiliarService,
    private val historicoSaudeService: HistoricoSaudeService,
    private val situacaoHabitacionalService: SituacaoHabitacionalService,
    private val arquivo: ArquivoService
) {

    suspend fun getPacientes(): List<Paciente> {
        return pacienteRepository.pacientes.first().map {
            it.toUI(
                getPessoaById = { id ->
                    pessoaService.getById(id) ?: PessoaUI()
                }
            )
        }
    }

    suspend fun getById(id: String): PacienteUI? {
        val paciente = pacienteRepository.getById(id)
        return paciente?.toUI(
            getPessoaById = {
                pessoaService.getById(it) ?: PessoaUI()
            }
        )
    }

    suspend fun getPacientesAlterados(status: Boolean): List<PacienteUI>? {
        val pacientes = pacienteRepository.getAlterado(status)
        return pacientes?.map {
            it.toUI(
                getPessoaById = { id ->
                    pessoaService.getById(id) ?: PessoaUI()
                }
            )
        }
    }

    suspend fun createPaciente(paciente: PacienteUI) {
        pacienteRepository.insert(
            paciente.toEntidade()
        )
    }

    suspend fun atualizaPaciente(paciente: PacienteUI) {
        pacienteRepository.update(
            paciente.toEntidade()
        )
    }

    private fun PacienteUI.toEntidade(): PacienteEntidade {
        Log.i(
            "PacienteService B",
            "tipoEscola: ${this.tipoEscola}"
        )
        return PacienteEntidade(
            id = this.id,
            pessoaId = this.pessoa.id,
            status = true,
            nomeMae = this.nomeMae,
            nomePai = this.nomePai,
            nomeOutro = this.nomeOutro,
            remuneracao = this.remuneracao,
            recebeBpc = this.bpc,
            valorBpc = this.valorBpc,
            diagnostico = this.diagnostico,
            profissionalResponsavel = "123",
            escolaNome = this.escolaNome,

            //Descontinuar esse campo
            anoEscolar = "1",

            tamRoupa = this.tamRoupa,
            tamCalcado = this.tamCalcado,
            origenInfoOng = this.origen_info_ong,
            observacoes = this.observacoes,
            responsavelId = this.responsavel.id,
            conjugeResponsavelId = this.conjugeResponsavel.id,
            outroResponsavelId = this.outroResponsavel.id,
            aptoReceberBpc = if (this.bpc != null && this.bpc == 1) 1 else this.aptoReceberBpc ?: 0,
            tipoEscola = this.tipoEscola,
            crasMunicipio = this.cras.municipio,
            ubsMunicipio = this.ubs.municipio,
            crasBairro = this.cras.bairro,
            ubsBairro = this.ubs.bairro,
            alterado = this.alterado,
            idBackend = this.idBackend
        )
    }

    private suspend fun PacienteEntidade.toUI(
        getPessoaById: suspend (String) -> PessoaUI,
    ): PacienteUI {
        return PacienteUI(
            id = this.id,
            pessoa = getPessoaById(this.pessoaId),
            status = this.status ?: false,
            nomeMae = this.nomeMae ?: "",
            nomePai = this.nomePai ?: "",
            nomeOutro = this.nomeOutro ?: "",
            remuneracao = this.remuneracao ?: "",
            bpc = this.recebeBpc,
            valorBpc = this.valorBpc ?: "",
            diagnostico = this.diagnostico ?: "",
            escolaNome = this.escolaNome ?: "",
            tamRoupa = this.tamRoupa ?: "",
            tamCalcado = this.tamCalcado ?: "",
            origen_info_ong = this.origenInfoOng ?: "",
            observacoes = this.observacoes ?: arrayOf(""),
            responsavel = getPessoaById(this.responsavelId ?: ""),
            conjugeResponsavel = getPessoaById(this.conjugeResponsavelId ?: ""),
            outroResponsavel = getPessoaById(this.outroResponsavelId ?: ""),
            profissionalResponsavel = ProfissionalResponsavel(
                id = this.profissionalResponsavel ?: "",
                nome = "Profissional Responsável",
                crm = "",
            ),
            quimios = quimioService.getQuimiosPorPaciente(this.id),
            cirurgias = cirurgiaService.getCirurgiasPorPaciente(this.id),
            radios = radioService.getRadiosByPaciente(this.id),
            composicaoFamiliar = composicaoFamiliarService.getComposicaoFamiliarPorPaciente(this.id),
            historicoSaude = historicoSaudeService.getHistoricoSaudeByPaciente(this.id)
                ?: HistoricoSaude(),
            aptoReceberBpc = this.aptoReceberBpc,
            tipoEscola = tipoEscola ?: 0,
            ubs = Ubs(
                municipio = this.ubsMunicipio ?: "",
                bairro = this.ubsBairro ?: ""
            ),
            cras = Cras(
                municipio = this.crasMunicipio ?: "",
                bairro = this.crasBairro ?: ""
            ),
            situacaoHabitacional = situacaoHabitacionalService.buscaSituacaoHabitacionalPorPaciente(this.id)?: SituacaoHabitacional(),
            arquivos = arquivo.getArquivosPorPaciente(this.id),
            alterado = this.alterado,
            idBackend = this.idBackend
        )
    }
}
