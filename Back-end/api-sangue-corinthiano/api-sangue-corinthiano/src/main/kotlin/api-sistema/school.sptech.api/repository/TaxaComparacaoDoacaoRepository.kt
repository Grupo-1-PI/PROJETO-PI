//package `api-sistema`.school.sptech.api.repository
package ProjetoPI.ProjetoDoadores.repository


import ProjetoPI.ProjetoDoadores.domain.TaxaComparacaoDoacao
import org.springframework.data.jpa.repository.JpaRepository

interface TaxaComparacaoDoacaoRepository: JpaRepository<TaxaComparacaoDoacao, Long> {
}