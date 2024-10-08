package ProjetoPI.ProjetoDoadores.repository

import ProjetoPI.ProjetoDoadores.domain.EnderecoInstituicao
import ProjetoPI.ProjetoDoadores.domain.InstituicaoEnderecoDto
import `api-sistema`.school.sptech.api.dto.EnderecoInstituicaoDto
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class EnderecoInstituicaoService(
    val repository: EnderecoInstituicaoRepository,
    val instituicaoService: InstituicaoService
) {


    fun validarExistencia(idEnderecoInstituicao: Int){
        if(!repository.existsById(idEnderecoInstituicao)){
            throw ResponseStatusException(
                HttpStatusCode.valueOf(404), "Endereço da Instituição não encontrado."
            )
        }
    }


    fun transformarEmDto(enderecoInstituicao: EnderecoInstituicao): EnderecoInstituicaoDto{

        val enderecoInstituicaoDto = EnderecoInstituicaoDto(
                enderecoInstituicao.idEnderecoInstituicao,
                enderecoInstituicao.rua,
                enderecoInstituicao.numero,
                enderecoInstituicao.bairro,
                enderecoInstituicao.complemento,
                enderecoInstituicao.cidade,
                enderecoInstituicao.estado,
                enderecoInstituicao.cep,
                enderecoInstituicao.latitude,
                enderecoInstituicao.longitude,
                InstituicaoEnderecoDto(
                    enderecoInstituicao.instituicao!!.idInstituicao,
                    enderecoInstituicao.instituicao.nome,
                    enderecoInstituicao.instituicao.cnpj,
                    enderecoInstituicao.instituicao.parceiro,
                    enderecoInstituicao.instituicao.tipoInstituicao
                )
        )
        return enderecoInstituicaoDto
    }

    fun transformarListaEmDto(listaEndereco: List<EnderecoInstituicao>): List<EnderecoInstituicaoDto>{
        val listaEnderecoInstituicaoDto = mutableListOf<EnderecoInstituicaoDto>()

        for(i in 0..listaEndereco.size - 1){
            listaEnderecoInstituicaoDto.add(
                transformarEmDto(listaEndereco[i])
            )
        }

        return listaEnderecoInstituicaoDto
    }

    fun validarSeAListaEstaVazia(lista: List<Any>){
        if(lista.isEmpty()){
            throw ResponseStatusException(
                HttpStatusCode.valueOf(204), "A lista de endereço de instituição encontra-se vazia."
            )
        }
    }

    fun atualizar(idEnderecoInstituicao: Int, idInstituicao: Int, enderecoInstituicao: EnderecoInstituicao): EnderecoInstituicaoDto {
        instituicaoService.validarExistencia(idInstituicao)
        validarExistencia(idEnderecoInstituicao)


        enderecoInstituicao.instituicao!!.idInstituicao = idInstituicao
        enderecoInstituicao.idEnderecoInstituicao = idEnderecoInstituicao
        val enderecoInstituicaoRetorno = repository.save(enderecoInstituicao)
        return transformarEmDto(enderecoInstituicaoRetorno)
    }

    fun cadastrar(idInstituicao: Int, enderecoInstituicao: EnderecoInstituicao): EnderecoInstituicaoDto {
        instituicaoService.validarExistencia(idInstituicao)

        val enderecoInstituicaoRetorno = repository.save(enderecoInstituicao)
        return transformarEmDto(enderecoInstituicaoRetorno)
    }

    fun buscar(parceiro: Boolean): List<EnderecoInstituicaoDto> {
        val listaEnderecoInstituicao = repository.findByInstituicaoParceiroIs(parceiro)
        validarSeAListaEstaVazia(listaEnderecoInstituicao)
        val listaEnderecoInstituicaoDto = transformarListaEmDto(listaEnderecoInstituicao)
        return listaEnderecoInstituicaoDto
    }

    fun buscarUm(idEnderecoInstituicao: Int): EnderecoInstituicaoDto{
        validarExistencia(idEnderecoInstituicao)

        val enderecoInstituicao = repository.findByIdEnderecoInstituicao(idEnderecoInstituicao)
        return transformarEmDto(enderecoInstituicao)
    }
}
