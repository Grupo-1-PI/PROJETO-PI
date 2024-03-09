drop database vidas;
create database vidas;
use vidas;

create table enderecoOng (
			idEnderecoOng int primary key auto_increment,
            cep char(11),
            rua varchar(45),
            numero char(7),
            bairro varchar(45)
            );
create table ong(
			idOng int primary key auto_increment,
            nome varchar(45),
            cnpj char(14),
            numeroContato char(11),
            numeroContato2 char(11),
            responsavel varchar(45),
            email varchar(45),
            fkEnderecoOng int,  foreign key (fkEnderecoOng) references enderecoOng (idEnderecoOng)
            );
create table enderecoDoador(
			idEnderecoDoador int primary key auto_increment,
            cep char(11),
            rua varchar(45),
            numero char(7),
            bairro varchar(45)
            );
create table doador(
			idDoador int primary key auto_increment,
            nome varchar(45),
            idade int,
            numeroContato char(11),
            numeroContato2 char(11),
            email varchar(45),
            fkEnderecoDoador int, foreign key (fkEnderecoDoador) references enderecoDoador(idEnderecoDoador)
            );
create table historico(
			fkOngH int, foreign key (fkOngH) references ong(idOng),
            fkDoadorH int, foreign key (fkDoadorH) references doador(idDoador),
            dataHorario date
            );
create table permissoes(
			idPermissoes int primary key auto_increment,
            visualizar tinyint(1),
            editar tinyint(1),
            cadastrar tinyint(1),
            deletar tinyint(1)
            );
create table nivelAcesso(
			idNivelAcesso int primary key auto_increment,
            descricao varchar(45),
            fkPermissoes int, foreign key(fkPermissoes) references permissoes(idPermissoes)
            );
create table funcionario(
			idFuncionario int primary key auto_increment,
            nomeFuncionario varchar(45),
            emailFuncionario varchar(45),
            senha varchar(45),
            statusFuncionario tinyint(1),
            fkNivelAcesso int, foreign key (fkNivelAcesso) references nivelAcesso(idNivelAcesso),
            fkOng int, foreign key(fkOng) references ong (idOng)
            );


            
