async function buscarEnderecoInstituicao(parceiro){
    try {
        const response = await fetch(`http://localhost:8080/enderecos-instituicoes?parceiro=${parceiro}`, {
            method: "GET",
        });

        const dados = await response.json()
        console.log(dados)

        preencherCardsDeLocais(dados)

    } catch (error) {

        console.log(`Houve um erro: ${error}`)
    }
    
}

function preencherCardsDeLocais(locais){
  const containerFilho = document.querySelector('.containerFilho')
  containerFilho.innerHTML = ""
  for(var i = 0; i < locais.length; i++){
    containerFilho.innerHTML += `
    <div class="inputCadastrado"data-cidade="São Paulo" data-tipo="Local de Doação" style="margin-bottom: 20px;">
                <span>Sangue Corinthiano ${locais[i].cidade}</span>
                <div class="botaoVizuEdi">
                    <div id="${locais[i].idEnderecoInstituicao}" onclick="visualizarLocal(this.id)" class="botaoVizu">
                        <span>Vizualizar</span>
                    </div>
                    <div class="botaoEdit">
                        <span>Editar</span>
                    </div>
                </div>
            </div>`
  }
}

function visualizarLocal(enderecoInstituicaoId){
    sessionStorage.setItem("ENDERECO-INSTITUICAO-ID", enderecoInstituicaoId)
    location.assign("http://127.0.0.1:5500/HTML/paginas-adm/ADMCadastrarSangueCorinthiano.html")
}

buscarEnderecoInstituicao(true)
