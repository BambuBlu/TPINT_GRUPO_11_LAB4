DELIMITER //

CREATE DEFINER=`root`@`localhost` PROCEDURE `ModificarEstadoCuenta`(
    IN p_numero_de_cuenta VARCHAR(20),
    IN p_estado VARCHAR(1)
)
BEGIN
    UPDATE Cuentas
    SET estado = p_estado
    WHERE numero_de_cuenta = p_numero_de_cuenta;
END //

DELIMITER ;