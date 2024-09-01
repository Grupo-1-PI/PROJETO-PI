package ProjetoPI.ProjetoDoadores.repository

import ProjetoPI.ProjetoDoadores.domain.Agendamento
import org.springframework.data.jpa.repository.JpaRepository

interface AgendamentoRepository: JpaRepository<Agendamento, Int> {
}