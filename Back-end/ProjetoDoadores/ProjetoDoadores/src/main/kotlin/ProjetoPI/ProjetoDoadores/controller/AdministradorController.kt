package ProjetoPI.ProjetoDoadores.controller

import ProjetoPI.ProjetoDoadores.domain.Administrador
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/administradores")
class AdministradorController {
    val listaAdministradores: MutableList<Administrador> = mutableListOf()

    fun existeAdministrador(id: Int): Boolean {
        return id >= 0 && id < listaAdministradores.size
    }

    @GetMapping("/buscarAdministrador/{id}")
    fun retornarAdministrador(@PathVariable id: Int): ResponseEntity<Administrador> {
        return if (existeAdministrador(id)) {
            ResponseEntity.status(200).body(listaAdministradores[id])
        } else {
            ResponseEntity.status(404).build()
        }
    }

    @PostMapping
    fun cadastrarAdministrador(@RequestBody administrador: Administrador): ResponseEntity<String> {
        return if (existeAdministrador(administrador.id)) {
            ResponseEntity.status(400).build()
        } else {
            listaAdministradores.add(administrador)
            ResponseEntity.status(201).body("Administrador ${administrador.nome} cadastrado com sucesso!")
        }
    }

    @PutMapping("/{id}")
    fun atualizarAdministrador(@PathVariable id: Int, @RequestBody administrador: Administrador): ResponseEntity<Administrador> {
        return if (existeAdministrador(id)) {
            listaAdministradores[id] = administrador
            ResponseEntity.status(200).body(listaAdministradores[id])
        } else {
            ResponseEntity.status(404).build()
        }
    }

    @DeleteMapping("/{id}")
    fun removerAdministrador(@PathVariable id: Int): ResponseEntity<String> {
        return if (existeAdministrador(id)) {
            listaAdministradores.removeAt(id)
            ResponseEntity.status(200).body("Administrador deletado com sucesso!")
        } else {
            ResponseEntity.status(404).build()
        }
    }
}
