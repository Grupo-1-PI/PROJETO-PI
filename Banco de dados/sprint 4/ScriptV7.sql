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
  tipo_instituicao VARCHAR(45),
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
  cpf_doador CHAR(11),
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
INSERT INTO origem_trafego (opcao) VALUES ('A'), ('B'), ('C'), ('D'), ('E');

-- Inserindo dados na tabela 'doador'
INSERT INTO doador (nome, email, dt_nasc, sexo, primeira_doacao, senha, id_origem_trafego) 
VALUES ('João Silva', 'joao.silva@example.com', '1990-01-15', 'M', 'S', 'senha123', 1),
       ('Maria Oliveira', 'maria.oliveira@example.com', '1985-05-20', 'F', 'N', 'senha456', 2),
       ('Carlos Souza', 'carlos.souza@example.com', '1978-09-30', 'M', 'S', 'senha789', 3),
       ('Ana Santos', 'ana.santos@example.com', '1995-12-12', 'F', 'N', 'senha101', 4),
       ('Pedro Lima', 'pedro.lima@example.com', '2000-03-22', 'M', 'S', 'senha202', 5);

-- Inserindo dados na tabela 'telefone_doador'
INSERT INTO telefone_doador (ddd, tel_cel, id_doador) 
VALUES ('11', '987654321', 1), 
       ('21', '987654322', 2), 
       ('31', '987654323', 3), 
       ('41', '987654324', 4), 
       ('51', '987654325', 5);

-- Inserindo dados na tabela 'log_doador'
INSERT INTO log_doador (descricao) 
VALUES ('Primeiro acesso'), 
       ('Atualização de perfil'), 
       ('Nova doação registrada'), 
       ('Cancelamento de agendamento'), 
       ('Alteração de senha');

-- Inserindo dados na tabela 'acesso_doador'
INSERT INTO acesso_doador (dt_hora, id_doador, id_log_doador) 
VALUES (NOW(), 1, 1), 
       (NOW(), 2, 2), 
       (NOW(), 3, 3), 
       (NOW(), 4, 4), 
       (NOW(), 5, 5);

-- Inserindo dados na tabela 'instituicao'
INSERT INTO instituicao (nome, cnpj, tipo_instituicao) 
VALUES ('Instituto Vida', '12345678901234', 'Instituição Parceira'), 
       ('Banco de Sangue Esperança', '23456789012345', 'Instituição Parceira'), 
       ('Hemocentro São Paulo', '34567890123456', 'Instituição Parceira'), 
       ('Fundação Pró-Sangue', '45678901234567', 'Instituição Parceira'), 
       ('Sangue Corinthiano', '56789012345678', 'Sangue Corinthiano');

-- Inserindo dados na tabela 'endereco_instituicao'
INSERT INTO endereco_instituicao (rua, numero, bairro, complemento, cidade, estado, cep, latitude, longitude, id_instituicao) 
VALUES ('Av. Paulista', 1000, 'Bela Vista', '', 'São Paulo', 'SP', '01310200', -23.561396, -46.655881, 1), 
       ('Rua XV de Novembro', 200, 'Centro', 'Bloco B', 'Rio de Janeiro', 'RJ', '20010100', -22.897635, -43.179345, 2), 
       ('Rua Vergueiro', 300, 'Liberdade', 'Ap. 101', 'São Paulo', 'SP', '01504001', -23.574769, -46.624094, 3), 
       ('Av. Rebouças', 400, 'Pinheiros', 'Sala 10', 'São Paulo', 'SP', '05402000', -23.561832, -46.679292, 4), 
       ('Rua do Estádio', 500, 'Tatuapé', 'Estádio', 'São Paulo', 'SP', '03072000', -23.532183, -46.639513, 5);

-- Inserindo dados na tabela 'unidade_sangue_corinthiano'
INSERT INTO unidade_sangue_corinthiano (nome, cnpj, codigo_unidade) 
VALUES ('Unidade Tatuapé', '67890123456789', 101), 
       ('Unidade Itaquera', '78901234567890', 102), 
       ('Unidade Paulista', '89012345678901', 103), 
       ('Unidade Morumbi', '90123456789012', 104), 
       ('Unidade Centro', '01234567890123', 105);

-- Inserindo dados na tabela 'nivel_acesso'
INSERT INTO nivel_acesso (nome, token) 
VALUES ('Admin', 'tokenadmin'), 
       ('Gerente', 'tokengerente'), 
       ('Coordenador', 'tokencoord'), 
       ('Assistente', 'tokenassist'), 
       ('Voluntário', 'tokenvolunt');

-- Inserindo dados na tabela 'cargo'
INSERT INTO cargo (nome, area, id_nivel_acesso, codigo_cargo) 
VALUES ('Administrador', 'TI', 1, 201), 
       ('Gerente Geral', 'Operações', 2, 202), 
       ('Coordenador de Doações', 'Captação', 3, 203), 
       ('Assistente Administrativo', 'Financeiro', 4, 204), 
       ('Voluntário', 'Eventos', 5, 205);

-- Inserindo dados na tabela 'admin_sangue_corinthiano'
INSERT INTO admin_sangue_corinthiano (nome, cpf, email, senha, dt_nasc, sexo, id_unidade, id_cargo) 
VALUES ('Carlos Administrador', '12345678901', 'admin.carlos@example.com', 'senhaadmin', '1980-06-15', 'M', 1, 1), 
       ('Maria Gerente', '23456789012', 'gerente.maria@example.com', 'senhagerente', '1975-08-20', 'F', 2, 2), 
       ('João Coordenador', '34567890123', 'coord.joao@example.com', 'senhacoord', '1985-02-10', 'M', 3, 3), 
       ('Ana Assistente', '45678901234', 'assist.ana@example.com', 'senhaassist', '1990-04-25', 'F', 4, 4), 
       ('Pedro Voluntário', '56789012345', 'volunt.pedro@example.com', 'senhavolunt', '1995-11-30', 'M', 5, 5);

-- Inserindo dados na tabela 'telefone_admin'
INSERT INTO telefone_admin (ddd, tel_cel, id_admin) 
VALUES ('11', '912345678', 1), 
       ('21', '922345679', 2), 
       ('31', '932345670', 3), 
       ('41', '942345671', 4), 
       ('51', '952345672', 5);

-- Inserindo dados na tabela 'log_admin'
INSERT INTO log_admin (descricao) 
VALUES ('Primeiro acesso admin'), 
       ('Admin criou nova campanha'), 
       ('Admin gerenciou doação'), 
       ('Admin alterou senha'), 
       ('Admin encerrou campanha');

-- Inserindo dados na tabela 'acesso_admin'
INSERT INTO acesso_admin (dt_hora, id_admin, id_log_admin) 
VALUES (NOW(), 1, 1), 
       (NOW(), 2, 2), 
       (NOW(), 3, 3), 
       (NOW(), 4, 4), 
       (NOW(), 5, 5);

-- Inserindo dados na tabela 'campanha'
INSERT INTO campanha (nome, dt_inicio, dt_fim, id_instituicao, id_admin) 
VALUES ('Campanha Vida Nova', '2023-01-01', '2023-01-31', 1, 1), 
       ('Doe Sangue Salve Vidas', '2023-02-01', '2023-02-28', 2, 2), 
       ('Ajude o Próximo', '2023-03-01', '2023-03-31', 3, 3), 
       ('Sangue Bom', '2023-04-01', '2023-04-30', 4, 4), 
       ('Amor em Cada Gota', '2023-05-01', '2023-05-31', 5, 5);

-- Inserindo dados na tabela 'agendamento'
INSERT INTO agendamento (data, hora, cpf_doador, id_doador, id_instituicao, id_campanha, status) 
VALUES ('2024-08-01', '09:00:00', '12345678901', 1, 1, 1, 1), 
       ('2024-08-02', '10:00:00', '23456789012', 2, 2, 2, 1), 
       ('2024-08-03', '11:00:00', '34567890123', 3, 3, 3, 1), 
       ('2024-08-04', '12:00:00', '45678901234', 4, 4, 4, 1), 
       ('2024-08-05', '13:00:00', '56789012345', 5, 5, 5, 1);

-- Inserindo dados na tabela 'historico_agendamento'
INSERT INTO historico_agendamento (id_agendamento, data_modificacao, status_anterior, status_atual) 
VALUES (1, NOW(), 1, 2), 
       (2, NOW(), 1, 3), 
       (3, NOW(), 1, 2), 
       (4, NOW(), 1, 3), 
       (5, NOW(), 1, 2);

-- Consultas corrigidas para verificar os dados:

-- Verificando as instituições e seus endereços
SELECT i.nome, i.tipo_instituicao, e.rua, e.latitude, e.longitude
FROM instituicao i
JOIN endereco_instituicao e ON i.id_instituicao = e.id_instituicao;

-- Verificando doadores
SELECT * FROM doador;

-- Verificando agendamentos
SELECT * FROM agendamento;

-- Verificando a taxa de conversão de agendamentos
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

-- Verificando a taxa de cancelamento de agendamentos
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
