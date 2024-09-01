package ProjetoPI.ProjetoDoadores.repository

import ProjetoPI.ProjetoDoadores.domain.TelefoneDoador
import org.springframework.data.jpa.repository.JpaRepository

interface TelefoneDoadorRepository: JpaRepository<TelefoneDoador, Int> {
//    fun findByDoadorId(doadorId: Int): List<TelefoneDoador>
}