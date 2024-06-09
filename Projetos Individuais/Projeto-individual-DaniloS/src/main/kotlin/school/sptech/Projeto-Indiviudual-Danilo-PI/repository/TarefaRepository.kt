package school.sptech.projetoestoque.repository

import org.springframework.data.jpa.repository.JpaRepository
import school.sptech.projetoestoque.dominio.Tarefa

interface TarefaRepository: JpaRepository<Tarefa, Int> {


    fun findBySistemaId(sistemaId: Int): List<Tarefa>
}