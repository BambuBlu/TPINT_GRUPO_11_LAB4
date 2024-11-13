DELIMITER //

CREATE DEFINER=`root`@`localhost` PROCEDURE `BajaLogicaUsuario`(
    IN p_dni VARCHAR(10)
)
BEGIN
    UPDATE Usuarios
    SET estado = 'I'
    WHERE dni_cliente = p_dni;
END //

DELIMITER ;
