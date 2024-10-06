package ProjetoPI.ProjetoDoadores.repository

import ProjetoPI.ProjetoDoadores.domain.Instituicao
import ProjetoPI.ProjetoDoadores.dto.InstituicaoDto
import org.springframework.stereotype.Service


@Service
class InstituicaoService(
    val repository: InstituicaoRepository
) {

    fun cadastrar(novaInstituicao: Instituicao): Instituicao{
       return repository.save(novaInstituicao)
    }

    fun atualizar(idInstituicao: Int, novaInstituicao: Instituicao): Instituicao{
        novaInstituicao.idInstituicao = idInstituicao
        return repository.save(novaInstituicao)
    }

    // Função para buscar as Instituições por tipo ("Sangue Corinthiano" ou "Instituição Parceira")
    fun getInstituicoesPorTipo(tipo: String): List<InstituicaoDto> {
        val instituicoes = repository.findByTipoInstituicao(tipo)
        return instituicoes.map { instituicao ->
            InstituicaoDto(
                nome = instituicao.nome,
                latitude = instituicao.latitude,  // Acesso correto para latitude
                longitude = instituicao.longitude  // Acesso correto para longitude
            )
        }
    }
}
