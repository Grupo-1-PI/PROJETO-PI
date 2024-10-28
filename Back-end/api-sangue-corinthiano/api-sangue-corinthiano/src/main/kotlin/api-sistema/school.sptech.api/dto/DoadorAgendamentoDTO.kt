package `api-sistema`.school.sptech.api.dto

import java.time.LocalTime

data class DoadorAgendamentoDTO(
    val hora: LocalTime,
    val nome: String
) {

}