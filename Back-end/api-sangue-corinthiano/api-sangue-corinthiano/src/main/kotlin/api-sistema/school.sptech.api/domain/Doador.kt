package ProjetoPI.ProjetoDoadores.domain

import jakarta.persistence.*
import java.time.LocalDate

@Entity
class Doador(
    @field:Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idDoador: Int = 0,

    nome: String,
    email: String,
    dtNasc: LocalDate,
    sexo: Char,
    var primeiraDoacao: Char,
    senha: String,
    var idOrigemTrafego: Int,
    nivelAcesso: Int,

    @OneToMany(mappedBy = "doador", cascade = [CascadeType.ALL], orphanRemoval = true)
    val telefones: MutableList<TelefoneDoador> = mutableListOf()

): Usuario(nome,email,dtNasc,sexo, senha, nivelAcesso) {
    override fun autenticar(): Boolean {
        return nivelAcesso >= 2
    }
    constructor() : this(
        nome = "",
        email = "",
        dtNasc = LocalDate.now(),
        sexo = 'M',
        primeiraDoacao = 'N',
        senha = "",
        idOrigemTrafego = 0,
        nivelAcesso = 0
    )

}