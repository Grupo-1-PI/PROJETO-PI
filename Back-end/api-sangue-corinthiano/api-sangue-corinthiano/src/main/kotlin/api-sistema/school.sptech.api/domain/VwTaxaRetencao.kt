package ProjetoPI.ProjetoDoadores.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "vw_taxa_retencao")
data class VwTaxaRetencao (
    @field:Id
    val taxaRetencao: Double
){
    constructor() : this(0.0)
}