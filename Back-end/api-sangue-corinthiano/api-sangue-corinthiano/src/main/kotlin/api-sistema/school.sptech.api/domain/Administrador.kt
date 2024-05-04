package ProjetoPI.ProjetoDoadores.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

@Entity
data class Administrador(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @field:NotBlank
    var nome: String = "",

    @field:NotBlank
    var cpf: String = "",

    @field:NotBlank
    var email: String = "",

    @field:NotBlank
    var senha: String = "",

    @field:NotNull
    var cargoId: Int = 0,

    @field:NotNull
    var unidadeId: Int = 0,

    @field:NotNull
    var dtNasc: LocalDate = LocalDate.now(),

    @field:NotNull
    var sexo: Char = ' '
) {
}
