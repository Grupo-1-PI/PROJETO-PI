package ProjetoPI.ProjetoDoadores.domain

import jakarta.persistence.*

@Entity
data class Instituicao(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idInstituicao: Int? = null,
    var nome: String? = null,
    var cnpj: String? = null,
    var parceiro: Boolean? = false,
    var tipoInstituicao: String? = null,
    @OneToMany(mappedBy = "instituicao")
    val enderecoInstituicao: List<EnderecoInstituicao>? = null
) {

}