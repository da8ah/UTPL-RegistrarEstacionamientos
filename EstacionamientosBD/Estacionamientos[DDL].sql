-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema estacionamientos
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `estacionamientos` ;

-- -----------------------------------------------------
-- Schema estacionamientos
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `estacionamientos` DEFAULT CHARACTER SET utf8 ;
SHOW WARNINGS;
USE `estacionamientos` ;

-- -----------------------------------------------------
-- Table `estacionamientos`.`propietarios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `estacionamientos`.`propietarios` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `estacionamientos`.`propietarios` (
  `idpropietarios` INT(10) UNSIGNED ZEROFILL NOT NULL,
  `nombres` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(100) NULL,
  `email` VARCHAR(45) NOT NULL,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idpropietarios`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `estacionamientos`.`estacionamientos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `estacionamientos`.`estacionamientos` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `estacionamientos`.`estacionamientos` (
  `idestacionamientos` INT UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(100) NOT NULL,
  `telefono` VARCHAR(10) NOT NULL,
  `url` VARCHAR(45) NULL,
  `latitud` VARCHAR(45) NULL,
  `longitud` VARCHAR(45) NULL,
  `propietarios_idpropietarios` INT(10) UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (`idestacionamientos`),
  CONSTRAINT `fk_estacionamientos_propietarios1`
    FOREIGN KEY (`propietarios_idpropietarios`)
    REFERENCES `estacionamientos`.`propietarios` (`idpropietarios`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_estacionamientos_propietarios1_idx` ON `estacionamientos`.`estacionamientos` (`propietarios_idpropietarios` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `estacionamientos`.`empleados`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `estacionamientos`.`empleados` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `estacionamientos`.`empleados` (
  `idempleados` INT UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `nombres` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(100) NULL,
  `email` VARCHAR(45) NOT NULL,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `estacionamientos_idestacionamientos` INT ZEROFILL UNSIGNED NOT NULL,
  PRIMARY KEY (`idempleados`),
  CONSTRAINT `fk_empleados_estacionamientos`
    FOREIGN KEY (`estacionamientos_idestacionamientos`)
    REFERENCES `estacionamientos`.`estacionamientos` (`idestacionamientos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_empleados_estacionamientos_idx` ON `estacionamientos`.`empleados` (`estacionamientos_idestacionamientos` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `estacionamientos`.`espacios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `estacionamientos`.`espacios` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `estacionamientos`.`espacios` (
  `idespacios` INT UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `referencia_identificacion` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(100) NOT NULL,
  `disponible` VARCHAR(10) NOT NULL,
  `estacionamientos_idestacionamientos` INT ZEROFILL UNSIGNED NOT NULL,
  PRIMARY KEY (`idespacios`),
  CONSTRAINT `fk_espacios_estacionamientos`
    FOREIGN KEY (`estacionamientos_idestacionamientos`)
    REFERENCES `estacionamientos`.`estacionamientos` (`idestacionamientos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_espacios_estacionamientos_idx` ON `estacionamientos`.`espacios` (`estacionamientos_idestacionamientos` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `estacionamientos`.`tarifas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `estacionamientos`.`tarifas` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `estacionamientos`.`tarifas` (
  `idtarifas` INT UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) NOT NULL,
  `cantidad_tiempo` VARCHAR(45) NOT NULL,
  `costo` DECIMAL(5,2) NOT NULL,
  `estacionamientos_idestacionamientos` INT ZEROFILL UNSIGNED NOT NULL,
  PRIMARY KEY (`idtarifas`),
  CONSTRAINT `fk_tarifas_estacionamientos`
    FOREIGN KEY (`estacionamientos_idestacionamientos`)
    REFERENCES `estacionamientos`.`estacionamientos` (`idestacionamientos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_tarifas_estacionamientos_idx` ON `estacionamientos`.`tarifas` (`estacionamientos_idestacionamientos` ASC);

SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
