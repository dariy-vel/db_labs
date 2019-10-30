-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`model`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`model` ;

CREATE TABLE IF NOT EXISTS `mydb`.`model` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `manufacturer` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`country`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`country` ;

CREATE TABLE IF NOT EXISTS `mydb`.`country` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`air_company`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`air_company` ;

CREATE TABLE IF NOT EXISTS `mydb`.`air_company` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `country_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  INDEX `country_id_idx` (`country_id` ASC) VISIBLE,
  CONSTRAINT `country_id`
    FOREIGN KEY (`country_id`)
    REFERENCES `mydb`.`country` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`aircraft`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`aircraft` ;

CREATE TABLE IF NOT EXISTS `mydb`.`aircraft` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `board_number` VARCHAR(10) NOT NULL,
  `registration_number` VARCHAR(15) NOT NULL,
  `registration_country_id` INT NOT NULL,
  `model_id` INT NOT NULL,
  `air_company_id` INT NULL,
  `longitude` DOUBLE NULL,
  `latitude` DOUBLE NULL,
  PRIMARY KEY (`id`, `model_id`),
  UNIQUE INDEX `board_number_UNIQUE` (`board_number` ASC) VISIBLE,
  UNIQUE INDEX `registration_number_UNIQUE` (`registration_number` ASC) VISIBLE,
  INDEX `registration_country_id_idx` (`registration_country_id` ASC) VISIBLE,
  INDEX `model_id_idx` (`model_id` ASC) VISIBLE,
  INDEX `air_company_id_idx` (`air_company_id` ASC) VISIBLE,
  CONSTRAINT `registration_country_id`
    FOREIGN KEY (`registration_country_id`)
    REFERENCES `mydb`.`country` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `model_id`
    FOREIGN KEY (`model_id`)
    REFERENCES `mydb`.`model` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `air_company_id`
    FOREIGN KEY (`air_company_id`)
    REFERENCES `mydb`.`air_company` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`airport`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`airport` ;

CREATE TABLE IF NOT EXISTS `mydb`.`airport` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(90) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`flight`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`flight` ;

CREATE TABLE IF NOT EXISTS `mydb`.`flight` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `aircraft_id` INT NOT NULL,
  `from_airport_id` INT NULL,
  `to_airport_id` INT NULL,
  `scheduled_departure_time` TIMESTAMP NULL,
  `scheduled_arrival_time` TIMESTAMP NULL,
  `actual_departure_time` TIMESTAMP NULL,
  `est_arrival_time` TIMESTAMP NULL,
  PRIMARY KEY (`id`),
  INDEX `from_airport_id_idx` (`from_airport_id` ASC) VISIBLE,
  INDEX `to_airport_id_idx` (`to_airport_id` ASC) VISIBLE,
  INDEX `aircraft_id_idx` (`aircraft_id` ASC) VISIBLE,
  CONSTRAINT `from_airport_id`
    FOREIGN KEY (`from_airport_id`)
    REFERENCES `mydb`.`airport` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `to_airport_id`
    FOREIGN KEY (`to_airport_id`)
    REFERENCES `mydb`.`airport` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `aircraft_id`
    FOREIGN KEY (`aircraft_id`)
    REFERENCES `mydb`.`aircraft` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Data for table `mydb`.`model`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`model` (`id`, `name`, `manufacturer`) VALUES (1, 'Mriya', '3');
INSERT INTO `mydb`.`model` (`id`, `name`, `manufacturer`) VALUES (2, '747', '1');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`country`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`country` (`id`, `name`) VALUES (1, 'Ukraine');
INSERT INTO `mydb`.`country` (`id`, `name`) VALUES (2, 'USA');
INSERT INTO `mydb`.`country` (`id`, `name`) VALUES (3, 'Qatar');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`air_company`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`air_company` (`id`, `name`, `country_id`) VALUES (1, 'Super Airlines', 1);
INSERT INTO `mydb`.`air_company` (`id`, `name`, `country_id`) VALUES (2, 'Qatar Airways', 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`aircraft`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`aircraft` (`id`, `board_number`, `registration_number`, `registration_country_id`, `model_id`, `air_company_id`, `longitude`, `latitude`) VALUES (1, 'GH7456', 'ET-ATY\n', 1, 1, 1, 79.1, 56.3);

COMMIT;

