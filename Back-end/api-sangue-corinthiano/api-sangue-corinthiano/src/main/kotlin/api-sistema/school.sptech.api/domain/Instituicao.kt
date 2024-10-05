package ProjetoPI.ProjetoDoadores.domain

import jakarta.persistence.*

@Entity
data class Instituicao(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idInstituicao: Int? = null,

    val nome: String,
    val tipoInstituicao: String,

    @OneToOne(mappedBy = "instituicao", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val enderecoInstituicao: EnderecoInstituicao? = null
)
