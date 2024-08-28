package org.example

class SangueCorinthiano {

    val campanhas = mutableListOf<Campanha>()
    val usuarios = mutableListOf<Usuario>()
    
    fun cadastrarUsuario(usuario: Usuario){

        usuarios.add(usuario)
        println("Usuário cadastrado!")
    }

    fun cadastrarCampanha(campanha: Campanha){

        for (u in usuarios){

            if (u is Admin || u is Root){
                campanhas.add(campanha)
                println("Campanha cadastrada!")
            }
        }
    }

    fun exibirDash(): Boolean{
        for (u in usuarios){
            if (u is Root){
                return true
            }
        }
        return false
    }

    fun exibirQtdUsuarios(): Int{

        return usuarios.size
    }

}