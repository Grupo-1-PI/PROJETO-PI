package ProjetoPI.ProjetoDoadores.repository

import ProjetoPI.ProjetoDoadores.domain.Agendamento
import `api-sistema`.school.sptech.api.dto.DoadorAgendamentoDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface
AgendamentoRepository: JpaRepository<Agendamento, Int> {
    @Query(
        value = " INSERT INTO agendamento (data, hora, cpf_doador, id_doador, id_instituicao, id_campanha)",
        nativeQuery = true
    )
    fun cadastraAgendamento(): Agendamento?

    @Query(
        value = "SELECT a.hora AS hora, d.nome AS nome FROM agendamento a " +
                "JOIN doador d ON a.id_doador = d.id_doador",
        nativeQuery = true
    )
    fun getDoadoresAgendamentos(): List<Map<String,Any>>

    @Query(
        value = "SELECT COUNT(*) FROM agendamento",
        nativeQuery = true
    )
    fun getTotalDoadoresPorCampanha(): Int


    @Query(
        value= "SELECT \n" +
                "    i.nome AS nome_instituicao,\n" +
                "    ei.cidade,\n" +
                "    ei.rua,\n" +
                "    ei.bairro,\n" +
                "    ei.estado,\n" +
                "    c.nome AS nome_campanha,\n" +
                "    a.status\n" +
                "FROM \n" +
                "    doador d\n" +
                "JOIN \n" +
                "    agendamento a ON d.id_doador = a.id_doador\n" +
                "JOIN \n" +
                "    campanha c ON a.id_campanha = c.id_campanha\n" +
                "JOIN \n" +
                "    instituicao i ON a.id_instituicao = i.id_instituicao\n" +
                "JOIN \n" +
                "    endereco_instituicao ei ON i.id_instituicao = ei.id_instituicao\n" +
                "WHERE \n" +
                "    d.id_doador = :idDoador\n" +
                "    AND a.status = 1;\n",
        nativeQuery =true
    )
    fun getDadosCampanha(): List<Map<String,Any>>

    @Modifying
    @Query(
        value = """
    UPDATE agendamento 
    SET status = 0 
    WHERE id_doador = :idDoador 
      AND id_agendamento = :idAgendamento
    """,
        nativeQuery = true
    )
    fun cancelarAgendamentoPorDoador(
        @Param("idDoador") idDoador: Int,
        @Param("idAgendamento") idAgendamento: Int
    ): Int
}

