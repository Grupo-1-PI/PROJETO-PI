//Carissimos,
//Morram.























// async function atualizarLocalSC(idEnderecoInstituicao, idInstituicao){
//     try {
//         const response = await fetch(`http://localhost:8080/enderecos-instituicoes/${idEnderecoInstituicao}/${idInstituicao}`, {
//             method: "PUT",
//             headers: {
//                 "Content-Type": "application/json"
//             },
//             body: JSON.stringify({
//                 idEnderecoInstituicao: idEnderecoInstituicao,
//                 rua: document.getElementById("input-rua").value,
//                 numero: parseInt(document.getElementById("input-numero").value),
//                 bairro: document.getElementById("input-bairro").value,
//                 complemento: document.getElementById("input-complemento").value,
//                 cidade: document.getElementById("input-cidade").value,
//                 estado: document.getElementById("input-estado").value,
//                 cep: document.getElementById("input-cep").value,
//                 latitude: parseFloat(document.getElementById("input-latitude").value), 
//                 longitude: parseFloat(document.getElementById("input-longitude").value),
//                 instituicao: {
// 		            idInstituicao: idInstituicao
// 	            }
//             })
//         });

//         const dados = await response.json()
//         console.log(dados)


//     } catch (error) {

//         console.log(`Houve um erro: ${error}`)
//     }
// }

//sla
// function adicionarEscutaBotaoAtualizar() {
//     var botaoAtualizar = document.querySelector('#button-addon2')

//     botaoAtualizar.addEventListener('click', function () {
//         Swal.fire({
//             title: "Você quer atualizar a campanha?",
//             showDenyButton: true,
//             showCancelButton: true,
//             confirmButtonText: "Sim",
//             denyButtonText: `Não`
//         }).then((result) => {
//             if (result.isConfirmed){
//                 atualizarLocalSC()
//                 Swal.fire("Salvo!", "", "sucess")
//             } else if(result.isDenied){
//                 Swal.fire("Não Alterado", "", "info");
//             }
//     }).catch((error) => {
//         console.log(`Houve um erro: ${error.message}`)
//     }
//     ) 
//     });
// }

// Cadastra a instituicao -- fecth de instituicao - cadastra
// Retorna os dados da instituicao -- retorna os dados com o id 
// Captura os dados da instituicao -- a gente envia para outra funcao
// Com base no id da instituicao a gente cadastra o endereco da instituicao


// Swal.fire({
//     template: "botao-atualizar-campanha"
//   });








// async function cadastrarInstituicao(){

//     try {
//         const response = await fetch(`http://localhost:8080/instituicoes`, {
//             method: "POST",
//             headers: {
//                 "Content-Type": "application/json"
//             },
//             body: JSON.stringify({
//                 nome: document.getElementById('input-nome').value,
//                 cnpj: document.getElementById('input-cnpj').value
//             })
//         });

//         const dados = await response.json()
//         console.log(dados)

//         return dados

//     } catch (error) {

//         console.log(`Houve um erro: ${error}`)
//     }
// }

// async function cadastrarLocalSC(instituicaoId){

//     console.log(instituicaoId);
    

//     try {
//         const response = await fetch(`http://localhost:8080/enderecos-instituicoes/${instituicaoId}`, {
//             method: "POST",
//             headers: {
//                 "Content-Type": "application/json"
//             },
//             body: JSON.stringify({
//                 rua: document.getElementById("input-rua").value,
//                 numero: parseInt(document.getElementById("input-numero").value),
//                 bairro: document.getElementById("input-bairro").value,
//                 complemento: document.getElementById("input-complemento").value,
//                 cidade: document.getElementById("input-cidade").value,
//                 estado: document.getElementById("input-estado").value,
//                 cep: document.getElementById("input-cep").value,
//                 latitude: document.getElementById("input-latitude").value, 
//                 longitude:document.getElementById("input-longitude").value,
//                 instituicao: {
// 		            idInstituicao: instituicaoId
// 	            }
//             })
//         });

//         const dados = await response.json()
//         console.log(dados)


//     } catch (error) {

//         console.log(`Houve um erro: ${error}`)
//     }
// }



// async function cadastrarInstituicaoCompleta(){
//     const novaInstituicao = await cadastrarInstituicao()
//     cadastrarLocalSC(novaInstituicao.idInstituicao)
// }



function removerCacheSessionStorage(){
    sessionStorage.removeItem("ENDERECO-INSTITUICAO-ID")
}


// function atualizarEstadoCheck(){
//     var elChecked = document.getElementById('input-parceiro')
//     elChecked.checked = !elChecked.checked
// }

// function atualizarCorBotao(elementoBotao){
//     if(elementoBotao.classList.contains('sc')){
//         document.querySelector('.ip').classList.remove('ativo')
//         elementoBotao.classList.add('ativo')
//     }

//     if(elementoBotao.classList.contais('ip')){
//         document.querySelector('.sc').classList.remove('ativo')
//         elementoBotao.classList.add('ativo')
//     }
// }




// function atualizarCorBotao(elementoA){

//     if(elementoA.classList.contains('sc')){
//         if(document.querySelector('.btn-ip').classList.contains('ativo')){
//             document.querySelector('.btn-ip').classList.remove('ativo')
//         }
//         document.querySelector('.btn-sc').classList.add('ativo')
//     }

//     if(elementoA.classList.contains('ip')){
//        if(document.querySelector('.btn-sc').classList.contains('ativo')){
//         document.querySelector('.btn-sc').classList.remove('ativo')
//        }
//         document.querySelector('.btn-ip').classList.add('ativo')
//     }
// }




