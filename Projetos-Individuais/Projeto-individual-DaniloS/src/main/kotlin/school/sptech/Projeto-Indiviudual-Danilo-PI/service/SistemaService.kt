package school.sptech.projetoestoque.service

import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import school.sptech.projetoestoque.dominio.Sistema
import school.sptech.projetoestoque.dto.SistemasResponse
import school.sptech.projetoestoque.repository.SistemaRepository

@Service
class SistemaService(
    val sistemaRepository: SistemaRepository,
    val mapper: ModelMapper = ModelMapper()
) {
    fun listarSistemas(): List<Sistema> = sistemaRepository.findAll()

    fun adicionarSistema(sistema: Sistema): Sistema = sistemaRepository.save(sistema)

    fun atualizarSistema(id: Int, sistema: Sistema): Sistema {
        if (sistemaRepository.existsById(id)) {
            return sistemaRepository.save(sistema.copy(id = id))
        } else {
            throw RuntimeException("Sistema não encontrado")
        }
    }

    fun excluirSistema(id: Int) {
        if (sistemaRepository.existsById(id)) {
            sistemaRepository.deleteById(id)
        } else {
            throw RuntimeException("Sistema não encontrado")
        }
    }

    fun get(id:Int):SistemasResponse {
        validarIdSistema(id)

        val sistema = sistemaRepository.findById(id).get()

        val dto = mapper.map(
            sistema,
            SistemasResponse::class.java
        )
        return dto
    }

    fun validarIdSistema(id:Int) {
        if (!sistemaRepository.existsById(id)) {
            throw ResponseStatusException(HttpStatusCode.valueOf(404))
        }
    }
}
