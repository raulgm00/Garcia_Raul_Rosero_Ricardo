DROP TABLE IF EXISTS ODONTOLOGOS;
CREATE TABLE ODONTOLOGOS(
    id int AUTO_INCREMENT PRIMARY KEY,
    numeroMatricula INT NOT NULL,
    nombre VARCHAR(50),
    apellido VARCHAR(50)
);


INSERT INTO ODONTOLOGOS VALUES (DEFAULT, 1111,'Raul','Garcia');
INSERT INTO ODONTOLOGOS VALUES (DEFAULT, 2222,'Rosero','Ricardo');