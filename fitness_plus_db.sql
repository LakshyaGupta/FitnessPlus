CREATE DATABASE fitness_plus_db;

DROP DATABASE fitness_plus_db;

USE fitness_plus_db;

CREATE TABLE dashboard (
id INT PRIMARY KEY NOT NULL,
avg_sleep DOUBLE NOT NULL,
avg_food INT NOT NULL,
avg_exercise DOUBLE NOT NULL ,
sleep_diff DOUBLE NOT NULL,
food_diff INT NOT NULL,
exercise_diff DOUBLE NOT NULL);

CREATE TABLE user (
id INT PRIMARY KEY NOT NULL,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
email VARCHAR(50) NOT NULL,
password VARCHAR(50) NOT NULL );

CREATE TABLE food (
id INT PRIMARY KEY NOT NULL,
name VARCHAR(50) NOT NULL, 
calories INT NOT NULL );

CREATE TABLE sleep (
id INT PRIMARY KEY NOT NULL,
hours VARCHAR(50) NOT NULL );

CREATE TABLE exercise (
id INT PRIMARY KEY NOT NULL,
name VARCHAR(50) NOT NULL, 
hours INT NOT NULL );

SELECT * FROM fitness_plus_db.dashboard;
SELECT * FROM fitness_plus_db.user;
SELECT * FROM fitness_plus_db.food;
SELECT * FROM fitness_plus_db.sleep;
SELECT * FROM fitness_plus_db.exercise;
