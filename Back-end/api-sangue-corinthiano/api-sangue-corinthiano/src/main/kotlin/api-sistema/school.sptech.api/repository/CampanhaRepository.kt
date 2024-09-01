package ProjetoPI.ProjetoDoadores.repository

import org.springframework.data.jpa.repository.JpaRepository
import ProjetoPI.ProjetoDoadores.domain.Campanha


interface CampanhaRepository: JpaRepository<Campanha, Int> {
}