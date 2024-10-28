package ProjetoPI.ProjetoDoadores.controller

import ProjetoPI.ProjetoDoadores.domain.Campanha
import ProjetoPI.ProjetoDoadores.domain.EnderecoInstituicao
import ProjetoPI.ProjetoDoadores.domain.Instituicao
import ProjetoPI.ProjetoDoadores.repository.CampanhaRepository
import ProjetoPI.ProjetoDoadores.repository.CampanhaService
import ProjetoPI.ProjetoDoadores.repository.EnderecoInstituicaoService
import `api-sistema`.school.sptech.api.dto.EnderecoInstituicaoDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.Stack

@CrossOrigin(origins = ["http://127.0.0.1:5500"])
@RestController
@RequestMapping("/campanhas")
class CampanhaController(
    var repository: CampanhaRepository,
    val service: CampanhaService
) {
    val pilhaCampanhas = Stack<Campanha>()

    @PostMapping()
    fun empilharCampanhaAdicionar(@RequestBody c: Campanha): ResponseEntity<String> {
        pilhaCampanhas.push(c)
        repository.save(c)
        return ResponseEntity.status(201).body("Campanha ${c.nome} adicionada à pilha com sucesso!")
    }

    @DeleteMapping
    fun empilharCampanhaRemover(@RequestBody c: Campanha): ResponseEntity<String> {
        pilhaCampanhas.pop() // Adiciona a campanha à pilha
        return ResponseEntity.status(201).body("Campanha ${c.nome} retirada da pilha com sucesso!") // Exemplo de acesso a uma propriedade de Campanha
    }

    @GetMapping("/listar")
    fun listarCampanhas(): ResponseEntity<List<Campanha>> {
        val listaCampanhas = repository.findAll()

        if(listaCampanhas.isEmpty()) {
            return ResponseEntity.status(204).build()
        }
        return ResponseEntity.status(200).body(listaCampanhas)
    }




//    @GetMapping
//    fun empilharCampanhaListar(): ResponseEntity<List<Campanha>> {
//        val listaCampanhas = repository.findAll()
//
//        if (listaCampanhas.isEmpty()) {
//            return ResponseEntity.status(204).build()  // Retorna 204 No Content se não houver campanhas
//        }
//
//        // Empilha todas as campanhas da lista na pilha
//        listaCampanhas.forEach { campanha ->
//            pilhaCampanhas.push(campanha)
//        }
//
//        // Faz uma cópia da pilha para retornar sem modificar a pilha original
//        val campanhasEmpilhadas = pilhaCampanhas.toList()
//
//        return ResponseEntity.status(200).body(campanhasEmpilhadas)
//    }

    @PostMapping("/{campanhas}")
    fun cadastrar( @RequestBody campanha: Campanha): ResponseEntity<Campanha>{
        return ResponseEntity.status(201).body(service.cadastrar(campanha))
    }


    // Endpoint para atualizar uma campanha existente
    @PutMapping("/{idCampanha}")
    fun atualizar(@PathVariable idCampanha: Int, @RequestBody novaCampanha: Campanha): ResponseEntity<Campanha> {
        val campanha = service.atualizar(idCampanha, novaCampanha)
        return ResponseEntity.status(201).body(campanha)
    }

    @GetMapping("/listar/{idCampanha}")
    fun buscarCampanhaUnica(
        @PathVariable idCampanha: Int,
    ): ResponseEntity<CampanhaDto> {
        val enderecoInstituicao = service.buscarUm(idCampanha)
        return ResponseEntity.status(200).body(enderecoInstituicao)
    }

    @GetMapping("/listarCampanhas/{idUsuario}")
    fun listarCampanhasPorId(@PathVariable idUsuario: Int): ResponseEntity<List<Array<Any>>> {
        val listaCampanhas = repository.listarCampanhaPorId(idUsuario)

        return if (listaCampanhas.isEmpty()) {
            ResponseEntity.status(204).build()
        } else {
            ResponseEntity.status(200).body(listaCampanhas)
        }
    }
}