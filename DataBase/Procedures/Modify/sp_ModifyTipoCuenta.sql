DELIMITER //

CREATE DEFINER=`root`@`localhost` PROCEDURE `ModificarTipoCuenta`(
    IN p_numero_de_cuenta VARCHAR(20),
    IN p_nuevo_id_tipo int(11)
)
BEGIN
    UPDATE Cuentas
    SET id_tipo_cuenta = p_nuevo_tipo
    WHERE numero_de_cuenta = p_numero_de_cuenta;
END //

DELIMITER ;