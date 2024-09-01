package ProjetoPI.ProjetoDoadores.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDate
import java.time.LocalTime

@Entity
data class Agendamento(
    @field:Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idAgendamento: Int = 0,

    //@field:NotNull
    var data: LocalDate = LocalDate.now(),

    var hora: LocalTime = LocalTime.now(),

    //@field:NotNull
    var cpfDoador: Char = 0.toChar(),

    //@field:NotNull
    var idDoador: Int = 1,

    //@field:NotNull
    var idInstituicao: Int = 1,

    //@field:NotNull
    var idCampanha: Int = 1,

    //@field:NotNull
    var status: Int = 1
)
