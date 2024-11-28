package ProjetoPI.ProjetoDoadores

import ProjetoPI.ProjetoDoadores.domain.Agendamento
import org.springframework.stereotype.Service
import java.io.Reader
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

@Service
class AgendamentoService {

    fun processarTXT(reader: Reader): List<Agendamento> {
        val leitor = Scanner(reader)
        val listaAgendamentos = mutableListOf<Agendamento>()

        while (leitor.hasNext()) {
            val linha = leitor.nextLine()
            val tipoRegistro = linha.substring(0, 2)

            when (tipoRegistro) {
                "00" -> {
                    val conteudo = linha.substring(2, 8).trim()
                    val dataHora = linha.substring(8, 28).trim()
                    val versaoLayout = linha.substring(28, 31).trim()

                    println("Header:")
                    println("Conteúdo do arquivo: $conteudo")
                    println("Data/hora de geração: $dataHora")
                    println("Versão do layout: $versaoLayout")
                }

                "02" -> {
                    val idAgendamento = linha.substring(2, 8).trim().toInt()
                    println("Id Agendamento: $idAgendamento")
                    val data = LocalDate.parse(linha.substring(8, 19).trim()) // Ajuste conforme o formato da data no texto
                    println("Data: $data")
                    val hora = LocalTime.parse(linha.substring(19, 28).trim()) // Ajuste conforme o formato da hora no texto
                    println("Hora: $hora")
                    val cpfDoador = linha.substring(28, 40).trim()
                    println("CPF: $cpfDoador")
                    val idDoador = linha.substring(40, 46).trim().toInt()
                    println("ID Doador: $idDoador")
                    val idInstituicao = linha.substring(46, 52).trim().toInt()
                    println("ID Instituição: $idInstituicao")
                    val idCampanha = linha.substring(52, 58).trim().toInt()
                    println("ID Campanha: $idCampanha")
                    val status = linha.substring(58, 60).trim().toInt()
                    println("STATUS: $status")

                    val agendamento = Agendamento(
                        idAgendamento = idAgendamento,
                        data = data,
                        hora = hora,
                        cpfDoador = cpfDoador,
                        idDoador = idDoador,
                        idInstituicao = idInstituicao,
                        idCampanha = idCampanha,
                        status = status
                    )
                    listaAgendamentos.add(agendamento)
                }


                "01" -> {
                    val quantidadeRegistros = linha.substring(2, 8).trim().toInt()

                    println("Trailer:")
                    println("Quantidade de registros: $quantidadeRegistros")
                    if (quantidadeRegistros == listaAgendamentos.size) {
                        println("Quantidade de registros conferida e válida.")
                    } else {
                        println("Quantidade de registros inconsistente. Esperado: $quantidadeRegistros, Encontrado: ${listaAgendamentos.size}")
                    }
                }

                else -> println("Tipo de registro desconhecido: $tipoRegistro")
            }
        }
        leitor.close()
        return listaAgendamentos
    }

}