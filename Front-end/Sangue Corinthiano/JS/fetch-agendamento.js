
function carregarDoadores() {
    
    fetch('http://localhost:8080/agendamentos/doadoresAgendamento')
        .then(response => response.json()) 
        .then(doadores => {
            const container = document.querySelector('.containerFilho');
            container.innerHTML = ''; 

            doadores.forEach(doador => {
                const div = document.createElement('div');
                div.classList.add('inputCadastrado');
                div.style.marginBottom = '20px';

                
                div.innerHTML = `
                    <span>${doador.nome}</span>
                    <span>${doador.hora}</span>
                    <div class="botaoVizuEdi">
                        <div class="botaoVizu">
                            <span>Compareceu</span>
                        </div>
                        <div class="botaoEdit">
                            <span>Não Compareceu</span>
                        </div>
                    </div>
                `;

                
                container.appendChild(div);
            });
        })
        .catch(error => {
            console.error('Erro ao carregar doadores:', error);
        });
}

function carregarTotalDoadores() {
   
    fetch('http://localhost:8080/agendamentos/doadoresTotalPorCampanha')
        .then(response => {
            if (response.status === 204) {
                return 0;  
            }
            return response.json(); 
        })
        .then(total => {
            
            const totalDoadoresSpan = document.querySelector('.tituloPreto');
            
            
            totalDoadoresSpan.innerHTML = `Total: ${total} doadores`;
        })
        .catch(error => {
            console.error('Erro ao carregar o total de doadores:', error);
        });
}

// document.querySelectorAll('.botaoVizu, .botaoEdit').forEach(button => {
//     button.addEventListener('click', function() {
//         const agendamentoId = this.getAttribute('data-agendamento-id');
//         const compareceu = this.classList.contains('botaoVizu'); 

//         fetch(`http://localhost:8080/agendamentos/atualizarComparecimento/${agendamentoId}`, {
//             method: 'PUT',
//             headers: {
//                 'Content-Type': 'a   pplication/json'
//             },
//             body: JSON.stringify({
//                 compareceu: compareceu
//             })
//         })
//         .then(response => {
//             if (response.ok) {
//                 alert('Status de comparecimento atualizado com sucesso!');
                
//             } else {
//                 alert('Falha ao atualizar o status de comparecimento.');
//             }
//         })
//         .catch(error => console.error('Erro:', error));
//     });
// });

window.onload = function() {
    carregarTotalDoadores();
    carregarDoadores();  
};


