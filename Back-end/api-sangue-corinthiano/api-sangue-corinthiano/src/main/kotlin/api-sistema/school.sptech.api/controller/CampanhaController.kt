package ProjetoPI.ProjetoDoadores.controller

import ProjetoPI.ProjetoDoadores.domain.Campanha
import ProjetoPI.ProjetoDoadores.repository.CampanhaRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Stack

@RestController
@RequestMapping("/campanhas")
class CampanhaController(var repository: CampanhaRepository) {
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

    @GetMapping
    fun empilharCampanhaListar(): ResponseEntity<List<Campanha>> {
        val listaCampanhas = repository.findAll()

        if (listaCampanhas.isEmpty()) {
            return ResponseEntity.status(204).build()  // Retorna 204 No Content se não houver campanhas
        }

        // Empilha todas as campanhas da lista na pilha
        listaCampanhas.forEach { campanha ->
            pilhaCampanhas.push(campanha)
        }

        // Faz uma cópia da pilha para retornar sem modificar a pilha original
        val campanhasEmpilhadas = pilhaCampanhas.toList()

        return ResponseEntity.status(200).body(campanhasEmpilhadas)
    }
}