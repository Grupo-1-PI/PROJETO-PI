package school.sptech.projetoestoque.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import school.sptech.projetoestoque.dominio.Sistema
import school.sptech.projetoestoque.dto.SistemasResponse
import school.sptech.projetoestoque.service.SistemaService

@RequestMapping("/sistemas")
@RestController
class SistemaController(
    val sistemaService: SistemaService

) {

    @GetMapping
    fun listarSistemas(): List<Sistema> = sistemaService.listarSistemas()

    @GetMapping("/{id}")
    fun get(@PathVariable id:Int) : ResponseEntity<SistemasResponse> {
        val dto = sistemaService.get(id)
        return ResponseEntity.status(200).body(dto)
    }

    @PostMapping
    fun adicionarSistema(@RequestBody sistema: Sistema): Sistema = sistemaService.adicionarSistema(sistema)

    @PutMapping("/{id}")
    fun atualizarSistema(@PathVariable id: Int, @RequestBody sistema: Sistema): Sistema =
        sistemaService.atualizarSistema(id, sistema)

    @DeleteMapping("/{id}")
    fun excluirSistema(@PathVariable id: Int) = sistemaService.excluirSistema(id)
}

