DELIMITER //

CREATE DEFINER=`root`@`localhost` PROCEDURE `ModificarCliente`(
    IN p_dni VARCHAR(10),
    IN p_cuil VARCHAR(20),
    IN p_nombre VARCHAR(50),
    IN p_apellido VARCHAR(50),
    IN p_sexo INT,
    IN p_nacionalidad VARCHAR(50),
    IN p_fecha_nacimiento DATE,
    IN p_direccion VARCHAR(100),
    IN p_localidad INT,
    IN p_mail VARCHAR(100),
    IN p_telefono VARCHAR(20),
    IN p_contrasena VARCHAR(20)
)
BEGIN
    UPDATE Clientes
    SET cuil = p_cuil,
        nombre = p_nombre,
        apellido = p_apellido,
        sexo_id = p_sexo,
        nacionalidad = p_nacionalidad,
        fecha_nacimiento = p_fecha_nacimiento,
        direccion = p_direccion,
        localidad_id = p_localidad,
        mail = p_mail,
        telefono = p_telefono
    WHERE dni = p_dni;
    
    UPDATE Usuarios
    SET `contraseña` = p_contrasena
    WHERE dni_cliente = p_dni;
END //

DELIMITER ;