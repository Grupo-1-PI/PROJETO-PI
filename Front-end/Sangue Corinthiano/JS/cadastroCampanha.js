async function cadastrarCampanhaSC(event) {
    event.preventDefault();  // Previne o comportamento padrão do formulário

    try {
        const response = await fetch(`http://localhost:8080/campanhas`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                nome: document.getElementById("campaign-name").value,
                dtInicio: document.getElementById("start-date").value,
                dtFim: document.getElementById("end-date").value,
                responsavel: document.getElementById("responsible").value,
                local: document.getElementById("location").value
            })
        });

        // Verifica o tipo de conteúdo da resposta
        const contentType = response.headers.get("Content-Type");

        if (response.ok) {
            // Se o tipo de conteúdo for JSON, tenta analisar a resposta como JSON
            if (contentType && contentType.includes("application/json")) {
                const dados = await response.json();
                console.log(dados);
                alert("Campanha criada com sucesso!");
            } else {
                // Se não for JSON, captura o texto simples da resposta
                const text = await response.text();
                console.log(text);
                alert("Campanha criada com sucesso");
            }
        } else {
            // Captura o erro da resposta e exibe a mensagem
            const errorMessage = contentType.includes("application/json") ? await response.json() : await response.text();
            alert(`Erro ao cadastrar a campanha: ${errorMessage}`);
        }

    } catch (error) {
        console.log(`Houve um erro: ${error}`);
        alert(`Erro ao cadastrar a campanha: ${error.message}`);
    }
}
