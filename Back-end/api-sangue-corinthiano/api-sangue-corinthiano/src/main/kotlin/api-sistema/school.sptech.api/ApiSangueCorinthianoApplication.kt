package ProjetoPI.ProjetoDoadores

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
class ProjetoDoadoresApplication

fun main(args: Array<String>) {
	runApplication<ProjetoDoadoresApplication>(*args)
}
//
//import org.springframework.boot.SpringApplication
//
//@SpringBootApplication
//@EntityScan(basePackages = ["ProjetoPI.ProjetoDoadores.entity"]) // Ajuste para o pacote correto das entidades
//@EnableJpaRepositories(basePackages = ["ProjetoPI.ProjetoDoadores.repository"]) // Ajuste para o pacote correto dos repositórios
//class ApiSangueCorinthianoApplication
//
//fun main(args: Array<String>) {
//	SpringApplication.run(ApiSangueCorinthianoApplication::class.java, *args)
//}
