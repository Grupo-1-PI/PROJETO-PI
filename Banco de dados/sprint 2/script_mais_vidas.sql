DROP DATABASE IF EXISTS maisVidas;
CREATE DATABASE IF NOT EXISTS maisVidas;
USE maisVidas;

CREATE TABLE IF NOT EXISTS doador (
id INT PRIMARY KEY auto_increment,
nome VARCHAR(45),
email VARCHAR(45),
dt_nasc DATE,
sexo CHAR(1),
primeira_doacao BOOLEAN,
senha VARCHAR(45),
motivo VARCHAR(45)
);

CREATE TABLE IF NOT EXISTS telefone(
id INT PRIMARY KEY auto_increment,
ddd CHAR(2),
tel_cel CHAR(9),
doador_id INT,
constraint fk_doador_telefone foreign key(doador_id) references doador(id)
);

CREATE TABLE IF NOT EXISTS log_doador(
id INT PRIMARY KEY auto_increment,
descricao VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS acesso_doador(
id INT auto_increment,
dt_hora DATETIME,
doador_id INT,
log_doador_id INT,
constraint fk_doador_acesso_doador foreign key(doador_id) references doador(id),
constraint fk_log_doador foreign key(log_doador_id) references log_doador(id),
constraint pk_acesso_doador primary key(id, doador_id, log_doador_id)
);

CREATE TABLE IF NOT EXISTS instituicao(
id INT PRIMARY KEY auto_increment,
nome VARCHAR(45),
cnpj INT,
latitude INT,
longitude INT
);

CREATE TABLE IF NOT EXISTS endereco_instituicao(
id INT auto_increment,
rua VARCHAR(45),
numero INT,
bairro VARCHAR(45),
complemento VARCHAR(45),
cidade VARCHAR(45),
estado VARCHAR(45),
cep INT,
instituicao_id INT,
constraint fk_instituicao_endereco_instituicao foreign key (instituicao_id) references instituicao(id),
constraint pk_endereco_instituicao primary key (id, instituicao_id)
);

CREATE TABLE IF NOT EXISTS nivel_acesso(
id INT PRIMARY KEY auto_increment,
nome VARCHAR(45),
token VARCHAR(45)
);

CREATE TABLE IF NOT EXISTS cargo(
id INT auto_increment,
nome VARCHAR(45),
area VARCHAR(45),
nivel_acesso_id INT,
codigo_cargo INT,
constraint fk_nivel_acesso_cargo foreign key (nivel_acesso_id) references cargo(id),
constraint pk_cargo primary key (id, nivel_acesso_id)
);

CREATE TABLE IF NOT EXISTS unidade_sangue_corinthiano(
id INT PRIMARY KEY auto_increment,
nome VARCHAR(45),
cidade VARCHAR(45),
estado CHAR(2),
codigo_unidade INT
);

CREATE TABLE IF NOT EXISTS admin_sangue_corinthiano(
id INT auto_increment,
nome VARCHAR(45),
cpf CHAR(11),
email VARCHAR(45),
senha VARCHAR(45),
cargo_id INT,
unidade_id INT,
dt_nasc DATE,
sexo CHAR(1),
constraint fk_cargo_admin_sc foreign key (cargo_id) references cargo(id),
constraint fk_unidade_admin_sc foreign key (unidade_id) references unidade_sangue_corinthiano(id),
constraint pk_admin_sangue_corinthiano primary key (id, cargo_id, unidade_id)
);

CREATE TABLE IF NOT EXISTS responsavel_campanha(
id INT auto_increment,
nome VARCHAR(45),
cpf CHAR(11),
admin_sc_id INT,
constraint fk_admin_sc_reponsavel_campanha foreign key(admin_sc_id) references admin_sangue_corinthiano(id),
constraint pk_responsavel_campanha primary key (id, admin_sc_id)
);

CREATE TABLE IF NOT EXISTS campanha(
id INT auto_increment,
nome VARCHAR(45),
dt_inicio DATE,
dt_fim DATE,
instituicao_id INT,
responsavel_campanha_id INT,
constraint fk_instituicao_campanha foreign key (instituicao_id) references instituicao(id),
constraint fk_responsavel_campanha_campanha foreign key (responsavel_campanha_id) references responsavel_campanha(id),
constraint pk_campanha primary key (id, instituicao_id, responsavel_campanha_id)
);

CREATE TABLE IF NOT EXISTS agendamento(
id INT auto_increment,
data DATE,
hora TIME,
doador_id INT,
instituicao_id INT,
campanha_id INT,
constraint fk_doador_agendamento foreign key (doador_id) references doador(id),
constraint fk_instituicao_agendamento foreign key (instituicao_id) references instituicao(id),
constraint fk_campanha_agendamento foreign key (campanha_id) references campanha(id),
constraint pk_agendamento primary key (id, doador_id, instituicao_id, campanha_id)
);

CREATE TABLE IF NOT EXISTS telefone_admin(
id INT auto_increment,
ddd CHAR(2),
tel_cel CHAR(9),
admin_sc_id INT,
constraint fk_admin_sc_telefone_admin foreign key (admin_sc_id) references admin_sangue_corinthiano(id),
constraint pk_telefone_admin primary key(id, admin_sc_id)
);

CREATE TABLE IF NOT EXISTS log_admin(
id INT PRIMARY KEY auto_increment,
descricao VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS acesso_admin(
id INT auto_increment,
dtHora DATETIME,
log_admin_id INT,
admin_id INT,
constraint fk_log_admin_acesso_admin foreign key (log_admin_id) references acesso_admin(id),
constraint fk_admin_acesso_admin foreign key (admin_id) references admin_sangue_corinthiano(id),
constraint pk_acesso_admin primary key (id, log_admin_id, admin_id)
);

SHOW TABLES;

SELECT * FROM Doador;
SELECT * FROM admin_sangue_corinthiano;

RENAME TABLE admin_sangue_corinthiano TO administrador;
SELECT * FROM administrador;

SELECT * FROM Cargo;
SELECT * FROM Unidade;

SHOW TABLES;

