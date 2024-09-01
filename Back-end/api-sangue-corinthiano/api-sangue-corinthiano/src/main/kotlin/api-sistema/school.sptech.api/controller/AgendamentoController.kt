package ProjetoPI.ProjetoDoadores.controller

import ProjetoPI.ProjetoDoadores.domain.Agendamento
import ProjetoPI.ProjetoDoadores.repository.AgendamentoRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/agendamentos")
class AgendamentoController(var repository: AgendamentoRepository) {
    @GetMapping
    fun listarAgendamentos(): ResponseEntity<List<Agendamento>> {
        val listaAgendamentos = repository.findAll()

        if(listaAgendamentos.isEmpty()) {
            return ResponseEntity.status(204).build()
        }
        return ResponseEntity.status(200).body(listaAgendamentos)
    }
}