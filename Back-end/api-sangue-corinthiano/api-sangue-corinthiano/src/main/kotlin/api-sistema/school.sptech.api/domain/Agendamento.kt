package ProjetoPI.ProjetoDoadores.domain

import jakarta.persistence.*
import org.hibernate.validator.constraints.br.CPF
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
//    @field:CPF
    var cpfDoador: String,

    //@field:NotNull
    //@field:ManyToOne
    //@JoinColumn(name = "id_doador")
    var idDoador: Int = 1,

    //@field:NotNull
    //@JoinColumn(name = "id_instituicao")
    var idInstituicao: Int = 1,

    //@field:NotNull
   //@JoinColumn(name = "id_campanha")
    var idCampanha: Int = 1,

    //@field:NotNull
    var status: Int = 1


)
