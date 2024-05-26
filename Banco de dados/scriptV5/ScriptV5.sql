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

CREATE TABLE IF NOT EXISTS TelefoneDoador (
  idTelefone INT AUTO_INCREMENT,
  ddd CHAR(2),
  telCel CHAR(9),
  fkDoador INT,
  PRIMARY KEY (idTelefone),
  CONSTRAINT fk_doador_telefone FOREIGN KEY (fkDoador) REFERENCES Doador(idDoador)
);

CREATE TABLE IF NOT EXISTS LogDoador (
  idLogDoador INT AUTO_INCREMENT,
  descricao VARCHAR(200),
  PRIMARY KEY (idLogDoador)
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

CREATE TABLE IF NOT EXISTS Instituicao (
  idInstituicao INT AUTO_INCREMENT,
  nome VARCHAR(100),
  cnpj INT,
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
  PRIMARY KEY (idCargo)
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
    idHistorico INT AUTO_INCREMENT PRIMARY KEY,
    idAgendamento INT,
    dataModificacao DATETIME,
    statusAnterior TINYINT(1),
    statusAtual TINYINT(1),
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

-- Testando atualização de um agendamento

INSERT INTO Agendamento (data, hora, cpfDoador, fkDoador, fkInstituicao, fkCampanha, status)
VALUES ('2024-05-27', '14:00:00', '12345678901', 1, 1, 1, 0);

UPDATE Agendamento SET status = 1 WHERE idAgendamento = 1;

SELECT * FROM HistoricoAgendamento;
SELECT * FROM Agendamento;

-- Verifica se o trigger está ativo 
SELECT * FROM information_schema.triggers WHERE trigger_schema = 'maisVidas';

SHOW TABLES;

-- View de Agendamentos com Detalhes do Doador e da Campanha:
CREATE VIEW vw_agendamentos AS
SELECT a.idAgendamento, a.data, a.hora, a.status, d.nome AS nomeDoador, c.nome AS nomeCampanha
FROM Agendamento a
JOIN Doador d ON a.fkDoador = d.idDoador
JOIN Campanha c ON a.fkCampanha = c.idCampanha;

-- View de Admins com Detalhes do Cargo e da Unidade:
CREATE VIEW vw_admins AS
SELECT a.idAdminSangueCorinthiano, a.nome, a.email, c.nome AS cargo, u.nome AS unidade
FROM AdminSangueCorinthiano a
JOIN Cargo c ON a.fkCargo = c.idCargo
JOIN UnidadeSangueCorinthiano u ON a.fkUnidade = u.idUnidadeSangueCorinthiano;

-- View de Histórico de Acesso dos Doadores:
CREATE VIEW vw_historico_acesso_doador AS
SELECT ad.dtHora, d.nome AS nomeDoador, ld.descricao AS acao
FROM AcessoDoador ad
JOIN Doador d ON ad.fkDoador = d.idDoador
JOIN LogDoador ld ON ad.fkLogDoador = ld.idLogDoador;
