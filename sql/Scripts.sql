-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Conference
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Conference
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Conference` DEFAULT CHARACTER SET utf8 ;
USE `Conference` ;

-- -----------------------------------------------------
-- Table `Conference`.`CONFERENCE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Conference`.`CONFERENCE` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(150) NOT NULL,
  `DATE` DATETIME NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Conference`.`SECTION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Conference`.`SECTION` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `CONFERENCE_ID` INT NOT NULL,
  `NAME` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Section_Conference_idx` (`CONFERENCE_ID` ASC) VISIBLE,
  CONSTRAINT `fk_Section_Conference`
    FOREIGN KEY (`CONFERENCE_ID`)
    REFERENCES `Conference`.`CONFERENCE` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Conference`.`USER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Conference`.`USER` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `ROLE` ENUM('USER', 'ADMIN') NOT NULL DEFAULT 'USER',
  `LOGIN` VARCHAR(45) NOT NULL,
  `PASSWORD` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Conference`.`TOPIC`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Conference`.`TOPIC` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `USER_ID` INT NOT NULL,
  `SECTION_ID` INT NOT NULL,
  `NAME` VARCHAR(150) NOT NULL,
  `STATUS` ENUM('considered', 'rejected', 'accepted') NOT NULL DEFAULT 'considered',
  PRIMARY KEY (`ID`),
  INDEX `fk_Topic_User1_idx` (`USER_ID` ASC) VISIBLE,
  INDEX `fk_Topic_Section1_idx` (`SECTION_ID` ASC) VISIBLE,
  CONSTRAINT `fk_Topic_User1`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `Conference`.`USER` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Topic_Section1`
    FOREIGN KEY (`SECTION_ID`)
    REFERENCES `Conference`.`SECTION` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Conference`.`QUESTION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Conference`.`QUESTION` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `CONTENT` VARCHAR(45) NOT NULL,
  `USER_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Question_User1_idx` (`USER_ID` ASC) VISIBLE,
  CONSTRAINT `fk_Question_User1`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `Conference`.`USER` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Conference`.`ANSWER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Conference`.`ANSWER` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `CONTENT` VARCHAR(45) NOT NULL,
  `USER_ID` INT NOT NULL,
  `QUESTION_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Answer_User1_idx` (`USER_ID` ASC) VISIBLE,
  INDEX `fk_Answer_Question1_idx` (`QUESTION_ID` ASC) VISIBLE,
  CONSTRAINT `fk_Answer_User1`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `Conference`.`USER` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Answer_Question1`
    FOREIGN KEY (`QUESTION_ID`)
    REFERENCES `Conference`.`QUESTION` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
