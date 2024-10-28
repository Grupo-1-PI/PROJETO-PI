package ProjetoPI.ProjetoDoadores.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

@Entity
class AdminSangueCorinthiano(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val idAdmin: Int = 0,

    @field:NotBlank(message = "CPF é obrigatório")
    var cpf: String = "",

    @field:NotBlank(message = "Nome é obrigatório")
    var nome: String = "",

    @field:NotBlank(message = "E-mail é obrigatório")
    @field:Email(message = "E-mail deve ser válido")
    var email: String = "",

    @field:NotNull(message = "Data de nascimento é obrigatória")
    var dtNasc: LocalDate = LocalDate.now(),

    @field:NotBlank(message = "Sexo é obrigatório")
    var sexo: String = "", // Agora é String

    @field:NotBlank(message = "Senha é obrigatória")
    var senha: String = "",

    @field:NotNull(message = "Cargo é obrigatório")
    var idCargo: Int = 0,

    @field:NotNull(message = "Unidade é obrigatória")
    var idUnidade: Int = 0,

    @field:NotNull(message = "Nível de acesso é obrigatório")
    var nivelAcesso: Int = 3
) {
    fun autenticar(): Boolean {
        return nivelAcesso >= 2
    }
}
