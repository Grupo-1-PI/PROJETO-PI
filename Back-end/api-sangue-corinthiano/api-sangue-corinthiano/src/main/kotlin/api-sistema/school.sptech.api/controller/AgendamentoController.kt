package ProjetoPI.ProjetoDoadores.controller

import ProjetoPI.ProjetoDoadores.domain.Agendamento
import ProjetoPI.ProjetoDoadores.repository.AgendamentoRepository
import ProjetoPI.ProjetoDoadores.repository.HistoricoAgendamentoRepository
import `api-sistema`.school.sptech.api.dto.DoadorAgendamentoDTO


import jakarta.validation.Valid
import org.modelmapper.ModelMapper
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import java.time.LocalTime

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

    @DeleteMapping("/cancelarAgendamento/{idDoador}/{idAgendamento}")
    @Transactional
    fun cancelarAgendamento(
        @PathVariable idDoador: Int,
        @PathVariable idAgendamento: Int
    ): ResponseEntity<String> {
        return try {
            val rowsUpdated = repository.cancelarAgendamentoPorDoador(idDoador, idAgendamento)
            if (rowsUpdated > 0) {
                ResponseEntity.ok("Agendamento cancelado com sucesso.")
            } else {
                ResponseEntity.status(404).body("Agendamento não encontrado para o doador especificado.")
            }
        } catch (e: Exception) {
            ResponseEntity.status(500).body("Erro ao cancelar o agendamento.")
        }
    }

}

