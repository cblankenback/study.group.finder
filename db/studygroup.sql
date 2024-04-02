-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema studygroup
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema studygroup
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `studygroup` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `studygroup` ;

-- -----------------------------------------------------
-- Table `studygroup`.`COURSE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `studygroup`.`COURSE` (
  `COURSE_ID` INT NOT NULL AUTO_INCREMENT,
  `COURSE_NAME` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`COURSE_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `studygroup`.`STUDENT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `studygroup`.`STUDENT` (
  `STUDENT_ID` INT NOT NULL AUTO_INCREMENT,
  `STUDENT_EMAIL` VARCHAR(50) NOT NULL,
  `STUDENT_PASSWORD` VARCHAR(50) NOT NULL,
  `STUDENT_FIRST_NAME` VARCHAR(50) NOT NULL,
  `STUDENT_LAST_NAME` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`STUDENT_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `studygroup`.`STUDENTCOURSES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `studygroup`.`STUDENTCOURSES` (
  `COURSE_ID` INT NOT NULL,
  `STUDENT_ID` INT NOT NULL,
  PRIMARY KEY (`COURSE_ID`, `STUDENT_ID`),
  INDEX `fk_studentcourses_student1_idx` (`STUDENT_ID` ASC) VISIBLE,
  CONSTRAINT `fk_studentcourses_course`
    FOREIGN KEY (`COURSE_ID`)
    REFERENCES `studygroup`.`COURSE` (`COURSE_ID`),
  CONSTRAINT `fk_studentcourses_student1`
    FOREIGN KEY (`STUDENT_ID`)
    REFERENCES `studygroup`.`STUDENT` (`STUDENT_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `studygroup`.`STUDYGROUP`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `studygroup`.`STUDYGROUP` (
  `SG_ID` INT NOT NULL AUTO_INCREMENT,
  `SG_NAME` VARCHAR(50) NOT NULL,
  `SG_WEEKDAY` VARCHAR(50) NOT NULL,
  `SG_TIME` VARCHAR(50) NOT NULL,
  `SG_LOCATION` VARCHAR(50) NOT NULL,
  `OWNER_ID` INT NOT NULL,
  `COURSE_ID` INT NOT NULL,
  PRIMARY KEY (`SG_ID`),
  INDEX `fk_student group_student1_idx` (`OWNER_ID` ASC) VISIBLE,
  INDEX `fk_studygroup_course1_idx` (`COURSE_ID` ASC) VISIBLE,
  CONSTRAINT `fk_student group_student1`
    FOREIGN KEY (`OWNER_ID`)
    REFERENCES `studygroup`.`STUDENT` (`STUDENT_ID`),
  CONSTRAINT `fk_studygroup_course1`
    FOREIGN KEY (`COURSE_ID`)
    REFERENCES `studygroup`.`COURSE` (`COURSE_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `studygroup`.`STUDENTSTUDYGROUP`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `studygroup`.`STUDENTSTUDYGROUP` (
  `STUDENT_ID` INT NOT NULL,
  `SG_ID` INT NOT NULL,
  PRIMARY KEY (`STUDENT_ID`, `SG_ID`),
  INDEX `fk_studentstudygroup_student group1_idx` (`SG_ID` ASC) VISIBLE,
  CONSTRAINT `fk_studentstudygroup_student group1`
    FOREIGN KEY (`SG_ID`)
    REFERENCES `studygroup`.`STUDYGROUP` (`SG_ID`),
  CONSTRAINT `fk_studentstudygroup_student1`
    FOREIGN KEY (`STUDENT_ID`)
    REFERENCES `studygroup`.`STUDENT` (`STUDENT_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
