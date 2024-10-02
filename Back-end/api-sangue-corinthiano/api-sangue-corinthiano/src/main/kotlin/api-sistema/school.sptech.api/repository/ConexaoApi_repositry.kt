
package ProjetoPI.ProjetoDoadores.repository

import ProjetoPI.ProjetoDoadores.domain.ConexaoApi_domain
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ConexaoApi_repository : JpaRepository<ConexaoApi_domain, Long> {
    fun findAllByTipoInstituicao(tipoInstituicao: String): List<ConexaoApi_domain>
}

