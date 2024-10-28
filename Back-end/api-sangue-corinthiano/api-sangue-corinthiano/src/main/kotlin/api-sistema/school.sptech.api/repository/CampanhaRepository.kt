package ProjetoPI.ProjetoDoadores.repository

import org.springframework.data.jpa.repository.JpaRepository
import ProjetoPI.ProjetoDoadores.domain.Campanha
import ProjetoPI.ProjetoDoadores.domain.EnderecoInstituicao


interface CampanhaRepository: JpaRepository<Campanha, Int> {

    fun findByIdCampanha(idEnderecoInstituicao: Int): Campanha

}