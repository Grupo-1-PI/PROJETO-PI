package ProjetoPI.ProjetoDoadores.domain

import java.time.LocalDate

data class Doador(
    val id: Int,
    val nome: String,
    val email: String,
    val ddd: String,
    val telCel: String,
    val dtNasc: LocalDate,
    val sexo: Char,
    val primeiraDoacao: Char,
    val senha: String
) {
    fun verificarCadastro(doador: Doador): Boolean {
        return doador.nome.isEmpty() && doador.email.isEmpty()
    }
}
