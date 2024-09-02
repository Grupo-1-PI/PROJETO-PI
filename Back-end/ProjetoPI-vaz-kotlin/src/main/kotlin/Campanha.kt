package org.example

class Campanha(
    var nome: String,
) {

    override fun toString(): String {
        return """
            Nome da Campanha: $nome
        """.trimIndent()
    }
}