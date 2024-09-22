package ProjetoPI.ProjetoDoadores.repository

import ProjetoPI.ProjetoDoadores.domain.Instituicao
import ProjetoPI.ProjetoDoadores.repository.InstituicaoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://127.0.0.1:5500"])
@RestController
@RequestMapping("/instituicoes")
class InstituicaoController(
    val service: InstituicaoService
) {

    @PostMapping
    fun cadastrar(@RequestBody novaInstituicao: Instituicao): ResponseEntity<Instituicao>{
        val instituicao = service.cadastrar(novaInstituicao)
        return ResponseEntity.status(201).body(instituicao)
    }

    @PutMapping("/{idInstituicao}")
    fun atualizar(@PathVariable idInstituicao: Int, @RequestBody novaInstituicao: Instituicao): ResponseEntity<Instituicao>{
        val instituicao = service.atualizar(idInstituicao, novaInstituicao)
        return ResponseEntity.status(201).body(instituicao)
    }
}