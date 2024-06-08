const chatIcon = document.getElementById('chat-icon');
const chatContainer = document.getElementById('chat-container');
const chatBody = document.getElementById('chat-body');
const userInput = document.getElementById('user-input');

// Função para alternar a visibilidade da janela de chat
function toggleChat() {
    if (chatContainer.style.display === 'block') {
        chatContainer.style.display = 'none';
    } else {
        chatContainer.style.display = 'block';
        addMessage('Querido doador, estou aqui para te ajudar!', 'bot');
        addMessage('Escolha uma opção:<br><br>1- Obter informações sobre a Doação.<br><br>2- Criar uma conta.<br><br>3- Finalizar Chat.', 'bot');
    }
}

// Função para adicionar mensagens ao chat
function addMessage(text, sender) {
    const messageElement = document.createElement('div');
    messageElement.innerHTML = text; // Usamos innerHTML para interpretar as tags HTML
    messageElement.classList.add('message', sender + '-message');

    chatBody.appendChild(messageElement);
    chatBody.scrollTop = chatBody.scrollHeight;
}

// Função para processar a entrada do usuário
function processUserInput() {
    const userInputText = userInput.value.trim().toLowerCase();
    userInput.value = '';

    switch(userInputText) {
        case '1':
            addMessage('Opção 1 selecionada:<br>Obter informações sobre a Doação.', 'user');
            addMessage(`
                <div class="title">Quem pode doar sangue?</div>
                <ul>
                    <li>Qualquer pessoa saudável.</li>
                    <li>Idade entre 16 e 69 anos.</li>
                    <li>Peso igual ou superior a 50kg.</li>
                    <li>Boas condições de saúde.</li>
                </ul>
                <div class="title">Requisitos adicionais:</div>
                <ul>
                    <li>Não estar em jejum.</li>
                    <li>Ter dormido bem na noite anterior.</li>
                    <li>Evitar bebidas alcoólicas nas últimas 12 horas.</li>
                    <li>Apresentar documento oficial com foto no momento da doação.</li>
                </ul>
                <div class="title">Restrições temporárias ou permanentes:</div>
                <ul>
                    <li>Gestantes.</li>
                    <li>Pessoas com doenças infecciosas.</li>
                    <li>Condições médicas específicas.</li>
                    <li>Cirurgias recentes.</li>
                </ul>
                <div class="title">Para mais informações:</div>
                <p>Entre em contato conosco para esclarecer dúvidas específicas sobre quem pode ou não doar sangue.<br>
                Email: <a href="mailto:sanguecorinthianosp@outlook.com" class="email-link">sanguecorinthianosp@outlook.com</a></p>
                <div class="contact-links">
                    - <a href="https://api.whatsapp.com/send?phone=5511992739503&text=Ol%C3%A1%2C+estava+visitando+seu+site+e+me+interessei+pelo+seu+trabalho." target="_blank">WhatsApp</a><br>
                    - <a href="https://www.instagram.com/sanguecorinthiano/" target="_blank">Instagram</a><br>
                    - <a href="https://www.facebook.com/sangueCorinthiano/" target="_blank">Facebook</a><br>
                    - <a href="https://x.com/i/flow/login?redirect_after_login=%2Fsgcorinthiano" target="_blank">Twitter</a>
                </div>
            `, 'bot');

            addMessage('Posso te ajudar com outra coisa?<br><br>2- Criar uma conta.<br><br>3- Finalizar Chat.', 'bot');

            break;


        case '2':
        addMessage('Opção 2 selecionada:<br>Ótimo, vamos criar uma conta!', 'user');
         break;

        case '3':
            addMessage('Opção 3 selecionada:<br>Finalizar Chat.', 'user');
            setTimeout(function() {
                chatContainer.style.display = 'none';
            }, 2000);
            break;
        default:
            addMessage('Desculpe, não entendi. Por favor, selecione uma opção válida.', 'bot');
    }
}

// Evento para processar a entrada do usuário ao pressionar Enter
userInput.addEventListener('keypress', function(e) {
    if (e.key === 'Enter') {
        processUserInput();
    }
});

// Adiciona evento de clique ao ícone do chatbot
chatIcon.addEventListener('click', toggleChat);
