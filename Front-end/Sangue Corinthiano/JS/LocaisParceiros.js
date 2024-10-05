// Inicializa o mapa
var map = L.map('map').setView([-23.55052, -46.633308], 13);

// Adiciona o tile layer
L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '© OpenStreetMap contributors'
}).addTo(map);

// Função para carregar dados dos parceiros
async function carregarParceiros() {
    try {
        // URL corrigida
        const response = await fetch('http://localhost:8080/instituicoes/parceiros');
        const locais = await response.json();

        // Adiciona os pontos no mapa
        locais.forEach(local => {
            L.marker([local.latitude, local.longitude]).addTo(map)
                .bindPopup(local.nome);
        });
    } catch (error) {
        console.error("Erro ao carregar os locais parceiros: ", error);
    }
}

// Chama a função para carregar os parceiros
carregarParceiros();
