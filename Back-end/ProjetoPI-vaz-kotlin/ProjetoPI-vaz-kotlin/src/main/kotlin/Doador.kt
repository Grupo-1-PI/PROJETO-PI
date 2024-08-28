package org.example

class Doador(
    nome: String,
    email: String,
    tel: String,
    dtNascimento: String,
    senha: String,
    nivelAcesso: Int = 0,
    sexo: String,
    var indicacao: String
): Usuario(nome,email,tel,dtNascimento,senha,nivelAcesso,sexo) {

    override fun toString(): String {
        return super.toString() + " " + """
        Indicação: $indicacao    
        """.trimIndent()
    }

}