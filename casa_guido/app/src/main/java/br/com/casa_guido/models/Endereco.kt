package br.com.casa_guido.models

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Endereco(
    val id: String = UUID.randomUUID().toString(),
    val cep: String = "",
    val logradouro: String = "",
    val numero: String = "",
    val complemento: String = "",
    val unidade: String = "",
    val bairro: String = "",
    val localidade: String = "",
    val referencia: String = "",
    val uf: String = "",
    val estado: String = "",
    val regiao: String = "",
    val ibge: String = "",
    val gia: String = "",
    val ddd: String = "",
    val siafi: String = "",
    val pais: String = "Brasil",
)

@Serializable
data class EnderecoDTO(
    val cep: String = "",
    val logradouro: String = "",
    val numero: String = "",
    val complemento: String = "",
    val unidade: String = "",
    val bairro: String = "",
    val localidade: String = "",
    val referencia: String = "",
    val uf: String = "",
    val estado: String = "",
    val regiao: String = "",
    val ibge: String = "",
    val gia: String = "",
    val ddd: String = "",
    val siafi: String = "",
    val pais: String = "Brasil",
)

fun Endereco.toRequestDTO(): EnderecoDTO {
    return EnderecoDTO(
        cep = this.cep,
        logradouro = this.logradouro,
        numero = this.numero,
        complemento = this.complemento,
        unidade = this.unidade,
        bairro = this.bairro,
        localidade = this.localidade,
        referencia = this.referencia,
        uf = this.uf,
        estado = this.estado,
        regiao = this.regiao,
        ibge = this.ibge,
        gia = this.gia,
        ddd = this.ddd,
        siafi = this.siafi,
        pais = this.pais
    )
}

