package ProjetoPI.ProjetoDoadores.controller

import ProjetoPI.ProjetoDoadores.domain.ConexaoApi_domain
import ProjetoPI.ProjetoDoadores.service.ConexaoApi_service
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/instituicoes")
class ConexaoApi_controller(private val conexaoApiService: ConexaoApi_service) {

    @GetMapping
    fun listarTodas(): List<ConexaoApi_domain> = conexaoApiService.listarTodas()

    @GetMapping("/tipo/{tipo}")
    fun listarPorTipo(@PathVariable tipo: String): List<ConexaoApi_domain> = conexaoApiService.listarPorTipo(tipo)

    @PostMapping
    fun adicionarInstituicao(@RequestBody instituicao: ConexaoApi_domain): ConexaoApi_domain = conexaoApiService.adicionarInstituicao(instituicao)

    @DeleteMapping("/{id}")
    fun removerInstituicao(@PathVariable id: Long): ResponseEntity<Void> {
        conexaoApiService.removerInstituicao(id)
        return ResponseEntity.noContent().build()
    }
}
