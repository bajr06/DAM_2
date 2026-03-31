-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema Jugueteria
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Jugueteria
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Jugueteria` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `Jugueteria` ;

-- -----------------------------------------------------
-- Table `Jugueteria`.`Empleado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Jugueteria`.`Empleado` (
	`ID_Empleado` INT UNSIGNED NOT NULL AUTO_INCREMENT,
	`Nombre` VARCHAR(20) NOT NULL,
	`Cargo` ENUM('Jefe', 'Cajero') NOT NULL,
	`Fecha_Ingreso` VARCHAR(30) NOT NULL,
	PRIMARY KEY (`ID_Empleado`),
	UNIQUE INDEX `ID_Empleado_UNIQUE` (`ID_Empleado` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Jugueteria`.`Juguete`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Jugueteria`.`Juguete` (
	`ID_Juguete` INT UNSIGNED NOT NULL AUTO_INCREMENT,
	`Nombre` VARCHAR(20) NOT NULL,
	`Descripcion` TEXT(150) NULL DEFAULT NULL,
	`Precio` FLOAT NOT NULL,
	`Cantidad_Stock` INT UNSIGNED NOT NULL,
	PRIMARY KEY (`ID_Juguete`),
	UNIQUE INDEX `ID_Juguete_UNIQUE` (`ID_Juguete` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Jugueteria`.`Zona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Jugueteria`.`Zona` (
	`ID_Zona` INT UNSIGNED NOT NULL AUTO_INCREMENT,
	`Nombre` VARCHAR(20) NOT NULL,
	`Descripcion` TEXT(150) NULL DEFAULT NULL,
	PRIMARY KEY (`ID_Zona`),
	UNIQUE INDEX `ID_Zona_UNIQUE` (`ID_Zona` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Jugueteria`.`Stand`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Jugueteria`.`Stand` (
	`ID_Stand` INT UNSIGNED NOT NULL AUTO_INCREMENT,
	`Nombre` VARCHAR(20) NOT NULL,
	`Descripcion` TEXT(150) NULL DEFAULT NULL,
	`Zona_ID_Zona` INT UNSIGNED NOT NULL,
	PRIMARY KEY (`ID_Stand`, `Zona_ID_Zona`),
	INDEX `fk_STAND_ZONA_idx` (`Zona_ID_Zona` ASC) VISIBLE,
	UNIQUE INDEX `ID_Stand_UNIQUE` (`ID_Stand` ASC) VISIBLE,
	CONSTRAINT `fk_STAND_ZONA`
		FOREIGN KEY (`Zona_ID_Zona`)
		REFERENCES `Jugueteria`.`Zona` (`ID_Zona`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Jugueteria`.`Stock`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Jugueteria`.`Stock` (
	`Stand_ID_Stand` INT UNSIGNED NOT NULL,
	`Stand_Zona_ID_Zona` INT UNSIGNED NOT NULL,
	`Juguete_ID_Juguete` INT UNSIGNED NOT NULL,
	`Cantidad` INT UNSIGNED NOT NULL,
	PRIMARY KEY (`Stand_ID_Stand`, `Stand_Zona_ID_Zona`, `Juguete_ID_Juguete`),
	INDEX `fk_STAND_has_JUGUETE_JUGUETE1_idx` (`Juguete_ID_Juguete` ASC) VISIBLE,
	INDEX `fk_STAND_has_JUGUETE_STAND1_idx` (`Stand_ID_Stand` ASC, `Stand_Zona_ID_Zona` ASC) VISIBLE,
	CONSTRAINT `fk_STAND_has_JUGUETE_JUGUETE1`
		FOREIGN KEY (`Juguete_ID_Juguete`)
		REFERENCES `Jugueteria`.`Juguete` (`ID_Juguete`)
		ON DELETE CASCADE,
	CONSTRAINT `fk_STAND_has_JUGUETE_STAND1`
		FOREIGN KEY (`Stand_ID_Stand` , `Stand_Zona_ID_Zona`)
		REFERENCES `Jugueteria`.`Stand` (`ID_Stand` , `Zona_ID_Zona`)
		ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Jugueteria`.`Cambio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Jugueteria`.`Cambio` (
	`ID_Cambio` INT UNSIGNED NOT NULL AUTO_INCREMENT,
	`Motivo` TEXT(150) NULL DEFAULT NULL,
	`Fecha` DATE NOT NULL,
	`Stock_Stand_ID_Stand_Original` INT UNSIGNED NOT NULL,
	`Stock_Stand_Zona_ID_Zona_Original` INT UNSIGNED NOT NULL,
	`Stock_Juguete_ID_Juguete_Original` INT UNSIGNED NOT NULL,
	`Stock_Stand_ID_Stand_Nuevo` INT UNSIGNED NOT NULL,
	`Stock_Stand_Zona_ID_Zona_Nuevo` INT UNSIGNED NOT NULL,
	`Stock_Juguete_ID_Juguete_Nuevo` INT UNSIGNED NOT NULL,
	`Empleado_ID_Empleado` INT UNSIGNED NOT NULL,
	PRIMARY KEY (`ID_Cambio`),
	INDEX `fk_CAMBIO_STOCK1_idx` (`Stock_Stand_ID_Stand_Original` ASC, `Stock_Stand_Zona_ID_Zona_Original` ASC, `Stock_Juguete_ID_Juguete_Original` ASC) VISIBLE,
	INDEX `fk_CAMBIO_STOCK2_idx` (`Stock_Stand_ID_Stand_Nuevo` ASC, `Stock_Stand_Zona_ID_Zona_Nuevo` ASC, `Stock_Juguete_ID_Juguete_Nuevo` ASC) VISIBLE,
	INDEX `fk_CAMBIO_EMPLEADO1_idx` (`Empleado_ID_Empleado` ASC) VISIBLE,
	UNIQUE INDEX `ID_Cambio_UNIQUE` (`ID_Cambio` ASC) VISIBLE,
	CONSTRAINT `fk_CAMBIO_EMPLEADO1`
		FOREIGN KEY (`Empleado_ID_Empleado`)
		REFERENCES `Jugueteria`.`Empleado` (`ID_Empleado`),
	CONSTRAINT `fk_CAMBIO_STOCK1`
		FOREIGN KEY (`Stock_Stand_ID_Stand_Original` , `Stock_Stand_Zona_ID_Zona_Original` , `Stock_Juguete_ID_Juguete_Original`)
		REFERENCES `Jugueteria`.`Stock` (`Stand_ID_Stand` , `Stand_ZONA_ID_Zona` , `Juguete_ID_Juguete`),
	CONSTRAINT `fk_CAMBIO_STOCK2`
		FOREIGN KEY (`Stock_Stand_ID_Stand_Nuevo` , `Stock_Stand_Zona_ID_Zona_Nuevo` , `Stock_Juguete_ID_Juguete_Nuevo`)
		REFERENCES `jugueteria`.`Stock` (`Stand_ID_Stand` , `Stand_ZONA_ID_Zona` , `Juguete_ID_Juguete`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Jugueteria`.`Venta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Jugueteria`.`Venta` (
	`ID_Venta` INT UNSIGNED NOT NULL AUTO_INCREMENT,
	`Fecha` DATE NOT NULL,
	`Monto` FLOAT NOT NULL,
	`Tipo_Pago` ENUM('Efectivo', 'Tarjeta', 'PayPal') NOT NULL,
	`Empleado_ID_Empleado` INT UNSIGNED NOT NULL,
	`Stock_Stand_ID_Stand` INT UNSIGNED NOT NULL,
	`Stock_Stand_Zona_ID_Zona` INT UNSIGNED NOT NULL,
	`Stock_Juguete_ID_Juguete` INT UNSIGNED NOT NULL,
	PRIMARY KEY (`ID_Venta`),
	INDEX `fk_VENTA_EMPLEADO1_idx` (`Empleado_ID_Empleado` ASC) VISIBLE,
	INDEX `fk_venta_stock1_idx` (`Stock_Stand_ID_Stand` ASC, `Stock_Stand_Zona_ID_Zona` ASC, `Stock_Juguete_ID_Juguete` ASC) VISIBLE,
	UNIQUE INDEX `ID_Venta_UNIQUE` (`ID_Venta` ASC) VISIBLE,
	CONSTRAINT `fk_VENTA_EMPLEADO1`
		FOREIGN KEY (`Empleado_ID_Empleado`)
		REFERENCES `Jugueteria`.`Empleado` (`ID_Empleado`),
	CONSTRAINT `fk_venta_stock1`
		FOREIGN KEY (`Stock_Stand_ID_Stand` , `Stock_Stand_Zona_ID_Zona` , `Stock_Juguete_ID_Juguete`)
		REFERENCES `jugueteria`.`Stock` (`Stand_ID_Stand` , `Stand_Zona_ID_Zona` , `Juguete_ID_Juguete`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
