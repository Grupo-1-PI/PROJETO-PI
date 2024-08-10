// Inicializa o mapa centralizado em São Paulo
var map = L.map('map').setView([-23.55052, -46.633308], 13);

// Adiciona o tile layer ao mapa
L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '© OpenStreetMap contributors'
}).addTo(map);

// Adiciona o primeiro marcador (Instituição Pro Sangue SP)
L.marker([-23.55052, -46.633308]).addTo(map)
    .bindPopup('Instituição Pro Sangue SP')
    .openPopup();

// Adiciona o segundo marcador (Instituição Mais Sangue SP)
L.marker([-23.55823, -46.629832]).addTo(map)  //coordenadas 
    .bindPopup('Instituição Mais Sangue SP');
