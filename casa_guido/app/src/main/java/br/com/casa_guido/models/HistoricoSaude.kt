package br.com.casa_guido.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.util.UUID

@Serializable
data class HistoricoSaude(
    @Transient
    val id: String = UUID.randomUUID().toString(),
    val doencasFamilia: Array<Int> = emptyArray(),
    val medicamentosUsoContinuo: String = "",
    val localProcuraAtendimento: Array<Int> = emptyArray(),
    val recebeBeneficio: Int? = null,
    val beneficioDescricao: String = "",
)

@Serializable
data class HistoricoSaudeDTO(
    val doencasFamilia: Array<Int> = emptyArray(),
    val medicamentosUsoContinuo: String = "",
    val localProcuraAtendimento: Array<Int> = emptyArray(),
    val recebeBeneficio: Int? = null,
    val beneficioDescricao: String = "",
)

fun HistoricoSaude.toRequestDTO(): HistoricoSaudeDTO {
    return HistoricoSaudeDTO(
        doencasFamilia = this.doencasFamilia,
        medicamentosUsoContinuo = this.medicamentosUsoContinuo,
        localProcuraAtendimento = this.localProcuraAtendimento,
        recebeBeneficio = this.recebeBeneficio,
        beneficioDescricao = this.beneficioDescricao
    )
}