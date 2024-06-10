package school.sptech.projetoestoque.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.*
import school.sptech.projetoestoque.dominio.StatusTarefa
import school.sptech.projetoestoque.dominio.Tarefa
import school.sptech.projetoestoque.repository.SistemaRepository
import school.sptech.projetoestoque.repository.TarefaRepository
import java.time.LocalDate

class TarefaServiceTest{

    lateinit var tarefaRepository: TarefaRepository
    lateinit var sistemaRepository: SistemaRepository
    lateinit var tarefaService: TarefaService


    @BeforeEach
    fun iniciar() {
        tarefaRepository = mock(TarefaRepository::class.java)
        sistemaRepository = mock(SistemaRepository::class.java)
        tarefaService = TarefaService(tarefaRepository, sistemaRepository)
    }

    @Test
    fun `deve listar todas as tarefas`() {
        val tarefas = listOf(
            Tarefa(titulo = "Tarefa 1", descricao = "Descrição 1", dataVencimento = LocalDate.now(), status = StatusTarefa.PENDENTE),
            Tarefa(titulo = "Tarefa 2", descricao = "Descrição 2", dataVencimento = LocalDate.now(), status = StatusTarefa.PENDENTE)
        )

        `when`(tarefaRepository.findAll()).thenReturn(tarefas)

        val result = tarefaService.listarTarefas()

        assertEquals(2, result.size)
        assertEquals("Tarefa 1", result[0].titulo)
        assertEquals("Tarefa 2", result[1].titulo)
    }

    @Test
    fun `deve listar todas as tarefas por sistema`() {
        val tarefas = listOf(
            Tarefa(titulo = "Tarefa 1", descricao = "Descrição 1", dataVencimento = LocalDate.now(), status = StatusTarefa.PENDENTE)
        )

        `when`(tarefaRepository.findBySistemaId(1)).thenReturn(tarefas)

        val result = tarefaService.listarTarefasPorSistema(1)

        assertEquals(1, result.size)
        assertEquals("Tarefa 1", result[0].titulo)
    }

    @Test
    fun `deve adicionar uma nova tarefa`() {
        val tarefa = Tarefa(titulo = "Nova Tarefa", descricao = "Descrição", dataVencimento = LocalDate.now(), status = StatusTarefa.PENDENTE)

        `when`(sistemaRepository.existsById(1)).thenReturn(true)
        `when`(tarefaRepository.save(tarefa)).thenReturn(tarefa.copy(id = 1))

        val result = tarefaService.adicionarTarefa(tarefa, 1)

        assertEquals(tarefa.copy(id = 1), result)
    }

    @Test
    fun `deve lançar exceção ao tentar adicionar uma tarefa a um sistema inexistente`() {
        val tarefa = Tarefa(titulo = "Nova Tarefa", descricao = "Descrição", dataVencimento = LocalDate.now(), status = StatusTarefa.PENDENTE)

        `when`(sistemaRepository.existsById(1)).thenReturn(false)

        val exception = assertThrows<RuntimeException> {
            tarefaService.adicionarTarefa(tarefa, 1)
        }

        assertEquals("Sistema não encontrado", exception.message)
    }

    @Test
    fun `deve atualizar uma tarefa existente`() {
        val tarefa = Tarefa(id = 1, titulo = "Tarefa Atualizada", descricao = "Descrição", dataVencimento = LocalDate.now(), status = StatusTarefa.PENDENTE)

        `when`(tarefaRepository.existsById(1)).thenReturn(true)
        `when`(tarefaRepository.save(tarefa)).thenReturn(tarefa)

        val result = tarefaService.atualizarTarefa(1, tarefa)

        assertEquals(tarefa, result)
    }

    @Test
    fun `deve lançar exceção ao tentar atualizar uma tarefa inexistente`() {
        val tarefa = Tarefa(id = 1, titulo = "Tarefa Atualizada", descricao = "Descrição", dataVencimento = LocalDate.now(), status = StatusTarefa.PENDENTE)

        `when`(tarefaRepository.existsById(1)).thenReturn(false)

        val exception = assertThrows<RuntimeException> {
            tarefaService.atualizarTarefa(1, tarefa)
        }

        assertEquals("Tarefa não encontrada", exception.message)
    }

    @Test
    fun `deve excluir uma tarefa existente`() {
        `when`(tarefaRepository.existsById(1)).thenReturn(true)
        doNothing().`when`(tarefaRepository).deleteById(1)

        tarefaService.excluirTarefa(1)

        verify(tarefaRepository).deleteById(1)
    }

}