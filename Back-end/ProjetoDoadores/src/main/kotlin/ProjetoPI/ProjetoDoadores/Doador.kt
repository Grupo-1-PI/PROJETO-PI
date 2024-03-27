package ProjetoPI.ProjetoDoadores

 data class Doador(
    val id: Int,
    val nome: String,
    val email: String,
    val telefone: String,
    val cpf: Int,
    val rua: String,
    val numero: Int,
    val bairro: String,
    val cep: Int,
    val cidade: String
) {
    fun verificarCadastro(doador: Doador):Boolean{
        if(doador.nome == "" && doador.email == ""){
            return true
        }
        return false
    }
}