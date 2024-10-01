package ProjetoPI.ProjetoDoadores.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

@Entity
class AdminSangueCorinthiano(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val idAdmin: Int = 0,

    @field:NotBlank
    var cpf: String = "",

    @field:NotNull
    var idCargo: Int = 0,

    @field:NotNull
    var idUnidade: Int = 0,

    nome: String ,
    email: String ,
    dtNasc: LocalDate = LocalDate.now() ,
    sexo: Char ,
    senha: String ,
    nivelAcesso: Int = 3 ,
    ): Usuario(nome,email,dtNasc,sexo, senha, nivelAcesso) {
    override fun autenticar(): Boolean {
        return nivelAcesso >= 2
    }
}
