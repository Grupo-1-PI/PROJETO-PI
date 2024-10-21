package ProjetoPI.ProjetoDoadores.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.NotBlank
import java.time.LocalDate

@Entity
data class Campanha(
    @field:Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idCampanha: Int = 0 ,

    @field:NotBlank
    var nome: String = "" ,

    //@field:NotNull
    var dtInicio: LocalDate? = null,

    var dtFim: LocalDate? = null,

    //@field:NotNull
    var idInstituicao: Int = 1 ,

    //@field:NotNull
    var idAdmin: Int = 1
) {
}