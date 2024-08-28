package org.example

class Admin(
    nome: String,
    email: String,
    tel: String,
    dtNascimento: String,
    senha: String,
    nivelAcesso: Int = 1,
    sexo: String,
): Usuario(nome,email,tel,dtNascimento,senha,nivelAcesso,sexo) {

}