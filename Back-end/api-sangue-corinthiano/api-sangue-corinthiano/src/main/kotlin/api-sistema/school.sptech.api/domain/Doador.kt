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
    val idDoador: Int = 0,

    @field:NotBlank
    var nome: String = "",

    @field:NotBlank
    var email: String = "",

    //@field:NotNull
    var dtNasc: LocalDate = LocalDate.now(),

    //@field:NotNull
    var sexo: Char = 0.toChar(),

    //@field:NotNull
    var primeiraDoacao: Char = 0.toChar(),

    //@field:NotBlank
    var senha: String = "",

    //@field:NotBlank
    //var motivo: String = "",

    var id_origem_trafego:Int = 1
) {
//    constructor(paramEmail: String, paramSenha: String):this(email = paramEmail, senha = paramSenha)
}
