package ProjetoPI.ProjetoDoadores.repository

import ProjetoPI.ProjetoDoadores.domain.EnderecoInstituicao
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class EnderecoInstituicaoService(
    val repository: EnderecoInstituicaoRepository
) {

    fun validarSeAListaEstaVazia(lista: List<Any>){
        if(lista.isEmpty()){
            throw ResponseStatusException(
                HttpStatusCode.valueOf(204), "A lista de endereço de instituição encontra-se vazia."
            )
        }
    }

    fun atualizar(idInstituicao: Int, enderecoInstituicao: EnderecoInstituicao): EnderecoInstituicao{
        enderecoInstituicao.instituicao!!.idInstituicao = idInstituicao
        return repository.save(enderecoInstituicao)
    }

    fun cadastrar(idInstituicao: Int, enderecoInstituicao: EnderecoInstituicao): EnderecoInstituicao {
        // Atribui a instituição correta ao endereço antes de salvar
        enderecoInstituicao.instituicao!!.idInstituicao = idInstituicao
        return repository.save(enderecoInstituicao)
    }

    fun buscar(parceiro: Boolean): MutableList<EnderecoInstituicao> {
        val listaEnderecoInstituicao = repository.findByIdInstituicaoParceiroIs(parceiro)
        validarSeAListaEstaVazia(listaEnderecoInstituicao)
        return listaEnderecoInstituicao
    }
}
