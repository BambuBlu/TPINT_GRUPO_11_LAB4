DELIMITER //

CREATE DEFINER=`root`@`localhost` PROCEDURE `ModificarSaldoCuenta`(
    IN p_numero_de_cuenta VARCHAR(20),
    IN p_nuevo_saldo DECIMAL(10,2)
)
BEGIN
    UPDATE Cuentas
    SET saldo = p_nuevo_saldo
    WHERE numero_de_cuenta = p_numero_de_cuenta;
END //

DELIMITER ;