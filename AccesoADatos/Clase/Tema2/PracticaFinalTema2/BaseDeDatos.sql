-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8mb3 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`asignatura`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`asignatura` (
  `Codigo` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `NombreAsignatura` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`Codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`categoria` (
  `idCategoria` INT NOT NULL AUTO_INCREMENT,
  `nombre_categoria` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCategoria`),
  UNIQUE INDEX `idCategoria_UNIQUE` (`idCategoria` ASC) VISIBLE,
  UNIQUE INDEX `nomber_categoria_UNIQUE` (`nombre_categoria` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`compra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`compra` (
  `idCompra` INT NOT NULL AUTO_INCREMENT,
  `precio_total` FLOAT UNSIGNED NOT NULL,
  PRIMARY KEY (`idCompra`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`dueno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`dueno` (
  `idDueno` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NOT NULL,
  `Apellido` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idDueno`),
  UNIQUE INDEX `idDueno_UNIQUE` (`idDueno` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`mascota`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`mascota` (
  `idMascota` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NOT NULL,
  `fecha_nac` DATE NOT NULL,
  `Dueno_idDueno` INT NOT NULL,
  `Categoria_idCategoria` INT NOT NULL,
  PRIMARY KEY (`idMascota`),
  UNIQUE INDEX `idMascota_UNIQUE` (`idMascota` ASC) VISIBLE,
  INDEX `fk_Mascota_Dueno_idx` (`Dueno_idDueno` ASC) VISIBLE,
  INDEX `fk_Mascota_Categoria1_idx` (`Categoria_idCategoria` ASC) VISIBLE,
  CONSTRAINT `fk_Mascota_Categoria1`
    FOREIGN KEY (`Categoria_idCategoria`)
    REFERENCES `mydb`.`categoria` (`idCategoria`),
  CONSTRAINT `fk_Mascota_Dueno`
    FOREIGN KEY (`Dueno_idDueno`)
    REFERENCES `mydb`.`dueno` (`idDueno`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`usuario` (
  `idAllumno` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NOT NULL,
  `FechaNacimiento` DATE NOT NULL,
  `Genero` ENUM('Hombre', 'Mujer') NOT NULL,
  PRIMARY KEY (`idAllumno`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`matrícula`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`matrícula` (
  `Usuario_idAllumno` INT UNSIGNED NOT NULL,
  `Asignatura_Codigo` INT UNSIGNED NOT NULL,
  `Nota` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`Usuario_idAllumno`, `Asignatura_Codigo`),
  INDEX `fk_Usuario_has_Asignatura_Asignatura1_idx` (`Asignatura_Codigo` ASC) VISIBLE,
  INDEX `fk_Usuario_has_Asignatura_Usuario_idx` (`Usuario_idAllumno` ASC) VISIBLE,
  CONSTRAINT `fk_Usuario_has_Asignatura_Asignatura1`
    FOREIGN KEY (`Asignatura_Codigo`)
    REFERENCES `mydb`.`asignatura` (`Codigo`),
  CONSTRAINT `fk_Usuario_has_Asignatura_Usuario`
    FOREIGN KEY (`Usuario_idAllumno`)
    REFERENCES `mydb`.`usuario` (`idAllumno`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`medicina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`medicina` (
  `idMedicina` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NOT NULL,
  `Precio` FLOAT UNSIGNED NOT NULL,
  PRIMARY KEY (`idMedicina`),
  UNIQUE INDEX `idMedicina_UNIQUE` (`idMedicina` ASC) VISIBLE,
  UNIQUE INDEX `Nombre_UNIQUE` (`Nombre` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`producto` (
  `idProducto` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(20) NOT NULL,
  `Precio` FLOAT UNSIGNED NOT NULL,
  PRIMARY KEY (`idProducto`),
  UNIQUE INDEX `idProducto_UNIQUE` (`idProducto` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`pedido` (
  `Compra_idCompra` INT NOT NULL,
  `Dueno_idDueno` INT NOT NULL,
  `Producto_idProducto` INT NOT NULL,
  PRIMARY KEY (`Compra_idCompra`, `Dueno_idDueno`, `Producto_idProducto`),
  INDEX `fk_Compra_has_Dueno_Dueno1_idx` (`Dueno_idDueno` ASC) VISIBLE,
  INDEX `fk_Compra_has_Dueno_Compra1_idx` (`Compra_idCompra` ASC) VISIBLE,
  INDEX `fk_Compra_has_Dueno_Producto1_idx` (`Producto_idProducto` ASC) VISIBLE,
  CONSTRAINT `fk_Compra_has_Dueno_Compra1`
    FOREIGN KEY (`Compra_idCompra`)
    REFERENCES `mydb`.`compra` (`idCompra`),
  CONSTRAINT `fk_Compra_has_Dueno_Dueno1`
    FOREIGN KEY (`Dueno_idDueno`)
    REFERENCES `mydb`.`dueno` (`idDueno`),
  CONSTRAINT `fk_Compra_has_Dueno_Producto1`
    FOREIGN KEY (`Producto_idProducto`)
    REFERENCES `mydb`.`producto` (`idProducto`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`tratamiento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tratamiento` (
  `Medicina_idMedicina` INT NOT NULL,
  `Mascota_idMascota` INT NOT NULL,
  `Fecha` DATE NOT NULL,
  PRIMARY KEY (`Medicina_idMedicina`, `Mascota_idMascota`),
  INDEX `fk_Medicina_has_Mascota_Mascota1_idx` (`Mascota_idMascota` ASC) VISIBLE,
  INDEX `fk_Medicina_has_Mascota_Medicina1_idx` (`Medicina_idMedicina` ASC) VISIBLE,
  CONSTRAINT `fk_Medicina_has_Mascota_Mascota1`
    FOREIGN KEY (`Mascota_idMascota`)
    REFERENCES `mydb`.`mascota` (`idMascota`),
  CONSTRAINT `fk_Medicina_has_Mascota_Medicina1`
    FOREIGN KEY (`Medicina_idMedicina`)
    REFERENCES `mydb`.`medicina` (`idMedicina`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
