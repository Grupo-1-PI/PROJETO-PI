package ProjetoPI.ProjetoDoadores.repository

import ProjetoPI.ProjetoDoadores.domain.Agendamento
import `api-sistema`.school.sptech.api.dto.DoadorAgendamentoDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

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

}