package ProjetoPI.ProjetoDoadores.domain

import jakarta.persistence.*
import java.time.LocalDate

class Root(
    @field:Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idRoot: Int = 0,

    nome: String,
    email: String,
    dtNasc: LocalDate = LocalDate.now(),
    sexo: Char,
    senha: String,
    nivelAcesso: Int = 1,

    @OneToMany(mappedBy = "doador", cascade = [CascadeType.ALL], orphanRemoval = true)
    val telefones: MutableList<TelefoneDoador> = mutableListOf()

): Usuario(nome,email,dtNasc,sexo, senha, nivelAcesso) {
    override fun autenticar(): Boolean {
        TODO("Not yet implemented")
    }
}
