package ProjetoPI.ProjetoDoadores.dto

import java.time.LocalDate
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class AdminSangueCorinthianoDTO(
    @field:NotBlank val nome: String,
    @field:NotBlank val cpf: String,
    @field:Email val email: String,
    @field:NotNull val dtNasc: LocalDate,
    @field:NotBlank val sexo: String,
    @field:NotBlank val senha: String,
    @field:NotNull val idUnidade: Int,
    @field:NotNull val idCargo: Int
)
