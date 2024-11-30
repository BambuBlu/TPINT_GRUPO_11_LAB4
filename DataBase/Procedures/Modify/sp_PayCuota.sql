DELIMITER //

CREATE PROCEDURE pagarCuota(
    IN p_id_prestamo INT,
    IN p_numero_de_cuenta INT,
    IN p_cbu INT,
    IN p_importe DECIMAL(10,2)
)
BEGIN
    DECLARE v_cuotas_a_pagar INT;
    DECLARE v_fecha_actual DATE;

    SET v_fecha_actual = NOW();

    SELECT cuotas_a_pagar
    INTO v_cuotas_a_pagar
    FROM Prestamos
    WHERE id = p_id_prestamo;

    SET v_cuotas_a_pagar = v_cuotas_a_pagar - 1;

    UPDATE Prestamos 
    SET 
        cuotas_a_pagar = v_cuotas_a_pagar
    WHERE
        id = p_id_prestamo;

    IF v_cuotas_a_pagar <= 0 THEN
        UPDATE Prestamos
        SET estado = 'I',
            prestamo_saldado = 'S'
        WHERE id = p_id_prestamo;
    END IF;

    INSERT INTO Movimientos (
        numero_de_cuenta, fecha, detalle, importe,
        id_tipo_movimiento, cuenta_origen, cuenta_destino
    )
    VALUES (
        p_numero_de_cuenta, v_fecha_actual, 'Pago de cuota', p_importe,
        3, p_numero_de_cuenta, NULL
    );

    UPDATE Cuentas 
    SET 
        saldo = saldo - p_importe
    WHERE
        cbu = p_cbu;

END //

DELIMITER ;
