# USE [spring-boot-app-db];
#
# DROP PROCEDURE IF EXISTS input_data_about_owner;
# DROP PROCEDURE IF EXISTS insert_10_credential;
# DROP PROCEDURE IF EXISTS dymanic_procedure;
#
# DELIMITER //
# CREATE PROCEDURE input_data_about_owner(
#     IN input_name VARCHAR(30),
#     IN input_surname VARCHAR(40),
#     IN input_age INT,
#     IN input_fortunes INT)
# BEGIN
#     INSERT INTO information_about_owner (name, surname, age, fortunes)
#     VALUES (input_name, input_surname, input_age, input_fortunes);
# END //
# DELIMITER ;
#
# DELIMITER //
# CREATE PROCEDURE insert_10_credential()
# BEGIN
#     DECLARE iterator INT DEFAULT 0;
#     SET iterator = 1;
#     WHILE iterator <= 10
#         DO
#             INSERT INTO credential (login, password)
#             VALUES (CONCAT('Login', iterator), CONCAT('Password', iterator));
#             SET iterator = iterator + 1;
#         END WHILE;
# END //
# DELIMITER ;
#
# DELIMITER //
# CREATE PROCEDURE dymanic_procedure()
# BEGIN
#     DECLARE done int DEFAULT false;
#     DECLARE name_T char(25);
#
#     DECLARE establishment_cursor CURSOR
#         FOR SELECT name FROM establishment;
#     DECLARE CONTINUE HANDLER
#         FOR NOT FOUND SET done = TRUE;
#
#     OPEN establishment_cursor;
#     label:
#     LOOP
#         FETCH establishment_cursor INTO name_T;
#         IF done = TRUE THEN
#             LEAVE label;
#         END IF;
#
#         SET @temp_query = CONCAT('CREATE DATABASE ', name_T);
#         SET @temp_drop = CONCAT('DROP DATABASE IF EXISTS ', name_T);
#
#         PREPARE my_query FROM @temp_drop;
#         EXECUTE my_query;
#         DEALLOCATE PREPARE my_query;
#
#         PREPARE my_query FROM @temp_query;
#         EXECUTE my_query;
#         DEALLOCATE PREPARE my_query;
#
#         SET @iterator = 1;
#         SET @LIMIT = FLOOR(1 + RAND() * (10 - 1));
#         WHILE @iterator <= @LIMIT
#             DO
#                 SET @temp_query = CONCAT('CREATE TABLE ', name_T, @iterator, ' (name VARCHAR(30) NOT NULL PRIMARY KEY);');
#                 SET @iterator = @iterator + 1;
#                 PREPARE my_query FROM @temp_query;
#                 EXECUTE my_query;
#                 DEALLOCATE PREPARE my_query;
#             END WHILE;
#     END LOOP;
#     CLOSE establishment_cursor;
# END //
# DELIMITER ;