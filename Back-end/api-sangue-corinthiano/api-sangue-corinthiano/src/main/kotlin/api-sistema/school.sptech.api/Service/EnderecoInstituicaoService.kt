package ProjetoPI.ProjetoDoadores.repository

import org.springframework.stereotype.Service

@Service
class EnderecoInstituicaoService(
    val repository: EnderecoInstituicaoRepository
) {

    fun atualizar(idInstituicao: Int, enderecoInstituicao: EnderecoInstituicao): EnderecoInstituicao{
        enderecoInstituicao.idInstituicao!!.idInstituicao = idInstituicao
        return repository.save(enderecoInstituicao)
    }

    fun cadastrar(idInstituicao: Int, enderecoInstituicao: EnderecoInstituicao): EnderecoInstituicao{
        enderecoInstituicao.idInstituicao!!.idInstituicao = idInstituicao
        return repository.save(enderecoInstituicao)
    }
}