package ProjetoPI.ProjetoDoadores.repository

import ProjetoPI.ProjetoDoadores.domain.EnderecoInstituicao
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EnderecoInstituicaoRepository: JpaRepository<EnderecoInstituicao, Int> {

    fun findByInstituicaoParceiroIs(parceiro: Boolean): MutableList<EnderecoInstituicao>

    fun findByIdEnderecoInstituicao(idEnderecoInstituicao: Int): EnderecoInstituicao
}