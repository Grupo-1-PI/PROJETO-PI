package ProjetoPI.ProjetoDoadores.domain

import jakarta.persistence.*

@Entity
data class EnderecoInstituicao(
    @field: Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idEnderecoInstituicao: Int? = null,
    var rua: String? =null,
    var numero: Int? =null,
    var bairro: String? =null,
    var complemento: String? = null,
    var cidade: String? =null,
    var estado: String? =null,
    var cep: String? =null,
    var latitude: Double? =null,
    var longitude: Double? =null,
    @field:OneToOne
    @JoinColumn(name = "id_instituicao")  // Nome da coluna de chave estrangeira
    val instituicao: Instituicao? = null
){
    constructor(
        paramIdEnderecoInstituicao: Int? = null,
        paramRua: String? = null,
        paramNumero: Int? = null,
        paramBairro: String? = null,
        paramComplemento: String? = null,
        paramCidade: String? =null,
        paramEstado: String? =null,
        paramCep: String? =null,
        paramLatitude: Double? =null,
        paramLongitude: Double? =null,
    ): this(
        idEnderecoInstituicao = paramIdEnderecoInstituicao,
        rua = paramRua,
        numero = paramNumero,
        bairro = paramBairro,
        complemento = paramComplemento,
        cidade = paramCidade,
        estado = paramEstado,
        cep = paramCep,
        latitude = paramLatitude,
        longitude = paramLongitude
    )
}
