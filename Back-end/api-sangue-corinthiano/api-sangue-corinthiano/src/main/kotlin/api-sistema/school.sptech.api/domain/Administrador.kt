package ProjetoPI.ProjetoDoadores.domain

data class Administrador(
    val id: Int,
    val nome: String,
    val cpf: String,
    val email: String,
    val ddd: String,
    val telCel: String,
    val codigoCargo: Int,
    val codigoUnidade: Int,
    val token: String,
    val senha: String
) {
}
