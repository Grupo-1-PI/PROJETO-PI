DROP DATABASE IF EXISTS mais_vidas;
CREATE DATABASE IF NOT EXISTS mais_vidas;
USE mais_vidas;

CREATE TABLE IF NOT EXISTS origem_trafego (
  id_origem_trafego INT AUTO_INCREMENT,
  opcao CHAR(1),
  PRIMARY KEY (id_origem_trafego)
);

CREATE TABLE IF NOT EXISTS doador (
  id_doador INT AUTO_INCREMENT,
  nome VARCHAR(45),
  email VARCHAR(100),
  dt_nasc DATE,
  sexo CHAR(1),
  primeira_doacao CHAR(1),
  senha VARCHAR(45),
  id_origem_trafego INT,
  PRIMARY KEY (id_doador),
  CONSTRAINT fk_doador_origem_trafego FOREIGN KEY (id_origem_trafego) REFERENCES origem_trafego(id_origem_trafego)
);

CREATE TABLE IF NOT EXISTS telefone_doador (
  id_telefone INT AUTO_INCREMENT,
  ddd CHAR(2),
  tel_cel CHAR(9),
  id_doador INT,
  PRIMARY KEY (id_telefone),
  CONSTRAINT fk_telefone_doador FOREIGN KEY (id_doador) REFERENCES doador(id_doador)
);

CREATE TABLE IF NOT EXISTS log_doador (
  id_log_doador INT AUTO_INCREMENT,
  descricao VARCHAR(200),
  PRIMARY KEY (id_log_doador)
);

CREATE TABLE IF NOT EXISTS acesso_doador (
  id_acesso_doador INT AUTO_INCREMENT,
  dt_hora DATETIME,
  id_doador INT,
  id_log_doador INT,
  PRIMARY KEY (id_acesso_doador),
  CONSTRAINT fk_acesso_doador_doador FOREIGN KEY (id_doador) REFERENCES doador(id_doador),
  CONSTRAINT fk_acesso_doador_log_doador FOREIGN KEY (id_log_doador) REFERENCES log_doador(id_log_doador)
);

CREATE TABLE IF NOT EXISTS instituicao (
  id_instituicao INT AUTO_INCREMENT,
  nome VARCHAR(100),
  cnpj CHAR(14),
  parceiro BOOLEAN DEFAULT FALSE,
  PRIMARY KEY (id_instituicao)
);

CREATE TABLE IF NOT EXISTS endereco_instituicao (
  id_endereco_instituicao INT AUTO_INCREMENT,
  rua VARCHAR(45),
  numero INT,
  bairro VARCHAR(45),
  complemento VARCHAR(45),
  cidade VARCHAR(45),
  estado CHAR(2),
  cep CHAR(8),
  latitude DECIMAL(10, 8),
  longitude DECIMAL(11, 8),
  id_instituicao INT,
  PRIMARY KEY (id_endereco_instituicao),
  CONSTRAINT fk_endereco_instituicao FOREIGN KEY (id_instituicao) REFERENCES instituicao(id_instituicao)
);

CREATE TABLE IF NOT EXISTS unidade_sangue_corinthiano (
  id_unidade INT AUTO_INCREMENT,
  nome VARCHAR(45),
  cnpj CHAR(14),
  codigo_unidade INT,
  PRIMARY KEY (id_unidade)
);

CREATE TABLE IF NOT EXISTS nivel_acesso (
  id_nivel_acesso INT AUTO_INCREMENT,
  nome VARCHAR(45),
  token VARCHAR(45),
  PRIMARY KEY (id_nivel_acesso)
);

CREATE TABLE IF NOT EXISTS cargo (
  id_cargo INT AUTO_INCREMENT,
  nome VARCHAR(45),
  area VARCHAR(45),
  id_nivel_acesso INT,
  codigo_cargo INT,
  PRIMARY KEY (id_cargo),
  CONSTRAINT fk_cargo_nivel_acesso FOREIGN KEY (id_nivel_acesso) REFERENCES nivel_acesso(id_nivel_acesso)
);

CREATE TABLE IF NOT EXISTS admin_sangue_corinthiano (
  id_admin INT AUTO_INCREMENT,
  nome VARCHAR(45),
  cpf CHAR(11),
  email VARCHAR(45),
  senha VARCHAR(45),
  dt_nasc DATE,
  sexo CHAR(1),
  nivel_acesso INT NOT NULL DEFAULT 3,
  id_unidade INT,
  id_cargo INT,
  PRIMARY KEY (id_admin),
  CONSTRAINT fk_admin_unidade FOREIGN KEY (id_unidade) REFERENCES unidade_sangue_corinthiano(id_unidade),
  CONSTRAINT fk_admin_cargo FOREIGN KEY (id_cargo) REFERENCES cargo(id_cargo)
);

CREATE TABLE IF NOT EXISTS telefone_admin (
  id_telefone_admin INT AUTO_INCREMENT,
  ddd CHAR(2),
  tel_cel CHAR(9),
  id_admin INT,
  PRIMARY KEY (id_telefone_admin),
  CONSTRAINT fk_telefone_admin FOREIGN KEY (id_admin) REFERENCES admin_sangue_corinthiano(id_admin)
);

CREATE TABLE IF NOT EXISTS log_admin (
  id_log_admin INT AUTO_INCREMENT,
  descricao VARCHAR(200),
  PRIMARY KEY (id_log_admin)
);

CREATE TABLE IF NOT EXISTS acesso_admin (
  id_acesso_admin INT AUTO_INCREMENT,
  dt_hora DATETIME,
  id_admin INT,
  id_log_admin INT,
  PRIMARY KEY (id_acesso_admin),
  CONSTRAINT fk_acesso_admin_admin FOREIGN KEY (id_admin) REFERENCES admin_sangue_corinthiano(id_admin),
  CONSTRAINT fk_acesso_admin_log_admin FOREIGN KEY (id_log_admin) REFERENCES log_admin(id_log_admin)
);

CREATE TABLE IF NOT EXISTS campanha (
  id_campanha INT AUTO_INCREMENT,
  nome VARCHAR(45),
  dt_inicio DATE,
  dt_fim DATE,
  id_instituicao INT,
  id_admin INT,
  PRIMARY KEY (id_campanha),
  CONSTRAINT fk_campanha_instituicao FOREIGN KEY (id_instituicao) REFERENCES instituicao(id_instituicao),
  CONSTRAINT fk_campanha_admin FOREIGN KEY (id_admin) REFERENCES admin_sangue_corinthiano(id_admin)
);

CREATE TABLE IF NOT EXISTS agendamento (
  id_agendamento INT AUTO_INCREMENT,
  data DATE,
  hora TIME,
  cpf_doador CHAR(14),
  id_doador INT,
  id_instituicao INT,
  id_campanha INT,
  status TINYINT(1),
  PRIMARY KEY (id_agendamento),
  CONSTRAINT fk_agendamento_doador FOREIGN KEY (id_doador) REFERENCES doador(id_doador),
  CONSTRAINT fk_agendamento_instituicao FOREIGN KEY (id_instituicao) REFERENCES instituicao(id_instituicao),
  CONSTRAINT fk_agendamento_campanha FOREIGN KEY (id_campanha) REFERENCES campanha(id_campanha)
);

CREATE TABLE IF NOT EXISTS historico_agendamento (
  id_historico INT AUTO_INCREMENT,
  id_agendamento INT,
  data_modificacao DATETIME,
  status_anterior TINYINT(1),
  status_atual TINYINT(1),
  PRIMARY KEY (id_historico),
  CONSTRAINT fk_historico_agendamento FOREIGN KEY (id_agendamento) REFERENCES agendamento(id_agendamento)
);

DELIMITER //
CREATE TRIGGER after_update_agendamento
AFTER UPDATE ON agendamento
FOR EACH ROW
BEGIN
  IF OLD.status != NEW.status THEN
    INSERT INTO historico_agendamento (id_agendamento, data_modificacao, status_anterior, status_atual)
    VALUES (NEW.id_agendamento, NOW(), OLD.status, NEW.status);
  END IF;
END//
DELIMITER ;

-- Inserindo dados na tabela 'origem_trafego'
INSERT INTO origem_trafego (opcao) 
VALUES 
('A'), ('B'), ('C'), ('D');

-- Inserindo dados na tabela 'doador'
INSERT INTO doador (nome, email, dt_nasc, sexo, primeira_doacao, senha, id_origem_trafego) 
VALUES 
('João Souza', 'joao.souza@gmail.com', '1990-05-15', 'M', 'S', 'Nina2027', 1),
('Maria Silva', 'maria.silva@gmail.com', '1985-08-20', 'F', 'N', 'Mari76589', 2),
('Carlos Pereira', 'carlos.pereira@yahoo.com', '1978-11-10', 'M', 'S', 'Grow5678432', 3),
('Ana Santos', 'ana.santos@gmail.com', '1995-02-25', 'F', 'N', 'Senha101101', 4),
('Pedro Lima', 'pedro.lima@gmail.com', '2000-03-10', 'M', 'S', 'Zequinha123', 1),
('Paulo Oliveira', 'paulo.oliveira@hotmail.com', '1992-06-18', 'M', 'S', 'Pau654321', 2),
('Fernanda Ribeiro', 'fernanda.ribeiro@gmail.com', '1988-03-23', 'F', 'N', 'Fer123456', 3),
('José Cardoso', 'jose.cardoso@yahoo.com', '1980-11-25', 'M', 'S', 'Jose987654', 4),
('Renata Albuquerque', 'renata.albuquerque@gmail.com', '1993-07-11', 'F', 'N', 'Renata2023', 1),
('Luciana Mendes', 'luciana.mendes@gmail.com', '1997-04-15', 'F', 'S', 'LuMend654', 2),
('Rafael Teixeira', 'rafael.teixeira@hotmail.com', '1982-09-09', 'M', 'N', 'RafTex123', 3),
('Gabriela Dias', 'gabriela.dias@gmail.com', '1996-12-30', 'F', 'S', 'GabDia123', 4),
('Bruno Alves', 'bruno.alves@gmail.com', '1994-10-21', 'M', 'N', 'BruAlv456', 1),
('Débora Martins', 'debora.martins@yahoo.com', '1989-01-05', 'F', 'S', 'DebMar101', 2),
('Felipe Araújo', 'felipe.araujo@gmail.com', '2001-08-14', 'M', 'S', 'Felipe765', 3);

-- Inserindo dados na tabela 'telefone_doador'
INSERT INTO telefone_doador (ddd, tel_cel, id_doador) 
VALUES 
('11', '987654321', 1), ('21', '987654322', 2), ('21', '987654323', 3), 
('11', '987654324', 4), ('21', '987654325', 5), ('21', '987654326', 6), 
('11', '987654327', 7), ('21', '987654328', 8), ('11', '987654329', 9), 
('11', '987654330', 10), ('15', '987654331', 11), ('15', '987654332', 12), 
('11', '987654333', 13), ('55', '987654334', 14), ('65', '987654335', 15);

-- Inserindo dados na tabela 'log_doador'
INSERT INTO log_doador (descricao) 
VALUES 
('Primeiro acesso'), ('Atualização de perfil'), ('Nova doação registrada'), 
('Cancelamento de agendamento'), ('Alteração de senha'), ('Confirmação de cadastro'), 
('Doação realizada com sucesso'), ('Participação em campanha'), ('E-mail de lembrete enviado'), 
('Atualização de dados pessoais'), ('Histórico revisado'), ('Doação agendada'), 
('Perfil inativo'), ('Retorno ao sistema'), ('Ação não autorizada');

-- Inserindo dados na tabela 'acesso_doador'
INSERT INTO acesso_doador (dt_hora, id_doador, id_log_doador) 
VALUES 
(NOW(), 1, 1), (NOW(), 2, 2), (NOW(), 3, 3), (NOW(), 4, 4), (NOW(), 5, 5), 
(NOW(), 6, 6), (NOW(), 7, 7), (NOW(), 8, 8), (NOW(), 9, 9), (NOW(), 10, 10), 
(NOW(), 11, 11), (NOW(), 12, 12), (NOW(), 13, 13), (NOW(), 14, 14), (NOW(), 15, 15);

-- Inserindo dados na tabela 'instituicao'
INSERT INTO instituicao (nome, cnpj, parceiro) 
VALUES 
('Instituto Vida', '12345678000199', true), ('Sangue Corinthiano RJ', '23456789000188', false), 
('Hemocentro São Paulo', '34567890000177', true), ('Fundação Pró-Sangue', '45678900000166', true), 
('Sangue Corinthiano São Paulo', '56789000000155', false);

-- Inserindo dados na tabela 'endereco_instituicao'
INSERT INTO endereco_instituicao (rua, numero, bairro, complemento, cidade, estado, cep, latitude, longitude, id_instituicao) 
VALUES 
('Rua do Estádio', 50, 'Tatuapé', '', 'São Paulo', 'SP', '03062000', -23.532293, -46.636798, 1), 
('Av. Brasil', 1000, 'Centro', 'Sala 5', 'Rio de Janeiro', 'RJ', '20040002', -22.908348, -43.172896, 2), 
('Rua Vergueiro', 150, 'Liberdade', '', 'São Paulo', 'SP', '01504001', -23.573091, -46.624412, 3), 
('Av. Paulista', 2200, 'Bela Vista', '', 'São Paulo', 'SP', '01310200', -23.561399, -46.655769, 4), 
('Rua Corinthians', 10, 'Itaquera', '', 'São Paulo', 'SP', '08295200', -23.544917, -46.475425, 5);

-- Inserindo dados na tabela 'unidade_sangue_corinthiano'
INSERT INTO unidade_sangue_corinthiano (nome, cnpj, codigo_unidade) 
VALUES 
('Unidade RJ', '67890123000199', 101), 
('Unidade Itaquera', '78901234000188', 102), 
('Unidade BH', '89012345000177', 103), 
('Unidade PB', '90123456000166', 104), 
('Unidade SC', '01234567000155', 105);

-- Inserindo dados na tabela 'nivel_acesso'
INSERT INTO nivel_acesso (nome, token) 
VALUES 
('Admin', 'tokenadmin'), ('Gerente', 'tokengerente'), ('Coordenador', 'tokencoord'), 
('Assistente', 'tokenassist'), ('Voluntário', 'tokenvolunt');

-- Inserindo dados na tabela 'cargo'
INSERT INTO cargo (nome, area, id_nivel_acesso, codigo_cargo) 
VALUES 
('Administrador', 'TI', 1, 201), 
('Gerente Geral', 'Operações', 2, 202), 
('Coordenador de Doações', 'Captação', 3, 203), 
('Assistente Administrativo', 'Financeiro', 4, 204), 
('Voluntário', 'Eventos', 5, 205);

-- Inserindo dados na tabela 'admin_sangue_corinthiano'
INSERT INTO admin_sangue_corinthiano (nome, cpf, email, senha, dt_nasc, sexo, nivel_acesso, id_unidade, id_cargo) 
VALUES 
('Carlos Albuquerque', '12345678901', 'admin.carlos@gmail.com', 'Admin123', '1980-06-15', 'M', 1, 1, 1), 
('Vânia Simonetti', '23456789012', 'gerente.vania@gmail.com', 'Gerente456', '1975-08-20', 'F', 2, 2, 2), 
('João Coordenador', '34567890123', 'coord.joao@gmail.com', 'Coord789', '1985-02-10', 'M', 3, 3, 3), 
('Ana Assistente', '45678901234', 'assist.ana@gmail.com', 'Assist101', '1990-04-25', 'F', 4, 4, 4), 
('Pedro Voluntário', '56789012345', 'volunt.pedro@gmail.com', 'Volunt202', '1995-11-30', 'M', 5, 5, 5);


-- Inserindo dados na tabela 'campanha'
INSERT INTO campanha (nome, dt_inicio, dt_fim, id_instituicao, id_admin) 
VALUES 
('Doe Sangue em Março', '2024-03-01', '2024-03-31', 5, 1), 
('Sangue Corinthiano - Junho', '2024-06-01', '2024-06-30', 5, 2), 
('Doe Vida em Setembro', '2024-09-01', '2024-09-30', 5, 3), 
('Doe Sangue em Novembro', '2024-11-27', '2024-11-28', 5, 4), 
('Março de Solidariedade', '2025-03-01', '2025-03-31', 5, 5);


-- Inserindo dados na tabela 'agendamento'
INSERT INTO agendamento (data, hora, cpf_doador, id_doador, id_instituicao, id_campanha, status) 
VALUES 
-- Campanha: Doe Sangue em Março
('2024-03-01', '09:00:00', '12345678901', 1, 5, 1, 1), 
('2024-03-05', '10:30:00', '23456789012', 2, 5, 1, 1), 
('2024-03-10', '11:15:00', '34567890123', 3, 5, 1, 1), 
('2024-03-15', '14:00:00', '45678901234', 4, 5, 1, 1), 
('2024-03-20', '15:30:00', '56789012345', 5, 5, 1, 2), 

-- Campanha: Sangue Corinthiano - Junho
('2024-06-01', '08:45:00', '12345678901', 1, 5, 2, 1), 
('2024-06-05', '09:30:00', '23456789012', 2, 5, 2, 1), 
('2024-06-10', '10:15:00', '34567890123', 3, 5, 2, 1), 
('2024-06-15', '13:00:00', '45678901234', 4, 5, 2, 2), 
('2024-06-20', '14:30:00', '56789012345', 5, 5, 2, 1), 

-- Campanha: Doe Vida em Setembro
('2024-09-01', '09:00:00', '12345678901', 1, 5, 3, 1), 
('2024-09-10', '10:00:00', '23456789012', 2, 5, 3, 1), 
('2024-09-15', '11:00:00', '34567890123', 3, 5, 3, 1), 
('2024-09-20', '12:30:00', '45678901234', 4, 5, 3, 2), 
('2024-09-25', '14:45:00', '56789012345', 5, 5, 3, 1), 

-- Campanha: Doe Sangue em Novembro
('2024-11-27', '08:30:00', '12345678901', 1, 5, 4, 1), 
('2024-11-27', '09:30:00', '23456789012', 2, 5, 4, 1), 
('2024-11-27', '10:30:00', '34567890123', 3, 5, 4, 1), 
('2024-11-27', '11:30:00', '45678901234', 4, 5, 4, 2), 
('2024-11-27', '14:00:00', '56789012345', 5, 5, 4, 1), 

-- Campanha: Março de Solidariedade (2025)
('2025-03-01', '08:30:00', '12345678901', 1, 5, 5, 1), 
('2025-03-05', '09:45:00', '23456789012', 2, 5, 5, 1), 
('2025-03-10', '10:30:00', '34567890123', 3, 5, 5, 1), 
('2025-03-15', '11:15:00', '45678901234', 4, 5, 5, 1), 
('2025-03-20', '14:00:00', '56789012345', 5, 5, 5, 1);


-- Consultas para verificação de dados:

SELECT i.nome, i.parceiro, e.rua, e.latitude, e.longitude
FROM instituicao i
JOIN endereco_instituicao e ON i.id_instituicao = e.id_instituicao;

SELECT * FROM doador;

SELECT * FROM agendamento;

SELECT
    (COUNT(DISTINCT a.id_agendamento) / COUNT(DISTINCT a.id_agendamento)) AS taxa_conversao
FROM agendamento a
JOIN doador d ON a.id_doador = d.id_doador
WHERE a.id_campanha = (
    SELECT id_campanha
    FROM campanha
    WHERE id_instituicao = (
        SELECT id_instituicao
        FROM unidade_sangue_corinthiano
        ORDER BY id_unidade DESC
        LIMIT 1
    )
    ORDER BY dt_inicio DESC
    LIMIT 1
);

SELECT
    (SUM(CASE WHEN a.status = 2 THEN 1 ELSE 0 END) / COUNT(a.id_agendamento)) AS taxa_cancelamento
FROM agendamento a
WHERE a.id_campanha = (
    SELECT id_campanha
    FROM campanha
    WHERE id_instituicao = (
        SELECT id_instituicao
        FROM unidade_sangue_corinthiano
        ORDER BY id_unidade DESC
        LIMIT 1
    )
    ORDER BY dt_inicio DESC
    LIMIT 1
);
