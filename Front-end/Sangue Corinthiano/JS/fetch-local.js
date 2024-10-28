async function buscarEnderecoInstituicao(parceiro){
    try {
        const response = await fetch(`http://localhost:8080/enderecos-instituicoes?parceiro=${parceiro}`, {
            method: "GET",
        });

        if(response.status == 204){
            console.log('Nenhum dado cadastrado.')
            return []
        }

        const dados = await response.json()
        console.log(dados)

        preencherCardsDeLocais(dados, parceiro)

    } catch (error) {

        console.log(`Houve um erro: ${error}`)
    }
    
}

function validarParceiro(parceiro){
    if(!parceiro){
        return "Sangue Corinthiano"
    } else{
        return ""
    } 
    
}

function preencherCardsDeLocais(locais, parceiro){
  const containerFilho = document.querySelector('.containerFilho')
  containerFilho.innerHTML = ""
  for(var i = 0; i < locais.length; i++){
    containerFilho.innerHTML += `
    <div class="inputCadastrado"data-cidade="São Paulo" data-tipo="Local de Doação" style="margin-bottom: 20px; ">
                <span>${validarParceiro(parceiro)} ${locais[i].instituicao.nome}</span>
                <div class="botaoVizuEdi">
                    <div id="${locais[i].idEnderecoInstituicao}" onclick="visualizarLocal(this.id)" class="botaoVizu">
                        <span>Visualizar</span>
                    </div>
                    <div class="botaoEdit">
                        <span>Deletar</span>
                    </div>
                </div>
            </div>`
  }
}

function atualizarCorBotao(elementoA){

    if(elementoA.classList.contains('sc')){
        if(document.querySelector('.btn-ip').classList.contains('ativo')){
            document.querySelector('.btn-ip').classList.remove('ativo')
        }
        document.querySelector('.btn-sc').classList.add('ativo')
    }

    if(elementoA.classList.contains('ip')){
       if(document.querySelector('.btn-sc').classList.contains('ativo')){
        document.querySelector('.btn-sc').classList.remove('ativo')
       }
        document.querySelector('.btn-ip').classList.add('ativo')
    }
}


function visualizarLocal(enderecoInstituicaoId){
    sessionStorage.setItem("ENDERECO-INSTITUICAO-ID", enderecoInstituicaoId)
    location.assign("http://127.0.0.1:5500/HTML/paginas-adm/ADMCadastrarSangueCorinthiano.html")
}

buscarEnderecoInstituicao(false)
