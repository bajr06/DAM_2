-- 1. Crear la base de datos (Schema)
CREATE DATABASE IF NOT EXISTS hotel_gestion_tfg;
USE hotel_gestion_tfg;

-- 2. Tabla Departamentos
CREATE TABLE Departamentos (
	id_departamento INT AUTO_INCREMENT PRIMARY KEY,
	nombre_departamento VARCHAR(100) NOT NULL
);

-- 3. Tabla Puestos
CREATE TABLE Puestos (
	id_puesto INT AUTO_INCREMENT PRIMARY KEY,
	nombre_puesto VARCHAR(100) NOT NULL,
	id_departamento INT,
	CONSTRAINT fk_puesto_dept FOREIGN KEY (id_departamento) 
		REFERENCES departamentos(id_departamento) ON DELETE SET NULL
);

-- 4. Tabla Trabajadores
CREATE TABLE Trabajadores (
	dni_trabajador VARCHAR(20) PRIMARY KEY,
	nombre VARCHAR(50) NOT NULL,
	apellidos VARCHAR(100) NOT NULL,
	telefono VARCHAR(20),
	email VARCHAR(100) UNIQUE,
	horario VARCHAR(100),
	id_puesto INT,
	CONSTRAINT fk_trabajador_puesto FOREIGN KEY (id_puesto) 
		REFERENCES puestos(id_puesto)
);

-- 5. Tabla Cuentas de Usuario
CREATE TABLE Cuentas_usuario (
	id_cuenta INT AUTO_INCREMENT PRIMARY KEY,
	email_acceso VARCHAR(100) UNIQUE NOT NULL,
	password_hash VARCHAR(255) NOT NULL,
	dni_trabajador VARCHAR(20),
	CONSTRAINT fk_cuenta_trabajador FOREIGN KEY (dni_trabajador) 
		REFERENCES trabajadores(dni_trabajador) ON DELETE CASCADE
);

-- 6. Tabla Huéspedes
CREATE TABLE Huespedes (
	dni_pasaporte VARCHAR(20) PRIMARY KEY,
	nombre VARCHAR(50) NOT NULL,
	apellidos VARCHAR(100) NOT NULL,
	nivel_fidelidad ENUM('WHITE', 'SILVER', 'GOLD', 'PLATINUM') DEFAULT 'WHITE',
	sexo ENUM('MASCULINO', 'FEMENINO', 'OTRO'),
	fecha_nacimiento DATE,
	pais_procedencia VARCHAR(50),
	ciudad VARCHAR(50),
	codigo_postal VARCHAR(15),
	telefono VARCHAR(20),
	email VARCHAR(100)
);

-- 7. Tabla Habitaciones
CREATE TABLE Habitaciones (
	numero_habitacion INT PRIMARY KEY, -- Ej: 101, 102
	categoria ENUM('ESTANDAR', 'PREMIUM', 'SUITE') NOT NULL,
	estado_limpieza ENUM('LIMPIA', 'SUCIA') DEFAULT 'LIMPIA',
	estado_disponibilidad ENUM('DISPONIBLE', 'INDISPONIBLE') DEFAULT 'DISPONIBLE',
	ocupada_bool BOOLEAN DEFAULT FALSE,
	capacidad_max INT DEFAULT 2
);

-- 8. Tabla Reservas (Cabecera)
CREATE TABLE Reservas (
	id_reserva INT AUTO_INCREMENT PRIMARY KEY,
	fecha_checkin DATE NOT NULL,
	fecha_checkout DATE NOT NULL,
	estado_reserva ENUM('PENDIENTE', 'ACTIVA', 'FINALIZADA', 'CANCELADA') DEFAULT 'PENDIENTE',
	fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP,
	dni_titular VARCHAR(20),
	CONSTRAINT fk_reserva_huesped FOREIGN KEY (dni_titular) 
		REFERENCES huespedes(dni_pasaporte)
);

-- 9. Tabla Intermedia Reserva_Habitacion (Para grupos: N a M)
CREATE TABLE Reserva_habitacion (
	id_reserva INT,
	numero_habitacion INT,
	precio_noche_aplicado DECIMAL(10,2),
	numero_ocupantes_reales INT,
	PRIMARY KEY (id_reserva, numero_habitacion),
	CONSTRAINT fk_rel_reserva FOREIGN KEY (id_reserva) REFERENCES reservas(id_reserva),
	CONSTRAINT fk_rel_habitacion FOREIGN KEY (numero_habitacion) REFERENCES habitaciones(numero_habitacion)
);

-- 10. Tabla Facturas
CREATE TABLE Facturas (
	id_factura INT AUTO_INCREMENT PRIMARY KEY,
	fecha_emision DATETIME DEFAULT CURRENT_TIMESTAMP,
	total_pagado DECIMAL(10,2),
	metodo_pago_token VARCHAR(255), -- Simula el hasheo de la tarjeta
	id_reserva INT,
	CONSTRAINT fk_factura_reserva FOREIGN KEY (id_reserva) REFERENCES reservas(id_reserva)
);

-- -- 11. Tabla de Logs / Auditoría
-- CREATE TABLE logs_actividad (
-- 	id_log INT AUTO_INCREMENT PRIMARY KEY,
-- 	fecha_hora DATETIME DEFAULT CURRENT_TIMESTAMP,
-- 	accion ENUM('LOGIN', 'LOGOUT', 'CREAR_RESERVA', 'CANCELAR_RESERVA', 'CHECK_IN', 'CHECK_OUT', 'MODIFICAR_DATOS', 'ELIMINAR_DATO') NOT NULL,
-- 	descripcion TEXT, -- Aquí puedes poner detalles como "Check-in realizado en hab 101"
-- 	dni_trabajador VARCHAR(20),
-- 	CONSTRAINT fk_log_trabajador FOREIGN KEY (dni_trabajador) 
-- 		REFERENCES trabajadores(dni_trabajador) ON DELETE SET NULL
-- );