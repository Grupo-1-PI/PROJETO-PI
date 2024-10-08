package `api-sistema`.school.sptech.api.dto

import ProjetoPI.ProjetoDoadores.domain.Instituicao
import ProjetoPI.ProjetoDoadores.domain.InstituicaoEnderecoDto

class EnderecoInstituicaoDto(
    val idEnderecoInstituicao: Int? = null,
    val rua: String? = null,
    val numero: Int? = null,
    val bairro: String? = null,
    val complemento: String? = null,
    val cidade: String? = null,
    val estado: String? = null,
    val cep: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val instituicao: InstituicaoEnderecoDto? = null
) {
}