//package ProjetoPI.ProjetoDoadores.service
//
//import ProjetoPI.ProjetoDoadores.domain.ConexaoApi_domain
//import ProjetoPI.ProjetoDoadores.repository.ConexaoApi_repository
//import org.springframework.stereotype.Service
//
//@Service
//class ConexaoApi_service(private val conexaoApiRepository: ConexaoApi_repository) {
//
//    fun listarTodas(): List<ConexaoApi_domain> = conexaoApiRepository.findAll()
//
//    fun listarPorTipo(tipo: String): List<ConexaoApi_domain> = conexaoApiRepository.findAllByTipoInstituicao(tipo)
//
//    fun adicionarInstituicao(instituicao: ConexaoApi_domain): ConexaoApi_domain = conexaoApiRepository.save(instituicao)
//
//    fun removerInstituicao(id: Long) = conexaoApiRepository.deleteById(id)
//}
