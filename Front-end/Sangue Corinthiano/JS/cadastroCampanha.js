    async function cadastrarCampanhaSC(campanha){

        console.log(campanha);
        
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
                    local: document.getElementById("location").value,
                    Campanha: {
                        idCampanha: campanha
                    }
                })  
            });

            if (response.ok === 201) {
                
                const dados = await response.json();
                console.log(dados);
                alert("Campanha criada com sucesso!");
            } else {
                
                alert(`Erro ao cadastrar a campanha: ${response.status()} - ${response.statusText()}`);
            }
    
        } catch (error) {
            
            console.log(`Houve um erro: ${error}`);
            alert(`Erro ao cadastrar a campanha: ${error.message}`);
        }
    }