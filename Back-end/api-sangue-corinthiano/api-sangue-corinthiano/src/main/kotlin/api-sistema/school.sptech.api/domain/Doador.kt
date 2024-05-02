package ProjetoPI.ProjetoDoadores.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

@Entity
data class Doador(
    @field:Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @field:NotBlank
    var nome: String = "",

    @field:NotBlank
    var email: String = "",

    @field:NotNull
    var dtNasc: LocalDate = LocalDate.now(),

    @field:NotNull
    var sexo: Char = ' ',

    @field:NotNull
    var primeiraDoacao: Boolean = false,

    @field:NotBlank
    var senha: String = "",

    @field:NotBlank
    var motivo: String = ""
) {
//    constructor(paramEmail: String, paramSenha: String):this(email = paramEmail, senha = paramSenha)
}
