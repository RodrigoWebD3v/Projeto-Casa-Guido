package br.com.casa_guido.service

import android.util.Log
import br.com.casa_guido.models.Paciente

class CriarPacienteService(
    private val pacienteService: PacienteService,
    private val pessoaService: PessoaService,
    private val enderecoService: EnderecoService,
    private val quimioService: QuimioService,
    private val cirurgiaService: CirurgiaService,
    private val radioService: RadioService,
    private val composicaoFamiliarService: ComposicaoFamiliarService,
    private val historicoSaudeService: HistoricoSaudeService
) {

    suspend fun criarPaciente(paciente: Paciente) {
        pacienteService.createPaciente(paciente)

        Log.i(
            "CriarPacienteService",
            "criarPaciente: ${paciente.pessoa.escolaridade} - ${paciente.pessoa.serie}"
        )
        pessoaService.createPessoa(
            pessoa = paciente.pessoa,
            endereco = paciente.pessoa.endereco
        )

        pessoaService.createPessoa(
            pessoa = paciente.responsavel,
            endereco = paciente.responsavel.endereco
        )

        pessoaService.createPessoa(
            pessoa = paciente.conjugeResponsavel,
            endereco = paciente.conjugeResponsavel.endereco
        )

        pessoaService.createPessoa(
            pessoa = paciente.outroResponsavel,
            endereco = paciente.outroResponsavel.endereco
        )

        enderecoService.createEndereco(
            endereco = paciente.pessoa.endereco
        )

        enderecoService.createEndereco(
            endereco = paciente.responsavel.endereco
        )

        enderecoService.createEndereco(
            endereco = paciente.conjugeResponsavel.endereco
        )

        enderecoService.createEndereco(
            endereco = paciente.outroResponsavel.endereco
        )

        cirurgiaService.deleteCirurgia(paciente.id)

        paciente.cirurgias.forEach {
            cirurgiaService.createCirurgia(
                cirurgia = it,
                pacienteId = paciente.id
            )
        }

        quimioService.deleteQuimio(paciente.id)

        paciente.quimios.forEach {
            quimioService.createQuimio(
                quimio = it,
                pacienteId = paciente.id
            )
        }

        radioService.deleteRadio(paciente.id)

        paciente.radios.forEach {
            radioService.createRadio(
                radio = it,
                pacienteId = paciente.id
            )
        }

        composicaoFamiliarService.deleteComposicaoFamiliar(pacienteId = paciente.id)
        paciente.composicaoFamiliar.forEach {
            composicaoFamiliarService.createComposicaoFamiliar(
                componenteFamiliar = it,
                pacienteId = paciente.id
            )
        }

        historicoSaudeService.createHistoricoSaude(
            historicoSaude = paciente.historicoSaude,
            pacienteId = paciente.id
        )
    }
}