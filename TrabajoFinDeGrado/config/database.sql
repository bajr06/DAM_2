-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema hotel_gestion_tfg
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema hotel_gestion_tfg
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hotel_gestion_tfg` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `hotel_gestion_tfg` ;

-- -----------------------------------------------------
-- Table `hotel_gestion_tfg`.`departamentos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel_gestion_tfg`.`departamentos` (
	`id_departamento` INT NOT NULL AUTO_INCREMENT,
	`nombre_departamento` VARCHAR(100) NOT NULL,
	PRIMARY KEY (`id_departamento`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hotel_gestion_tfg`.`puestos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel_gestion_tfg`.`puestos` (
	`id_puesto` INT NOT NULL AUTO_INCREMENT,
	`id_departamento` INT NULL DEFAULT NULL,
	PRIMARY KEY (`id_puesto`),
	INDEX `fk_puesto_dept` (`id_departamento` ASC) VISIBLE,
	CONSTRAINT `fk_puesto_dept`
		FOREIGN KEY (`id_departamento`)
		REFERENCES `hotel_gestion_tfg`.`departamentos` (`id_departamento`)
		ON DELETE SET NULL)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hotel_gestion_tfg`.`trabajadores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel_gestion_tfg`.`trabajadores` (
	`dni_trabajador` VARCHAR(20) NOT NULL,
	`nombre` VARCHAR(50) NOT NULL,
	`apellidos` VARCHAR(100) NOT NULL,
	`email` VARCHAR(100) NULL DEFAULT NULL,
	`id_puesto` INT NULL DEFAULT NULL,
	PRIMARY KEY (`dni_trabajador`),
	UNIQUE INDEX `email` (`email` ASC) VISIBLE,
	INDEX `fk_trabajador_puesto` (`id_puesto` ASC) VISIBLE,
	CONSTRAINT `fk_trabajador_puesto`
		FOREIGN KEY (`id_puesto`)
		REFERENCES `hotel_gestion_tfg`.`puestos` (`id_puesto`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hotel_gestion_tfg`.`cuentas_empleado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel_gestion_tfg`.`cuentas_empleado` (
	`id_cuenta` INT NOT NULL AUTO_INCREMENT,
	`rol` VARCHAR(30) NOT NULL,
	`email_acceso` VARCHAR(100) NOT NULL,
	`password_hash` VARCHAR(255) NOT NULL,
	`dni_empleado` VARCHAR(20) NULL DEFAULT NULL,
	PRIMARY KEY (`id_cuenta`),
	UNIQUE INDEX `email_acceso` (`email_acceso` ASC) VISIBLE,
	INDEX `fk_cuenta_trabajador` (`dni_empleado` ASC) VISIBLE,
	CONSTRAINT `fk_cuenta_trabajador`
		FOREIGN KEY (`dni_empleado`)
		REFERENCES `hotel_gestion_tfg`.`trabajadores` (`dni_trabajador`)
		ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hotel_gestion_tfg`.`huespedes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel_gestion_tfg`.`huespedes` (
	`dni_pasaporte` VARCHAR(20) NOT NULL,
	`nombre` VARCHAR(50) NOT NULL,
	`apellidos` VARCHAR(100) NOT NULL,
	`nivel_fidelidad` ENUM('WHITE', 'SILVER', 'GOLD', 'PLATINUM') NULL DEFAULT 'WHITE',
	`sexo` ENUM('MASCULINO', 'FEMENINO', 'OTRO') NULL DEFAULT NULL,
	`fecha_nacimiento` DATE NULL DEFAULT NULL,
	`pais_procedencia` VARCHAR(50) NULL DEFAULT NULL,
	`ciudad` VARCHAR(50) NULL DEFAULT NULL,
	`codigo_postal` VARCHAR(15) NULL DEFAULT NULL,
	`telefono` VARCHAR(20) NULL DEFAULT NULL,
	`email` VARCHAR(100) NULL DEFAULT NULL,
	PRIMARY KEY (`dni_pasaporte`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hotel_gestion_tfg`.`reservas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel_gestion_tfg`.`reservas` (
	`id_reserva` INT NOT NULL AUTO_INCREMENT,
	`fecha_checkin` DATE NOT NULL,
	`fecha_checkout` DATE NOT NULL,
	`estado_reserva` ENUM('PENDIENTE', 'ACTIVA', 'FINALIZADA', 'CANCELADA') NULL DEFAULT 'PENDIENTE',
	`fecha_creacion` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
	`dni_pasaporte` VARCHAR(20) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL DEFAULT NULL,
	PRIMARY KEY (`id_reserva`),
	INDEX `fk_reserva_huesped` (`dni_pasaporte` ASC) VISIBLE,
	CONSTRAINT `fk_reserva_huesped`
		FOREIGN KEY (`dni_pasaporte`)
		REFERENCES `hotel_gestion_tfg`.`huespedes` (`dni_pasaporte`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hotel_gestion_tfg`.`facturas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel_gestion_tfg`.`facturas` (
	`id_factura` INT NOT NULL AUTO_INCREMENT,
	`fecha_emision` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
	`total_pagado` DECIMAL(10,2) NULL DEFAULT NULL,
	`metodo_pago_token` VARCHAR(255) NULL DEFAULT NULL,
	`id_reserva` INT NULL DEFAULT NULL,
	PRIMARY KEY (`id_factura`),
	INDEX `fk_factura_reserva` (`id_reserva` ASC) VISIBLE,
	CONSTRAINT `fk_factura_reserva`
		FOREIGN KEY (`id_reserva`)
		REFERENCES `hotel_gestion_tfg`.`reservas` (`id_reserva`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hotel_gestion_tfg`.`habitaciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel_gestion_tfg`.`habitaciones` (
	`numero_habitacion` INT NOT NULL,
	`categoria` ENUM('ESTANDAR', 'PREMIUM', 'SUITE') NOT NULL,
	`estado_limpieza` ENUM('LIMPIA', 'SUCIA') NULL DEFAULT 'LIMPIA',
	`estado_disponibilidad` ENUM('DISPONIBLE', 'INDISPONIBLE') NULL DEFAULT 'DISPONIBLE',
	`ocupada_bool` TINYINT(1) NULL DEFAULT '0',
	`capacidad_max` INT NULL DEFAULT '2',
	`precio_noche` DECIMAL(10,2) NOT NULL DEFAULT '0.00',
	PRIMARY KEY (`numero_habitacion`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hotel_gestion_tfg`.`reserva_habitacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel_gestion_tfg`.`reserva_habitacion` (
	`id_reserva` INT NOT NULL,
	`numero_habitacion` INT NOT NULL,
	`precio_noche_aplicado` DECIMAL(10,2) NULL DEFAULT NULL,
	`numero_ocupantes_reales` INT NULL DEFAULT NULL,
	PRIMARY KEY (`id_reserva`, `numero_habitacion`),
	INDEX `fk_rel_habitacion` (`numero_habitacion` ASC) VISIBLE,
	CONSTRAINT `fk_rel_habitacion`
		FOREIGN KEY (`numero_habitacion`)
		REFERENCES `hotel_gestion_tfg`.`habitaciones` (`numero_habitacion`),
	CONSTRAINT `fk_rel_reserva`
		FOREIGN KEY (`id_reserva`)
		REFERENCES `hotel_gestion_tfg`.`reservas` (`id_reserva`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
