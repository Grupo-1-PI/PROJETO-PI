package ProjetoPI.ProjetoDoadores.service

import ProjetoPI.ProjetoDoadores.domain.AdminSangueCorinthiano
import ProjetoPI.ProjetoDoadores.dto.AdminSangueCorinthianoDTO
import ProjetoPI.ProjetoDoadores.repository.AdminSangueCorinthianoRepository
import org.springframework.stereotype.Service

@Service
class AdminSangueCorinthianoService(
    private val repository: AdminSangueCorinthianoRepository
) {

    fun cadastrarColaborador(dto: AdminSangueCorinthianoDTO) {
        val colaborador = AdminSangueCorinthiano(
            nome = dto.nome,
            cpf = dto.cpf,
            email = dto.email,
            dtNasc = dto.dtNasc,
            sexo = dto.sexo,
            senha = dto.senha,
            idUnidade = dto.idUnidade,
            idCargo = dto.idCargo
        )
        repository.save(colaborador)
    }
}
