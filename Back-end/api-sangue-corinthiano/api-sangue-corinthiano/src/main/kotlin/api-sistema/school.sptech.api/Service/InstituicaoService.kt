package ProjetoPI.ProjetoDoadores.service

import ProjetoPI.ProjetoDoadores.domain.Instituicao
import ProjetoPI.ProjetoDoadores.dto.InstituicaoDto
import ProjetoPI.ProjetoDoadores.repository.InstituicaoRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class InstituicaoService(
    private val repository: InstituicaoRepository
) {

    fun validarExistencia(idInstituicao: Int) {
        if (!repository.existsById(idInstituicao)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Instituição não encontrada.")
        }
    }

    fun cadastrar(novaInstituicao: Instituicao): Instituicao {
        return repository.save(novaInstituicao)
    }

    fun atualizar(idInstituicao: Int, novaInstituicao: Instituicao): Instituicao {
        val instituicao = repository.findById(idInstituicao)
        if (instituicao.isPresent) {
            val instituicaoAtualizada = instituicao.get().copy(
                nome = novaInstituicao.nome,
                cnpj = novaInstituicao.cnpj,
                parceiro = novaInstituicao.parceiro,
                tipoInstituicao = novaInstituicao.tipoInstituicao
            )
            return repository.save(instituicaoAtualizada)
        }
        throw RuntimeException("Instituição não encontrada")
    }


    fun getInstituicoesPorTipo(tipoInstituicao: String): List<InstituicaoDto> {
        return repository.findByTipoInstituicao(tipoInstituicao)
    }

    fun getAllInstituicoes(): List<InstituicaoDto> {
        return repository.findAll().map {
            InstituicaoDto(
                idInstituicao = it.idInstituicao ?: 0,
                nome = it.nome ?: ""
            )
        }
    }

    fun buscarInstituicaoPorNome(nomeInstituicao: String): InstituicaoDto? {
        val instituicao = repository.findByNome(nomeInstituicao)
        return instituicao?.let {
            InstituicaoDto(
                idInstituicao = it.idInstituicao ?: 0,
                nome = it.nome ?: ""
            )
        }
    }

}
