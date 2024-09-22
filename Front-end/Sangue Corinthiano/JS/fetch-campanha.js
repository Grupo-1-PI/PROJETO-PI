// Cadastra a instituicao -- fecth de instituicao - cadastra
// Retorna os dados da instituicao -- retorna os dados com o id 
// Captura os dados da instituicao -- a gente envia para outra funcao
// Com base no id da instituicao a gente cadastra o endereco da instituicao


// Swal.fire({
//     template: "botao-atualizar-campanha"
//   });


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

        const dados = await response.json()
        console.log(dados)

        return dados

    } catch (error) {

        console.log(`Houve um erro: ${error}`)
    }
}


async function cadastrarLocalSC(instituicaoId){

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
                latitude: parseFloat(document.getElementById("input-latitude").value), 
                longitude: parseFloat(document.getElementById("input-longitude").value),
                idInstituicao: {
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



async function atualizarInstituicao(){

    const instituicaoId = 1

    try {
        const response = await fetch(`http://localhost:8080/instituicoes/${instituicaoId}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                idInstituicao: instituicaoId,
                nome: document.getElementById('input-nome').value,
                cnpj: document.getElementById('input-cnpj').value
            })
        });

        const dados = await response.json()
        console.log(dados)

        return dados


    } catch (error) {

        console.log(`Houve um erro: ${error}`)
    }
}



async function atualizarLocalSC(instituicaoId){
    try {
        const response = await fetch(`http://localhost:8080/enderecos-instituicoes/${instituicaoId}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                idEnderecoInstituicao: 1,
                rua: document.getElementById("input-rua").value,
                numero: parseInt(document.getElementById("input-numero").value),
                bairro: document.getElementById("input-bairro").value,
                complemento: document.getElementById("input-complemento").value,
                cidade: document.getElementById("input-cidade").value,
                estado: document.getElementById("input-estado").value,
                cep: document.getElementById("input-cep").value,
                latitude: parseFloat(document.getElementById("input-latitude").value), 
                longitude: parseFloat(document.getElementById("input-longitude").value),
                idInstituicao: {
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
    const novaInstituicao = await atualizarInstituicao()
    atualizarLocalSC(novaInstituicao.idInstituicao)
}







