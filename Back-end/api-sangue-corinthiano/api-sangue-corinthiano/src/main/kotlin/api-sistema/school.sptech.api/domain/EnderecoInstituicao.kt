package ProjetoPI.ProjetoDoadores.repository

import ProjetoPI.ProjetoDoadores.domain.Instituicao
import jakarta.persistence.*

@Entity
class EnderecoInstituicao(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var idEnderecoInstituicao: Int? = null,
    var rua: String? = null,
    var numero: Int? = null,
    var bairro: String? = null,
    var complemento: String? = null,
    var cidade: String? = null,
    var estado: String? = null,
    var cep: String? = null,
    var latitude: Double? = null,
    var longitude: Double? = null,
    @field:ManyToOne
    @JoinColumn(name = "id_instituicao")
    var idInstituicao: Instituicao? = null
) {
}