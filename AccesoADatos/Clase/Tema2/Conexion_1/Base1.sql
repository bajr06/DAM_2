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
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Usuario` (
  `idAllumno` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NOT NULL,
  `FechaNacimiento` DATE NOT NULL,
  `Genero` ENUM("Hombre", "Mujer") NOT NULL,
  PRIMARY KEY (`idAllumno`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Asignatura`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Asignatura` (
  `Codigo` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `NombreAsignatura` VARCHAR(45) NULL,
  PRIMARY KEY (`Codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Matrícula`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Matrícula` (
  `Usuario_idAllumno` INT UNSIGNED NOT NULL,
  `Asignatura_Codigo` INT UNSIGNED NOT NULL,
  `Nota` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`Usuario_idAllumno`, `Asignatura_Codigo`),
  INDEX `fk_Usuario_has_Asignatura_Asignatura1_idx` (`Asignatura_Codigo` ASC) VISIBLE,
  INDEX `fk_Usuario_has_Asignatura_Usuario_idx` (`Usuario_idAllumno` ASC) VISIBLE,
  CONSTRAINT `fk_Usuario_has_Asignatura_Usuario`
    FOREIGN KEY (`Usuario_idAllumno`)
    REFERENCES `mydb`.`Usuario` (`idAllumno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Usuario_has_Asignatura_Asignatura1`
    FOREIGN KEY (`Asignatura_Codigo`)
    REFERENCES `mydb`.`Asignatura` (`Codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
