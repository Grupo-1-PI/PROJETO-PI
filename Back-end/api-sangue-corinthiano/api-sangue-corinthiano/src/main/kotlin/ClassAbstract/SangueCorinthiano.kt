package ClassAbstract

class SangueCorinthiano {

    val campanhas = mutableListOf<Campanha>()
    val usuarios = mutableListOf<Usuario>()
    
    fun cadastrarUsuario(usuario: Usuario){

        usuarios.add(usuario)
    }

    fun exibirQtdUsuarios(): Int{
        return usuarios.size
    }

    fun exibirQtdAdmin(): Int{
        var qtdAdmin = 0

        for (u in usuarios){
            if (u is Admin){
                qtdAdmin++
            }
        }

        return qtdAdmin
    }

    fun exibirQtdRoot(): Int{
        var qtdRoot = 0

        for (u in usuarios){
            if (u is Root){
                qtdRoot++
            }
        }

        return qtdRoot
    }

    fun cadastrarCampanha(u: Usuario, c: Campanha): Boolean{
        if (u in usuarios){
            if (u is Admin || u is Root){

                campanhas.add(c)
                return true
            }
        }
        return false
    }

    fun exibirQtdCampanhas(): Int{
        return campanhas.size
    }

    fun exibirDash(u: Usuario): Boolean{
        if (u in usuarios){
            if (u is Root){

                return true
            }
        }
        return false
    }


}