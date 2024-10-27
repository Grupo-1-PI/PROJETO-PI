package ProjetoPI.ProjetoDoadores.controller

import ProjetoPI.ProjetoDoadores.dto.AdminSangueCorinthianoDTO
import ProjetoPI.ProjetoDoadores.service.AdminSangueCorinthianoService
import ProjetoPI.ProjetoDoadores.service.InstituicaoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ProjetoPI.ProjetoDoadores.dto.InstituicaoDto

@RestController
@RequestMapping("/colaboradores")
class AdminSangueCorinthianoController(
    private val service: AdminSangueCorinthianoService,
    private val instituicaoService: InstituicaoService // Certifique-se de que isso está injetado corretamente
) {

    // Endpoint para "cancelar" colaborador (soft delete)
    @DeleteMapping("/{idColaborador}")
    fun cancelarColaborador(@PathVariable idColaborador: Int): ResponseEntity<Void> {
        service.cancelarColaborador(idColaborador)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/instituicao/{instituicaoId}")
    fun listarPorInstituicao(@PathVariable instituicaoId: Int): ResponseEntity<List<AdminSangueCorinthianoDTO>> {
        val colaboradores = service.buscarPorInstituicao(instituicaoId)
        return ResponseEntity.ok(colaboradores)
    }

    @GetMapping("/instituicao/nome/{nomeInstituicao}")
    fun buscarInstituicaoPorNome(@PathVariable nomeInstituicao: String): ResponseEntity<InstituicaoDto> {
        val instituicao = instituicaoService.buscarInstituicaoPorNome(nomeInstituicao)
        return if (instituicao != null) {
            ResponseEntity.ok(instituicao)
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
