// Inicializa o mapa centrado em São Paulo
const map = L.map('map').setView([-23.5505, -46.6333], 12);  // Coordenadas de São Paulo com zoom inicial

// Adiciona o tileLayer (camada do mapa) do OpenStreetMap
L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
}).addTo(map);

// Função para carregar e adicionar os locais Sangue Corinthiano ao mapa
function carregarLocaisSC() {
    fetch('http://localhost:8080/instituicoes/sangue-corinthiano')  // Faz a requisição para o endpoint correto
        .then(response => response.json())
        .then(data => {
            console.log(data);  // Verifica os dados no console para garantir que estão corretos

            // Itera sobre os dados recebidos e adiciona marcadores no mapa
            data.forEach(local => {
                // Verifica se as coordenadas são válidas antes de adicionar o marcador
                if (local.latitude && local.longitude) {
                    L.marker([local.latitude, local.longitude]).addTo(map)
                        .bindPopup(`<b>${local.nome}</b><br />`);
                } else {
                    console.error('Coordenadas inválidas para o local:', local);
                }
            });
        })
        .catch(error => {
            console.error('Erro ao carregar os locais Sangue Corinthiano:', error);
        });
}

// Chama a função para carregar os locais
carregarLocaisSC();
