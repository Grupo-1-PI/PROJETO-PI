package ProjetoPI.ProjetoDoadores.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Instituicao(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var idInstituicao: Int? = null,
    var nome: String? = null,
    var cnpj: String? = null,
    var latitude: String? = null,
    var longitude: String? = null,
    var parceiro: Boolean? = false
) {
}