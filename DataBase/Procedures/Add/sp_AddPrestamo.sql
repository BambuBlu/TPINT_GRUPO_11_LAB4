DELIMITER //

CREATE PROCEDURE ActivarPrestamo(
    IN p_numero_de_cuenta INT(11),
    IN p_id_prestamo INT(11),
    IN p_cbu INT(11),
    IN p_importe_pedido DECIMAL(10,2)
)
BEGIN
    -- Activa el préstamo
    UPDATE Prestamos 
    SET estado = 'A' 
    WHERE id = p_id_prestamo;

    -- Genera el movimiento
    INSERT INTO Movimientos (
        numero_de_cuenta, fecha, detalle, importe, id_tipo_movimiento, cuenta_origen, cuenta_destino
    )
    VALUES (
        p_numero_de_cuenta, NOW(), 'Alta de Prestamo', p_importe_pedido, 2, NULL, p_numero_de_cuenta
    );

    -- Actualiza el saldo en la cuenta
    UPDATE Cuentas 
    SET saldo = saldo + p_importe_pedido 
    WHERE numero_de_cuenta = p_numero_de_cuenta;

END //

DELIMITER ;