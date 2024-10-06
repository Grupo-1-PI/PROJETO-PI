package ProjetoPI.ProjetoDoadores.domain

import jakarta.persistence.*

@Entity
data class Instituicao(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idInstituicao: Int? = null,
    var nome: String? = null,
    var cnpj: String? = null,
    var latitude: String? = null,
    var longitude: String? = null,
    var parceiro: Boolean? = false,
    @OneToOne(mappedBy = "instituicao", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val enderecoInstituicao: EnderecoInstituicao? = null
) {
}