CREATE DATABASE simulacro_01;

USE simulacro_01;

CREATE TABLE especialidad(
	id int auto_increment primary key,
    nombre varchar(255) not null,
    descripcion TEXT
);

CREATE TABLE medico(
	id int auto_increment primary key,
    nombre varchar(255) not null,
    apellidos varchar(255) not null,
    id_especialidad int,
    FOREIGN KEY (id_especialidad) REFERENCES especialidad(id) ON DELETE CASCADE
);

CREATE TABLE paciente(
	id int auto_increment primary key,
    nombre varchar(255) not null,
    apellidos varchar(255) not null,
    fecha_nacimiento DATE not null,
    documento_identidad varchar(255) unique not null
);

CREATE TABLE cita(
	id int auto_increment primary key,
    fecha DATE not null,
    hora TIME not null,
    motivo VARCHAR(255),
    id_paciente int,
    id_medico int,
    FOREIGN KEY (id_paciente) REFERENCES paciente(id) ON DELETE CASCADE,
    FOREIGN KEY (id_medico)  REFERENCES medico(id) ON DELETE CASCADE
);