package ProjetoPI.ProjetoDoadores.controller

import ProjetoPI.ProjetoDoadores.domain.EnderecoInstituicao
import ProjetoPI.ProjetoDoadores.repository.EnderecoInstituicaoService
import `api-sistema`.school.sptech.api.dto.EnderecoInstituicaoDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://127.0.0.1:5500"])
@RestController
@RequestMapping("/enderecos-instituicoes")
class EnderecoInstituicaoController(
    val service: EnderecoInstituicaoService
) {
    @PutMapping("/{idEnderecoInstituicao}/{idInstituicao}")
    fun atualizar(@PathVariable idEnderecoInstituicao: Int, @PathVariable idInstituicao: Int, @RequestBody enderecoInstituicao: EnderecoInstituicao): ResponseEntity<EnderecoInstituicaoDto>{
        return ResponseEntity.status(200).body(service.atualizar(idEnderecoInstituicao, idInstituicao, enderecoInstituicao))
    }

    @PostMapping("/{idInstituicao}")
    fun cadastrar(@PathVariable idInstituicao: Int, @RequestBody enderecoInstituicao: EnderecoInstituicao): ResponseEntity<EnderecoInstituicaoDto>{
        return ResponseEntity.status(201).body(service.cadastrar(idInstituicao, enderecoInstituicao))
    }

    @GetMapping
    fun listar(@RequestParam parceiro: Boolean): ResponseEntity<List<EnderecoInstituicaoDto>>{
        val listaEnderecoInstituicao = service.buscar(parceiro)
        return ResponseEntity.status(200).body(listaEnderecoInstituicao)
    }

    @GetMapping("/{idEnderecoInstituicao}")
    fun buscarPorUm(
        @PathVariable idEnderecoInstituicao: Int,
    ): ResponseEntity<EnderecoInstituicaoDto>{
        val enderecoInstituicao = service.buscarUm(idEnderecoInstituicao)
        return ResponseEntity.status(200).body(enderecoInstituicao)
    }

}