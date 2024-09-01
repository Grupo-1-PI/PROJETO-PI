package ProjetoPI.ProjetoDoadores.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

@Entity
data class TelefoneDoador(
    @field:Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idTelefone: Int = 0,

    @field:Size(min = 2, max = 2, message = "DDD deve ter 2 dígitos")
    @field:Pattern(regexp = "\\d{2}", message = "DDD deve conter apenas números")
    @Column(columnDefinition = "CHAR(2)")
    var ddd: String,

    @field:Size(min = 9, max = 9, message = "Telefone deve ter 9 dígitos")
    @field:Pattern(regexp = "\\d{9}", message = "Telefone deve conter apenas números")
    @Column(columnDefinition = "CHAR(9)")
    var telCel: String,

    @ManyToOne
    @JoinColumn(name = "id_doador")
    @JsonIgnore
    val doador: Doador? = null
){
    constructor() : this(0, "", "", null)
}
