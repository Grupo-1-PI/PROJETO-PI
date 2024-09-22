package ProjetoPI.ProjetoDoadores.repository

import ProjetoPI.ProjetoDoadores.repository.EnderecoInstituicao
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EnderecoInstituicaoRepository: JpaRepository<EnderecoInstituicao, Int> {

}