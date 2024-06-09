package school.sptech.projetoestoque.dominio

import jakarta.persistence.*
import java.time.LocalDate

@Entity
data class Tarefa(

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    var titulo: String? = null,

    var descricao: String? = null,

    var dataVencimento: LocalDate? = null,

    @Enumerated(EnumType.STRING)
    val status: StatusTarefa,

    @field:ManyToOne
    @JoinColumn(name = "sistema_id")
    var sistema: Sistema? = null,

)
