package `api-sistema`.school.sptech.api.repository

import ProjetoPI.ProjetoDoadores.domain.NovoDoadorRequest
import org.springframework.data.jpa.repository.JpaRepository

interface NovoDoadorRequestRepository: JpaRepository<NovoDoadorRequest, Int> {
}