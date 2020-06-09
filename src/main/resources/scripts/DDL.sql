create database cliente;
create schema cliente;

create table cliente.usuario
(
	id	SERIAL not null,
	nome varchar(100) not null,
	perfil varchar(15) not null,
	usuario varchar(50) not null,
	senha varchar(20) not null,
	
	CONSTRAINT pk_usuario PRIMARY KEY (id)
);

create table cliente.cliente
(
	id	SERIAL not null,
	nome varchar(100) not null,
	logradouro varchar(100) not null,
	bairro varchar(100) not null,
	cidade varchar(100) not null,
	uf varchar(2) not null,
	complemento varchar(100),
	cpf BIGINT not null,
	cep BIGINT not null,
	
	CONSTRAINT pk_cliente PRIMARY KEY (id)
);

CREATE TABLE cliente.telefones
(
    id	SERIAL not null,
    id_cliente	INT NULL,
    tipo	VARCHAR(15) NOT NULL,
    numero	BIGINT NOT null,
     
    CONSTRAINT pk_telefone PRIMARY KEY (id),
    CONSTRAINT fk_telefones_cliente FOREIGN KEY (id_cliente)
                    REFERENCES cliente.cliente (id)
);


create table cliente.emails
(
	id	SERIAL not null,
    id_cliente	INT NULL,
    email	VARCHAR(50) NOT NULL,
     
    CONSTRAINT pk_email PRIMARY KEY (id),
    CONSTRAINT fk_email_cliente FOREIGN KEY (id_cliente)
                    REFERENCES cliente.cliente (id)
);

INSERT INTO cliente.usuario
(nome, perfil, usuario, senha)
VALUES('admin', 'admin', 'admin', 123456);

INSERT INTO cliente.usuario
(nome, perfil, usuario, senha)
VALUES('comum', 'comum', 'comum', 123456);