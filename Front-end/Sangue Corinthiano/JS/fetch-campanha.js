// Cadastra a instituicao -- fecth de instituicao - cadastra
// Retorna os dados da instituicao -- retorna os dados com o id 
// Captura os dados da instituicao -- a gente envia para outra funcao
// Com base no id da instituicao a gente cadastra o endereco da instituicao


// Swal.fire({
//     template: "botao-atualizar-campanha"
//   });


async function verificarSeEVisualizacao(){
    if(sessionStorage.getItem("ENDERECO-INSTITUICAO-ID") != null){
        const enderecoInstituicao = await buscarEnderecoInstituicaoUnico()
        
        bloquearInputs()
        preencherInputsEnderecoInstituicao(enderecoInstituicao)

        const botaoCadastro = document.querySelector('#botao-cadastrar')
        botaoCadastro.innerText = "Editar"
        botaoCadastro.setAttribute('onclick', 'redefinirBotaoCadastro(this)')
    }
}

async function redefinirBotaoCadastro(botao) {
    desbloquearInputs()
    const enderecoInstituicao = await buscarEnderecoInstituicaoUnico()
    botao.innerText = "Atualizar"
    botao.id = enderecoInstituicao.idEnderecoInstituicao
    botao.value = enderecoInstituicao.instituicao.idInstituicao
    botao.setAttribute('onclick', 'atualizarInstituicaoCompleta()')
}

function desbloquearInputs(){
    const inputs = document.querySelectorAll("input")
    inputs.forEach(input => input.disabled = false)
}

function bloquearInputs(){
    const inputs = document.querySelectorAll("input")
    inputs.forEach(input => input.disabled = true)
}

async function buscarEnderecoInstituicaoUnico(){
   var enderecoInstituicaoId = sessionStorage.getItem('ENDERECO-INSTITUICAO-ID')

    try {
        const response = await fetch(`http://localhost:8080/enderecos-instituicoes/${enderecoInstituicaoId}`, {
            method: "GET",
        });

        const dados = await response.json()
        console.log(dados)

        return dados

    } catch (error) {

        console.log(`Houve um erro: ${error}`)
    }
}

function preencherInputsEnderecoInstituicao(enderecoInstituicao){
    document.getElementById('input-nome').value = enderecoInstituicao.instituicao.nome
    document.getElementById('input-cnpj').value = enderecoInstituicao.instituicao.cnpj
    document.getElementById('input-cep').value = enderecoInstituicao.cep
    document.getElementById('input-latitude').value = enderecoInstituicao.latitude
    document.getElementById('input-longitude').value = enderecoInstituicao.longitude
    document.getElementById('input-cidade').value = enderecoInstituicao.cidade
    document.getElementById('input-estado').value = enderecoInstituicao.estado
    document.getElementById('input-bairro').value = enderecoInstituicao.bairro
    document.getElementById('input-rua').value = enderecoInstituicao.rua
    document.getElementById('input-numero').value = enderecoInstituicao.numero
    document.getElementById('input-complemento').value = enderecoInstituicao.complemento    
    document.getElementById('input-parceiro').checked = enderecoInstituicao.instituicao.parceiro

}

async function cadastrarInstituicao(){

    try {
        const response = await fetch(`http://localhost:8080/instituicoes`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                nome: document.getElementById('input-nome').value,
                cnpj: document.getElementById('input-cnpj').value
            })
        });

        if (response.ok) {
            const body = document.querySelector("body");
        
            // Exibir o alert
            alert("Instituição cadastrada com sucesso!");
        
            // Aplicar a animação de fade-out
            body.style.transition = "opacity 1s";
            body.style.opacity = "0";
        
            // Redirecionar após 1 segundo, tempo da animação
            setTimeout(() => {
                window.location.href = "../paginas-adm/ADMLocaisCadastradosSC.html";
            }, 1000);
        
        } else {
            // Exibir erro se a resposta não for bem-sucedida
            alert("Erro ao cadastrar a instituição.");
        }

    } catch (error) {
        console.log(`Houve um erro: ${error}`);
        alert("Ocorreu um erro ao tentar cadastrar a instituição.");
    }
}


async function cadastrarLocalSC(instituicaoId){

    console.log(instituicaoId);
    

    try {
        const response = await fetch(`http://localhost:8080/enderecos-instituicoes/${instituicaoId}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                rua: document.getElementById("input-rua").value,
                numero: parseInt(document.getElementById("input-numero").value),
                bairro: document.getElementById("input-bairro").value,
                complemento: document.getElementById("input-complemento").value,
                cidade: document.getElementById("input-cidade").value,
                estado: document.getElementById("input-estado").value,
                cep: document.getElementById("input-cep").value,
                latitude: document.getElementById("input-latitude").value, 
                longitude:document.getElementById("input-longitude").value,
                instituicao: {
		            idInstituicao: instituicaoId
                    
	            }       
                
            })
        });

        const dados = await response.json()
        console.log(dados)


    } catch (error) {

        console.log(`Houve um erro: ${error}`)
    }
}



async function atualizarInstituicao(idInstituicao){

    try {
        const response = await fetch(`http://localhost:8080/instituicoes/${idInstituicao}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                idInstituicao: idInstituicao,
                nome: document.getElementById('input-nome').value,
                cnpj: document.getElementById('input-cnpj').value,
                parceiro: document.getElementById('input-parceiro').checked
            })
        });

        if (response.ok) {
            const body = document.querySelector("body");
        
            // Exibir o alert
            alert("Instituição atualizada com sucesso!");
        
            // Aplicar a animação de fade-out
            body.style.transition = "opacity 1s";
            body.style.opacity = "0";
        
            // Redirecionar após 1 segundo, tempo da animação
            setTimeout(() => {
                window.location.href = "../paginas-adm/ADMLocaisCadastradosSC.html";
            }, 1000);
        } else {
            // Exibir erro se a resposta não for bem-sucedida
            alert("Erro ao cadastrar a instituição.");
        }

    } catch (error) {
        console.log(`Houve um erro: ${error}`);
        alert("Ocorreu um erro ao tentar cadastrar a instituição.");
    }
}




async function atualizarLocalSC(idEnderecoInstituicao, idInstituicao){
    try {
        const response = await fetch(`http://localhost:8080/enderecos-instituicoes/${idEnderecoInstituicao}/${idInstituicao}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                idEnderecoInstituicao: idEnderecoInstituicao,
                rua: document.getElementById("input-rua").value,
                numero: parseInt(document.getElementById("input-numero").value),
                bairro: document.getElementById("input-bairro").value,
                complemento: document.getElementById("input-complemento").value,
                cidade: document.getElementById("input-cidade").value,
                estado: document.getElementById("input-estado").value,
                cep: document.getElementById("input-cep").value,
                latitude: parseFloat(document.getElementById("input-latitude").value), 
                longitude: parseFloat(document.getElementById("input-longitude").value),
                instituicao: {
		            idInstituicao: idInstituicao
	            }
            })
        });

        const dados = await response.json()
        console.log(dados)


    } catch (error) {

        console.log(`Houve um erro: ${error}`)
    }
}



function adicionarEscutaBotaoAtualizar() {
    var botaoAtualizar = document.querySelector('#button-addon2')

    botaoAtualizar.addEventListener('click', function () {
        Swal.fire({
            title: "Você quer atualizar a campanha?",
            showDenyButton: true,
            showCancelButton: true,
            confirmButtonText: "Sim",
            denyButtonText: `Não`
        }).then((result) => {
            if (result.isConfirmed){
                atualizarLocalSC()
                Swal.fire("Salvo!", "", "sucess")
            } else if(result.isDenied){
                Swal.fire("Não Alterado", "", "info");
            }
    }).catch((error) => {
        console.log(`Houve um erro: ${error.message}`)
    }
    ) 
    });
}


async function cadastrarInstituicaoCompleta(){
    const novaInstituicao = await cadastrarInstituicao()
    cadastrarLocalSC(novaInstituicao.idInstituicao)
}

async function atualizarInstituicaoCompleta(){
    const botaoAtualizar = document.querySelector('.botao-cadastrar')
    const novaInstituicao = await atualizarInstituicao(botaoAtualizar.value)
    atualizarLocalSC(botaoAtualizar.id, botaoAtualizar.value)
}

function removerCacheSessionStorage(){
    sessionStorage.removeItem("ENDERECO-INSTITUICAO-ID")
}


function atualizarEstadoCheck(){
    var elChecked = document.getElementById('input-parceiro')
    elChecked.checked = !elChecked.checked
}

function atualizarCorBotao(elementoBotao){
    if(elementoBotao.classList.contains('sc')){
        document.querySelector('.ip').classList.remove('ativo')
        elementoBotao.classList.add('ativo')
    }

    if(elementoBotao.classList.contais('ip')){
        document.querySelector('.sc').classList.remove('ativo')
        elementoBotao.classList.add('ativo')
    }
}


verificarSeEVisualizacao()