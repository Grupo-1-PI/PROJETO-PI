package ProjetoPI.ProjetoDoadores.repository

import ProjetoPI.ProjetoDoadores.domain.Agendamento
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface AgendamentoRepository: JpaRepository<Agendamento, Int> {
    @Query(
        value = " INSERT INTO agendamento (data, hora, cpf_doador, id_doador, id_instituicao, id_campanha)",
        nativeQuery = true
    )
    fun cadastraAgendamento(): Agendamento?
}