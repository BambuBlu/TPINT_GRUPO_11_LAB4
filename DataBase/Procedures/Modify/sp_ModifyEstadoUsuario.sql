DELIMITER //

CREATE DEFINER=`root`@`localhost` PROCEDURE `ModificarEstadoUsuario`(
    IN p_dni VARCHAR(10),
    IN p_estado varchar(1)
)
BEGIN
    UPDATE Usuarios
    SET estado = p_estado
    WHERE dni = p_dni;
END //

DELIMITER ;