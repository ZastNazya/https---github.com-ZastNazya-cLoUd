# USE [spring-boot-app-db];
#
# DROP FUNCTION IF EXISTS max_fortunes;
#
# DELIMITER //
# CREATE FUNCTION max_fortunes()
#     RETURNS DOUBLE DETERMINISTIC
# BEGIN
#     DECLARE max_fortune DOUBLE;
#     SELECT MAX(fortunes)
#     INTO max_fortune
#     FROM information_about_owner;
#     RETURN max_fortune;
# END; //
