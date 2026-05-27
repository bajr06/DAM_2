-- ========================================================
-- INSERCIÓN DE DATOS INICIALES PARA LA JUGUETERÍA
-- ========================================================

-- 1. Insertar Zonas de la tienda
INSERT INTO Zona (ID_Zona, Nombre, Descripcion) VALUES (1, 'Zona Infantil', 'Área dedicada a peluches, sonajeros y juguetes para bebés.');
INSERT INTO Zona (ID_Zona, Nombre, Descripcion) VALUES (2, 'Zona de Estrategia', 'Área de juegos de mesa, cartas y rompecabezas para jóvenes y adultos.');
INSERT INTO Zona (ID_Zona, Nombre, Descripcion) VALUES (3, 'Zona de Acción', 'Área de figuras articuladas, superhéroes y vehículos teledirigidos.');

-- 2. Insertar Stands en las respectivas Zonas
INSERT INTO Stand (ID_Stand, ID_Zona, Nombre, Descripcion) VALUES (1, 1, 'Stand Peluches Gigantes', 'Expositor principal de osos y animales de felpa.');
INSERT INTO Stand (ID_Stand, ID_Zona, Nombre, Descripcion) VALUES (2, 1, 'Stand Primeros Pasos', 'Estantería con andadores y bloques blandos.');
INSERT INTO Stand (ID_Stand, ID_Zona, Nombre, Descripcion) VALUES (3, 2, 'Stand Eurogames', 'Vitrina con juegos de mesa modernos y de estrategia.');
INSERT INTO Stand (ID_Stand, ID_Zona, Nombre, Descripcion) VALUES (4, 3, 'Stand Bloques de Construcción', 'Pasillo dedicado a Legos y sets de montaje.');

-- 3. Insertar Juguetes (Inventario Global)
INSERT INTO Juguete (ID_Juguete, Nombre, Descripcion, Precio, Cantidad_en_stock, Categoria) VALUES (1, 'Oso de Peluche XL', 'Oso de felpa suave de 1 metro de altura', 29.99, 45, 'Peluches');
INSERT INTO Juguete (ID_Juguete, Nombre, Descripcion, Precio, Cantidad_en_stock, Categoria) VALUES (2, 'Catan (Juego Base)', 'Juego de mesa de estrategia y negociación', 39.95, 30, 'Juegos de Mesa');
INSERT INTO Juguete (ID_Juguete, Nombre, Descripcion, Precio, Cantidad_en_stock, Categoria) VALUES (3, 'Castillo Medieval de Bloques', 'Set de construcción con 500 piezas y figuras', 49.99, 20, 'Construcción');
INSERT INTO Juguete (ID_Juguete, Nombre, Descripcion, Precio, Cantidad_en_stock, Categoria) VALUES (4, 'Coche Teledirigido 4x4', 'Vehículo radiocontrol con batería recargable', 24.50, 15, 'Acción');
INSERT INTO Juguete (ID_Juguete, Nombre, Descripcion, Precio, Cantidad_en_stock, Categoria) VALUES (5, 'Rompecabezas 1000 piezas', 'Paisaje de montaña desafiante', 14.90, 25, 'Juegos de Mesa');

-- 4. Insertar Empleados
INSERT INTO Empleado (ID_Empleado, Nombre, Cargo, Fecha_ingreso) VALUES (1, 'Carlos Gómez', 'Vendedor Principal', '2025-01-15');
INSERT INTO Empleado (ID_Empleado, Nombre, Cargo, Fecha_ingreso) VALUES (2, 'Ana López', 'Supervisora de Tienda', '2024-06-10');
INSERT INTO Empleado (ID_Empleado, Nombre, Cargo, Fecha_ingreso) VALUES (3, 'Luis Martínez', 'Asistente de Almacén', '2025-03-01');

-- 5. Asignar Stock específico a cada Stand
-- Nota: La suma de estas cantidades debería guardar coherencia con el stock global anterior
INSERT INTO Stock (ID_Stand, ID_Zona, ID_Juguete, Cantidad_disponible) VALUES (1, 1, 1, 15);
INSERT INTO Stock (ID_Stand, ID_Zona, ID_Juguete, Cantidad_disponible) VALUES (2, 1, 1, 5);
INSERT INTO Stock (ID_Stand, ID_Zona, ID_Juguete, Cantidad_disponible) VALUES (3, 2, 2, 12);
INSERT INTO Stock (ID_Stand, ID_Zona, ID_Juguete, Cantidad_disponible) VALUES (3, 2, 5, 10);
INSERT INTO Stock (ID_Stand, ID_Zona, ID_Juguete, Cantidad_disponible) VALUES (4, 3, 3, 8);
INSERT INTO Stock (ID_Stand, ID_Zona, ID_Juguete, Cantidad_disponible) VALUES (4, 3, 4, 7);
