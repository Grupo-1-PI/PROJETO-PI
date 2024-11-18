package ProjetoPI.ProjetoDoadores.service

import ProjetoPI.ProjetoDoadores.domain.Instituicao
import ProjetoPI.ProjetoDoadores.dto.InstituicaoDto
import ProjetoPI.ProjetoDoadores.repository.InstituicaoRepository
import ProjetoPI.ProjetoDoadores.repository.InstituicaoService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.*
import org.springframework.web.server.ResponseStatusException

class InstituicaoServiceTest {

    private val repository: InstituicaoRepository = mock(InstituicaoRepository::class.java)
    private val service = InstituicaoService(repository)

    @Test
    fun `validarExistencia deve lançar exceção se a Instituicao não existir`() {

        `when`(repository.existsById(1)).thenReturn(false)


        val exception = assertThrows<ResponseStatusException> {
            service.validarExistencia(1)
        }


        assertEquals("Instituição não encontrada.", exception.reason)


        verify(repository).existsById(1)
    }

    @Test
    fun `validarExistencia não deve lançar exceção se a Instituicao existir`() {

        `when`(repository.existsById(1)).thenReturn(true)

        assertDoesNotThrow {
            service.validarExistencia(1)
        }


        verify(repository).existsById(1)
    }

    @Test
    fun `cadastrar deve salvar uma nova Instituicao`() {
        val instituicao = Instituicao(nome = "Instituição X", tipoInstituicao = "Sangue Corinthiano")


        `when`(repository.save(instituicao)).thenReturn(instituicao)


        val resultado = service.cadastrar(instituicao)

        assertEquals(instituicao, resultado)

        verify(repository).save(instituicao)
    }

    @Test
    fun `atualizar deve salvar a Instituicao com o ID especificado`() {
        val instituicao = Instituicao(nome = "Instituição Atualizada", tipoInstituicao = "Instituição Parceira")


        `when`(repository.save(instituicao)).thenReturn(instituicao)

        val resultado = service.atualizar(2, instituicao)

        assertEquals(2, resultado.idInstituicao)

        verify(repository).save(instituicao)
    }

}
