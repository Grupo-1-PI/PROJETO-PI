package ProjetoPI.ProjetoDoadores.service

import ProjetoPI.ProjetoDoadores.controller.DoadorController
import ProjetoPI.ProjetoDoadores.domain.Doador
import ProjetoPI.ProjetoDoadores.repository.DoadorRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import java.time.LocalDate
import java.util.*

@ExtendWith(MockitoExtension::class)
class DoadorControllerTest {

	@Mock
	lateinit var repository: DoadorRepository

	@InjectMocks
	lateinit var controller: DoadorController

	@Test
	fun `test cadastrarDoador - sucesso`() {
		val novoDoador = Doador(1, "nome", "email@test.com",LocalDate.of(1990, 1, 1), "M", true, "senha", "motivo")
		Mockito.`when`(repository.findByEmail("email@test.com")).thenReturn(null)
		Mockito.`when`(repository.save(novoDoador)).thenReturn(novoDoador)

		val response = controller.cadastrarDoador(novoDoador)

		assert(response.statusCode == HttpStatus.CREATED)
		assert(response.body == "Cadastro realizado com sucesso!")
	}

	@Test
	fun `test cadastrarDoador - email ja cadastrado`() {
		val novoDoador = Doador(1, "nome", "email@test.com",LocalDate.of(1990, 1, 1), "M", true, "senha", "motivo")
		Mockito.`when`(repository.findByEmail("email@test.com")).thenReturn(novoDoador)

		val response = controller.cadastrarDoador(novoDoador)

		assert(response.statusCode == HttpStatus.BAD_REQUEST)
		assert(response.body == "Email já cadastrado!")
	}

	@Test
	fun `test listarDoadores - sucesso`() {
		val doador1 = Doador(1, "nome1", "email1@test.com",  LocalDate.of(1990, 1, 1), "M", true, "senha", "motivo")
		val doador2 = Doador(2, "nome2", "email2@test.com", LocalDate.of(1990, 1, 1), "F", false, "senha", "motivo")
		Mockito.`when`(repository.findAll()).thenReturn(listOf(doador1, doador2))

		val response = controller.listarDoadores()

		assert(response.statusCode == HttpStatus.OK)
		assert(response.body?.size == 2)
	}

	@Test
	fun `test listarDoadores - sem conteudo`() {
		Mockito.`when`(repository.findAll()).thenReturn(emptyList())

		val response = controller.listarDoadores()

		assert(response.statusCode == HttpStatus.NO_CONTENT)
	}

	@Test
	fun `test listarDoadorPorId - sucesso`() {
		val doador = Doador(1, "nome", "email@test.com",  LocalDate.of(1990, 1, 1), "M", true, "senha", "motivo")
		Mockito.`when`(repository.existsById(1)).thenReturn(true)
		Mockito.`when`(repository.findById(1)).thenReturn(Optional.of(doador))

		val response = controller.listarDoadorPorId(1)

		assert(response.statusCode == HttpStatus.OK)
		assert(response.body == doador)
	}

	@Test
	fun `test listarDoadorPorId - nao encontrado`() {
		Mockito.`when`(repository.existsById(1)).thenReturn(false)

		val response = controller.listarDoadorPorId(1)

		assert(response.statusCode == HttpStatus.NOT_FOUND)
	}

	@Test
	fun `test atualizarDoador - sucesso`() {
		val doadorAtual = Doador(1, "nome", "email@test.com", LocalDate.of(1990, 1, 1), "M", true, "senha", "motivo")
		val novoDoador = Doador(1, "novo nome", "novo_email@test.com",  LocalDate.of(1990, 1, 1), "F", false, "nova senha", "novo motivo")
		Mockito.`when`(repository.existsById(1)).thenReturn(true)
		Mockito.`when`(repository.findById(1)).thenReturn(Optional.of(doadorAtual))
		Mockito.`when`(repository.save(doadorAtual)).thenReturn(doadorAtual)

		val response = controller.atualizarDoador(1, novoDoador)

		assert(response.statusCode == HttpStatus.OK)
		assert(response.body?.nome == "novo nome")
		assert(response.body?.email == "novo_email@test.com")
	}

	@Test
	fun `test atualizarDoador - nao encontrado`() {
		val novoDoador = Doador(1, "novo nome", "novo_email@test.com",  LocalDate.of(1990, 1, 1), "F", false, "nova senha", "novo motivo")
		Mockito.`when`(repository.existsById(1)).thenReturn(false)

		val response = controller.atualizarDoador(1, novoDoador)

		assert(response.statusCode == HttpStatus.NOT_FOUND)
	}

	@Test
	fun `test deletarDoador - sucesso`() {
		Mockito.`when`(repository.existsById(1)).thenReturn(true)
		Mockito.doNothing().`when`(repository).deleteById(1)

		val response = controller.deletarDoador(1)

		assert(response.statusCode == HttpStatus.OK)
		assert(response.body == "Doador deletado com sucesso!")
	}

	@Test
	fun `test deletarDoador - nao encontrado`() {
		Mockito.`when`(repository.existsById(1)).thenReturn(false)

		val response = controller.deletarDoador(1)

		assert(response.statusCode == HttpStatus.NOT_FOUND)
	}

	@Test
	fun `test login - sucesso`() {
		val doador = Doador(1, "nome", "email@test.com",  LocalDate.of(1990, 1, 1), "M", true, "senha", "motivo")
		Mockito.`when`(repository.findByEmailAndSenha("email@test.com", "senha")).thenReturn(doador)

		val response = controller.login2("email@test.com", "senha")

		assert(response.statusCode == HttpStatus.OK)
		assert(response.body == "Login realizado com sucesso!")
	}

	@Test
	fun `test login - invalido`() {
		Mockito.`when`(repository.findByEmailAndSenha("email@test.com", "senha")).thenReturn(null)

		val response = controller.login2("email@test.com", "senha")

		assert(response.statusCode == HttpStatus.BAD_REQUEST)
		assert(response.body == "Login inválido!")
	}

	@Test
	fun `test logout - sucesso`() {
		val response = controller.logout()

		assert(response.statusCode == HttpStatus.OK)
		assert(response.body == "Logout realizado com sucesso!")
	}
}
