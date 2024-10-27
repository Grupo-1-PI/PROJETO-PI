package ProjetoPI.ProjetoDoadores.repository

import org.springframework.data.jpa.repository.JpaRepository
import ProjetoPI.ProjetoDoadores.domain.Campanha
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param


interface CampanhaRepository: JpaRepository<Campanha, Int> {

    @Query(

        value = """
        SELECT 
            i.nome AS nome_instituicao,
            ei.cidade,
            ei.rua,
            ei.bairro,
            ei.estado,
            c.nome AS nome_campanha,
            a.status,
            a.data
        FROM 
            doador d
        JOIN 
            agendamento a ON d.id_doador = a.id_doador
        JOIN 
            campanha c ON a.id_campanha = c.id_campanha
        JOIN 
            instituicao i ON a.id_instituicao = i.id_instituicao
        JOIN 
            endereco_instituicao ei ON i.id_instituicao = ei.id_instituicao
        WHERE 
            d.id_doador = :idDoador
    """,
        nativeQuery = true
    )

    fun listarCampanhaPorId(@Param("idDoador") idDoador: Int): List<Array<Any>>
}

