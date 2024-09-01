package ProjetoPI.ProjetoDoadores.controller

import ProjetoPI.ProjetoDoadores.domain.Campanha
import ProjetoPI.ProjetoDoadores.repository.CampanhaRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/campanhas")
class CampanhaController(var repository: CampanhaRepository) {

    @GetMapping
    fun listarCampanhas(): ResponseEntity<List<Campanha>> {
        val listaCampanhas = repository.findAll()

        if(listaCampanhas.isEmpty()) {
            return ResponseEntity.status(204).build()
        }
        return ResponseEntity.status(200).body(listaCampanhas)
    }
}