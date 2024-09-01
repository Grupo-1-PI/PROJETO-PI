package ProjetoPI.ProjetoDoadores.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "vw_taxa_cancelamento_agendamentos")
data class VwTaxaCancelamentoAgendamento (
    @field:Id
    val taxaCancelamento: Double
){
    constructor() : this(0.0)
}