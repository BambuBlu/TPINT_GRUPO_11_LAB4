DELIMITER //

CREATE DEFINER=`root`@`localhost` PROCEDURE `ModificarEstadoCliente`(
    IN p_dni VARCHAR(10),
    IN p_estado varchar(1)
)
BEGIN
    UPDATE clientes
    SET estado = p_estado
    WHERE dni = p_dni;
END //

DELIMITER ;