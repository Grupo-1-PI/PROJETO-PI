package school.sptech.projetoestoque.service

import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import school.sptech.projetoestoque.dominio.Tarefa
import school.sptech.projetoestoque.repository.SistemaRepository
import school.sptech.projetoestoque.repository.TarefaRepository

@Service
class TarefaService(
    val tarefaRepository: TarefaRepository,
    val sistemaRepository: SistemaRepository)
{
    fun validarLista(lista:List<*>) {
        if (lista.isEmpty()) {
            throw ResponseStatusException(HttpStatusCode.valueOf(204))
        }
    }

    fun listarTarefas(): List<Tarefa> = tarefaRepository.findAll()

    fun listarTarefasPorSistema(sistemaId: Int): List<Tarefa> {
        val lista = tarefaRepository.findBySistemaId(sistemaId)
        validarLista(lista)

        return lista
    }

    fun adicionarTarefa(tarefa: Tarefa, sistemaId: Int): Tarefa {
        if (!sistemaRepository.existsById(sistemaId)) {
            throw RuntimeException("Sistema não encontrado")
        }
        return tarefaRepository.save(tarefa)
    }

    fun atualizarTarefa(id: Int, tarefa: Tarefa): Tarefa {

        if (tarefaRepository.existsById(id)) {
            return tarefaRepository.save(tarefa.copy(id = id))
        }
        else {
            throw RuntimeException("Tarefa não encontrada")
        }
    }

    fun excluirTarefa(id: Int) {

        if (tarefaRepository.existsById(id)) {
            tarefaRepository.deleteById(id)
        }
        else {
            throw RuntimeException("Tarefa não encontrada")
        }
    }
}