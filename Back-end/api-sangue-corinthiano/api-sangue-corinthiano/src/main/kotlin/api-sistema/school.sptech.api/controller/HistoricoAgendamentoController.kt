package ProjetoPI.ProjetoDoadores.controller

import ProjetoPI.ProjetoDoadores.repository.HistoricoAgendamento
import ProjetoPI.ProjetoDoadores.repository.HistoricoAgendamentoRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/historico-agendamentos")
 class HistoricoAgendamentoController(
    val historicoAgendamentoRepository: HistoricoAgendamentoRepository
) {
    @GetMapping
    fun buscaHistoricoAgendamento(): ResponseEntity<List<HistoricoAgendamento>> {
        val historico = historicoAgendamentoRepository.findAll()
        if(historico.isEmpty()) return ResponseEntity.status(204).build()
        return ResponseEntity.status(200).body(historico)
    }
    //caso queira deletar somente do historico utilizar esse endpoint
    @DeleteMapping("/{id}")
    fun deleteHistorico(@PathVariable id: Int): ResponseEntity<String> {
        // lógica para deletar o histórico
        return if (historicoAgendamentoRepository.existsById(id)) {
            historicoAgendamentoRepository.deleteById(id)
            ResponseEntity.ok("Histórico deletado com sucesso")
        } else {
            ResponseEntity.status(404).body("Histórico não encontrado")
        }
    }
}
