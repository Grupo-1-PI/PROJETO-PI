package ProjetoPI.ProjetoDoadores.controller

import ProjetoPI.ProjetoDoadores.domain.Doador
import ProjetoPI.ProjetoDoadores.repository.DoadorRepository
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/doadores")
class DoadorController (var repository: DoadorRepository){

    @PostMapping
    fun cadastrarDoador(@RequestBody @Valid novoDoador: Doador): ResponseEntity<String> {
        val existe = repository.findByEmail(novoDoador.email)

        return if (existe != null) {
            ResponseEntity.status(400).body("Email já cadastrado!")
        } else {
            try {
                repository.save(novoDoador)
                ResponseEntity.status(201).body("Cadastro realizado com sucesso!")
            } catch (ex: Exception) {
                ResponseEntity.status(500).body("Erro ao realizar cadastro: ${ex.message}")
            }
        }
    }

    @GetMapping
    fun listarDoadores(): ResponseEntity<List<Doador>> {
        val listaDoadores = repository.findAll()

        if(listaDoadores.isEmpty()) {
            return ResponseEntity.status(204).build()
        }
        return ResponseEntity.status(200).body(listaDoadores)
    }

    @GetMapping("/{id}")
    fun listarDoadorPorId(@PathVariable id: Int): ResponseEntity<Doador> {

        return if (repository.existsById(id)) {
            val doador = repository.findById(id).get()
            ResponseEntity.status(200).body(doador)
        } else {
            ResponseEntity.status(404).build()
        }
    }

    @PutMapping("/{id}")
    fun atualizarDoador(@PathVariable id: Int, @RequestBody novoDoador: Doador): ResponseEntity<Doador> {

        return if (repository.existsById(id)) {
            val doador = repository.findById(id).get()

            doador.nome = novoDoador.nome
            doador.email = novoDoador.email
            doador.dtNasc = novoDoador.dtNasc
            doador.sexo = novoDoador.sexo
            doador.primeiraDoacao = novoDoador.primeiraDoacao
            doador.senha = novoDoador.senha
            doador.motivo = novoDoador.motivo

            val doadorAtualizado = repository.save(doador)
            ResponseEntity.status(200).body(doadorAtualizado)
        } else {
            ResponseEntity.status(404).build()
        }
    }

    @DeleteMapping("/{id}")
    fun deletarDoador(@PathVariable id: Int): ResponseEntity<String> {
        return if (repository.existsById(id)) {
            repository.deleteById(id)
            ResponseEntity.status(200).body("Doador deletado com sucesso!")
        } else {
            ResponseEntity.status(404).build()
        }
    }

    @PostMapping("/login")
    fun login(@RequestParam email: String, @RequestParam senha: String): ResponseEntity<String> {
        val doador = repository.findByEmailAndSenha(email, senha)

        if (doador != null) {
            return ResponseEntity.status(200).body("Login realizado com sucesso!")
        }
        return ResponseEntity.status(400).body("Login inválido!")
    }

    @PostMapping("/logout")
    fun logout(): ResponseEntity<String> {
        return ResponseEntity.status(200).body("Logout realizado com sucesso!")
    }
}




