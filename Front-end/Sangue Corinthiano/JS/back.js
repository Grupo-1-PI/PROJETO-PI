// // Função de Cadastro
// async function cadastrar() {
//     // Seu código de cadastro aqui
// }

// // Função de Login
// const url = 'http://localhost:8080';

// document.getElementById('formLogin').addEventListener('submit', function(event) {
//     // Seu código de login aqui
// });

// // Função de Tela de Usuário
// async function buscar() {
//     // Seu código de busca de usuário aqui
// }

// // Função de Dashboard
// async function buscar() {
//     // Seu código de busca de dashboard aqui
// }

// // Chamar funções principais ao carregar a página
// document.addEventListener('DOMContentLoaded', function() {
//     cadastrar(); // Exemplo de chamada de função de cadastro
//     buscar();    // Exemplo de chamada de função de busca (pode ser buscar usuário ou dashboard)
// });

// Função de Cadastro
async function cadastrar() {
    const url = 'http://localhost:8080';
    const id = sessionStorage.getItem('userId');

    const nomeDigitado = document.getElementById("nome").value;
    const sobrenomeDigitado = document.getElementById("sobrenome").value;
    const emailDigitado = document.getElementById("inputAddress").value;
    const telefoneDigitado = document.getElementById("telefone").value;
    const dtNascDigitada = document.getElementById("dtNasc").value;
    const senhaDigitada = document.getElementById("senha").value;

    const selectMotivo = document.getElementById("inputState");
    const motivoDigitado = selectMotivo.value;

    const SexoOpcoes = document.getElementsByName("gridRadios");
    let sexoSelecionado;
    SexoOpcoes.forEach(button => {
        if (button.checked) {
            sexoSelecionado = button.value;
        }
    });

    console.log(SexoOpcoes)
    console.log(sexoSelecionado)

    const nomeFinal = `${nomeDigitado} ${sobrenomeDigitado}`

    const dadosDoador = {
        "dtNasc": dtNascDigitada,
        "email": emailDigitado,
        "motivo": motivoDigitado,
        "nome": nomeFinal,
        //"telefone": telefoneDigitado,

        "primeiraDoacao": true,
        "senha": senhaDigitada,
        "sexo": sexoSelecionado
    }

    const respostaCadastro = await fetch(`${url}/doadores`, {
        method: "POST",
        body: JSON.stringify(dadosDoador),
        headers: { "Content-type": "application/json; charset=UTF-8" }
    });

    if (respostaCadastro.status == 201) {
        alert("Cadastro realizado com sucesso!")
        window.location.href = "./Login.html";
    } else {
        alert("Ocorreu um erro ao cadastrar")
    }
}

// Função de Login
async function login() {
const url = 'http://localhost:8080';

document.getElementById('formLogin').addEventListener('submit', function(event) {
    event.preventDefault();
    const formData = new FormData(this);
    const email = formData.get('InputEmail');
    const senha = formData.get('InputSenha');

    fetch(`${url}/doadores/login?email=${email}&senha=${senha}`, { method: 'POST' })
        .then(response => {
            alert("Hi")
            if (response.ok) {
                return response.json(); 
            } else {
                throw new Error('Login inválido');
            }
        })
        .then(data => {
            console.log("sla")
            console.log(data);
            if (data.message === "Login realizado com sucesso!") {
                const userId = data.userId; 
                alert("Login realizado com sucesso! ID do usuário: " + userId);
                sessionStorage.setItem('userId', userId);
                window.location.href = "\Usuario.html";
            } else {
                alert("Login inválido!");
            }
        })
        .catch(error => {
            alert("rtm")
            console.error('Erro durante a requisição:', error);
            alert("Erro durante a requisição: " + error.message);
        });
    });
}

// Função de Tela de Usuário
async function buscar() {
        const url = 'http://localhost:8080';
        const id = sessionStorage.getItem('userId');
    
        const resposta = await fetch(`${url}/doadores/${id}`);
        const respostaDadosDoador = await resposta.json();
        console.log("Resposta:", respostaDadosDoador);
    
        const nomeUsuario = await document.getElementById('nomeUsuario')
        nomeUsuario.innerHTML = await respostaDadosDoador.nome
    
        const cards = document.getElementById("card_usuario");
    
        cards.innerHTML = `
            <div class="container format-Container ">
                <div class="boxUsuario displayRow space-evenly">
                    <div class="usuario column format-Center space-evenly borda sombra">
                        <div class="icon">
    
                        </div>
    
                        <div class="texto">
                            <span>
                                ${respostaDadosDoador.nome}
                            </span>
                        </div>
                    </div>
                    <div class="formularioUsuario column format-Center borda sombra">
                        <div class="col-11">
                            <label for="inputEmail4" class="form-label">Nome:</label>
                            <div class="input-group mb-3">
                                <input type="text" id="nome" class="form-control" placeholder="Vania"
                                    aria-label="Recipient's username" aria-describedby="button-addon2" value="${respostaDadosDoador.nome}">
                                <button class="btn btn-outline-secondary" type="button" id="button-addon2">Editar</button>
                            </div>
                        </div>
                        <div class="col-11">
                            <label for="inputEmail4" class="form-label">E-mail:</label>
                            <div class="input-group mb-3">
                                <input type="text" id="email" class="form-control" placeholder="Vânia.SangueCorinthiano@gmail.com"
                                aria-label="Recipient's username" aria-describedby="button-addon2" value="${respostaDadosDoador.email}">
                                <button class="btn btn-outline-secondary" type="button" id="button-addon2">Editar</button>
                            </div>
                        </div>
                        <div class="col-11">
                            <label for="inputEmail4" class="form-label">Senha:</label>
                            <div class="input-group mb-3">
                                <input type="text" class="form-control" placeholder="**********"
                                    aria-label="Recipient's username" aria-describedby="button-addon2" disabled>
                            </div>
                        </div>
    
                        <div class="botoes space-between ">
                            <button id="buttonUpdate" type="button" class="btn btn-outline-success">Salvar</button>
                            <button id="buttonDelete" type="button" class="btn btn-outline-danger">Deletar Conta</button>
                        </div>
    
                    </div>
                    <!-- <div class="agendamentosUsuario">
        
                    </div> -->
                </div>
            </div>
            `
    
        document.getElementById('buttonUpdate').addEventListener('click', function() {
            const nomeDigitado = document.getElementById("nome")
        const emailDigitado = document.getElementById("email")
        const telefoneDigitado = document.getElementById("telefone")
    
        const nome = nomeDigitado.value
        const email = emailDigitado.value
    
        const a = document.getElementById("nome").value;
    
                fetch(`${url}/doadores/${id}?nome=${a}&email=${email}`, { method: 'PATCH'})
                    .then(response => {  
                        if (response.ok) {
                            console.log(a)
                            console.log(email)
                            alert('Dados atualizados com sucesso!');
                        } else {
                            alert('Erro ao atualizar dados!');
                            console.error('Falha no logout:', response.statusText);
                        }
                    })
                    .catch(error => console.error('Erro inesperado:', error));
            });
    
        
        document.getElementById('buttonDelete').addEventListener('click', function() {
                fetch(`${url}/doadores/${id}`, { method: 'DELETE' })
                    .then(response => {
                        if (response.ok) {
                            alert('Conta deletada com sucesso... Redirecionando');
                            window.location.href = "\Login.html";
                        } else {
                            console.error('Falha no logout:', response.statusText);
                        }
                    })
                    .catch(error => console.error('Erro inesperado:', error));
            });
    
            }

// Função de Dashboard
async function buscar2() {

    const url = 'http://localhost:8080';
    const id = sessionStorage.getItem('userId');

    const usuario = await fetch(`${url}/doadores/${id}`);
    const respostaDadosDoador = await usuario.json();
    console.log("Resposta:", respostaDadosDoador);

    const nomeUsuario2 = await document.getElementById('nomeUsuario2')
    nomeUsuario2.innerHTML = await respostaDadosDoador.nome

    const resposta = await fetch(`${url}/dash`);
    const dados = await resposta.json();
    console.log("Resposta:", dados);

    const meses = dados.map(item => item.mes);
    console.log("Meses:", meses);

    const quantidades = dados.map(item => item.quantidade);
    console.log("Quantidades:", quantidades);

    const anos = dados.map(item => item.ano);
    console.log("Anos:", anos);

    const anoMesArray = dados.map(item => `${item.mes}-${item.ano}`);
    console.log("Ano e Mês:", anoMesArray);

    const arrayUnico = meses.filter((item, index) => {
    return meses.indexOf(item) === index;
});

console.log(arrayUnico);    

    const ctx = document.getElementById('myChart');

    new Chart(ctx, {
        type: 'line',
        data: {
            labels: anoMesArray,
            datasets: [{
                label: '# of Votes',
                data: quantidades,
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

    // Organizar os dados para o segundo gráfico
    const dadosPorAno = {};
    dados.forEach(item => {
        if (!dadosPorAno[item.ano]) {
            dadosPorAno[item.ano] = {};
        }
        dadosPorAno[item.ano][item.mes] = item.quantidade;
    });

    // const mesesDoAno = ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'];
    const mesesDoAno = [1, 2, 3,4, 5, 6, 7, 8, 9, 10, 11, 12];

    const ctx2 = document.getElementById('myChart2');

    const datasets = Object.entries(dadosPorAno).map(([ano, valores]) => {
        return {
            label: ano,
            data: arrayUnico.map(mes => valores[mes] || 0),
            backgroundColor: ano === '2023' ? 'rgba(0, 0, 0, 1)' : 'rgba(255, 0, 0, 1)',
            borderColor: ano === '2023' ? 'rgba(0, 0, 0, 1)' : 'rgba(255, 0, 0, 1)',
            borderWidth: 1
        };
    });

    new Chart(ctx2, {
        type: 'bar',
        data: {
            labels: arrayUnico,
            datasets: datasets
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true,
                    title: {
                        display: true,
                        text: 'Quantidade de Pessoas'
                    },
                    grid: {
                        display: false
                    }
                },
                x: {
                    title: {
                        display: true,
                        text: 'Meses'
                    },
                    grid: {
                        display: false
                    }
                }
            },
            plugins: {
                legend: {
                    labels: {
                        color: 'rgba(0, 0, 0, 0.7)',
                        font: {
                            size: 14
                        }
                    }
                }
            }
        }
    });
}



// Chamar funções principais ao carregar a página
document.addEventListener('DOMContentLoaded', function() {
    cadastrar(); // Exemplo de chamada de função de cadastro
    buscar();    // Exemplo de chamada de função de busca (pode ser buscar usuário ou dashboard)
    buscar2();
    login();
});
