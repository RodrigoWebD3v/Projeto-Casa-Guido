package br.com.casa_guido.models

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class HistoricoSaude(
    val id: String = UUID.randomUUID().toString(),
    val doencasFamilia: Array<Int> = emptyArray(),
    val medicamentosUsoContinuo: String = "",
    val localProcuraAtendimento: Array<Int> = emptyArray(),
    val recebeBeneficio: Int? = null,
    val beneficioDescricao: String = "",
)