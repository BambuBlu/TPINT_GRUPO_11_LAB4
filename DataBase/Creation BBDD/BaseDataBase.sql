-- drop database dbbanco
CREATE DATABASE dbbanco;
USE dbbanco;

-- Tabla Paises
CREATE TABLE Paises (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL
);

-- Tabla Provincias
CREATE TABLE Provincias (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    id_pais INT NOT NULL,
    FOREIGN KEY (id_pais) REFERENCES Paises(id)
);

-- Tabla Localidades
CREATE TABLE Localidades (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    id_provincia INT NOT NULL,
    FOREIGN KEY (id_provincia) REFERENCES Provincias(id)
);

-- Tabla TipoDeCuenta
CREATE TABLE TipoDeCuenta (
    id INT PRIMARY KEY AUTO_INCREMENT,
    descripcion VARCHAR(50) NOT NULL
);

-- Tabla TipoDeMovimiento
CREATE TABLE TipoDeMovimiento (
    id INT PRIMARY KEY AUTO_INCREMENT,
    descripcion VARCHAR(50) NOT NULL
);

CREATE TABLE Generos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    descripcion VARCHAR(50) NOT NULL
);

-- Tabla Clientes
CREATE TABLE Clientes (
    dni VARCHAR(10) NOT NULL UNIQUE PRIMARY KEY,
    cuil VARCHAR(20) NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    sexo_id INT NOT NULL,
    nacionalidad varchar(50) not null,
    fecha_nacimiento DATE NOT NULL,
    direccion VARCHAR(100) NOT NULL,
    localidad_id INT,
    mail VARCHAR(100),
    telefono VARCHAR(20),
    estado varchar(1) not null default 'A',
    FOREIGN KEY (localidad_id) REFERENCES Localidades(id),
    FOREIGN KEY (sexo_id) REFERENCES Generos(id)
);

-- Tabla Usuarios
CREATE TABLE Usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dni_cliente VARCHAR(10) UNIQUE NOT NULL,
    usuario VARCHAR(50) NOT NULL,
    contraseña VARCHAR(100) NOT NULL,
    tipo_usuario ENUM('Cliente', 'Admin') NOT NULL DEFAULT 'Cliente',
    estado VARCHAR(1) NOT NULL DEFAULT 'A',
    FOREIGN KEY (dni_cliente) REFERENCES Clientes(dni)
);

-- Tabla Cuentas
CREATE TABLE Cuentas (
    numero_de_cuenta int PRIMARY KEY auto_increment,
    dni_cliente VARCHAR(10) NOT NULL,
    fecha_de_creacion DATE NOT NULL,
    id_tipo_cuenta INT,
    cbu int NOT NULL unique,
    saldo DECIMAL(10,2) NOT NULL,
    estado VARCHAR(1) NOT NULL DEFAULT 'A',
    FOREIGN KEY (dni_cliente) REFERENCES Clientes(dni),
    FOREIGN KEY (id_tipo_cuenta) REFERENCES TipoDeCuenta(id)
);

-- Tabla solicitudes
CREATE TABLE solicitudDeAlta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dniCliente VARCHAR(10),
    tipoDeCuenta int,
    estado VARCHAR(1) NOT NULL DEFAULT 'A',
    FOREIGN KEY (dniCliente) REFERENCES Clientes(dni)
);

-- Tabla Movimientos
CREATE TABLE Movimientos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero_de_cuenta int NOT NULL,
    fecha DATE NOT NULL,
    detalle VARCHAR(255) NOT NULL,
    importe DECIMAL(10,2) NOT NULL,
    id_tipo_movimiento INT,
    cuenta_origen int,
    cuenta_destino int,
    FOREIGN KEY (numero_de_cuenta) REFERENCES Cuentas(numero_de_cuenta),
    FOREIGN KEY (id_tipo_movimiento) REFERENCES TipoDeMovimiento(id)
);

-- Tabla Prestamos
CREATE TABLE Prestamos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dni_cliente VARCHAR(10) NOT NULL,
    cbu int not null,
    fecha DATE NOT NULL,
    importe_con_intereses DECIMAL(10,2) NOT NULL,
    importe_pedido DECIMAL(10,2) NOT NULL,
    plazo_de_pago_en_meses INT NOT NULL,
    monto_mensual DECIMAL(10,2) NOT NULL,
    cuotas_a_pagar INT NOT NULL,
    estado VARCHAR(1) NOT NULL DEFAULT 'A',
    prestamo_saldado VARCHAR(1) NOT NULL,
    FOREIGN KEY (dni_cliente) REFERENCES Clientes(dni),
    FOREIGN KEY (cbu) REFERENCES Cuentas(cbu)
);

-- Tabla Cuotas
CREATE TABLE Cuotas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_prestamo INT NOT NULL,
    numero_cuota INT NOT NULL,
    fecha_pago DATE NOT NULL,
    monto DECIMAL(10,2) NOT NULL,
    estado VARCHAR(1) NOT NULL DEFAULT 'P', -- 'P' para pendiente, 'A' para abonada
    FOREIGN KEY (id_prestamo) REFERENCES Prestamos(id)
);

-- Insertando datos en la tabla Paises
INSERT INTO Paises (id,nombre) VALUES (1,'Argentina');

-- Insertando datos en la tabla Provincias
INSERT INTO provincias (id, nombre, id_pais) VALUES
(1, 'Buenos Aires', 1),
(2, 'Córdoba', 1),
(3, 'Santa Fe', 1),
(4, 'Mendoza', 1),
(5, 'Tucumán', 1),
(6, 'Salta', 1),
(7, 'Entre Ríos', 1),
(8, 'Misiones', 1),
(9, 'Chaco', 1),
(10, 'Corrientes', 1),
(11, 'Santiago del Estero', 1),
(12, 'San Juan', 1),
(13, 'Jujuy', 1),
(14, 'Río Negro', 1),
(15, 'Formosa', 1),
(16, 'Neuquén', 1),
(17, 'Chubut', 1),
(18, 'San Luis', 1),
(19, 'Catamarca', 1),
(20, 'La Rioja', 1),
(21, 'La Pampa', 1),
(23, 'Tierra del Fuego', 1);

-- Insertando datos en la tabla Localidades
-- Buenos Aires
INSERT INTO Localidades (nombre, id_provincia) VALUES
('Capital Federal', 1),
('Mar del Plata', 1),
('La Plata', 1),
('Córdoba Capital', 2),
('Villa María', 2),
('Río Cuarto', 2),
('Rosario', 3),
('Santa Fe', 3),
('Venado Tuerto', 3),
('Mendoza Capital', 4),
('San Rafael', 4),
('Godoy Cruz', 4),
('San Miguel de Tucumán', 5),
('Yerba Buena', 5),
('Tafí Viejo', 5),
('Salta Capital', 6),
('Jujuy', 6),
('Orán', 6),
('Paraná', 7),
('Concordia', 7),
('Gualeguaychú', 7),
('Posadas', 8),
('Puerto Iguazú', 8),
('Eldorado', 8),
('Resistencia', 9),
('Barranqueras', 9),
('Villa Ángela', 9),
('Corrientes Capital', 10),
('Goya', 10),
('Mercedes', 10),
('Santiago del Estero Capital', 11),
('Termas de Río Hondo', 11),
('La Banda', 11),
('San Juan Capital', 12),
('Rawson', 12),
('Pocito', 12),
('San Salvador de Jujuy', 13),
('Palpalá', 13),
('Libertador General San Martín', 13),
('Viedma', 14),
('General Roca', 14),
('San Carlos de Bariloche', 14),
('Formosa Capital', 15),
('Clorinda', 15),
('Pirané', 15),
('Neuquén Capital', 16),
('Cutral Có', 16),
('Plottier', 16),
('Comodoro Rivadavia', 17),
('Puerto Madryn', 17),
('Trelew', 17),
('San Luis Capital', 18),
('Villa Mercedes', 18),
('Merlo', 18),
('San Fernando del Valle de Catamarca', 19),
('San José', 19),
('Valle Viejo', 19),
('La Rioja Capital', 20),
('Chilecito', 20),
('Aimogasta', 20),
('Santa Rosa', 21),
('General Pico', 21),
('Toay', 21),
('Ushuaia', 23),
('Río Grande', 23),
('Tolhuin', 23);

-- Insertando datos en la tabla TipoDeCuenta
INSERT INTO TipoDeCuenta (descripcion) VALUES ('Caja de ahorro'), ('Cuenta corriente');

-- Insertando datos en la tabla TipoDeMovimiento
INSERT INTO TipoDeMovimiento (descripcion) VALUES
('Alta de cuenta'),
('Alta de préstamo'),
('Pago de préstamo'),
('Transferencia');

-- Inserción de datos en la tabla Genero
INSERT INTO Generos (descripcion) VALUES
('Masculino'),
('Femenino'),
('No Binario'),
('Prefiero no decirlo'),
('Otro');

  -- Insertar un admin, cuenta y usuario de prueba
INSERT INTO Clientes (dni, cuil, nombre, apellido, sexo_id, nacionalidad, fecha_nacimiento, direccion, localidad_id, mail, telefono)
VALUES (1, 1001, 'Admin', 'Admin', 1, 'Argentino', '1980-01-01', 'Casa', 1, 'mail@mail.com', '1168740444');
INSERT INTO usuarios (dni_cliente, usuario, contraseña, tipo_usuario, estado) 
VALUES (1, 'admin', 123, 'Admin', 'A');