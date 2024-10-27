package ProjetoPI.ProjetoDoadores.controller

import ProjetoPI.ProjetoDoadores.dto.AdminSangueCorinthianoDTO
import ProjetoPI.ProjetoDoadores.service.AdminSangueCorinthianoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import jakarta.validation.Valid

@RestController
@RequestMapping("/colaboradores")
class AdminSangueCorinthianoController(
    private val service: AdminSangueCorinthianoService
) {

    @PostMapping
    fun cadastrarColaborador(@RequestBody @Valid colaboradorDTO: AdminSangueCorinthianoDTO): ResponseEntity<String> {
        service.cadastrarColaborador(colaboradorDTO)
        return ResponseEntity.ok("Colaborador cadastrado com sucesso!")
    }
}
