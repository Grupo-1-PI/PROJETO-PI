package ProjetoPI.ProjetoDoadores.repository

import ProjetoPI.ProjetoDoadores.domain.VwTaxaCancelamentoAgendamento
import ProjetoPI.ProjetoDoadores.domain.VwTaxaRetencao
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VwTaxaRetencaoRepository: JpaRepository<VwTaxaRetencao, Double> {
}