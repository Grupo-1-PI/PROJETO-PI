package org.example



fun main() {
    val sangueCorinthiano = SangueCorinthiano()

    val campanha1 = Campanha("Campanha de Dezembro")
    val campanha2 = Campanha("Campanha de Agosto")

    val doador = Doador(
        "João Silva" ,
        "joao.silva@email.com" ,
        "+55 11 91234-5678" ,
        "25/08/1990" ,
        "senhaSegura1" ,
        0 ,
        "Masculino" ,
        "Instagram"
    )
    val doador1 = Doador(
        "Maria Oliveira" ,
        "maria.oliveira@email.com" ,
        "+55 21 98765-4321" ,
        "12/03/1985" ,
        "senhaSegura2" ,
        0 ,
        "Feminino" ,
        "Facebook"
    )
    val doador2 = Doador(
        "Carlos Pereira" ,
        "carlos.pereira@email.com" ,
        "+55 31 99876-5432" ,
        "07/07/1992" ,
        "senhaSegura3" ,
        0 ,
        "Masculino" ,
        "Twitter"
    )
    val doador3 = Doador(
        "Ana Souza" ,
        "ana.souza@email.com" ,
        "+55 41 98765-6789" ,
        "19/05/1988" ,
        "senhaSegura4" ,
        0 ,
        "Feminino" ,
        "Indicação de amigos"
    )
    val doador4 = Doador(
        "Pedro Fernandes" ,
        "pedro.fernandes@email.com" ,
        "+55 71 91234-9876" ,
        "03/11/1990" ,
        "senhaSegura5" ,
        0 ,
        "Masculino" ,
        "Site"
    )
    val doador5 = Doador(
        "Juliana Santos" ,
        "juliana.santos@email.com" ,
        "+55 61 99876-1234" ,
        "22/09/1987" ,
        "senhaSegura6" ,
        0 ,
        "Feminino" ,
        "Instagram"
    )
    val doador6 = Doador(
        "Rafael Lima" ,
        "rafael.lima@email.com" ,
        "+55 51 98765-2345" ,
        "14/02/1993" ,
        "senhaSegura7" ,
        0 ,
        "Masculino" ,
        "Facebook"
    )
    val doador7 = Doador(
        "Camila Araujo" ,
        "camila.araujo@email.com" ,
        "+55 81 91234-3456" ,
        "08/06/1986" ,
        "senhaSegura8" ,
        0 ,
        "Feminino" ,
        "Twitter"
    )
    val doador8 = Doador(
        "Lucas Almeida" ,
        "lucas.almeida@email.com" ,
        "+55 11 99876-4567" ,
        "30/12/1989" ,
        "senhaSegura9" ,
        0 ,
        "Masculino" ,
        "Indicação de amigos"
    )
    val doador9 = Doador(
        "Fernanda Costa" ,
        "fernanda.costa@email.com" ,
        "+55 21 98765-5678" ,
        "11/01/1984" ,
        "senhaSegura10" ,
        0 ,
        "Feminino" ,
        "Site"
    )


    val admin1 = Admin(
        "Thiago Mendes" ,
        "thiago.mendes@email.com" ,
        "+55 31 91234-7890" ,
        "10/04/1991" ,
        "senhaSegura11" ,
        2 ,
        "Masculino"
    )

    val admin2 = Admin(
        "Carla Rodrigues" ,
        "carla.rodrigues@email.com" ,
        "+55 71 98765-6789" ,
        "05/08/1986" ,
        "senhaSegura12" ,
        2 ,
        "Feminino"
    )


    val root = Root(
        "Vânia Simonetti" ,
        "vania.simonetti@email.com" ,
        "+55 11 98765-0179" ,
        "11/01/1984" ,
        "senhaSegura11" ,
        3 ,
        "Feminino" ,
    )

    println("Adicionando Doadores....")
    sangueCorinthiano.cadastrarUsuario(doador)
    sangueCorinthiano.cadastrarUsuario(doador1)
    sangueCorinthiano.cadastrarUsuario(doador2)
    sangueCorinthiano.cadastrarUsuario(doador3)
    sangueCorinthiano.cadastrarUsuario(doador4)
    sangueCorinthiano.cadastrarUsuario(doador5)
    sangueCorinthiano.cadastrarUsuario(doador6)
    sangueCorinthiano.cadastrarUsuario(doador7)
    sangueCorinthiano.cadastrarUsuario(doador8)
    sangueCorinthiano.cadastrarUsuario(doador9)
    sangueCorinthiano.cadastrarUsuario(admin1)
    sangueCorinthiano.cadastrarUsuario(admin2)
    sangueCorinthiano.cadastrarUsuario(root)
    println("Doadores Adicionados!")
    println()

    println("Autenticando Usuários (esse método serve para saber quem pode adicionar uma campanha)")
    println("Usuario com nível de acesso de doador (igual a 0)")
    println(doador.autenticar())
    println()

    println("Usuario com nível de acesso de Admin/Root (maior que 0)")
    println(root.autenticar())
    println()
    
    println("Fazendo as validações...")
    println("Perfil Doador:")
    println(doador)
    println()

    println("Perfil Admin:")
    println(admin1)
    println()

    println("Pefil Root:")
    println(root)
    println()

    println("Quantidade de Usuários Cadastrados no Site...")
    println(sangueCorinthiano.exibirQtdUsuarios())
    println()

    println("Quantidade de Admin`s Cadastrados no Site...")
    println(sangueCorinthiano.exibirQtdAdmin())
    println()

    println("Quantidade de Root`s Cadastrados no Site...")
    println(sangueCorinthiano.exibirQtdRoot())
    println()

    println("Agora Vamos Para Campanhas")
    println("Retornar True (Campanha cadastrada por um Admin ou Root)")
    println(sangueCorinthiano.cadastrarCampanha( admin1,campanha1))
    println()

    println("Retornar false (Campanha cadastrada por um Doador)")
    println(sangueCorinthiano.cadastrarCampanha( doador,campanha2))
    println()

    println("Campanhas Cadastradas")
    println(sangueCorinthiano.exibirQtdCampanhas())
    println()

    println("Campanha info:")
    println(campanha1)

}