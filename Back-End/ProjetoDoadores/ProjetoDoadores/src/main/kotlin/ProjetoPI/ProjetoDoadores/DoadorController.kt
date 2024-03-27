package ProjetoPI.ProjetoDoadores

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/doadores")
class DoadorController {
    val listaDoadores: MutableList<Doador> = mutableListOf()

    fun existeDoador(id: Int): Boolean {
        return id >= 0 && id < listaDoadores.size
    }

    @GetMapping("/buscarDoador/{id}")
    fun retornarDoador(@PathVariable id: Int): ResponseEntity<Doador> {
        if (existeDoador(id)) {
            return ResponseEntity.status(200).body(listaDoadores[id])
        } else {
            return ResponseEntity.status(404).build()
        }
    }

    @PostMapping
    fun cadastrarDoador(@RequestBody doador: Doador): ResponseEntity<String> {
        if (existeDoador(doador.id)) {
            return ResponseEntity.status(400).build()
        }
        listaDoadores.add(doador)
        return ResponseEntity.status(201).body("Doador ${doador.nome} cadastrado com sucesso!")
    }

    @PutMapping("/{id}")
    fun atualizarDoador(@PathVariable id: Int, @RequestBody doador: Doador): ResponseEntity<Doador> {
        if (existeDoador(id)) {
            listaDoadores[id] = doador
            return ResponseEntity.status(200).body(listaDoadores[id])
        }
        return ResponseEntity.status(404).build()
    }

    @DeleteMapping("/{id}")
    fun removerDoador(@PathVariable id: Int): ResponseEntity<String> {
        if (existeDoador(id)) {
            listaDoadores.removeAt(id)
            return ResponseEntity.status(200).body("Doador deletado com sucesso!")
        }
        return ResponseEntity.status(404).build()
    }
}



