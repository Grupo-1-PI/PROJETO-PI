package school.sptech.projetoestoque.dominio

import jakarta.persistence.*

@Entity
data class Sistema(

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    val nome: String? = null,

)
