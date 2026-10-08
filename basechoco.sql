SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE producto;
TRUNCATE TABLE categoria;
TRUNCATE TABLE usuario;
SET FOREIGN_KEY_CHECKS = 1;

-- ============================================
-- CATEGORÍAS (Las 5 del frontend)
-- ============================================
INSERT INTO categoria (nombre) VALUES ("Frutos Secos");
INSERT INTO categoria (nombre) VALUES ("Semillas");
INSERT INTO categoria (nombre) VALUES ("Frutas Deshidratadas");
INSERT INTO categoria (nombre) VALUES ("Chocolates");
INSERT INTO categoria (nombre) VALUES ("Mezclas");

SELECT * FROM categoria;

-- ============================================

INSERT INTO usuario (id, username, email, password, rol, enabled, created_at, updated_at)
VALUES (1, "admin", "admin@chocofruta.cl", "$2a$12$/vhzyanAe.DAmAqmUuWnve6P/O3k7dYHgKzb1.r1zhRC5g5RSKl/C", "ADMIN", 1, NOW(), NOW());

INSERT INTO usuario (id, username, email, password, rol, enabled, created_at, updated_at)
VALUES (3, "juan", "juan@gmail.com", "$2a$12$hDgqSGc/EQac9y.TdA5eZeYL8a36fb.vgQN/TItjpOxTE/r94PICS", "CLIENTE", 1, NOW(), NOW());

INSERT INTO usuario (id, username, email, password, rol, enabled, created_at, updated_at)
VALUES (4, "maria", "maria@gmail.com", "$2a$12$hDgqSGc/EQac9y.TdA5eZeYL8a36fb.vgQN/TItjpOxTE/r94PICS", "CLIENTE", 1, NOW(), NOW());

SELECT * FROM usuario;

-- ============================================
-- PRODUCTOS (20 productos distribuidos)
-- Map de categorías:
-- 1 -> Frutos Secos
-- 2 -> Semillas
-- 3 -> Frutas Deshidratadas
-- 4 -> Chocolates
-- 5 -> Mezclas
-- ============================================

-- Frutos Secos
INSERT INTO producto (activo, descripcion, nombre, precio, stock, imagen, fecha_creacion, categoria_id) 
VALUES (true, "Almendras naturales seleccionadas de primera calidad", "Almendras Premium", 3500, 25, "Almen.jpg", NOW(), 1);

INSERT INTO producto (activo, descripcion, nombre, precio, stock, imagen, fecha_creacion, categoria_id) 
VALUES (true, "Nueces frescas y crujientes - 400g", "Nueces de Nogal", 4200, 3, "Nueces.jpg", NOW(), 1);

INSERT INTO producto (activo, descripcion, nombre, precio, stock, imagen, fecha_creacion, categoria_id) 
VALUES (true, "Pistachos con sal de mar - 300g", "Pistachos Tostados", 5500, 8, "Pistach.jpg", NOW(), 1);

INSERT INTO producto (activo, descripcion, nombre, precio, stock, imagen, fecha_creacion, categoria_id) 
VALUES (true, "Castañas de cajú sin sal - 250g", "Castañas de Cajú", 4800, 15, "kahu.jpg", NOW(), 1);

-- Semillas 
INSERT INTO producto (activo, descripcion, nombre, precio, stock, imagen, fecha_creacion, categoria_id) 
VALUES (true, "Chía orgánica de alta calidad - 500g", "Semillas de Chía", 2800, 30, "chii.jpg", NOW(), 2);

INSERT INTO producto (activo, descripcion, nombre, precio, stock, imagen, fecha_creacion, categoria_id) 
VALUES (true, "Semillas de calabaza tostadas - 300g", "Semillas de Calabaza", 3200, 20, "Calab.png", NOW(), 2);

INSERT INTO producto (activo, descripcion, nombre, precio, stock, imagen, fecha_creacion, categoria_id) 
VALUES (true, "Almendras Francesas - 500g", "Almendras Francesas", 3200, 20, "AlmendFran.jpg", NOW(), 2);

INSERT INTO producto (activo, descripcion, nombre, precio, stock, imagen, fecha_creacion, categoria_id) 
VALUES (true, "Maravilla sin cáscara - 250g", "Semillas de Maravilla", 1800, 4, "Marav.png", NOW(), 2);

INSERT INTO producto (activo, descripcion, nombre, precio, stock, imagen, fecha_creacion, categoria_id) 
VALUES (true, "Linaza molida orgánica - 400g", "Semillas de Linaza", 2500, 18, "Lina.jpg", NOW(), 2);

-- Frutas Deshidratadas 
INSERT INTO producto (activo, descripcion, nombre, precio, stock, imagen, fecha_creacion, categoria_id) 
VALUES (true, "Rodajas de plátano naturalmente dulces - 200g", "Plátano Deshidratado", 3200, 12, "platano.jpg", NOW(), 3);

INSERT INTO producto (activo, descripcion, nombre, precio, stock, imagen, fecha_creacion, categoria_id) 
VALUES (true, "Pasas sin semillas - 250g", "Pasas Rubias", 2200, 25, "Pas.png", NOW(), 3);

INSERT INTO producto (activo, descripcion, nombre, precio, stock, imagen, fecha_creacion, categoria_id) 
VALUES (true, "Trozos de mango natural - 180g", "Mango Deshidratado", 3800, 3, "Mango.jpg", NOW(), 3);

INSERT INTO producto (activo, descripcion, nombre, precio, stock, imagen, fecha_creacion, categoria_id) 
VALUES (true, "Arándanos deshidratados - 150g", "Arándanos Secos", 4500, 10, "Arand.jpg", NOW(), 3);

-- Chocolates
INSERT INTO producto (activo, descripcion, nombre, precio, stock, imagen, fecha_creacion, categoria_id) 
VALUES (true, "Chocolate amargo premium - 100g", "Chocolate 70% Cacao", 2500, 30, "Choco.jpg", NOW(), 4);

INSERT INTO producto (activo, descripcion, nombre, precio, stock, imagen, fecha_creacion, categoria_id) 
VALUES (true, "Chocolate suave y cremoso - 100g", "Chocolate con Leche", 2000, 35, "Chocolate.webp", NOW(), 4);

INSERT INTO producto (activo, descripcion, nombre, precio, stock, imagen, fecha_creacion, categoria_id) 
VALUES (true, "Chocolate con leche y almendras - 120g", "Chocolate con Almendras", 2800, 4, "Chocoalmendras.jpg", NOW(), 4);

INSERT INTO producto (activo, descripcion, nombre, precio, stock, imagen, fecha_creacion, categoria_id) 
VALUES (true, "Caja de 12 bombones artesanales", "Bombones Surtidos", 4500, 8, "Bom.jpg", NOW(), 4);

-- Mezclas 
INSERT INTO producto (activo, descripcion, nombre, precio, stock, imagen, fecha_creacion, categoria_id) 
VALUES (true, "Mezcla de frutas tropicales - 300g", "Mix Tropical", 3600, 20, "MixFru.png", NOW(), 5);

INSERT INTO producto (activo, descripcion, nombre, precio, stock, imagen, fecha_creacion, categoria_id) 
VALUES (true, "Frutos secos y semillas - 350g", "Mix Energético", 4200, 2, "MixEN.jpg", NOW(), 5);

INSERT INTO producto (activo, descripcion, nombre, precio, stock, imagen, fecha_creacion, categoria_id) 
VALUES (true, "Combinación alta en proteínas - 400g", "Mix Proteico", 4800, 15, "MixTro.png", NOW(), 5);

SELECT * FROM producto;
