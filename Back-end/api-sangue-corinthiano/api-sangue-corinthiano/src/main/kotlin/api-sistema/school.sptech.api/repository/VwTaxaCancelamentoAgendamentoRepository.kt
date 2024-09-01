package ProjetoPI.ProjetoDoadores.repository

import ProjetoPI.ProjetoDoadores.domain.VwTaxaCancelamentoAgendamento
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VwTaxaCancelamentoAgendamentoRepository: JpaRepository<VwTaxaCancelamentoAgendamento, Double> {
}