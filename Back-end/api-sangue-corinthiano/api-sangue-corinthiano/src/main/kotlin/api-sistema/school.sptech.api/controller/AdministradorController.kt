package ProjetoPI.ProjetoDoadores.controller

import ProjetoPI.ProjetoDoadores.domain.Administrador
import ProjetoPI.ProjetoDoadores.domain.Doador
import ProjetoPI.ProjetoDoadores.repository.AdministradorRepository
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/administradores")
class AdministradorController(val repository: AdministradorRepository) {

    @PostMapping
    fun cadastrarAdministrador(@RequestBody @Valid novoAdministrador: Administrador): ResponseEntity<String> {
        val existe = repository.findByEmail(novoAdministrador.email)

        return if (existe != null) {
            ResponseEntity.status(400).body("Email já cadastrado!")
        } else {
            try {
                repository.save(novoAdministrador)
                ResponseEntity.status(201).body("Cadastro realizado com sucesso!")
            } catch (ex: Exception) {
                ResponseEntity.status(500).body("Erro ao realizar cadastro: ${ex.message}")
            }
        }
    }

    @GetMapping
    fun listarAdministradores(): ResponseEntity<List<Administrador>> {
        val listaAdministradores = repository.findAll()

        if(listaAdministradores.isEmpty()) {
            return ResponseEntity.status(204).build()
        }
        return ResponseEntity.status(200).body(listaAdministradores)
    }


    @GetMapping("/{id}")
    fun listarAdministradorPorId(@PathVariable id: Int): ResponseEntity<Administrador> {
        return if (repository.existsById(id)) {
            val administrador = repository.findById(id).get()
            ResponseEntity.status(200).body(administrador)
        } else {
            ResponseEntity.status(404).build()
        }
    }


    @PutMapping("/{id}")
    fun atualizarAdministrador(@PathVariable id: Int, @RequestBody novoAdministrador: Administrador): ResponseEntity<Administrador> {
        return if (repository.existsById(id)) {
            val administrador = repository.findById(id).get()

            administrador.nome = novoAdministrador.nome
            administrador.cpf = novoAdministrador.cpf
            administrador.email = novoAdministrador.email
            administrador.senha = novoAdministrador.senha
            administrador.cargoId = novoAdministrador.cargoId
            administrador.unidadeId = novoAdministrador.unidadeId
            administrador.dtNasc = novoAdministrador.dtNasc
            administrador.sexo = novoAdministrador.sexo

            val administradorAtualizado = repository.save(administrador)
            ResponseEntity.status(200).body(administradorAtualizado)
        } else {
            ResponseEntity.status(404).build()
        }
    }

    @DeleteMapping("/{id}")
    fun deletarAdministrador(@PathVariable id: Int): ResponseEntity<String> {
        return if (repository.existsById(id)) {
            repository.deleteById(id)
            ResponseEntity.status(200).body("Administrador deletado com sucesso!")
        } else {
            ResponseEntity.status(404).build()
        }
    }

    @PostMapping("/login")
    fun login(@RequestParam email: String, @RequestParam senha: String): ResponseEntity<String> {
        val administrador = repository.findByEmailAndSenha(email, senha)

        if (administrador != null) {
            return ResponseEntity.status(200).body("Login realizado com sucesso!")
        }
        return ResponseEntity.status(400).body("Login inválido!")
    }

    @PostMapping("/logout")
    fun logout(): ResponseEntity<String> {
        return ResponseEntity.status(200).body("Logout realizado com sucesso!")
    }
}
