package ProjetoPI.ProjetoDoadores.controller

import ProjetoPI.ProjetoDoadores.domain.Agendamento
import ProjetoPI.ProjetoDoadores.repository.AgendamentoRepository
import ProjetoPI.ProjetoDoadores.repository.HistoricoAgendamentoRepository


import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
@CrossOrigin(origins = ["http://127.0.0.1:5500"])
@RestController
@RequestMapping("/agendamentos")
class AgendamentoController(var repository: AgendamentoRepository,
var historicoAgendamentoRepository: HistoricoAgendamentoRepository) {
    @GetMapping
    fun listarAgendamentos(): ResponseEntity<List<Agendamento>> {
        val listaAgendamentos = repository.findAll()

        if(listaAgendamentos.isEmpty()) {
            return ResponseEntity.status(204).build()
        }
        return ResponseEntity.status(200).body(listaAgendamentos)
    }


    @PostMapping("/cadastrarAgendamento")
    fun criarAgendamento(@RequestBody @Valid novoAgendamentoRequest: Agendamento): ResponseEntity<String> {
        return try {
            val novoAgendamento = Agendamento(
                data = novoAgendamentoRequest.data,
                hora = novoAgendamentoRequest.hora,
                cpfDoador = novoAgendamentoRequest.cpfDoador,
                idDoador = novoAgendamentoRequest.idDoador,
                idInstituicao = novoAgendamentoRequest.idInstituicao,
                idCampanha = novoAgendamentoRequest.idCampanha,
            )
            val agendamentoSalvo = repository.save(novoAgendamento)

            return ResponseEntity.status(201).body("Agendamento realizado com sucesso!")
        } catch (ex: Exception) {
            ResponseEntity.status(500).body("Erro ao realizar cadastro: ${ex.message}")
        }
    }
    //deleta do historico primeiro para conseguir deletar o agendamento
  @DeleteMapping("/{id}")
   fun cancelarAgendamento(@PathVariable id: Int): ResponseEntity<String>{
      return if (repository.existsById(id)) {

          historicoAgendamentoRepository.deleteById(id)

          // Depois, excluir o próprio agendamento
          repository.deleteById(id)

          ResponseEntity.status(200).body("Agendamento e histórico cancelados com sucesso!")
      } else {
          ResponseEntity.status(404).build()
      }
    }
}