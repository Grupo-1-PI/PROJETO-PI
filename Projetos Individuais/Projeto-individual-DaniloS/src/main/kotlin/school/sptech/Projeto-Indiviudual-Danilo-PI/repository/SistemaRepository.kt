package school.sptech.projetoestoque.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import school.sptech.projetoestoque.dominio.Sistema
import school.sptech.projetoestoque.dominio.Tarefa

interface SistemaRepository: JpaRepository<Sistema, Int>{


}