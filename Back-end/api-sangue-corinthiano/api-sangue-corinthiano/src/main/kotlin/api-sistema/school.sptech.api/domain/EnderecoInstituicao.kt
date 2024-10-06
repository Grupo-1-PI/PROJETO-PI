package ProjetoPI.ProjetoDoadores.domain

import jakarta.persistence.*

@Entity
data class EnderecoInstituicao(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idEnderecoInstituicao: Int? = null,

    val rua: String,
    val numero: Int,
    val bairro: String,
    val complemento: String?,
    val cidade: String,
    val estado: String,
    val cep: String,
    val latitude: Double,
    val longitude: Double,

    @OneToOne
    @JoinColumn(name = "idInstituicao")  // Nome da coluna de chave estrangeira
    val instituicao: Instituicao? = null
)
