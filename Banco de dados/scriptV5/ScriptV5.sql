
DROP DATABASE IF EXISTS maisVidas;
CREATE DATABASE IF NOT EXISTS maisVidas;
USE maisVidas;


CREATE TABLE IF NOT EXISTS OrigemTrafego (
  idOrigemTrafego INT AUTO_INCREMENT,
  opcao CHAR(1),
  PRIMARY KEY (idOrigemTrafego)
);


CREATE TABLE IF NOT EXISTS Doador (
  idDoador INT AUTO_INCREMENT,
  nome VARCHAR(45),
  email VARCHAR(100),
  dtNasc DATE,
  sexo CHAR(1),
  primeiraDoacao CHAR(1),
  senha VARCHAR(45),
  fkOrigemTrafego INT,
  PRIMARY KEY (idDoador),
  CONSTRAINT fk_origem_trafego FOREIGN KEY (fkOrigemTrafego) REFERENCES OrigemTrafego(idOrigemTrafego)
);


CREATE TABLE IF NOT EXISTS LogDoador (
  idLogDoador INT AUTO_INCREMENT,
  descricao VARCHAR(200),
  PRIMARY KEY (idLogDoador)
);

CREATE TABLE IF NOT EXISTS Instituicao (
  idInstituicao INT AUTO_INCREMENT,
  nome VARCHAR(100),
  cnpj BIGINT,
  latitude INT,
  longitude INT,
  PRIMARY KEY (idInstituicao)
);

CREATE TABLE IF NOT EXISTS UnidadeSangueCorinthiano (
  idUnidadeSangueCorinthiano INT AUTO_INCREMENT,
  nome VARCHAR(45),
  cnpj VARCHAR(45),
  codigoUnidade INT,
  PRIMARY KEY (idUnidadeSangueCorinthiano)
);

CREATE TABLE IF NOT EXISTS Nivel_acesso (
  idNivelAcesso INT AUTO_INCREMENT,
  nome VARCHAR(45),
  token VARCHAR(45),
  PRIMARY KEY (idNivelAcesso)
);

CREATE TABLE IF NOT EXISTS Cargo (
  idCargo INT AUTO_INCREMENT,
  nome VARCHAR(45),
  area VARCHAR(45),
  fkNivelAcesso INT,
  codigoCargo INT,
  PRIMARY KEY (idCargo),
  CONSTRAINT fk_nivel_acesso FOREIGN KEY (fkNivelAcesso) REFERENCES Nivel_acesso(idNivelAcesso)
);


CREATE TABLE IF NOT EXISTS AdminSangueCorinthiano (
  idAdminSangueCorinthiano INT AUTO_INCREMENT,
  nome VARCHAR(45),
  cpf CHAR(11),
  email VARCHAR(45),
  senha VARCHAR(45),
  dtNasc DATE,
  sexo CHAR(1),
  fkUnidade INT,
  fkCargo INT,
  PRIMARY KEY (idAdminSangueCorinthiano),
  CONSTRAINT fk_admin_unidade FOREIGN KEY (fkUnidade) REFERENCES UnidadeSangueCorinthiano(idUnidadeSangueCorinthiano),
  CONSTRAINT fk_admin_cargo FOREIGN KEY (fkCargo) REFERENCES Cargo(idCargo)
);

CREATE TABLE IF NOT EXISTS Campanha (
  idCampanha INT AUTO_INCREMENT,
  nome VARCHAR(45),
  dtInicio DATE,
  dtFim DATE,
  fkInstituicao INT,
  fkAdminSangueCorinthiano INT,
  PRIMARY KEY (idCampanha),
  CONSTRAINT fk_instituicao_campanha FOREIGN KEY (fkInstituicao) REFERENCES Instituicao(idInstituicao),
  CONSTRAINT fk_admin_sangue_corinthiano_campanha FOREIGN KEY (fkAdminSangueCorinthiano) REFERENCES AdminSangueCorinthiano(idAdminSangueCorinthiano)
);

CREATE TABLE IF NOT EXISTS Agendamento (
  idAgendamento INT AUTO_INCREMENT,
  data DATE,
  hora TIME,
  cpfDoador CHAR(11),
  fkDoador INT,
  fkInstituicao INT,
  fkCampanha INT,
  status TINYINT(1),
  PRIMARY KEY (idAgendamento),
  CONSTRAINT fk_doador_agendamento FOREIGN KEY (fkDoador) REFERENCES Doador(idDoador),
  CONSTRAINT fk_instituicao_agendamento FOREIGN KEY (fkInstituicao) REFERENCES Instituicao(idInstituicao),
  CONSTRAINT fk_campanha_agendamento FOREIGN KEY (fkCampanha) REFERENCES Campanha(idCampanha)
);


CREATE TABLE IF NOT EXISTS TelefoneDoador (
  idTelefone INT AUTO_INCREMENT,
  ddd CHAR(2),
  telCel CHAR(9),
  fkDoador INT,
  PRIMARY KEY (idTelefone),
  CONSTRAINT fk_doador_telefone FOREIGN KEY (fkDoador) REFERENCES Doador(idDoador)
);

CREATE TABLE IF NOT EXISTS AcessoDoador (
  idAcessoDoador INT AUTO_INCREMENT,
  dtHora DATETIME,
  fkDoador INT,
  fkLogDoador INT,
  PRIMARY KEY (idAcessoDoador),
  CONSTRAINT fk_doador_acesso_doador FOREIGN KEY (fkDoador) REFERENCES Doador(idDoador),
  CONSTRAINT fk_log_doador FOREIGN KEY (fkLogDoador) REFERENCES LogDoador(idLogDoador)
);

CREATE TABLE IF NOT EXISTS EnderecoInstituicao (
  idEnderecoInstituicao INT AUTO_INCREMENT,
  rua VARCHAR(45),
  numero INT,
  bairro VARCHAR(45),
  complemento VARCHAR(45),
  cidade VARCHAR(45),
  estado VARCHAR(45),
  cep INT,
  latitude INT,
  longitude INT,
  fkInstituicao INT,
  PRIMARY KEY (idEnderecoInstituicao),
  CONSTRAINT fk_instituicao_endereco_instituicao FOREIGN KEY (fkInstituicao) REFERENCES Instituicao(idInstituicao)
);

CREATE TABLE IF NOT EXISTS TelefoneAdmin (
  idTelefoneAdmin INT AUTO_INCREMENT,
  ddd CHAR(2),
  telCel CHAR(9),
  fkAdminSangueCorinthiano INT,
  PRIMARY KEY (idTelefoneAdmin),
  CONSTRAINT fk_admin_sangue_corinthiano_telefone_admin FOREIGN KEY (fkAdminSangueCorinthiano) REFERENCES AdminSangueCorinthiano(idAdminSangueCorinthiano)
);

CREATE TABLE IF NOT EXISTS LogAdmin (
  idLogAdmin INT AUTO_INCREMENT,
  descricao VARCHAR(200),
  PRIMARY KEY (idLogAdmin)
);

CREATE TABLE IF NOT EXISTS AcessoAdmin (
  idAcessoAdmin INT AUTO_INCREMENT,
  dtHora DATETIME,
  fkLogin INT,
  fkAdminSangueCorinthiano INT,
  PRIMARY KEY (idAcessoAdmin),
  CONSTRAINT fk_admin_acesso_admin FOREIGN KEY (fkAdminSangueCorinthiano) REFERENCES AdminSangueCorinthiano(idAdminSangueCorinthiano)
);

CREATE TABLE IF NOT EXISTS HistoricoAgendamento (
  idHistorico INT AUTO_INCREMENT,
  idAgendamento INT,
  dataModificacao DATETIME,
  statusAnterior TINYINT(1),
  statusAtual TINYINT(1),
  PRIMARY KEY (idHistorico),
  FOREIGN KEY (idAgendamento) REFERENCES Agendamento(idAgendamento)
);


DELIMITER //
CREATE TRIGGER AfterUpdateAgendamento
AFTER UPDATE ON Agendamento
FOR EACH ROW
BEGIN
  IF OLD.status != NEW.status THEN
    INSERT INTO HistoricoAgendamento (idAgendamento, dataModificacao, statusAnterior, statusAtual)
    VALUES (NEW.idAgendamento, NOW(), OLD.status, NEW.status);
  END IF;
END//
DELIMITER ;


-- OrigemTrafego
INSERT INTO OrigemTrafego (opcao) VALUES 
('A'), ('B'), ('C'), ('D'), ('E');

-- Doador
INSERT INTO Doador (nome, email, dtNasc, sexo, primeiraDoacao, senha, fkOrigemTrafego) VALUES
('Maria Silva', 'maria@example.com', '1990-05-15', 'F', 'S', 'senha123', 1),
('João Santos', 'joao@example.com', '1985-08-20', 'M', 'S', 'senha456', 2),
('Ana Oliveira', 'ana@example.com', '1998-01-10', 'F', 'N', 'senha789', 3),
('Pedro Souza', 'pedro@example.com', '1980-11-25', 'M', 'S', 'senhaabc', 1),
('Carla Lima', 'carla@example.com', '1995-03-30', 'F', 'S', 'senhadef', 2);

-- TelefoneDoador
INSERT INTO TelefoneDoador (ddd, telCel, fkDoador) VALUES
('11', '987654321', 1),
('21', '912345678', 2),
('31', '923456789', 3),
('41', '934567890', 4),
('51', '945678901', 5);

-- Instituicao
INSERT INTO Instituicao (nome, cnpj, latitude, longitude) VALUES
('Hospital ABC', 1234567890, 40.7128, -74.0060),
('Hospital XYZ', 9876543210, 34.0522, -118.2437);

-- UnidadeSangueCorinthiano
INSERT INTO UnidadeSangueCorinthiano (nome, cnpj, codigoUnidade) VALUES
('Unidade A', 'ABC123', 101),
('Unidade B', 'XYZ789', 202);

-- Nivel_acesso
INSERT INTO Nivel_acesso (nome, token) VALUES
('Nivel 1', 'token123'),
('Nivel 2', 'token456');

-- Cargo
INSERT INTO Cargo (nome, area, fkNivelAcesso, codigoCargo) VALUES
('Cargo 1', 'Area 1', 1, 1001),
('Cargo 2', 'Area 2', 2, 2002);

-- AdminSangueCorinthiano
INSERT INTO AdminSangueCorinthiano (nome, cpf, email, senha, dtNasc, sexo, fkUnidade, fkCargo) VALUES
('Admin 1', '12345678901', 'admin1@example.com', 'senha123', '1980-06-20', 'M', 1, 1),
('Admin 2', '98765432109', 'admin2@example.com', 'senha456', '1975-09-15', 'F', 2, 2);

-- Campanha
INSERT INTO Campanha (nome, dtInicio, dtFim, fkInstituicao, fkAdminSangueCorinthiano) VALUES
('Campanha 1', '2024-06-01', '2024-06-30', 1, 1),
('Campanha 2', '2024-07-01', '2024-07-31', 2, 2);

-- Inserindo dados na tabela Doador
INSERT INTO Doador (nome, email, dtNasc, sexo, primeiraDoacao, senha, fkOrigemTrafego) VALUES
('Maria Silva', 'maria@example.com', '1990-05-15', 'F', 'S', 'senha123', 1),
('João Santos', 'joao@example.com', '1985-08-20', 'M', 'S', 'senha456', 2),
('Ana Oliveira', 'ana@example.com', '1998-01-10', 'F', 'N', 'senha789', 3),
('Pedro Souza', 'pedro@example.com', '1980-11-25', 'M', 'S', 'senhaabc', 1),
('Carla Lima', 'carla@example.com', '1995-03-30', 'F', 'S', 'senhadef', 2);


-- Inserindo dados na tabela Instituicao
INSERT INTO Instituicao (nome, cnpj, latitude, longitude) VALUES
('Hospital ABC', 1234567890, 40.7128, -74.0060),
('Hospital XYZ', 9876543210, 34.0522, -118.2437);

-- Inserindo dados na tabela Campanha
INSERT INTO Campanha (nome, dtInicio, dtFim, fkInstituicao, fkAdminSangueCorinthiano) VALUES
('Campanha 1', '2024-01-01', '2024-12-31', 1, 1),
('Campanha 2', '2024-01-01', '2024-12-31', 2, 2);


-- Agendamento
INSERT INTO Agendamento (data, hora, cpfDoador, fkDoador, fkInstituicao, fkCampanha, status) VALUES
('2024-06-05', '09:00:00', '12345678901', 1, 1, 1, 0),
('2024-06-10', '10:30:00', '98765432109', 2, 2, 2, 1),
('2024-07-15', '14:00:00', '12345678901', 1, 1, 1, 1),
('2024-07-20', '15:30:00', '98765432109', 2, 2, 2, 1);

-- Inserindo dados para janeiro, março e junho de 2024
INSERT INTO Agendamento (data, hora, cpfDoador, fkDoador, fkInstituicao, fkCampanha, status) VALUES
('2024-01-15', '09:00:00', '12345678901', 1, 1, 1, 1),
('2024-03-20', '10:00:00', '98765432109', 2, 2, 2, 1),
('2024-06-05', '11:00:00', '11111111111', 3, 1, 1, 1),
('2024-01-12', '12:00:00', '22222222222', 4, 2, 2, 1),
('2024-03-18', '13:00:00', '33333333333', 5, 1, 1, 1),
('2024-06-10', '14:00:00', '44444444444', 6, 2, 2, 1);

-- Inserindo dados para janeiro, março e junho de 2023
INSERT INTO Agendamento (data, hora, cpfDoador, fkDoador, fkInstituicao, fkCampanha, status) VALUES
('2023-01-25', '09:00:00', '55555555555', 1, 1, 1, 1),
('2023-03-30', '10:00:00', '66666666666', 2, 2, 2, 1),
('2023-06-15', '11:00:00', '77777777777', 3, 1, 1, 1),
('2023-01-20', '12:00:00', '88888888888', 4, 2, 2, 1),
('2023-03-28', '13:00:00', '99999999999', 5, 1, 1, 1),
('2023-06-25', '14:00:00', '10101010101', 6, 2, 2, 1);

-- Inserindo dados para janeiro, março e junho de 2022
INSERT INTO Agendamento (data, hora, cpfDoador, fkDoador, fkInstituicao, fkCampanha, status) VALUES
('2022-01-30', '09:00:00', '12121212121', 1, 1, 1, 1),
('2022-03-10', '10:00:00', '13131313131', 2, 2, 2, 1),
('2022-06-20', '11:00:00', '14141414141', 3, 1, 1, 1),
('2022-01-22', '12:00:00', '15151515151', 4, 2, 2, 1),
('2022-03-15', '13:00:00', '16161616161', 5, 1, 1, 1),
('2022-06-30', '14:00:00', '17171717171', 6, 2, 2, 1);

-- EnderecoInstituicao
INSERT INTO EnderecoInstituicao (rua, numero, bairro, complemento, cidade, estado, cep, latitude, longitude, fkInstituicao) VALUES
('Rua A', 123, 'Centro', 'Sala 101', 'São Paulo', 'SP', 12345678, 40.7128, -74.0060, 1),
('Rua B', 456, 'Centro', 'Sala 202', 'Los Angeles', 'CA', 98765432, 34.0522, -118.2437, 2);

-- Creating views
-- Quantidade de usuários por sexo feminino ou masculino:
CREATE VIEW QuantidadeUsuariosPorSexo AS
SELECT 
    COUNT(CASE WHEN sexo = 'F' THEN 1 END) AS Qtd_Feminino,
    COUNT(CASE WHEN sexo = 'M' THEN 1 END) AS Qtd_Masculino
FROM Doador;

SELECT * FROM QuantidadeUsuariosPorSexo;


-- Idade dividida pela média de usuários em cada faixa etária
CREATE VIEW IdadeDivididaPorMedia AS
SELECT 
    FLOOR(DATEDIFF(CURRENT_DATE(), dtNasc) / 365.25 / 10) AS FaixaEtaria,
    COUNT(*) / (SELECT COUNT(*) FROM Doador) AS MediaUsuariosPorFaixaEtaria
FROM Doador
GROUP BY FaixaEtaria;

SELECT * FROM IdadeDivididaPorMedia;

-- Número de doadores que retornam para doar sangue novamente
CREATE VIEW NumeroDoadoresQueRetornam AS
SELECT 
    COUNT(*) AS Qtd_Doadores_Retornam
FROM (
    SELECT cpfDoador
    FROM Agendamento
    GROUP BY cpfDoador
    HAVING COUNT(*) > 1
) AS DoadoresRetornam;

SELECT * FROM NumeroDoadoresQueRetornam;

-- Fontes de tráfego que direcionam os doadores para o site
CREATE VIEW FontesTrafegoDoadores AS
SELECT 
    opcao,
    COUNT(*) AS Qtd_Doadores
FROM OrigemTrafego
GROUP BY opcao;

SELECT * FROM FontesTrafegoDoadores;

-- Número total de agendamentos e doações efetivas realizadas por mês em cada local
CREATE VIEW AgendamentosDoacoesPorMes AS
SELECT 
    MONTH(data) AS Mes,
    fkInstituicao,
    COUNT(*) AS Qtd_Agendamentos,
    SUM(status) AS Qtd_Doacoes_Efetivas
FROM Agendamento
GROUP BY Mes, fkInstituicao;

SELECT * FROM AgendamentosDoacoesPorMes;

-- Taxa de conversão de agendamentos em doações efetivas
CREATE VIEW TaxaConversao AS
SELECT 
    fkInstituicao,
    SUM(status) / COUNT(*) AS Taxa_Conversao
FROM Agendamento
GROUP BY fkInstituicao;

SELECT * FROM TaxaConversao;

-- Taxa de cancelamento de agendamentos
CREATE VIEW TaxaCancelamento AS
SELECT 
    fkInstituicao,
    1 - (SUM(status) / COUNT(*)) AS Taxa_Cancelamento
FROM Agendamento
GROUP BY fkInstituicao;

SELECT * FROM TaxaCancelamento;

-- Padrões temporais de doação
CREATE VIEW PadroesTemporaisDoacao AS
SELECT 
    DAYOFWEEK(data) AS DiaSemana,
    HOUR(hora) AS HoraDia,
    fkInstituicao,
    COUNT(*) AS Qtd_Doadores
FROM Agendamento
GROUP BY DiaSemana, HoraDia, fkInstituicao;

SELECT * FROM PadroesTemporaisDoacao;

-- Quantidade de doações por ano 
CREATE VIEW QuantidadeDoadoresPorAno AS
SELECT
    YEAR(data) AS Ano,
    MONTH(data) AS Mes,
    COUNT(*) AS Qtd_Doadores
FROM Agendamento
GROUP BY YEAR(data), MONTH(data)
ORDER BY Ano, Mes;

SELECT * FROM QuantidadeDoadoresPorAno;

-- Testando atualização de um agendamento
INSERT INTO Agendamento (data, hora, cpfDoador, fkDoador, fkInstituicao, fkCampanha, status)
VALUES ('2024-05-27', '14:00:00', '12345678901', 1, 1, 1, 0);

UPDATE Agendamento SET status = 1 WHERE idAgendamento = 1;

SELECT * FROM HistoricoAgendamento;
SELECT * FROM Agendamento;

-- Verifica se o trigger está ativo 
SELECT * FROM information_schema.triggers WHERE trigger_schema = 'maisVidas';

SHOW TABLES;


