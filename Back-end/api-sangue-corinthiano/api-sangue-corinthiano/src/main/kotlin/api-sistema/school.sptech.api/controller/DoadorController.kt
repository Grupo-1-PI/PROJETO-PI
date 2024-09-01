package ProjetoPI.ProjetoDoadores.controller

import ProjetoPI.ProjetoDoadores.domain.Doador
import ProjetoPI.ProjetoDoadores.domain.NovoDoadorRequest
import ProjetoPI.ProjetoDoadores.domain.TelefoneDoador
import ProjetoPI.ProjetoDoadores.repository.DoadorRepository
import ProjetoPI.ProjetoDoadores.repository.TelefoneDoadorRepository
import ProjetoPI.ProjetoDoadores.repository.TelefoneRepository
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = ["http://127.0.0.1:5500"])
@RestController
@RequestMapping("/doadores")
class DoadorController (var repository: DoadorRepository, val telefoneRepository: TelefoneRepository, val telefoneRepository2: TelefoneDoadorRepository){

    @PostMapping
    fun cadastrarDoador(@RequestBody @Valid novoDoadorRequest: NovoDoadorRequest): ResponseEntity<String> {
        val existe = repository.findByEmail(novoDoadorRequest.email)

        return if (existe != null) {
            ResponseEntity.status(400).body("Email já cadastrado!")
        } else {
            try {
                val novoDoador = Doador(
                    nome = novoDoadorRequest.nome,
                    email = novoDoadorRequest.email,
                    dtNasc = novoDoadorRequest.dtNasc,
                    sexo = novoDoadorRequest.sexo,
                    primeiraDoacao = novoDoadorRequest.primeiraDoacao,
                    senha = novoDoadorRequest.senha,
                    idOrigemTrafego = novoDoadorRequest.idOrigemTrafego
                )

                val doadorSalvo = repository.save(novoDoador)

                novoDoadorRequest.telefones.forEach { telefoneRequest ->
                    val telefoneDoador = TelefoneDoador(
                        ddd = telefoneRequest.ddd,
                        telCel = telefoneRequest.telCel,
                        doador = doadorSalvo
                    )
                    telefoneRepository.save(telefoneDoador)
                }

                ResponseEntity.status(201).body("Cadastro realizado com sucesso!")
            } catch (ex: Exception) {
                ResponseEntity.status(500).body("Erro ao realizar cadastro: ${ex.message}")
            }
        }
    }

//    @PostMapping
//    fun cadastrarDoador(@RequestBody @Valid novoDoador: Doador): ResponseEntity<String> {
//        val existe = repository.findByEmail(novoDoador.email)
//
//        return if (existe != null) {
//            ResponseEntity.status(400).body("Email já cadastrado!")
//        } else {
//            try {
//                repository.save(novoDoador)
//                ResponseEntity.status(201).body("Cadastro realizado com sucesso!")
//            } catch (ex: Exception) {
//                ex.printStackTrace()
//                ResponseEntity.status(500).body("Erro ao realizar cadastro: ${ex.message}")
//            }
//        }
//    }

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
//            doador.motivo = novoDoador.motivo

            val doadorAtualizado = repository.save(doador)
            ResponseEntity.status(200).body(doadorAtualizado)
        } else {
            ResponseEntity.status(404).build()
        }
    }


    @PatchMapping("/{id}")
    fun atualizarNomeEmailSenhaDoador(
        @PathVariable id: Int,
        @RequestParam nome: String,
        @RequestParam email: String,
        @RequestParam telefone: String,
        @RequestParam senha: String
    ): ResponseEntity<String> {

        val doador = repository.findById(id).orElse(null)

        if (doador == null) {
            return ResponseEntity.status(404).body("Doador não encontrado!")
        }

        // Verificar se o email já está cadastrado
        val emailExistente = repository.findByEmail(email)
        if (emailExistente != null && emailExistente.idDoador != id) {
            return ResponseEntity.status(400).body("Email já cadastrado!")
        }

        // Atualizar os dados do doador
        doador.nome = nome
        doador.email = email
        doador.senha = senha

        // Ajustar o telefone
        val telefoneLimpo = telefone.replace(Regex("\\D"), "") // Remove caracteres não numéricos

        if (telefoneLimpo.length != 11) { // Verifica se tem 11 dígitos
            return ResponseEntity.status(400).body("Telefone deve ter 11 dígitos, incluindo DDD.")
        }

        // Dividir o telefone em DDD e número
        val ddd = telefoneLimpo.substring(0, 2)
        val telCel = telefoneLimpo.substring(2)

        val telefoneExistente = telefoneRepository.findByDoadorIdDoador(id)

        if (telefoneExistente.isNotEmpty()) {
            val telefoneDoador = telefoneExistente[0]
            telefoneDoador.ddd = ddd
            telefoneDoador.telCel = telCel
            telefoneRepository.save(telefoneDoador)
        } else {
            val novoTelefone = TelefoneDoador(ddd = ddd, telCel = telCel, doador = doador)
            telefoneRepository.save(novoTelefone)
        }

        // Salvar as mudanças no doador
        repository.save(doador)

        return ResponseEntity.status(200).body("Doador atualizado com sucesso!")
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
    fun login(@RequestParam email: String, @RequestParam senha: String): ResponseEntity<Map<String, Any>> {
        val doador = repository.findByEmailAndSenha(email, senha)

        return if (doador != null) {
            ResponseEntity.ok().body(mapOf(
                "message" to "Login realizado com sucesso!",
                "userId" to doador.idDoador
            ))
        } else {
            ResponseEntity.badRequest().body(mapOf(
                "message" to "Login inválido!"
            ))
        }
    }

    @PostMapping("/login2")
    fun login2(@RequestParam email: String, @RequestParam senha: String): ResponseEntity<String> {
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




