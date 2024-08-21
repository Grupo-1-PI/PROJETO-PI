package ProjetoPI.ProjetoDoadores.repository

import ProjetoPI.ProjetoDoadores.domain.AdminSangueCorinthiano
import org.springframework.data.jpa.repository.JpaRepository

interface AdministradorRepository: JpaRepository<AdminSangueCorinthiano, Int> {
    fun findByEmailAndSenha(email: String, senha: String): AdminSangueCorinthiano?

    fun findByEmail(email: String): AdminSangueCorinthiano?
}