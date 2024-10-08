package ClassAbstract

class Root(
    nome: String,
    email: String,
    tel: String,
    dtNascimento: String,
    senha: String,
    nivelAcesso: Int = 2,
    sexo: String,
): Usuario(nome,email,tel,dtNascimento,senha,nivelAcesso,sexo) {

    override fun autenticar(): Boolean {
        return nivelAcesso >= 1
    }

    override fun toString(): String {
        return super.toString()
    }
}