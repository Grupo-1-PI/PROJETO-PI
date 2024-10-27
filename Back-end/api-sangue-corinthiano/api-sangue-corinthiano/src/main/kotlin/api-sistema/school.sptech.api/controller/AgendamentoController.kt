package ProjetoPI.ProjetoDoadores.controller

import ProjetoPI.ProjetoDoadores.domain.Agendamento
import ProjetoPI.ProjetoDoadores.repository.AgendamentoRepository
import ProjetoPI.ProjetoDoadores.repository.HistoricoAgendamentoRepository
import `api-sistema`.school.sptech.api.dto.DoadorAgendamentoDTO


import jakarta.validation.Valid
import org.modelmapper.ModelMapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalTime
import kotlin.text.get

@CrossOrigin(origins = ["http://127.0.0.1:5500"])
@RestController
@RequestMapping("/agendamentos")
class AgendamentoController(
    var repository: AgendamentoRepository,
    var historicoAgendamentoRepository: HistoricoAgendamentoRepository,
    var modelMapper: ModelMapper = ModelMapper()
) {
    @GetMapping
    fun listarAgendamentos(): ResponseEntity<List<Agendamento>> {
        val listaAgendamentos = repository.findAll()

        if (listaAgendamentos.isEmpty()) {
            return ResponseEntity.status(204).build()
        }
        return ResponseEntity.status(200).body(listaAgendamentos)
    }


    @PostMapping("/cadastrarAgendamento")
    fun criarAgendamento(@RequestBody @Valid novoAgendamentoRequest: Agendamento): ResponseEntity<Agendamento> {

        val novoAgendamento = Agendamento(
            data = novoAgendamentoRequest.data,
            hora = novoAgendamentoRequest.hora,
            cpfDoador = novoAgendamentoRequest.cpfDoador,
            idDoador = novoAgendamentoRequest.idDoador,
            idInstituicao = novoAgendamentoRequest.idInstituicao,
            idCampanha = novoAgendamentoRequest.idCampanha,
        )
        val agendamentoSalvo = repository.save(novoAgendamento)

        return ResponseEntity.status(201).body(agendamentoSalvo)
    }

    //deleta do historico primeiro para conseguir deletar o agendamento
    @DeleteMapping("/{id}")
    fun cancelarAgendamento(@PathVariable id: Int): ResponseEntity<String> {
        return if (repository.existsById(id)) {
            try {
                // Tente deletar o histórico
                historicoAgendamentoRepository.deleteById(id)
                // Em seguida, delete o agendamento
                repository.deleteById(id)
                ResponseEntity.ok("Agendamento e histórico cancelados com sucesso!")
            } catch (e: Exception) {
                ResponseEntity.status(500).body("Erro ao cancelar o agendamento: ${e.message}")
            }
        } else {
            ResponseEntity.status(404).body("Agendamento não encontrado.")
        }
    }
    

    @GetMapping("/doadoresAgendamento")
    fun getDoadoresAgendamento(): List<DoadorAgendamentoDTO> {
        val result = repository.getDoadoresAgendamentos()

        return result.map { map ->
            val horaSql = map["hora"] as java.sql.Time
            val hora = horaSql.toLocalTime()
            val nome = map["nome"] as String
            DoadorAgendamentoDTO(hora = hora, nome = nome)
        }
    }

    @GetMapping("/doadoresTotalPorCampanha")
    fun getTotalDoadoresAgendamentos(): ResponseEntity<Int> {
        val listaAgendamentos = repository.getTotalDoadoresPorCampanha()

        if (listaAgendamentos == 0) {
            return ResponseEntity.status(204).build()
        }
        return ResponseEntity.status(200).body(listaAgendamentos)
    }



}