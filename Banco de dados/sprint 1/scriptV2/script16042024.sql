-- CREATE DATABASE vidaz;
USE vidaz;

CREATE TABLE IF NOT EXISTS  Local (
  idLocal INT PRIMARY KEY AUTO_INCREMENT,
  longitude DECIMAL(10, 8),
  latitude DECIMAL(10, 8)
);

CREATE TABLE IF NOT EXISTS Campanha (
  idCampanha INT PRIMARY KEY AUTO_INCREMENT,
  data DATETIME,
  Local_idLocal INT,
  FOREIGN KEY (Local_idLocal) REFERENCES Local(idLocal)
);
CREATE TABLE IF NOT EXISTS BancoDeSangue (
  idBancoDeSangue INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(45)
);

CREATE TABLE IF NOT EXISTS Endereco (
  idEndereco INT PRIMARY KEY AUTO_INCREMENT,
  cep CHAR(8),
  rua VARCHAR(45),
  numero CHAR(8),
  bairro VARCHAR(45),
  BancoDeSangue_idBancoDeSangue INT,
  FOREIGN KEY (BancoDeSangue_idBancoDeSangue) REFERENCES BancoDeSangue(idBancoDeSangue)
);
CREATE TABLE IF NOT EXISTS Doador (
  idDoador INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(45),
  dataNascimento DATE,
  email VARCHAR(45),
  sexo ENUM('M', 'F'),
  senha VARCHAR(45),
  Campanha_idCampanha INT,
  Endereco_idEndereco INT,
  FOREIGN KEY (Campanha_idCampanha) REFERENCES Campanha(idCampanha),
  FOREIGN KEY (Endereco_idEndereco) REFERENCES Endereco(idEndereco)
);

CREATE TABLE IF NOT EXISTS Contato (
  idContato INT PRIMARY KEY AUTO_INCREMENT,
  numero CHAR(11),
  Doador_idDoador INT,
  BancoDeSangue_idBancoDeSangue INT,
  FOREIGN KEY (Doador_idDoador) REFERENCES Doador(idDoador),
  FOREIGN KEY (BancoDeSangue_idBancoDeSangue) REFERENCES BancoDeSangue(idBancoDeSangue)
);

CREATE TABLE IF NOT EXISTS NivelDeAcesso (
  idNivelDeAcesso INT PRIMARY KEY AUTO_INCREMENT,
  editar TINYINT,
  deletar TINYINT
);

CREATE TABLE IF NOT EXISTS GrupoNivelAcesso (
  Usuario_idDoador INT,
  NivelDeAcesso_idNivelDeAcesso INT,
  Admin INT,
  Comum INT,
  PRIMARY KEY (Usuario_idDoador, NivelDeAcesso_idNivelDeAcesso),
  FOREIGN KEY (Usuario_idDoador) REFERENCES Doador(idDoador),
  FOREIGN KEY (NivelDeAcesso_idNivelDeAcesso) REFERENCES NivelDeAcesso(idNivelDeAcesso)
);
