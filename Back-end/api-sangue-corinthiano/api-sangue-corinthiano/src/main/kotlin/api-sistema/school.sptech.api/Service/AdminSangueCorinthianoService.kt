package ProjetoPI.ProjetoDoadores.service

import ProjetoPI.ProjetoDoadores.dto.AdminSangueCorinthianoDTO
import ProjetoPI.ProjetoDoadores.repository.AdminSangueCorinthianoRepository
import org.springframework.stereotype.Service

@Service
class AdminSangueCorinthianoService(
    private val repository: AdminSangueCorinthianoRepository
) {

    fun buscarPorInstituicao(instituicaoId: Int): List<AdminSangueCorinthianoDTO> {
        val colaboradores = repository.findByInstituicaoAtivos(instituicaoId)
        return colaboradores.map { colaborador ->
            AdminSangueCorinthianoDTO(
                idAdmin = colaborador.idAdmin,
                nome = colaborador.nome,
                cpf = colaborador.cpf,
                email = colaborador.email,
                dtNasc = colaborador.dtNasc,
                sexo = colaborador.sexo,
                senha = colaborador.senha,
                idUnidade = colaborador.idUnidade,
                idCargo = colaborador.idCargo
            )
        }
    }

    fun cancelarColaborador(idColaborador: Int) {
        // Lógica para cancelar o colaborador, como por exemplo:
        val colaborador = repository.findById(idColaborador).orElseThrow {
            RuntimeException("Colaborador não encontrado")
        }
        colaborador.status = "CANCELADO"  // Atualize para o status desejado
        repository.save(colaborador)
    }

}
