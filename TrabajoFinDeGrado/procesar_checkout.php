<?php
include('config/conexion.php');
session_start();

if (!isset($_SESSION['empleado_id'])) {
	header("Location: index.php");
	exit();
}

if (isset($_GET['hab'])) {
	$hab = mysqli_real_escape_string($conexion, $_GET['hab']);

	$sql_datos = "SELECT r.id_reserva, r.fecha_checkin, r.fecha_checkout, h.precio_noche 
				  FROM Reservas r
				  JOIN reserva_habitacion rh ON r.id_reserva = rh.id_reserva
				  JOIN habitaciones h ON rh.numero_habitacion = h.numero_habitacion
				  WHERE rh.numero_habitacion = '$hab' AND r.estado_reserva = 'ACTIVA'
				  LIMIT 1";
	
	$resultado = mysqli_query($conexion, $sql_datos);
	
	if ($fila = mysqli_fetch_assoc($resultado)) {
		$id_reserva = $fila['id_reserva'];
		$fecha_in = new DateTime($fila['fecha_checkin']);
		$fecha_out = new DateTime(date('Y-m-d')); 
		$precio_noche = $fila['precio_noche'];

		$intervalo = $fecha_in->diff($fecha_out);
		$noches = $intervalo->days;
		if($noches <= 0) $noches = 1;

		$total_pagar = $noches * $precio_noche;

		$sql_factura = "INSERT INTO facturas (total_pagado, metodo_pago_token, id_reserva, fecha_emision) 
						VALUES ($total_pagar, 'EFECTIVO', $id_reserva, NOW())";
		
		if (mysqli_query($conexion, $sql_factura)) {
			$nueva_factura_id = mysqli_insert_id($conexion);

			mysqli_query($conexion, "UPDATE Reservas SET estado_reserva = 'FINALIZADA' WHERE id_reserva = $id_reserva");

			$sql_update_hab = "UPDATE habitaciones SET ocupada_bool = 0, estado_limpieza = 'SUCIA', estado_disponibilidad = 'DISPONIBLE' WHERE numero_habitacion = '$hab'";
			
			if (mysqli_query($conexion, $sql_update_hab)) {
				header("Location: vistas/facturas.php?msg=checkout_ok&ver_factura=$nueva_factura_id");
				exit();
			} else {
				echo "Error al liberar habitación: " . mysqli_error($conexion);
			}
		} else {
			echo "Error al generar factura: " . mysqli_error($conexion);
		}
	} else {
		header("Location: vistas/habitaciones.php?error=no_activa");
	}
}
?>
