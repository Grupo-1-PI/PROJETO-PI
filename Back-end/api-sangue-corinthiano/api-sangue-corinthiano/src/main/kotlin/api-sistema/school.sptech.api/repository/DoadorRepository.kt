package ProjetoPI.ProjetoDoadores.repository

import ProjetoPI.ProjetoDoadores.domain.Doador
import org.springframework.data.jpa.repository.JpaRepository

interface DoadorRepository:JpaRepository<Doador, Int> {

    fun findByEmailAndSenha(email: String, senha: String): Doador?

    fun findByEmail(email: String):Doador?
}