CREATE DATABASE IF NOT EXISTS jugueteria;
USE jugueteria;

-- 1. Empleado
CREATE TABLE Empleado (
	ID_Empleado INT AUTO_INCREMENT PRIMARY KEY,
	Nombre VARCHAR(100),
	Cargo VARCHAR(50),
	Fecha_ingreso DATE
);

-- 2. Juguete
CREATE TABLE Juguete (
	ID_Juguete INT AUTO_INCREMENT PRIMARY KEY,
	Nombre VARCHAR(100),
	Descripcion TEXT,
	Precio DECIMAL(10,2),
	Cantidad_en_stock INT,
	Categoria VARCHAR(50)
);

-- 3. Zona
CREATE TABLE Zona (
	ID_Zona INT AUTO_INCREMENT PRIMARY KEY,
	Nombre VARCHAR(100),
	Descripcion TEXT
);

-- 4. Stand
CREATE TABLE Stand (
	ID_Stand INT,
	ID_Zona INT,
	Nombre VARCHAR(100),
	Descripcion TEXT,
	PRIMARY KEY (ID_Stand, ID_Zona),
	FOREIGN KEY (ID_Zona) REFERENCES Zona(ID_Zona) ON DELETE CASCADE
);

-- 7. Stock
CREATE TABLE Stock (
	ID_Stand INT,
	ID_Zona INT,
	ID_Juguete INT,
	Cantidad_disponible INT,
	PRIMARY KEY (ID_Stand, ID_Zona, ID_Juguete),
	FOREIGN KEY (ID_Stand, ID_Zona) REFERENCES Stand(ID_Stand, ID_Zona) ON DELETE CASCADE,
	FOREIGN KEY (ID_Juguete) REFERENCES Juguete(ID_Juguete) ON DELETE CASCADE
);

-- 5. Venta
CREATE TABLE Venta (
	ID_Venta INT AUTO_INCREMENT PRIMARY KEY,
	ID_Empleado INT,
	ID_Juguete INT,
	ID_Stand INT,
	ID_Zona INT,
	Fecha DATE,
	Monto DECIMAL(10,2),
	Tipo_pago VARCHAR(50),
	FOREIGN KEY (ID_Empleado) REFERENCES Empleado(ID_Empleado),
	FOREIGN KEY (ID_Juguete) REFERENCES Juguete(ID_Juguete),
	FOREIGN KEY (ID_Stand, ID_Zona) REFERENCES Stand(ID_Stand, ID_Zona)
);

-- 6. Cambio
CREATE TABLE Cambio (
	ID_Cambio INT AUTO_INCREMENT PRIMARY KEY,
	ID_Empleado INT,
	ID_Juguete_Original INT,
	ID_Juguete_Nuevo INT,
	Motivo TEXT,
	Fecha DATE,
	Stand_origen INT,
	ID_zona_origen INT,
	Stand_destino INT,
	ID_zona_destino INT,
	FOREIGN KEY (ID_Empleado) REFERENCES Empleado(ID_Empleado),
	FOREIGN KEY (ID_Juguete_Original) REFERENCES Juguete(ID_Juguete),
	FOREIGN KEY (ID_Juguete_Nuevo) REFERENCES Juguete(ID_Juguete),
	FOREIGN KEY (Stand_origen, ID_zona_origen) REFERENCES Stand(ID_Stand, ID_Zona),
	FOREIGN KEY (Stand_destino, ID_zona_destino) REFERENCES Stand(ID_Stand, ID_Zona)
);
