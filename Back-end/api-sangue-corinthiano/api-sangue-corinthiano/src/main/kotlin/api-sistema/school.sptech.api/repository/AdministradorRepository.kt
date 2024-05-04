package ProjetoPI.ProjetoDoadores.repository

import ProjetoPI.ProjetoDoadores.domain.Administrador
import org.springframework.data.jpa.repository.JpaRepository

interface AdministradorRepository: JpaRepository<Administrador, Int> {
    fun findByEmailAndSenha(email: String, senha: String): Administrador?

    fun findByEmail(email: String): Administrador?
}