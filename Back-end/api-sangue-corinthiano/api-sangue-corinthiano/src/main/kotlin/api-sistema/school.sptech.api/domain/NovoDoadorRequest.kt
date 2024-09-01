package ProjetoPI.ProjetoDoadores.domain

import java.time.LocalDate

data class NovoDoadorRequest(
    val nome: String,
    val email: String,
    val dtNasc: LocalDate,
    val sexo: Char,
    val primeiraDoacao: Char,
    val senha: String,
    val idOrigemTrafego: Int,
    val telefones: List<TelefoneRequest>
)



