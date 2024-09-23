package ProjetoPI.ProjetoDoadores.repository

import ProjetoPI.ProjetoDoadores.domain.Agendamento
import jakarta.persistence.*
import java.time.LocalDate

@Entity
data class HistoricoAgendamento (
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id_historico:Int = 0,
    @ManyToOne
    @JoinColumn(name = "id_agendamento")
    val id_agendamento: Agendamento?,
    val data_modificacao:LocalDate = LocalDate.now(),
    val status_anterior: Int,
    val status_atual: Int

){constructor() : this(0, null, LocalDate.now(), 0, 0)}

