package ProjetoPI.ProjetoDoadores.controller

import ProjetoPI.ProjetoDoadores.repository.VwTaxaCancelamentoAgendamentoRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/taxa-cancelamento")
class VwTaxaCancelamentoAgendamentoController(val repository: VwTaxaCancelamentoAgendamentoRepository) {
    @GetMapping
    fun getTaxaCancelamento(): Double? {
        return repository.findAll().firstOrNull()?.taxaCancelamento
    }

}