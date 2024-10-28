package ProjetoPI.ProjetoDoadores.controller

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.NotBlank
import java.time.LocalDate

class CampanhaDto (
    val idCampanha: Int? = null,
    var nome: String? = null ,
    var dtInicio: LocalDate? = null,
    var dtFim: LocalDate? = null,
    var idInstituicao: Int? = null,
    var idAdmin: Int? = null
){
}