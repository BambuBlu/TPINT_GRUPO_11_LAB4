CREATE DEFINER=`root`@`localhost` PROCEDURE `CrearCliente`(
	IN p_dni VARCHAR(10),
    IN p_cuil VARCHAR(20),
    IN p_nombre VARCHAR(50),
    IN p_apellido VARCHAR(50),
    IN p_sexo INT,
    IN p_nacionalidad varchar(50),
    IN p_fecha_nacimiento DATE,
    IN p_direccion VARCHAR(100),
    IN p_localidad_id INT,
    IN p_mail VARCHAR(100),
    IN p_telefono VARCHAR(100),
	IN p_usuario varchar(50),
	IN p_contrasena varchar(100)
    
)
BEGIN
	
	DECLARE nuevo_cbu INT;
    DECLARE cant_cuentas INT;

    -- Obtener el número de cuentas
    SELECT COUNT(*) INTO cant_cuentas FROM cuentas;

    -- Generar nuevo CBU
    IF cant_cuentas = 0 THEN
        SET nuevo_cbu = 100000000;
    ELSE
        SELECT MAX(cbu) INTO nuevo_cbu FROM Cuentas;
        SET nuevo_cbu = nuevo_cbu + 1;
    END IF;

    -- Insertar nuevo cliente
    INSERT INTO Clientes (dni, cuil, nombre, apellido, sexo_id, nacionalidad, fecha_nacimiento, direccion, localidad_id, mail, telefono)
    VALUES (p_dni, p_cuil, p_nombre, p_apellido, p_sexo, p_nacionalidad , p_fecha_nacimiento , p_direccion, p_localidad_id, p_mail, p_telefono);

    -- Insertar nueva cuenta
    INSERT INTO Cuentas (dni_cliente, fecha_de_creacion, id_tipo_cuenta, cbu, saldo)
    VALUES (p_dni,CURRENT_DATE, 1, nuevo_cbu, 10000);
    
    INSERT INTO usuarios (dni_cliente, usuario, contraseña, tipo_usuario, estado) 
	VALUES (p_dni, p_usuario, p_contrasena, 'cliente', 'A');

END