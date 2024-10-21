package ProjetoPI.ProjetoDoadores.repository

import ProjetoPI.ProjetoDoadores.domain.Campanha
import org.springframework.stereotype.Service

@Service
class CampanhaService(
    val repository: CampanhaRepository
) {

    fun cadastrar(novaCampanha: Campanha): Campanha {
        return repository.save(novaCampanha)
    }
}