var map = L.map('map').setView([-23.55052, -46.633308], 13);

L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '© OpenStreetMap contributors'
}).addTo(map);

// Função para buscar e exibir locais Sangue Corinthiano
async function carregarLocaisSC() {
    try {
        const response = await fetch('http://localhost:8080/instituicoes/tipo/Sangue corinthiano');
        const locais = await response.json();
        
        locais.forEach(local => {
            L.marker([local.latitude, local.longitude]).addTo(map)
                .bindPopup(`${local.nome}`)
                .openPopup();
        });
    } catch (error) {
        console.error("Erro ao carregar locais Sangue Corinthiano:", error);
    }
}

carregarLocaisSC();
