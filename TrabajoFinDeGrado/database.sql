DROP TABLE IF EXISTS Habitacion;
DROP TABLE IF EXISTS Recepcionista;
DROP TABLE IF EXISTS Usuario;
DROP TABLE IF EXISTS Hotel;

CREATE TABLE Habitacion (
	numero_habitacion INTEGER NOT NULL PRIMARY KEY,
	categoria ENUM('ESTANDAR', 'PREMIUM', 'SUITES') NOT NULL,
	esta_ocupada BOOLEAN NOT NULL,
	estado ENUM('LIMPIA', 'SUCIA', 'DISPONIBLE', 'INDISPONIBLE'),
);

CREATE TABLE Recepcionista (
	id_recepcionista INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	nombres_recepcionista VARCHAR(20) NOT NULL,
	apellidos_recepcionista VARCHAR(50) NOT NULL,
	nivel_recepcionista ENUM('AUXILIAR', 'SUPERVISOR', 'JEFE') NOT NULL,
);

CREATE TABLE Usuario (
	id_usuario INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	nombres_usuario VARCHAR(20) NOT NULL,
	apellidos_usuario VARCHAR(50) NOT NULL,
	categoria_usuario ENUM('WHITE', 'SILVER', 'GOD', 'PLATINUM')
);

CREATE TABLE Hotel (
	id_hotel INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	nombre_hotel VARCHAR(20),
	estrellas CHECK (Edad >= 1 AND Edad <= 5),
);

-- TODO: Falta revisar el campos y tablas intermidias que sean necesarias
