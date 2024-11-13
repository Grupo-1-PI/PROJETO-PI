import ProjetoPI.ProjetoDoadores.domain.Campanha
import ProjetoPI.ProjetoDoadores.repository.CampanhaRepository
import ProjetoPI.ProjetoDoadores.repository.CampanhaService
import org.junit.jupiter.api.Assertions.assertEquals

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import java.time.LocalDate


class DoadorControllerTest {

	private lateinit var campanhaService: CampanhaService
	private lateinit var campanhaRepository: CampanhaRepository

	@BeforeEach
	fun setUp() {
		campanhaRepository = mock(CampanhaRepository::class.java)
		campanhaService = CampanhaService(campanhaRepository)
	}

	@Test
	fun `test cadastrar campanha`() {
		val campanha = Campanha(idCampanha = 1, nome = "Campanha de Doação", dtInicio = LocalDate.now(), dtFim = LocalDate.now(), idInstituicao = 1, idAdmin = 1)
		`when`(campanhaRepository.save(campanha)).thenReturn(campanha)

		val resultado = campanhaService.cadastrar(campanha)

		assertEquals(campanha, resultado)
	}


	@Test
	fun `test transformarEmDto`() {
		val campanha = Campanha(idCampanha = 1, nome = "Campanha de Doação", dtInicio = LocalDate.now(), dtFim = LocalDate.now(), idInstituicao = 1, idAdmin = 1)

		val dto = campanhaService.transformarEmDto(campanha)

		assertEquals(campanha.idCampanha, dto.idCampanha)
		assertEquals(campanha.nome, dto.nome)
		assertEquals(campanha.dtInicio, dto.dtInicio)
		assertEquals(campanha.dtFim, dto.dtFim)
		assertEquals(campanha.idInstituicao, dto.idInstituicao)
		assertEquals(campanha.idAdmin, dto.idAdmin)
	}

	@Test
	fun `test buscarUm campanha`() {
		val campanha = Campanha(idCampanha = 1, nome = "Campanha de Doação", dtInicio = LocalDate.now(), dtFim = LocalDate.now(), idInstituicao = 1, idAdmin = 1)
		val idEnderecoInstituicao = 1
		`when`(campanhaRepository.existsById(idEnderecoInstituicao)).thenReturn(true)
		`when`(campanhaRepository.findByIdCampanha(idEnderecoInstituicao)).thenReturn(campanha)

		val dto = campanhaService.buscarUm(idEnderecoInstituicao)

		assertEquals(campanha.idCampanha, dto.idCampanha)
		assertEquals(campanha.nome, dto.nome)
	}

	@Test
	fun `test atualizar campanha`() {
		val campanhaExistente = Campanha(idCampanha = 1, nome = "Campanha Existente", dtInicio = LocalDate.now(), dtFim = LocalDate.now(), idInstituicao = 1, idAdmin = 1)
		val campanhaAtualizada = Campanha(idCampanha = 1, nome = "Campanha Atualizada", dtInicio = LocalDate.now(), dtFim = LocalDate.now(), idInstituicao = 1, idAdmin = 1)
		`when`(campanhaRepository.save(campanhaAtualizada)).thenReturn(campanhaAtualizada)

		val resultado = campanhaService.atualizar(1, campanhaAtualizada)

		assertEquals(campanhaAtualizada.idCampanha, resultado.idCampanha)
		assertEquals(campanhaAtualizada.nome, resultado.nome)
	}
}
