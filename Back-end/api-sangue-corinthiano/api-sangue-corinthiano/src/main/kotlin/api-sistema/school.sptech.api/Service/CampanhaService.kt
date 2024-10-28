package ProjetoPI.ProjetoDoadores.repository

import ProjetoPI.ProjetoDoadores.controller.CampanhaDto
import ProjetoPI.ProjetoDoadores.domain.Campanha
import ProjetoPI.ProjetoDoadores.domain.EnderecoInstituicao
import ProjetoPI.ProjetoDoadores.domain.Instituicao
import ProjetoPI.ProjetoDoadores.domain.InstituicaoEnderecoDto
import `api-sistema`.school.sptech.api.dto.EnderecoInstituicaoDto
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class CampanhaService(
    val repository: CampanhaRepository
) {

    fun cadastrar(novaCampanha: Campanha): Campanha {
        return repository.save(novaCampanha)
    }

    fun validarExistencia(idEnderecoInstituicao: Int){
        if(!repository.existsById(idEnderecoInstituicao)){
            throw ResponseStatusException(
                HttpStatusCode.valueOf(404), "Endereço da Instituição não encontrado."
            )
        }
    }

    fun transformarEmDto(enderecoInstituicao: Campanha): CampanhaDto{

        val enderecoInstituicaoDto = CampanhaDto(
            enderecoInstituicao.idCampanha,
            enderecoInstituicao.nome,
            enderecoInstituicao.dtInicio,
            enderecoInstituicao.dtFim,
            enderecoInstituicao.idInstituicao,
            enderecoInstituicao.idAdmin
        )
        return enderecoInstituicaoDto
    }


    fun buscarUm(idEnderecoInstituicao: Int): CampanhaDto {
        validarExistencia(idEnderecoInstituicao)

        val enderecoInstituicao = repository.findByIdCampanha(idEnderecoInstituicao)
        return transformarEmDto(enderecoInstituicao)
    }

    fun atualizar(idCampanha: Int, novaCampanha: Campanha): Campanha {
        novaCampanha.idCampanha = idCampanha
        return repository.save(novaCampanha)
    }
}