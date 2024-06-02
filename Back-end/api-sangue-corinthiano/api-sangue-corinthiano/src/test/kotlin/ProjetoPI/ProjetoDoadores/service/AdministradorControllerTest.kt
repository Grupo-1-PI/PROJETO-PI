package ProjetoPI.ProjetoDoadores.service

import ProjetoPI.ProjetoDoadores.controller.AdministradorController
import ProjetoPI.ProjetoDoadores.domain.Administrador
import ProjetoPI.ProjetoDoadores.repository.AdministradorRepository
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
class AdministradorControllerTest {

    @Mock
    lateinit var repository: AdministradorRepository

    @InjectMocks
    lateinit var controller: AdministradorController

    @Test
    fun `test cadastrarAdministrador - sucesso`() {
        val novoAdministrador = Administrador(1, "nome", "1487307840", "email@teste.com", "12324asda", 1,1, LocalDate.now())
        Mockito.`when`(repository.findByEmail("email@teste.com")).thenReturn(null)
        Mockito.`when`(repository.save(novoAdministrador)).thenReturn(novoAdministrador)

        val response = controller.cadastrarAdministrador(novoAdministrador)

        assert(response.statusCode == HttpStatus.CREATED)
        assert(response.body == "Cadastro realizado com sucesso!")
    }

    @Test
    fun `test cadastrarAdministrador - email ja cadastrado`() {
        val novoAdministrador = Administrador(1, "nome", "1487307840", "email@teste.com", "12324asda", 1,1, LocalDate.now())
        Mockito.`when`(repository.findByEmail("email@teste.com")).thenReturn(null)

        val response = controller.cadastrarAdministrador(novoAdministrador)

        assert(response.statusCode == HttpStatus.BAD_REQUEST)
        assert(response.body == "Email já cadastrado!")
    }

    @Test
    fun `test listarAdministradores - sucesso`() {
        val administrador1 = Administrador(1, "nome", "1487307840", "email@teste.com", "12324asda", 1,1, LocalDate.now(),"F")
        val administrador2 = Administrador(1, "nome", "1487307840", "email@teste.com", "12324asda", 1,1, LocalDate.now(),"M")
        Mockito.`when`(repository.findAll()).thenReturn(listOf(administrador1, administrador2))

        val response = controller.listarAdministradores()

        assert(response.statusCode == HttpStatus.OK)
        assert(response.body?.size == 2)
    }

    @Test
    fun `test listarAdministradores - sem conteudo`() {
        Mockito.`when`(repository.findAll()).thenReturn(emptyList())

        val response = controller.listarAdministradores()

        assert(response.statusCode == HttpStatus.NO_CONTENT)
    }

    @Test
    fun `test listarAdministradorPorId - sucesso`() {
        val administrador = Administrador(1, "nome", "1487307840", "email@teste.com", "12324asda", 1,1, LocalDate.now())
        Mockito.`when`(repository.existsById(1)).thenReturn(true)
        Mockito.`when`(repository.findById(1)).thenReturn(Optional.of(administrador))

        val response = controller.listarAdministradorPorId(1)

        assert(response.statusCode == HttpStatus.OK)
        assert(response.body == administrador)
    }

    @Test
    fun `test listarAdministradorPorId - nao encontrado`() {
        Mockito.`when`(repository.existsById(1)).thenReturn(false)

        val response = controller.listarAdministradorPorId(1)

        assert(response.statusCode == HttpStatus.NOT_FOUND)
    }

    @Test
    fun `test atualizarAdministrador - sucesso`() {
        val administradorAtual = Administrador(1, "nome", "1487307840", "email@teste.com", "12324asda", 1,1, LocalDate.now())
        val novoAdministrador = Administrador(1, "nome", "1487307840", "email@teste.com", "12324asda", 1,1, LocalDate.now())
        Mockito.`when`(repository.existsById(1)).thenReturn(true)
        Mockito.`when`(repository.findById(1)).thenReturn(Optional.of(administradorAtual))
        Mockito.`when`(repository.save(administradorAtual)).thenReturn(administradorAtual)

        val response = controller.atualizarAdministrador(1, novoAdministrador)

        assert(response.statusCode == HttpStatus.OK)
        assert(response.body?.nome == "novo nome")
        assert(response.body?.email == "novo_email@test.com")
    }

    @Test
    fun `test atualizarAdministrador - nao encontrado`() {
        val novoAdministrador = Administrador(1, "nome", "1487307840", "email@teste.com", "12324asda", 1,1, LocalDate.now())
        Mockito.`when`(repository.existsById(1)).thenReturn(false)

        val response = controller.atualizarAdministrador(1, novoAdministrador)

        assert(response.statusCode == HttpStatus.NOT_FOUND)
    }

    @Test
    fun `test deletarAdministrador - sucesso`() {
        Mockito.`when`(repository.existsById(1)).thenReturn(true)
        Mockito.doNothing().`when`(repository).deleteById(1)

        val response = controller.deletarAdministrador(1)

        assert(response.statusCode == HttpStatus.OK)
        assert(response.body == "Administrador deletado com sucesso!")
    }

    @Test
    fun `test deletarAdministrador - nao encontrado`() {
        Mockito.`when`(repository.existsById(1)).thenReturn(false)

        val response = controller.deletarAdministrador(1)

        assert(response.statusCode == HttpStatus.NOT_FOUND)
    }

    @Test
    fun `test login - sucesso`() {
        val administrador = Administrador(1, "nome", "1487307840", "email@teste.com", "12324asda", 1,1, LocalDate.now())
        Mockito.`when`(repository.findByEmailAndSenha("email@test.com", "senha")).thenReturn(administrador)

        val response = controller.login("email@test.com", "senha")

        assert(response.statusCode == HttpStatus.OK)
        assert(response.body == "Login realizado com sucesso!")
    }

    @Test
    fun `test login - invalido`() {
        Mockito.`when`(repository.findByEmailAndSenha("email@test.com", "senha")).thenReturn(null)

        val response = controller.login("email@test.com", "senha")

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
