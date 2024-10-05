package ProjetoPI.ProjetoDoadores.controller

import ProjetoPI.ProjetoDoadores.domain.Instituicao
import ProjetoPI.ProjetoDoadores.dto.InstituicaoDto
import ProjetoPI.ProjetoDoadores.service.InstituicaoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://127.0.0.1:5500"])
@RestController
@RequestMapping("/instituicoes")
class InstituicaoController(
    val service: InstituicaoService
) {

    // Endpoint para cadastrar uma nova Instituição
    @PostMapping
    fun cadastrar(@RequestBody novaInstituicao: Instituicao): ResponseEntity<Instituicao> {
        val instituicao = service.cadastrar(novaInstituicao)
        return ResponseEntity.status(201).body(instituicao)
    }

    // Endpoint para atualizar uma Instituição existente
    @PutMapping("/{idInstituicao}")
    fun atualizar(@PathVariable idInstituicao: Int, @RequestBody novaInstituicao: Instituicao): ResponseEntity<Instituicao> {
        val instituicao = service.atualizar(idInstituicao, novaInstituicao)
        return ResponseEntity.status(201).body(instituicao)
    }

    // Endpoint para buscar instituições do tipo "Sangue Corinthiano"
    @GetMapping("/sangue-corinthiano")
    fun getLocaisSangueCorinthiano(): ResponseEntity<List<InstituicaoDto>> {
        val locais = service.getInstituicoesPorTipo("Sangue Corinthiano")
        return ResponseEntity.ok(locais)
    }

    // Endpoint para buscar instituições do tipo "Instituição Parceira"
    @GetMapping("/parceiros")
    fun getParceirosLocais(): ResponseEntity<List<InstituicaoDto>> {
        val locais = service.getInstituicoesPorTipo("Instituição Parceira")
        return ResponseEntity.ok(locais)
    }


}
