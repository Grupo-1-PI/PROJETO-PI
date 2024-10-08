package ProjetoPI.ProjetoDoadores.domain

data class InstituicaoEnderecoDto(
    var idInstituicao: Int? = null,
    var nome: String? = null,
    var cnpj: String? = null,

    var parceiro: Boolean? = false,
    var tipoInstituicao: String? = null,
) {

}