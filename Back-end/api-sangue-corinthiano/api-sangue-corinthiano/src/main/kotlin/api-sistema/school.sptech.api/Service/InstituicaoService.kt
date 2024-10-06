package ProjetoPI.ProjetoDoadores.repository

import ProjetoPI.ProjetoDoadores.domain.Instituicao
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


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



}