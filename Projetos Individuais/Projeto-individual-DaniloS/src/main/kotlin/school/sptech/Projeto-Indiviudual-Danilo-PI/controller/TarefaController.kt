package school.sptech.projetoestoque.controller

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import school.sptech.projetoestoque.dominio.Tarefa
import school.sptech.projetoestoque.repository.TarefaRepository
import school.sptech.projetoestoque.service.TarefaService

@RestController
@RequestMapping("/tarefa")
class TarefaController(
    val tarefaService: TarefaService,
    val tarefaRepository: TarefaRepository
) {

    @PostMapping
    fun criar(@RequestBody @Valid novaTarefa: Tarefa) :
            ResponseEntity<Tarefa> {

        val tarefaSalva = tarefaRepository.save(novaTarefa)
        return ResponseEntity.status(201).body(tarefaSalva)
    }

    @GetMapping
    fun listarTarefas(): List<Tarefa> = tarefaService.listarTarefas()


    @GetMapping("/sistema/{sistemaId}")
    fun listarTarefasPorSistema(@PathVariable sistemaId: Int): List<Tarefa> = tarefaService.listarTarefasPorSistema(sistemaId)


    @PostMapping("/sistema/{sistemaId}")
    fun adicionarTarefa(@RequestBody tarefa: Tarefa, @PathVariable sistemaId: Int): Tarefa =
        tarefaService.adicionarTarefa(tarefa, sistemaId)


    @PutMapping("/{id}")
    fun atualizarTarefa(@PathVariable id: Int, @RequestBody tarefa: Tarefa): Tarefa =
        tarefaService.atualizarTarefa(id, tarefa)


    @DeleteMapping("/{id}")
    fun excluirTarefa(@PathVariable id: Int) = tarefaService.excluirTarefa(id)
}

