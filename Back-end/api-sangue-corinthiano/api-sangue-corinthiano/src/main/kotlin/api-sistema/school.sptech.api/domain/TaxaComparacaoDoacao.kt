//package `api-sistema`.school.sptech.api.domain
package ProjetoPI.ProjetoDoadores.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class TaxaComparacaoDoacao(
    @field:Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0,
    val ano: Int,
    val mes: Int,
    val quantidade: Int
){
    constructor() : this(0, 0, 0, 0)
}
