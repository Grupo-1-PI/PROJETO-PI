package ProjetoPI.ProjetoDoadores.repository

import ProjetoPI.ProjetoDoadores.domain.Instituicao
import ProjetoPI.ProjetoDoadores.dto.InstituicaoDto
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class InstituicaoService(
    val repository: InstituicaoRepository
) {


    fun validarExistencia(idInstituicao: Int){
        if(!repository.existsById(idInstituicao)){
            throw ResponseStatusException(
                HttpStatusCode.valueOf(404), "Instituição não encontrada."
            )
        }
    }
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
