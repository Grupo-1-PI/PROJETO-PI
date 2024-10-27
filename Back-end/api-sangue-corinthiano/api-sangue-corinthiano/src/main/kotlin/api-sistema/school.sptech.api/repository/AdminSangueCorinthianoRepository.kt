package ProjetoPI.ProjetoDoadores.repository

import ProjetoPI.ProjetoDoadores.domain.AdminSangueCorinthiano
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface AdminSangueCorinthianoRepository : JpaRepository<AdminSangueCorinthiano, Int> {

    // Filtra colaboradores ativos
    @Query("SELECT a FROM AdminSangueCorinthiano a WHERE a.status = 'ATIVO'")
    fun findColaboradoresAtivos(): List<AdminSangueCorinthiano>

    // Busca colaboradores por instituição (idUnidade representa a instituição)
    @Query("SELECT a FROM AdminSangueCorinthiano a WHERE a.idUnidade = :instituicaoId AND a.status = 'ATIVO'")
    fun findByInstituicaoAtivos(@Param("instituicaoId") instituicaoId: Int): List<AdminSangueCorinthiano>
}


