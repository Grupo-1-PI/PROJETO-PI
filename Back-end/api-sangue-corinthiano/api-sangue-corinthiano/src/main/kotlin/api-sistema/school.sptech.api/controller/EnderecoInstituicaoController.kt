package ProjetoPI.ProjetoDoadores.controller

import ProjetoPI.ProjetoDoadores.repository.EnderecoInstituicao
import ProjetoPI.ProjetoDoadores.repository.EnderecoInstituicaoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://127.0.0.1:5500"])
@RestController
@RequestMapping("/enderecos-instituicoes")
class EnderecoInstituicaoController(
    val service: EnderecoInstituicaoService
) {
    @PutMapping("/{idInstituicao}")
    fun atualizar(@PathVariable idInstituicao: Int, @RequestBody enderecoInstituicao: EnderecoInstituicao): ResponseEntity<EnderecoInstituicao>{
        return ResponseEntity.status(200).body(service.atualizar(idInstituicao, enderecoInstituicao))
    }

}