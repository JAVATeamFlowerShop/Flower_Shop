-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema flowershop
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema flowershop
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `flowershop` DEFAULT CHARACTER SET utf8 ;
USE `flowershop` ;

-- -----------------------------------------------------
-- Table `flowershop`.`products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flowershop`.`products` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` ENUM('TREE', 'FLOWER', 'DECORATION') NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `price` FLOAT NOT NULL,
  `quantity` INT UNSIGNED NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `flowershop`.`flowers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flowershop`.`flowers` (
  `idflower` INT UNSIGNED NOT NULL,
  `colour` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idflower`),
  INDEX `fk_flowers_products_idx` (`idflower` ASC),
  CONSTRAINT `fk_flowers_products`
    FOREIGN KEY (`idflower`)
    REFERENCES `flowershop`.`products` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `flowershop`.`trees`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flowershop`.`trees` (
  `idtree` INT UNSIGNED NOT NULL,
  `height` FLOAT UNSIGNED NOT NULL,
  PRIMARY KEY (`idtree`),
  INDEX `fk_flowers_products_idx` (`idtree` ASC) ,
  CONSTRAINT `fk_flowers_products0`
    FOREIGN KEY (`idtree`)
    REFERENCES `flowershop`.`products` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `flowershop`.`decoration`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flowershop`.`decorations` (
  `iddecoration` INT UNSIGNED NOT NULL,
  `material` ENUM('PLASTIC', 'WOOD') NOT NULL,
  PRIMARY KEY (`iddecoration`),
  INDEX `fk_flowers_products_idx` (`iddecoration` ASC) ,
  CONSTRAINT `fk_flowers_products00`
    FOREIGN KEY (`iddecoration`)
    REFERENCES `flowershop`.`products` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `flowershop`.`tickets`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flowershop`.`tickets` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `total` FLOAT NOT NULL,
  `time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `flowershop`.`tickets_has_products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flowershop`.`tickets_has_products` (
  `idticket` INT UNSIGNED NOT NULL,
  `idproduct` INT UNSIGNED NOT NULL,
  `quantity` INT UNSIGNED NOT NULL DEFAULT 1,
  PRIMARY KEY (`idticket`, `idproduct`),
  INDEX `fk_tickets_has_products_products1_idx` (`idproduct` ASC),
  INDEX `fk_tickets_has_products_tickets1_idx` (`idticket` ASC),
  CONSTRAINT `fk_tickets_has_products_tickets1`
    FOREIGN KEY (`idticket`)
    REFERENCES `flowershop`.`tickets` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tickets_has_products_products1`
    FOREIGN KEY (`idproduct`)
    REFERENCES `flowershop`.`products` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
