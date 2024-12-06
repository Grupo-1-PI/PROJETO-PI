package `api-sistema`.school.sptech.api.dto

import java.time.LocalTime

data class AgendamentoComNomeDTO(
    val nomeCampanha: String,
    val hora: LocalTime,
    val nomeDoador:String
)