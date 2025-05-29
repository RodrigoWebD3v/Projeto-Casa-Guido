package br.com.casa_guido.service

import br.com.casa_guido.repository.PacienteRepository
import br.com.casa_guido.screens.HistoricoSaude
import br.com.casa_guido.screens.Paciente
import br.com.casa_guido.screens.ProfissionalResponsavel
import kotlinx.coroutines.flow.first
import br.com.casa_guido.room.entidades.Paciente as PacienteEntidade
import br.com.casa_guido.screens.Paciente as PacienteUI
import br.com.casa_guido.screens.Pessoa as PessoaUI

class PacienteService(
    private val pacienteRepository: PacienteRepository,
    private val pessoaService: PessoaService,
    private val quimioService: QuimioService,
    private val cirurgiaService: CirurgiaService,
    private val radioService: RadioService,
    private val composicaoFamiliarService: ComposicaoFamiliarService,
    private val historicoSaudeService: HistoricoSaudeService
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
            getPessoaById = { id ->
                pessoaService.getById(id) ?: PessoaUI()
            }
        )
    }

    suspend fun createPaciente(paciente: PacienteUI) {
        pacienteRepository.insert(
            paciente.toEntidade()
        )
    }

    private fun PacienteUI.toEntidade(): PacienteEntidade {
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
            anoEscolar = "1",
            tamRoupa = this.tamRoupa,
            tamCalcado = this.tamCalcado,
            origenInfoOng = this.origen_info_ong,
            observacoes = this.observacoes,
            responsavelId = this.responsavel.id,
            conjugeResponsavelId = this.conjugeResponsavel.id,
            outroResponsavelId = this.outroResponsavel.id,
        )
    }

    private suspend fun PacienteEntidade.toUI(
        getPessoaById: suspend (String) -> PessoaUI,
    ): PacienteUI {
        return PacienteUI(
            id = this.id,
            pessoa = getPessoaById(this.pessoaId),
            status = this.status!!,
            nomeMae = this.nomeMae!!,
            nomePai = this.nomePai!!,
            nomeOutro = this.nomeOutro!!,
            remuneracao = this.remuneracao!!,
            bpc = this.recebeBpc!!,
            valorBpc = this.valorBpc!!,
            diagnostico = this.diagnostico!!,
            escolaNome = this.escolaNome!!,
            tamRoupa = this.tamRoupa!!,
            tamCalcado = this.tamCalcado!!,
            origen_info_ong = this.origenInfoOng!!,
            observacoes = this.observacoes!!,
            responsavel = getPessoaById(this.responsavelId!!),
            conjugeResponsavel = getPessoaById(this.conjugeResponsavelId!!),
            outroResponsavel = getPessoaById(this.outroResponsavelId!!),
            profissionalResponsavel = ProfissionalResponsavel(
                id = this.profissionalResponsavel!!,
                nome = "Profissional Responsável",
                crm = "",
            ),
            quimios = quimioService.getQuimiosPorPaciente(this.id),
            cirurgias = cirurgiaService.getCirurgiasPorPaciente(this.id),
            radios = radioService.getRadiosByPaciente(this.id),
            composicaoFamiliar = composicaoFamiliarService.getComposicaoFamiliarPorPaciente(this.id),
            historicoSaude = historicoSaudeService.getHistoricoSaudeByPaciente(this.id)
                ?: HistoricoSaude()
        )
    }
}
