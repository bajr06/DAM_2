DROP TABLE IF EXISTS Habitacion;
DROP TABLE IF EXISTS Huesped;
DROP TABLE IF EXISTS Ocupacion;
DROP TABLE IF EXISTS Recepcionista;
DROP TABLE IF EXISTS Hotel;


CREATE TABLE Habitacion (
	id_habitacion INTEGER NOT NULL UNIQUE PRIMARY KEY,
	categoria ENUM('ESTANDAR', 'PREMIUM', 'SUITES') NOT NULL,
	estado BOOLEAN NOT NULL,
	situacion ENUM('LIMPIA', 'SUCIA', 'DISPONIBLE', 'INDISPONIBLE'),
);

CREATE TABLE Huesped (
	id_huesped INTEGER NOT NULL UNIQUE PRIMARY KEY AUTOINCREMENT,
	dni VARCHAR(50) NOT NULL UNIQUE;
	nombres VARCHAR(50) NOT NULL,
	apellidos VARCHAR(50) NOT NULL,
	fecha_nacimiento DATE NOT NULL;
	categoria ENUM('WHITE', 'SILVER', 'GOD', 'PLATINUM')
);

CREATE TABLE Ocupacion {
	id_habitacion INT UNIQUE NOT NULL;
	id_huesped INT UNIQUE NOT NULL;
	-- TODO Preferible hacer todo esto en MySQL Workbench.
}

CREATE TABLE Trabajador (
	id_trabajador INTEGER NOT NULL UNIQUE PRIMARY KEY AUTOINCREMENT,
	nombres VARCHAR(50) NOT NULL,
	apellidos VARCHAR(50) NOT NULL,
	puesto ENUM(); -- TODO
);

CREATE TABLE Hotel (
	id_hotel INTEGER NOT NULL UNIQUE PRIMARY KEY AUTOINCREMENT,
	nombre_hotel VARCHAR(20),
	estrellas CHECK (estrellas >= 1 AND estrellas <= 5),
);
