DROP DATABASE IF EXISTS shop;

CREATE DATABASE shop;
USE shop;

CREATE TABLE employee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    employeeId INT NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL
);

INSERT INTO employee (employeeId, name, password) VALUES 
(123, 'Santi', 'test1'),
(456, 'Jose', 'test2'),
(789, 'Vicente', 'test3');
