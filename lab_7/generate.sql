DROP SCHEMA IF EXISTS `velychkoDB`;

CREATE SCHEMA IF NOT EXISTS `velychkoDB` DEFAULT CHARACTER SET utf8 ;
USE `velychkoDB` ;

SET GLOBAL log_bin_trust_function_creators = 1;

DROP TABLE IF EXISTS `velychkoDB`.`region` ;
DROP TABLE IF EXISTS `velychkoDB`.`city` ;
DROP TABLE IF EXISTS `velychkoDB`.`debt` ;
DROP TABLE IF EXISTS `velychkoDB`.`group` ;
DROP TABLE IF EXISTS `velychkoDB`.`middle_school` ;
DROP TABLE IF EXISTS `velychkoDB`.`student` ;


CREATE TABLE IF NOT EXISTS `velychkoDB`.`region` (
  `key` INT NOT NULL UNIQUE,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`key`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `velychkoDB`.`city` (
  `name` VARCHAR(45) NOT NULL UNIQUE,
  `region_key` INT NOT NULL,
  PRIMARY KEY (`name`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `velychkoDB`.`middle_school`  (
  `id` INT NOT NULL UNIQUE AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(20) NOT NULL,
  `head_name` VARCHAR(45) NOT NULL,
  `city_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `velychkoDB`.`group`  (
  `id` INT NOT NULL UNIQUE AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `number` INT NOT NULL,
  `enrollment_year` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `velychkoDB`.`debt`  (
  `id` INT NOT NULL UNIQUE AUTO_INCREMENT,
  `subject_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `velychkoDB`.`student`  (
  `id` INT NOT NULL UNIQUE AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `middle_name` VARCHAR(45) NOT NULL,
  `second_name` VARCHAR(45) NOT NULL,
  `rating` INT NOT NULL,
  `birthday` DATE NOT NULL,
  `enrollment` DATE NOT NULL,
  `student_card` VARCHAR(15) NOT NULL,
  `email` VARCHAR(25),
  `city_name`  VARCHAR(45) NOT NULL,
  `middle_school_id` INT NOT NULL,
  `group_id` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `velychkoDB`.`student_has_debt` (
  `student_id` INT NOT NULL,
  `debt_id` INT NOT NULL,
  PRIMARY KEY (`student_id`, `debt_id`))
ENGINE = InnoDB;

INSERT INTO `region` VALUES(1, 'Lvivska');
INSERT INTO `region` VALUES(2, 'Ternopilska');
INSERT INTO `region` VALUES(3, 'Volynska');

INSERT INTO `city` VALUES('Lviv', 1);
INSERT INTO `city` VALUES('Ternopil', 2);
INSERT INTO `city` VALUES('Lutsk', 3);

INSERT INTO `middle_school` VALUES(1, 'School N2', '+38097798033', 'Petrenko Oleg', 'Lviv');
INSERT INTO `middle_school` VALUES(2, 'School N3', '+38097798034', 'Petrenko Petro', 'Ternopil');
INSERT INTO `middle_school` VALUES(3, 'School N4', '+38097798035', 'Petrenko Vasyl', 'Lutsk');

INSERT INTO `group` VALUES(1, 'Internet Rechey', 21, 2018);
INSERT INTO `group` VALUES(2, 'Computer Science', 11, 2019);
INSERT INTO `group` VALUES(3, 'Internet Rechey', 43, 2016);

INSERT INTO `debt` VALUES(1, 'Computer Electronics');
INSERT INTO `debt` VALUES(2, 'Databases');
INSERT INTO `debt` VALUES(3, 'Algorithms');

INSERT INTO `student` VALUES(1, 'Petro', 'Olexandrovych', 'Mokrenko', 12, '1990-09-01', '2018-09-01', 'A12345678D',
	'petrenko@gmail.com', 'Lviv', 1, 1);
INSERT INTO `student` VALUES(2, 'Oleg', 'Olexandrovych', 'Petrenko', 101, '1990-09-05', '2018-09-01', 'A12345673D',
	'petrenko@gmail.com', 'Ternopil', 2, 2);
INSERT INTO `student` VALUES(3, 'Ivan', 'Olexandrovych', 'Ivanenko', 1, '1999-09-01', '2016-09-01', 'A12345674f',
	'petrenko@gmail.com', 'Lutsk', 3, 3);
    
INSERT INTO `student_has_debt` VALUES(1, 1);
INSERT INTO `student_has_debt` VALUES(1, 2);
INSERT INTO `student_has_debt` VALUES(1, 3);
INSERT INTO `student_has_debt` VALUES(3, 1);
INSERT INTO `student_has_debt` VALUES(3, 2);

DELIMITER //

CREATE PROCEDURE set_student(
	IN first_name VARCHAR(45), 
    IN middle_name VARCHAR(45),
    IN second_name VARCHAR(45),
    IN rating INT,
    IN birthday DATE,
    IN enrollment DATE,
    IN student_card VARCHAR(15),
	IN email VARCHAR(25),
	IN city_name VARCHAR(45),
    IN middle_school_name VARCHAR(45),
    IN group_id INT)
BEGIN

SELECT id INTO @middle_school_id FROM middle_school where `name`= middle_school_name;

INSERT INTO student(first_name, middle_name, second_name, rating, birthday, enrollment, student_card, email, city_name, middle_school_id, group_id)
VALUES(first_name, middle_name, second_name, rating, birthday, enrollment, student_card, email, city_name, @middle_school_id, group_id);

END //

CREATE PROCEDURE select_student_has_debt(
	IN first_name VARCHAR(45),
	IN second_name VARCHAR(45))
BEGIN
IF (first_name = NULL OR second_name = NULL) THEN
	SELECT student.`first_name`, student.`second_name`, debt.subject_name FROM student_has_debt
    LEFT JOIN student ON student.id = student_id
    LEFT JOIN debt ON debt.id = debt_id;
ELSE
	SELECT student.`first_name`, student.`second_name`, debt.subject_name FROM student_has_debt
    LEFT JOIN student ON student.id = student_id
    LEFT JOIN debt ON debt.id = debt_id
    WHERE student.first_name = first_name AND student.second_name = second_name;
END IF;
END //

CREATE PROCEDURE create_table()
BEGIN
DECLARE done INT DEFAULT FALSE;
DECLARE stud_id INT;
DECLARE was INT DEFAULT FALSE;

DECLARE student_fields CURSOR FOR SELECT id FROM `student` ORDER BY RAND();
DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = true;
SET @v = (SELECT NOW()); 
SET @temp_query=CONCAT('CREATE TABLE IF NOT EXISTS `velychkoDB`.`', @v, '`  (
  `id` INT NOT NULL UNIQUE AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `middle_name` VARCHAR(45) NOT NULL,
  `second_name` VARCHAR(45) NOT NULL,
  `rating` INT NOT NULL,
  `birthday` DATE NOT NULL,
  `enrollment` DATE NOT NULL,
  `student_card` VARCHAR(15) NOT NULL,
  `email` VARCHAR(25),
  `city_name`  VARCHAR(45) NOT NULL,
  `middle_school_id` INT NOT NULL,
  `group_id` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;');
PREPARE myquery FROM @temp_query;
EXECUTE myquery;
DEALLOCATE PREPARE myquery;
SET @temp_query1 = CONCAT('CREATE TABLE IF NOT EXISTS `velychkoDB`.`', @v, '_1`  (
  `id` INT NOT NULL UNIQUE AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `middle_name` VARCHAR(45) NOT NULL,
  `second_name` VARCHAR(45) NOT NULL,
  `rating` INT NOT NULL,
  `birthday` DATE NOT NULL,
  `enrollment` DATE NOT NULL,
  `student_card` VARCHAR(15) NOT NULL,
  `email` VARCHAR(25),
  `city_name`  VARCHAR(45) NOT NULL,
  `middle_school_id` INT NOT NULL,
  `group_id` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;');
PREPARE myquery FROM @temp_query1;
EXECUTE myquery;
DEALLOCATE PREPARE myquery;

OPEN student_fields;
myLoop: LOOP

	 FETCH student_fields INTO stud_id;

	 IF done=true THEN LEAVE myLoop;
	 END IF;
     
     IF was=TRUE THEN
		SET was=FALSE;
        SET @temp_query = CONCAT('INSERT INTO `velychkoDB`.`', @v, '` SELECT * FROM student WHERE id=', stud_id, ";");
	 ELSE 
		SET was=TRUE;
        SET @temp_query = CONCAT('INSERT INTO `velychkoDB`.`', @v, '_1` SELECT * FROM student WHERE id=', stud_id, ";");
     END IF;  

	 PREPARE myquery FROM @temp_query;
	 EXECUTE myquery;
	 DEALLOCATE PREPARE myquery;
END LOOP;
CLOSE student_fields;
END //

DELIMITER ;

DELIMITER //

CREATE FUNCTION avg_rating()
	RETURNS INT
BEGIN
	SELECT AVG(student.rating) INTO @w FROM student;
    RETURN @w;
END //

CREATE FUNCTION region(city_name VARCHAR(45))
	RETURNS VARCHAR(45)
BEGIN
	SELECT region_key INTO @w FROM city WHERE `name` = city_name;
	SELECT CONCAT(`name`, ' ', `key`) INTO @v FROM region WHERE `key` = @w LIMIT 1;
    RETURN @v;
END //

DELIMITER ;

DELIMITER //

CREATE TRIGGER before_student_insert
BEFORE INSERT
ON student FOR EACH ROW
BEGIN
	IF (SELECT COUNT(*) FROM `city` WHERE `name`=new.city_name) = 0 THEN
		SIGNAL SQLSTATE '45000' SET MYSQL_ERRNO = 30001, MESSAGE_TEXT = 'Can\'t insert record. Foreign parent key for city does not exist!';
	ELSEIF (SELECT COUNT(*) FROM `middle_school` WHERE `id`=new.middle_school_id) = 0 THEN
		SIGNAL SQLSTATE '45000' SET MYSQL_ERRNO = 30001, MESSAGE_TEXT = 'Can\'t insert record. Foreign parent key for middle_school does not exist!';
	ELSEIF (SELECT COUNT(*) FROM `group` WHERE `id`=new.group_id) = 0 THEN
		SIGNAL SQLSTATE '45000' SET MYSQL_ERRNO = 30001, MESSAGE_TEXT = 'Can\'t insert record. Foreign parent key for group does not exist!';
	ELSEIF new.student_card NOT REGEXP '^[a-zA-Z][0-9]{8}(?![sS])[a-zA-Z]$' THEN
		SIGNAL SQLSTATE '45000' SET MYSQL_ERRNO = 30001, MESSAGE_TEXT = 'Student card doesn`t match pattern!';
	ELSEIF TIMESTAMPDIFF(year, new.enrollment, new.birthday) < 16 THEN
		SIGNAL SQLSTATE '45000' SET MYSQL_ERRNO = 30001, MESSAGE_TEXT = 'Student is too young!';
END IF;
END //

CREATE TRIGGER before_city_insert
BEFORE INSERT
ON city FOR EACH ROW
BEGIN
    IF (SELECT COUNT(*) FROM `region` WHERE `key` = new.`region_key`) = 0 THEN	
	SIGNAL SQLSTATE '45000' SET MYSQL_ERRNO = 30001, MESSAGE_TEXT = 'Can\'t insert record. Foreign parent key for region does not exist!';
END IF;
END //

CREATE TRIGGER before_region_delete
BEFORE DELETE
ON region FOR EACH ROW
BEGIN
    IF NOT (SELECT COUNT(*) FROM `city` WHERE `region_key` = old.`key`) = 0 THEN	
		SIGNAL SQLSTATE '45000' SET MYSQL_ERRNO = 30001, MESSAGE_TEXT = 'Can\'t delete record. Foreign parent key for city exists!';
END IF;
END //

CREATE TRIGGER before_middle_school_insert
BEFORE INSERT
ON middle_school FOR EACH ROW
BEGIN
    IF (SELECT COUNT(*) FROM `city` WHERE `name` = new.`city_name`) = 0 THEN	
		SIGNAL SQLSTATE '45000' SET MYSQL_ERRNO = 30001, MESSAGE_TEXT = 'Can\'t insert record. Foreign parent key for city does not exist!';
END IF;
END //

CREATE TRIGGER before_city_delete
BEFORE DELETE
ON city FOR EACH ROW
BEGIN
    IF NOT (SELECT COUNT(*) FROM `middle_school` WHERE `city_name` = old.`name`) = 0 THEN	
		SIGNAL SQLSTATE '45000' SET MYSQL_ERRNO = 30001, MESSAGE_TEXT = 'Can\'t delete record. Foreign parent key for city exists!';
    ELSEIF NOT (SELECT COUNT(*) FROM `student` WHERE `city_name` = old.`name`) = 0 THEN	
		SIGNAL SQLSTATE '45000' SET MYSQL_ERRNO = 30001, MESSAGE_TEXT = 'Can\'t delete record. Foreign parent key for city exists!';    
END IF;
END //

CREATE TRIGGER before_student_delete
BEFORE DELETE
ON student FOR EACH ROW
BEGIN
    IF NOT (SELECT COUNT(*) FROM `student_has_debt` WHERE `student_id` = old.`id`) = 0 THEN	
		SIGNAL SQLSTATE '45000' SET MYSQL_ERRNO = 30001, MESSAGE_TEXT = 'Can\'t delete record. Foreign parent key for student_has_debt exists!';
END IF;
END //

CREATE TRIGGER before_debt_delete
BEFORE DELETE
ON debt FOR EACH ROW
BEGIN
    IF NOT (SELECT COUNT(*) FROM `student_has_debt` WHERE `debt_id` = old.`id`) = 0 THEN	
		SIGNAL SQLSTATE '45000' SET MYSQL_ERRNO = 30001, MESSAGE_TEXT = 'Can\'t delete record. Foreign parent key for student_has_debt exists!';
END IF;
END //

CREATE TRIGGER before_group_insert
BEFORE INSERT
ON `group` FOR EACH ROW
BEGIN
    IF (SELECT COUNT(id) FROM `group`) >= 6 THEN	
		SIGNAL SQLSTATE '45000' SET MYSQL_ERRNO = 30001, MESSAGE_TEXT = 'Can\'t insert the record. Reached max count 6!';
END IF;
END //

DELIMITER ;