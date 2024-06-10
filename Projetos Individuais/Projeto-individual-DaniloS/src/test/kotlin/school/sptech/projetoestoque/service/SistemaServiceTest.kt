package school.sptech.projetoestoque.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import school.sptech.projetoestoque.dominio.Sistema
import school.sptech.projetoestoque.repository.SistemaRepository
import school.sptech.projetoestoque.repository.TarefaRepository

@ExtendWith(MockitoExtension::class)
class SistemaServiceTest{
    @Mock
    private lateinit var sistemaRepository: SistemaRepository
    private lateinit var sistemaService: SistemaService

    @BeforeEach
    fun iniciar() {
        sistemaRepository = mock(sistemaRepository::class.java)
        sistemaService = SistemaService(sistemaRepository)
    }

    @Test
    fun `deve listar todos os sistemas`() {
        val sistemas = listOf(
            Sistema(nome = "Sistema 1"),
            Sistema(nome = "Sistema 2")
        )

        `when`(sistemaRepository.findAll()).thenReturn(sistemas)

        val result = sistemaService.listarSistemas()

        assertEquals(2, result.size)
        assertEquals("Sistema 1", result[0].nome)
        assertEquals("Sistema 2", result[1].nome)
    }

    @Test
    fun `deve adicionar um novo sistema`() {
        val sistema = Sistema(nome = "Novo Sistema")
        val sistemaSalvo = sistema.copy(id = 1)

        `when`(sistemaRepository.save(sistema)).thenReturn(sistemaSalvo)

        val result = sistemaService.adicionarSistema(sistema)

        assertEquals(sistemaSalvo, result)
    }

    @Test
    fun `deve atualizar um sistema existente`() {
        val sistema = Sistema(id = 1, nome = "Sistema Atualizado")

        `when`(sistemaRepository.existsById(1)).thenReturn(true)
        `when`(sistemaRepository.save(sistema)).thenReturn(sistema)

        val result = sistemaService.atualizarSistema(1, sistema)

        assertEquals(sistema, result)
    }

    @Test
    fun `deve lançar exceção ao tentar atualizar um sistema inexistente`() {
        val sistema = Sistema(id = 1, nome = "Sistema Atualizado")

        `when`(sistemaRepository.existsById(1)).thenReturn(false)

        val exception = assertThrows<RuntimeException> {
            sistemaService.atualizarSistema(1, sistema)
        }

        assertEquals("Sistema não encontrado", exception.message)
    }

    @Test
    fun `deve excluir um sistema existente`() {
        `when`(sistemaRepository.existsById(1)).thenReturn(true)
        doNothing().`when`(sistemaRepository).deleteById(1)

        sistemaService.excluirSistema(1)

        verify(sistemaRepository).deleteById(1)
    }

    @Test
    fun `deve lançar exceção ao tentar excluir um sistema inexistente`() {
        `when`(sistemaRepository.existsById(1)).thenReturn(false)

        val exception = assertThrows<RuntimeException> {
            sistemaService.excluirSistema(1)
        }

        assertEquals("Sistema não encontrado", exception.message)
    }
}