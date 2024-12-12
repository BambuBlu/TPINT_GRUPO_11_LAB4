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

INSERT INTO clientes (dni, cuil, nombre, apellido, sexo_id, nacionalidad, fecha_nacimiento, direccion, localidad_id, mail, telefono, estado)
VALUES
    ('14012536', '20140125363', 'Pedro', 'Ramírez', 1, 'Argentina', '1990-01-15', 'Calle Principal 45', 1, 'pedro.ramirez@example.com', '1123456789', 'A'),
    ('21543689', '27215436895', 'Laura', 'Sosa', 2, 'Argentina', '1982-07-25', 'Av. San Martín 567', 2, 'laura.sosa@example.com', '2219876543', 'A'),
    ('39001234', '23390012340', 'Carlos', 'Fernández', 1, 'Argentina', '1992-03-10', 'San Martín 789', 3, 'carlos.fernandez@example.com', '3214567890', 'I'),
    ('17654321', '25176543218', 'Ana', 'Martínez', 2, 'Argentina', '1988-07-25', 'Belgrano 123', 4, 'ana.martinez@example.com', '2236547891', 'A'),
    ('24012345', '20240123453', 'Luis', 'Gómez', 1, 'Argentina', '1985-05-20', 'Calle Falsa 234', 5, 'luis.gomez@example.com', '3345678912', 'A'),
    ('36547812', '27365478125', 'Sofía', 'Pérez', 2, 'Argentina', '1995-10-30', 'Avenida Siempreviva 234', 6, 'sofia.perez@example.com', '4456789123', 'A'),
    ('15478932', '23154789320', 'Juan', 'García', 1, 'Argentina', '1987-11-15', 'Calle Luna 789', 7, 'juan.garcia@example.com', '5567891234', 'I'),
    ('29451278', '25294512788', 'María', 'Ruiz', 2, 'Argentina', '1990-12-25', 'Calle Sol 123', 8, 'maria.ruiz@example.com', '6678912345', 'A'),
    ('18765423', '20187654233', 'Ricardo', 'Alonso', 1, 'Argentina', '1992-01-10', 'Calle Norte 234', 9, 'ricardo.alonso@example.com', '7789123456', 'A'),
    ('31246789', '27312467895', 'Lucía', 'Sánchez', 2, 'Argentina', '1991-04-18', 'Avenida Sur 456', 10, 'lucia.sanchez@example.com', '8891234567', 'I'),
    ('17564823', '23175648230', 'Diego', 'Herrera', 1, 'Argentina', '1989-03-12', 'Calle Este 678', 11, 'diego.herrera@example.com', '9912345678', 'A'),
    ('32015478', '25320154788', 'Camila', 'Torres', 2, 'Argentina', '1986-06-22', 'Calle Oeste 789', 12, 'camila.torres@example.com', '1012345678', 'A'),
    ('14987654', '20149876543', 'Fernando', 'Morales', 1, 'Argentina', '1994-02-17', 'Av. Principal 890', 13, 'fernando.morales@example.com', '1123456789', 'I'),
    ('12345678', '20123456783', 'Admin', 'Admin', 1, 'Argentina', '1980-01-01', 'Sede Central', 1, 'admin@example.com', '1111111111', 'A'),
    ('23456789', '20234567893', 'Admin1', 'Admin1', 1, 'Argentina', '1981-01-01', 'Sede Central', 1, 'admin1@example.com', '2222222222', 'A');

INSERT INTO usuarios (dni_cliente, usuario, contraseña, tipo_usuario, estado)
VALUES 
    (14012536, 'pedro.ramirez', '1234', 'Cliente', 'A'),
    (21543689, 'laura.sosa', '1234', 'Cliente', 'A'),
    (39001234, 'carlos.fernandez', '1234', 'Cliente', 'I'),
    (17654321, 'ana.martinez', '1234', 'Cliente', 'A'),
    (24012345, 'luis.gomez', '1234', 'Cliente', 'A'),
    (36547812, 'sofia.perez', '1234', 'Cliente', 'A'),
    (15478932, 'juan.garcia', '1234', 'Cliente', 'I'),
    (29451278, 'maria.ruiz', '1234', 'Cliente', 'A'),
    (18765423, 'ricardo.alonso', '1234', 'Cliente', 'A'),
    (31246789, 'lucia.sanchez', '1234', 'Cliente', 'I'),
    (17564823, 'diego.herrera', '1234', 'Cliente', 'A'),
    (32015478, 'camila.torres', '1234', 'Cliente', 'A'),
    (14987654, 'fernando.morales', '1234', 'Cliente', 'I'),
    (12345678, 'admin', '1234', 'Admin', 'A'),
    (23456789, 'admin1', '1234', 'Admin', 'A');


INSERT INTO cuentas (numero_de_cuenta, dni_cliente, fecha_de_creacion, id_tipo_cuenta, cbu, saldo, estado)
VALUES 
    (10000001, 14012536, '2024-01-01', 1, '100000000', 10000.00, 'A'),
    (10000002, 21543689, '2024-01-02', 1, '100000001', 10000.00, 'A'),
    (10000003, 39001234, '2024-01-03', 1, '100000002', 10000.00, 'I'),
    (10000004, 17654321, '2024-01-04', 1, '100000003', 10000.00, 'A'),
    (10000005, 24012345, '2024-01-05', 1, '100000004', 10000.00, 'A'),
    (10000006, 36547812, '2024-01-06', 1, '100000005', 10000.00, 'A'),
    (10000007, 15478932, '2024-01-07', 1, '100000006', 10000.00, 'I'),
    (10000008, 29451278, '2024-01-08', 1, '100000007', 10000.00, 'A'),
    (10000009, 18765423, '2024-01-09', 1, '100000008', 10000.00, 'A'),
    (10000010, 31246789, '2024-01-10', 1, '100000009', 10000.00, 'I'),
    (10000011, 17564823, '2024-01-11', 1, '100000010', 10000.00, 'A'),
    (10000012, 32015478, '2024-01-12', 1, '100000011', 10000.00, 'A'),
    (10000013, 14987654, '2024-01-13', 1, '100000012', 10000.00, 'I'),
    (10000014, 12345678, '2024-01-14', 1, '100000013', 10000.00, 'A'),
    (10000015, 23456789, '2024-01-15', 1, '100000014', 10000.00, 'A');


INSERT INTO movimientos (numero_de_cuenta, fecha, detalle, importe, id_tipo_movimiento, cuenta_origen, cuenta_destino)
VALUES
    ('1000001', '2024-12-10', 'Alta de cuenta', 10000.00, 1, 0, '1000001'),
    ('1000002', '2024-12-10', 'Alta de cuenta', 10000.00, 1, 0, '1000002'),
    ('1000003', '2024-12-10', 'Alta de cuenta', 10000.00, 1, 0, '1000003'),
    ('1000004', '2024-12-10', 'Alta de cuenta', 10000.00, 1, 0, '1000004'),
    ('1000005', '2024-12-10', 'Alta de cuenta', 10000.00, 1, 0, '1000005'),
    ('1000006', '2024-12-10', 'Alta de cuenta', 10000.00, 1, 0, '1000006'),
    ('1000007', '2024-12-10', 'Alta de cuenta', 10000.00, 1, 0, '1000007'),
    ('1000008', '2024-12-10', 'Alta de cuenta', 10000.00, 1, 0, '1000008'),
    ('1000009', '2024-12-10', 'Alta de cuenta', 10000.00, 1, 0, '1000009'),
    ('10000010', '2024-12-10', 'Alta de cuenta', 10000.00, 1, 0, '10000010'),
    ('10000011', '2024-12-10', 'Alta de cuenta', 10000.00, 1, 0, '10000011'),
    ('10000012', '2024-12-10', 'Alta de cuenta', 10000.00, 1, 0, '10000012'),
    ('10000013', '2024-12-10', 'Alta de cuenta', 10000.00, 1, 0, '10000013'),
    ('10000014', '2024-12-10', 'Alta de cuenta', 10000.00, 1, 0, '10000014'),
    ('10000015', '2024-12-10', 'Alta de cuenta', 10000.00, 1, 0, '10000015'),
    ('10000016', '2024-12-10', 'Alta de cuenta', 10000.00, 1, 0, '10000016');