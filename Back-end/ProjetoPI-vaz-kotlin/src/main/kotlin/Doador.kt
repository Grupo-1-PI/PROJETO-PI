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

    override fun autenticar(): Boolean {
        return nivelAcesso >=1
    }

    override fun toString(): String {
        return """
        Nivel de acesso: $nivelAcesso;
        Nome: $nome;
        Email: $email;
        Senha: ******
        Telefone: $tel;
        Data de nascimento: $dtNascimento;
        Idade: ${calcularIdade(dtNascimento)};
        Sexo: $sexo;
        Indicação: $indicacao;
        """
    }

}