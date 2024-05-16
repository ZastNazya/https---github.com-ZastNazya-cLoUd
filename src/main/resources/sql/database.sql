IF DB_ID('iot-cloud-course') IS NULL
    BEGIN
        CREATE DATABASE [iot-cloud-course];
    END
USE [iot-cloud-course];

DROP TABLE IF EXISTS establishment_review_of_establishment;
DROP TABLE IF EXISTS establishment;
DROP TABLE IF EXISTS street;
DROP TABLE IF EXISTS city;
DROP TABLE IF EXISTS review_of_establishment;
DROP TABLE IF EXISTS user_account;
DROP TABLE IF EXISTS type_of_establishment;
DROP TABLE IF EXISTS information_about_owner;
DROP TABLE IF EXISTS credential;
DROP TABLE IF EXISTS country;

CREATE TABLE information_about_owner
(
    id       int         NOT NULL,
    name     varchar(30) NOT NULL,
    surname  varchar(40) NOT NULL,
    age      int         NOT NULL,
    fortunes int         NOT NULL,
    CONSTRAINT information_about_owner_pk PRIMARY KEY (id)
);

CREATE TABLE country
(
    name varchar(45) NOT NULL,
    CONSTRAINT country_pk PRIMARY KEY (name)
);

CREATE TABLE city
(
    id           int         NOT NULL,
    name         varchar(45) NOT NULL,
    country_name varchar(45) NULL,
    CONSTRAINT city_pk PRIMARY KEY (id)
);

CREATE TABLE street
(
    id      int         NOT NULL,
    name    varchar(45) NOT NULL,
    city_id int         NULL,
    CONSTRAINT street_pk PRIMARY KEY (id)
);

CREATE TABLE credential
(
    id       int         NOT NULL,
    login    varchar(30) NOT NULL,
    password varchar(45) NOT NULL,
    CONSTRAINT credential_pk PRIMARY KEY (id)
);

CREATE TABLE user_account
(
    nickname      varchar(30) NOT NULL,
    name          varchar(30) NOT NULL,
    surname       varchar(40) NOT NULL,
    credential_id int         NULL,
    CONSTRAINT user_account_pk PRIMARY KEY (nickname)
);

CREATE TABLE review_of_establishment
(
    id                    int          NOT NULL,
    review                varchar(255) NULL,
    user_account_nickname varchar(30)  NULL,
    CONSTRAINT review_of_establishment_pk PRIMARY KEY (id)
);

CREATE TABLE type_of_establishment
(
    id        int         NOT NULL,
    type_name varchar(30) NOT NULL,
    CONSTRAINT type_of_establishment_pk PRIMARY KEY (id)
);

CREATE TABLE establishment
(
    id                         int         NOT NULL,
    name                       varchar(30) NOT NULL,
    type_of_establishment_id   int         NULL,
    rating                     float(2)    NULL,
    street_id                  int         NULL,
    Information_about_owner_id int         NULL,
    CONSTRAINT establishment_pk PRIMARY KEY (id)
);

CREATE TABLE establishment_review_of_establishment
(
    review_of_establishment_id int NOT NULL,
    establishment_id           int NOT NULL,
    CONSTRAINT establishment_review_of_establishment_pk PRIMARY KEY (review_of_establishment_id, establishment_id)
);

-- foreign keys
-- Reference: city_country (table: city)
ALTER TABLE city
    ADD CONSTRAINT city_country FOREIGN KEY (country_name)
        REFERENCES country (name);

-- Reference: establishment_review_of_establishment_establishment (table: establishment_review_of_establishment)
ALTER TABLE establishment_review_of_establishment
    ADD CONSTRAINT establishment_review_of_establishment_establishment FOREIGN KEY (establishment_id)
        REFERENCES establishment (id);


ALTER TABLE establishment_review_of_establishment
    ADD CONSTRAINT establishment_review_of_establishment_review_of_establishment FOREIGN KEY (review_of_establishment_id)
        REFERENCES review_of_establishment (id);

ALTER TABLE establishment
    ADD CONSTRAINT establishment_street FOREIGN KEY (street_id)
        REFERENCES street (id);

ALTER TABLE establishment
    ADD CONSTRAINT establishment_type_of_establishment FOREIGN KEY (type_of_establishment_id)
        REFERENCES type_of_establishment (id);

ALTER TABLE establishment
    ADD CONSTRAINT establishment_Information_about_owner FOREIGN KEY (Information_about_owner_id)
        REFERENCES Information_about_owner (id);

ALTER TABLE review_of_establishment
    ADD CONSTRAINT review_of_establishment_user_account FOREIGN KEY (user_account_nickname)
        REFERENCES user_account (nickname);

ALTER TABLE street
    ADD CONSTRAINT street_city FOREIGN KEY (city_id)
        REFERENCES city (id);

ALTER TABLE user_account
    ADD CONSTRAINT user_account_credential FOREIGN KEY (credential_id)
        REFERENCES credential (id);

INSERT INTO information_about_owner(id, name, surname, age, fortunes)
VALUES (1, 'Pavlo', 'Turchynyak', 35, 15000),
       (2, 'Oleg', 'Kiruch', 40, 300000),
       (3, 'Igor', 'Vilkov', 25, 250000),
       (4, 'Marko', 'Yaminskyy', 45, 85000),
       (5, 'Denys', 'Izhyk', 60, 875000),
       (6, 'Roman', 'Push', 23, 873525);

INSERT INTO country(name)
VALUES ('Ukraine'),
       ('Poland'),
       ('USA'),
       ('France'),
       ('Germany'),
       ('Pidarasia'),
       ('Greece');

INSERT INTO city (id, name, country_name)
VALUES (1, 'Lviv', 'Ukraine'),
       (2, 'Kyiv', 'Ukraine'),
       (3, 'Warsaw', 'Poland'),
       (4, 'Berlin', 'Germany'),
       (5, 'Paris', 'France'),
       (6, 'Dalos', 'USA');

INSERT INTO street (id, name, city_id)
VALUES (1, 'Veres', 2),
       (2, 'Peace', 3),
       (3, 'IoT', 1),
       (4, 'Polytechic', 1),
       (5, 'Ukrainer', 3),
       (6, 'Zelensky', 5);

INSERT INTO credential (id, login, password)
VALUES (1, '32121', '32143311'),
       (2, '32dgfda1', '3214rewrw1'),
       (3, '354354ddsawewq', '32proradsa11'),
       (4, '4bxfzrre21', '321gfdgdsasa1'),
       (5, '3rewrew', 'rew2rerewr'),
       (6, '54354dksaorjirkew', 'k3hjejmdkldspe'),
       (7, 'o432432432', '3fdzldsds');

INSERT INTO user_account(nickname, name, surname, credential_id)
VALUES ('mtk', 'Pavlo', 'Arach', 2),
       ('elena', 'Olena', 'Arach', 1),
       ('32Looo32', 'Igor', 'Rock', 5),
       ('krasava', 'Iryna', 'Pistun', 2);

INSERT INTO review_of_establishment(id, review, user_account_nickname)
VALUES (1, '324324rejitre', 'mtk'),
       (2, '324324dsadasdsadsdsadsadsatrteihtjrejitre', 'mtk'),
       (3, '32435i4395430mvc,mfsnajfjjfnsjht bewkj akxdadsa,dsjoprjlre', 'elena'),
       (4, '3kkwipoewqeowqp[wihtfdsfdsfdsxfdxfsdasjrejitre', 'krasava'),
       (5, '432432', 'krasava');

INSERT INTO type_of_establishment(id, type_name)
VALUES (1, 'restoran'),
       (2, 'caffe'),
       (3, 'bar'),
       (4, 'pub'),
       (5, 'coffeer');

INSERT INTO establishment (id, name, type_of_establishment_id, rating, street_id, Information_about_owner_id)
VALUES (1, 'FKшллльS', 2, 4.3, 2, 2),
       (2, 'FKdfsfdszS', 3, 5.1, 5, 2),
       (3, 'IOT', 2, 3.4, 1, 5),
       (4, 'XSRE', 4, 5.0, 2, 2);

INSERT INTO establishment_review_of_establishment(review_of_establishment_id, establishment_id)
VALUES (1, 1),
       (2, 1),
       (3, 4),
       (2, 3),
       (4, 1),
       (4, 3);

CREATE INDEX name_index ON city (name);
CREATE INDEX name_index ON street (name);
CREATE INDEX name_index ON Information_about_owner (name);
CREATE INDEX surname_index ON Information_about_owner (surname);
CREATE INDEX rating_index ON establishment (rating);
CREATE INDEX name_index ON establishment (name);
CREATE INDEX name_index ON user_account (name);
CREATE INDEX surname_index ON user_account (surname);
