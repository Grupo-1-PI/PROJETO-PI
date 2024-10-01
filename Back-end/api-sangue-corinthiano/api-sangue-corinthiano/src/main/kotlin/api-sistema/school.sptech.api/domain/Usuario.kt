package ProjetoPI.ProjetoDoadores.domain

import jakarta.persistence.MappedSuperclass
import jakarta.validation.constraints.NotBlank
import java.time.LocalDate
import java.time.Period

@MappedSuperclass
abstract class Usuario(
    @field:NotBlank
    var nome: String = "" ,

    @field:NotBlank
    var email: String = "" ,

    //@field:NotNull
    var dtNasc: LocalDate = LocalDate.now() ,

    //@field:NotNull
    var sexo: Char = 0.toChar() ,

    //@field:NotNull
    //var primeiraDoacao: Char = 0.toChar() ,

    //@field:NotBlank
    var senha: String = "",

    var nivelAcesso: Int = 1,
    ) {

    abstract fun autenticar(): Boolean

    // Função agora usa dtNasc diretamente, sem precisar passar parâmetro
    fun calcularIdade(): Int {
        // Pega a data atual
        val currentDate = LocalDate.now()

        // Calcula a diferença entre a data atual e a data de nascimento
        return Period.between(dtNasc, currentDate).years
    }

    override fun toString(): String {
        return """
        Nivel de acesso: $nivelAcesso;
        Nome: $nome;
        Email: $email;
        Senha: ******
        Data de nascimento: $dtNasc
        Idade: ${calcularIdade()}
        Sexo: $sexo
        """.trimIndent()
    }
}