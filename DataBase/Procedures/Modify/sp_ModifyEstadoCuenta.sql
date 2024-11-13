DELIMITER //

CREATE DEFINER=`root`@`localhost` PROCEDURE `ModificarEstadoCuenta`(
	IN p_numero_de_cuenta VARCHAR(20)
)
BEGIN
UPDATE Cuentas
    SET estado = 'I'
    WHERE numero_de_cuenta = p_numero_de_cuenta;
END //

DELIMITER ;