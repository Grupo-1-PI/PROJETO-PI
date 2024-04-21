package ProjetoPI.ProjetoDoadores.controller

import ProjetoPI.ProjetoDoadores.domain.Doador
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/doadores")
class DoadorController {
    val listaDoadores: MutableList<Doador> = mutableListOf()

    fun existeDoador(id: Int): Boolean {
        return id >= 0 && id < listaDoadores.size
    }

    @GetMapping("/buscarDoador/{id}")
    fun retornarDoador(@PathVariable id: Int): ResponseEntity<Doador> {
        return if (existeDoador(id)) {
            ResponseEntity.status(200).body(listaDoadores[id])
        } else {
            ResponseEntity.status(404).build()
        }
    }

    @PostMapping
    fun cadastrarDoador(@RequestBody doador: Doador): ResponseEntity<String> {
        return if (existeDoador(doador.id)) {
            ResponseEntity.status(400).build()
        } else {
            listaDoadores.add(doador)
            ResponseEntity.status(201).body("Doador ${doador.nome} cadastrado com sucesso!")
        }
    }

    @PutMapping("/{id}")
    fun atualizarDoador(@PathVariable id: Int, @RequestBody doador: Doador): ResponseEntity<Doador> {
        return if (existeDoador(id)) {
            listaDoadores[id] = doador
            ResponseEntity.status(200).body(listaDoadores[id])
        } else {
            ResponseEntity.status(404).build()
        }
    }

    @DeleteMapping("/{id}")
    fun removerDoador(@PathVariable id: Int): ResponseEntity<String> {
        return if (existeDoador(id)) {
            listaDoadores.removeAt(id)
            ResponseEntity.status(200).body("Doador deletado com sucesso!")
        } else {
            ResponseEntity.status(404).build()
        }
    }
}




