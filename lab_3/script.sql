DROP SCHEMA IF EXISTS `mydb`;
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`manufacturer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`manufacturer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`model_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`model_type` (
  `type` VARCHAR(45) NOT NULL,
  UNIQUE INDEX `type_UNIQUE` (`type` ASC),
  PRIMARY KEY (`type`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`model`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`model` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `manufacturer_id` INT NOT NULL,
  `model_type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`, `manufacturer_id`, `model_type`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  INDEX `manufacturer_id_idx` (`manufacturer_id` ASC),
  INDEX `model_type_idx` (`model_type` ASC),
  CONSTRAINT `manufacturer_id`
    FOREIGN KEY (`manufacturer_id`)
    REFERENCES `mydb`.`manufacturer` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `model_type`
    FOREIGN KEY (`model_type`)
    REFERENCES `mydb`.`model_type` (`type`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`country`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`country` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`air_company`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`air_company` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `country_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  INDEX `country_id_idx` (`country_id` ASC),
  CONSTRAINT `registered_country_id`
    FOREIGN KEY (`country_id`)
    REFERENCES `mydb`.`country` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`aircraft`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`aircraft` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `board_number` VARCHAR(10) NOT NULL,
  `registration_number` VARCHAR(15) NOT NULL,
  `registration_country_id` INT NOT NULL,
  `model_id` INT NOT NULL,
  `air_company_id` INT NULL,
  `longtitude` DOUBLE NULL,
  `latitude` DOUBLE NULL,
  `altitude` DOUBLE NULL,
  `current_speed` DOUBLE NULL,
  `image_link` VARCHAR(100) NULL,
  `icao_adress` VARCHAR(15) NULL,
  PRIMARY KEY (`id`, `model_id`),
  UNIQUE INDEX `board_number_UNIQUE` (`board_number` ASC),
  UNIQUE INDEX `registration_number_UNIQUE` (`registration_number` ASC),
  INDEX `registration_country_id_idx` (`registration_country_id` ASC),
  INDEX `model_id_idx` (`model_id` ASC),
  INDEX `air_company_id_idx` (`air_company_id` ASC),
  UNIQUE INDEX `icao_adress_UNIQUE` (`icao_adress` ASC),
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
-- Table `mydb`.`city`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`city` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(60) NOT NULL,
  `country_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  INDEX `country_id_idx` (`country_id` ASC),
  CONSTRAINT `country_id`
    FOREIGN KEY (`country_id`)
    REFERENCES `mydb`.`country` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`airport`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`airport` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(90) NOT NULL,
  `city_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  INDEX `city_id_idx` (`city_id` ASC),
  CONSTRAINT `city_id`
    FOREIGN KEY (`city_id`)
    REFERENCES `mydb`.`city` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`status` (
  `name` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`name`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`flight`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`flight` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `aircraft_id` INT NOT NULL,
  `from_airport_id` INT NULL,
  `to_airport_id` INT NULL,
  `status_name` VARCHAR(25) NULL,
  `scheduled_departure_time` DATETIME NULL,
  `scheduled_arrival_time` DATETIME NULL,
  `actual_departure_time` DATETIME NULL,
  `est_arrival_time` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `from_airport_id_idx` (`from_airport_id` ASC),
  INDEX `to_airport_id_idx` (`to_airport_id` ASC),
  INDEX `aircraft_id_idx` (`aircraft_id` ASC),
  INDEX `status_name_idx` (`status_name` ASC),
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
    ON UPDATE CASCADE,
  CONSTRAINT `status_name`
    FOREIGN KEY (`status_name`)
    REFERENCES `mydb`.`status` (`name`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`pilot`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`pilot` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `license_number` VARCHAR(45) NOT NULL,
  `aircraft_id` INT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `aircraft_id_idx` (`aircraft_id` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  CONSTRAINT `owned_aircraft_id`
    FOREIGN KEY (`aircraft_id`)
    REFERENCES `mydb`.`aircraft` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`pilot_login`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`pilot_login` (
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `pilot_id` INT NOT NULL,
  PRIMARY KEY (`pilot_id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC),
  UNIQUE INDEX `password_UNIQUE` (`password` ASC),
  UNIQUE INDEX `pilot_id_UNIQUE` (`pilot_id` ASC),
  CONSTRAINT `pilot_id`
    FOREIGN KEY (`pilot_id`)
    REFERENCES `mydb`.`pilot` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Data for table `mydb`.`manufacturer`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`manufacturer` (`name`) VALUES ('Boeing');
INSERT INTO `mydb`.`manufacturer` (`name`) VALUES ('Airbus');
INSERT INTO `mydb`.`manufacturer` (`name`) VALUES ('Antonov');
INSERT INTO `mydb`.`manufacturer` (`name`) VALUES ('Planic');
INSERT INTO `mydb`.`manufacturer` (`name`) VALUES ('Corny');
INSERT INTO `mydb`.`manufacturer` (`name`) VALUES ('AirPlane');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`model_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`model_type` (`type`) VALUES ('passenger');
INSERT INTO `mydb`.`model_type` (`type`) VALUES ('cargo');
INSERT INTO `mydb`.`model_type` (`type`) VALUES ('small');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`model`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`model` (`name`, `manufacturer_id`, `model_type`) VALUES ('Mriya', 3, 'passenger');
INSERT INTO `mydb`.`model` (`name`, `manufacturer_id`, `model_type`) VALUES ('747', 1, 'cargo');
INSERT INTO `mydb`.`model` (`name`, `manufacturer_id`, `model_type`) VALUES ('344', 2, 'passenger');
INSERT INTO `mydb`.`model` (`name`, `manufacturer_id`, `model_type`) VALUES ('Farmer', 5, 'small');
INSERT INTO `mydb`.`model` (`name`, `manufacturer_id`, `model_type`) VALUES ('Hellcat', 6, 'cargo');
INSERT INTO `mydb`.`model` (`name`, `manufacturer_id`, `model_type`) VALUES ('141', 1, 'small');
INSERT INTO `mydb`.`model` (`name`, `manufacturer_id`, `model_type`) VALUES ('b-315-c', 3, 'cargo');
INSERT INTO `mydb`.`model` (`name`, `manufacturer_id`, `model_type`) VALUES ('hole-47', 4, 'passenger');
INSERT INTO `mydb`.`model` (`name`, `manufacturer_id`, `model_type`) VALUES ('zone-51', 5, 'cargo');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`country`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`country` (`name`) VALUES ('Ukraine');
INSERT INTO `mydb`.`country` (`name`) VALUES ('USA');
INSERT INTO `mydb`.`country` (`name`) VALUES ('Qatar');
INSERT INTO `mydb`.`country` (`name`) VALUES ('Turkey');
INSERT INTO `mydb`.`country` (`name`) VALUES ('France');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`air_company`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`air_company` (`name`, `country_id`) VALUES ('Super Airlines', 1);
INSERT INTO `mydb`.`air_company` (`name`, `country_id`) VALUES ('Qatar Airways', 3);
INSERT INTO `mydb`.`air_company` (`name`, `country_id`) VALUES ('Turkish Airlines', 4);
INSERT INTO `mydb`.`air_company` (`name`, `country_id`) VALUES ('FranceAir', 5);
INSERT INTO `mydb`.`air_company` (`name`, `country_id`) VALUES ('RyanAir', 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`aircraft`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`aircraft` (`board_number`, `registration_number`, `registration_country_id`, `model_id`, `air_company_id`, `longtitude`, `latitude`, `altitude`, `current_speed`, `image_link`, `icao_adress`) VALUES ('GH7456', 'K-AIY', 1, 8, 3, 79.1, 56.3, 30, 56, 'image.com', '040131');
INSERT INTO `mydb`.`aircraft` (`board_number`, `registration_number`, `registration_country_id`, `model_id`, `air_company_id`, `longtitude`, `latitude`, `altitude`, `current_speed`, `image_link`, `icao_adress`) VALUES ('GH7477', 'B-ATIC', 2, 4, 5, null, null, 398, 56, 'image.com', '040333');
INSERT INTO `mydb`.`aircraft` (`board_number`, `registration_number`, `registration_country_id`, `model_id`, `air_company_id`, `longtitude`, `latitude`, `altitude`, `current_speed`, `image_link`, `icao_adress`) VALUES ('AR7456', 'UA-FLY', 3, 3, 2, 156.4, 234, 7500, 56, 'image.com', '567899');
INSERT INTO `mydb`.`aircraft` (`board_number`, `registration_number`, `registration_country_id`, `model_id`, `air_company_id`, `longtitude`, `latitude`, `altitude`, `current_speed`, `image_link`, `icao_adress`) VALUES ('UK7666', 'ET-AT', 4, 5, 1, 7.89, 233, 30, 1200, 'image.com', '123331');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`city`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`city` (`name`, `country_id`) VALUES ('Kyiv', 1);
INSERT INTO `mydb`.`city` (`name`, `country_id`) VALUES ('Doha', 3);
INSERT INTO `mydb`.`city` (`name`, `country_id`) VALUES ('New York', 2);
INSERT INTO `mydb`.`city` (`name`, `country_id`) VALUES ('Lviv', 1);
INSERT INTO `mydb`.`city` (`name`, `country_id`) VALUES ('Paris', 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`airport`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`airport` (`name`, `city_id`) VALUES ('Danylo Halytskii', 4);
INSERT INTO `mydb`.`airport` (`name`, `city_id`) VALUES ('Qatarian', 2);
INSERT INTO `mydb`.`airport` (`name`, `city_id`) VALUES ('Boryspil', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`status`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`status` (`name`) VALUES ('in progress');
INSERT INTO `mydb`.`status` (`name`) VALUES ('delayed');
INSERT INTO `mydb`.`status` (`name`) VALUES ('cancelled');
INSERT INTO `mydb`.`status` (`name`) VALUES ('future');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`flight`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`flight` (`aircraft_id`, `from_airport_id`, `to_airport_id`, `status_name`, `scheduled_departure_time`, `scheduled_arrival_time`, `actual_departure_time`, `est_arrival_time`) VALUES (1, 1, 3, 'future', '2019-09-30 23:50:00', '2019-10-01 04:00:00', '2019-10-01 00:20:20', '2019-10-01 05:30:00');

COMMIT;

