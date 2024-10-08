package ProjetoPI.ProjetoDoadores.repository

import ProjetoPI.ProjetoDoadores.dto.InstituicaoDto
import ProjetoPI.ProjetoDoadores.domain.Instituicao
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface InstituicaoRepository : JpaRepository<Instituicao, Int> {

    @Query("""
        SELECT new ProjetoPI.ProjetoDoadores.dto.InstituicaoDto(i.nome, e.latitude, e.longitude) 
        FROM Instituicao i 
        JOIN i.enderecoInstituicao e 
        WHERE i.tipoInstituicao = :tipoInstituicao
    """)
    fun findByTipoInstituicao(tipoInstituicao: String): List<InstituicaoDto>
}
