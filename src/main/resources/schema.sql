DROP TABLE IF EXISTS todo;
CREATE TABLE todo (id INT PRIMARY KEY, description VARCHAR(255), details VARCHAR(4096), done BIT);
CREATE TABLE Usuario (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255), 
    apellido VARCHAR(255),
    clave VARCHAR(255),
    usuario VARCHAR(255),
    rol INT,
    foto TEXT,
    created_at timestamp default now(),
    updated_at timestamp default now() on update now() 
 );

INSERT INTO Usuario (
    nombre,
    apellido,
    clave,
    usuario,
    rol,
    foto) VALUES (
        'admin',
        'apellido',
        'admin',
        'admin',
        0,
        'null'
    );

CREATE TABLE Concepto (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255), 
    tipo INT,
    precio VARCHAR(255),
    estado VARCHAR(255),
    descripcion TEXT,
    foto TEXT,
    created_at timestamp default now(),
    updated_at timestamp default now() on update now() 
 );

INSERT INTO Concepto (
    nombre,
    tipo,
    precio,
    estado,
    descripcion,
    foto) VALUES (
        'fosforos',
        0,
        '2000',
        0,
        'Caja de fosforos',
        'null'
    );

