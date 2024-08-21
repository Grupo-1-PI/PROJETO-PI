package ProjetoPI.ProjetoDoadores.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

@Entity
data class AdminSangueCorinthiano(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val idAdmin: Int = 0,

    @field:NotBlank
    var nome: String = "",

    @field:NotBlank
    var cpf: Char = 0.toChar(),

    @field:NotBlank
    var email: String = "",

    @field:NotBlank
    var senha: String = "",

    @field:NotNull
    var dtNasc: LocalDate = LocalDate.now(),

    @field:NotNull
    var sexo: Char = 0.toChar(),

    @field:NotNull
    var idUnidade: Int = 0,

    @field:NotNull
    var idCargo: Int = 0
) {
}
