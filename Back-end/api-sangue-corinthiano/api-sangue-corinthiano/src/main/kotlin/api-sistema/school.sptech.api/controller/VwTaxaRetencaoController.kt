package ProjetoPI.ProjetoDoadores.controller

import ProjetoPI.ProjetoDoadores.repository.VwTaxaCancelamentoAgendamentoRepository
import ProjetoPI.ProjetoDoadores.repository.VwTaxaRetencaoRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/taxa-retencao")
class VwTaxaRetencaoController(val repository: VwTaxaRetencaoRepository) {
    @GetMapping
    fun getTaxaRetencao(): Double? {
        return repository.findAll().firstOrNull()?.taxaRetencao
    }

}