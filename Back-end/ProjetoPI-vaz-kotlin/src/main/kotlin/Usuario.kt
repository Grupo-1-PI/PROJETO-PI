package org.example

import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

abstract class Usuario(
    var nome: String,
    var email: String,
    var tel: String,
    var dtNascimento: String,
    var senha: String,
    var nivelAcesso: Int = 0,
    var sexo: String
) {

    abstract fun autenticar (): Boolean

    fun calcularIdade(dtNascimento: String): Int{
        // Define o formato da data de entrada
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

        // Converte a string de data de nascimento em um objeto LocalDate
        val birthLocalDate = LocalDate.parse(dtNascimento, formatter)

        // Pega a data atual
        val currentDate = LocalDate.now()

        // Calcula a diferença entre a data atual e a data de nascimento
        val age = Period.between(birthLocalDate, currentDate).years

        return age
    }

    override fun toString(): String {
        return """
        Nivel de acesso: $nivelAcesso;
        Nome: $nome;
        Email: $email;
        Senha: ******
        Telefone: $tel;
        Data de nascimento: $dtNascimento
        Idade: ${calcularIdade(dtNascimento)}
        Sexo: $sexo
        """.trimIndent()
    }
}