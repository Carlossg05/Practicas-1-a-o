CREATE DATABASE akihabara_db;

USE akihabara_db;

CREATE USER 'userAkihabara@%@%' IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON akihabara_db.producto TO 'userAkihabara@%@%';

CREATE TABLE producto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    categoria VARCHAR(100),
    precio DECIMAL(10, 2),
    stock INT
);


INSERT INTO producto (nombre, categoria, precio, stock) VALUES
('Figura de Acción - Saber', 'Figura', 125.50, 5),
('Manga - Naruto Vol. 1', 'Manga', 9.99, 20),
('Póster - Attack on Titan', 'Póster', 15.00, 10),
('Llavero - Pikachu', 'Llavero', 5.75, 30),
('Camiseta - One Piece', 'Ropa', 29.99, 15),
('Figura de Acción - Luffy', 'Figura', 130.00, 8),
('Manga - Bleach Vol. 5', 'Manga', 8.50, 25),
('Póster - Death Note', 'Póster', 12.00, 12),
('Llavero - Totoro', 'Llavero', 6.25, 22),
('Sudadera - Dragon Ball', 'Ropa', 45.00, 7),
('Figura de Acción - Asuka', 'Figura', 110.00, 3),
('Manga - One Punch Man Vol. 3', 'Manga', 10.50, 18),
('Póster - Fullmetal Alchemist', 'Póster', 14.50, 9),
('Llavero - Hello Kitty', 'Llavero', 4.99, 35),
('Gorra - Cowboy Bebop', 'Ropa', 27.50, 11);