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
data class AdminSangueCorinthiano(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idAdmin: Int = 0,

    @NotBlank(message = "CPF é obrigatório")
    var cpf: String = "",

    @NotBlank(message = "Nome é obrigatório")
    var nome: String = "",

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail deve ser válido")
    var email: String = "",

    @NotNull(message = "Data de nascimento é obrigatória")
    var dtNasc: LocalDate = LocalDate.now(),

    @NotBlank(message = "Sexo é obrigatório")
    var sexo: String = "",

    @NotBlank(message = "Senha é obrigatória")
    var senha: String = "",

    @NotNull(message = "Cargo é obrigatório")
    var idCargo: Int = 0,

    @NotNull(message = "Unidade é obrigatória")
    var idUnidade: Int = 0,

    // Adicionando o campo de status para soft delete
    var status: String = "ATIVO"
)
