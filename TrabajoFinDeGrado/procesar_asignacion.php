<?php
include('config/conexion.php');
session_start();

if (isset($_GET['reserva_id']) && isset($_GET['hab'])) {
	$reserva_id = $_GET['reserva_id'];
	$hab = $_GET['hab'];

	$sql_vinculo = "INSERT INTO Reserva_habitacion (id_reserva, numero_habitacion) VALUES ($reserva_id, $hab)";
	
	if (mysqli_query($conexion, $sql_vinculo)) {
		mysqli_query($conexion, "UPDATE Reservas SET estado_reserva = 'ACTIVA' WHERE id_reserva = $reserva_id");
		mysqli_query($conexion, "UPDATE Habitaciones SET ocupada_bool = TRUE WHERE numero_habitacion = $hab");

		header("Location: vistas/habitaciones.php?msg=asignacion_ok");
	}
}
?>