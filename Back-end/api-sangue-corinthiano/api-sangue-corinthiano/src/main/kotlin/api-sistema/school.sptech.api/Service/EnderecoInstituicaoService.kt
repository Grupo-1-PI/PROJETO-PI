package ProjetoPI.ProjetoDoadores.repository

import ProjetoPI.ProjetoDoadores.domain.EnderecoInstituicao
import org.springframework.stereotype.Service

@Service
class EnderecoInstituicaoService(
    val repository: EnderecoInstituicaoRepository
) {

    fun atualizar(idInstituicao: Int, enderecoInstituicao: EnderecoInstituicao): EnderecoInstituicao {
        // Atribui a instituição correta ao endereço antes de salvar
        enderecoInstituicao.instituicao!!.idInstituicao = idInstituicao
        return repository.save(enderecoInstituicao)
    }

    fun cadastrar(idInstituicao: Int, enderecoInstituicao: EnderecoInstituicao): EnderecoInstituicao {
        // Atribui a instituição correta ao endereço antes de salvar
        enderecoInstituicao.instituicao!!.idInstituicao = idInstituicao
        return repository.save(enderecoInstituicao)
    }
}
