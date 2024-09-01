package ProjetoPI.ProjetoDoadores.repository

import ProjetoPI.ProjetoDoadores.domain.TelefoneDoador
import org.springframework.data.jpa.repository.JpaRepository

interface TelefoneRepository: JpaRepository<TelefoneDoador, Int> {
    fun findByDoadorIdDoador(doadorId: Int): List<TelefoneDoador>
}