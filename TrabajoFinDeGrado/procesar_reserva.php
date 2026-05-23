<?php
include('config/conexion.php');
session_start();

if (!isset($_SESSION['empleado_id'])) {
	header("Location: index.php");
	exit();
}

if ($_SERVER["REQUEST_METHOD"] == "POST") {
	$dni = mysqli_real_escape_string($conexion, $_POST['dni']);
	$nombre = mysqli_real_escape_string($conexion, $_POST['nombre']);
	$apellidos = mysqli_real_escape_string($conexion, $_POST['apellidos']);
	$email = mysqli_real_escape_string($conexion, $_POST['email']);
	$fecha_in = $_POST['fecha_in'];
	$fecha_out = $_POST['fecha_out'];

	$sql_huesped = "INSERT INTO Huespedes (dni_pasaporte, nombre, apellidos, email) 
					VALUES ('$dni', '$nombre', '$apellidos', '$email')
					ON DUPLICATE KEY UPDATE nombre='$nombre', apellidos='$apellidos', email='$email'";
	
	if (mysqli_query($conexion, $sql_huesped)) {
		
		$sql_reserva = "INSERT INTO Reservas (fecha_checkin, fecha_checkout, estado_reserva, dni_pasaporte) 
						VALUES ('$fecha_in', '$fecha_out', 'PENDIENTE', '$dni')";
		
		if (mysqli_query($conexion, $sql_reserva)) {
			header("Location: vistas/reservas.php?msg=reserva_creada");
		} else {
			echo "Error al crear la reserva: " . mysqli_error($conexion);
		}
	} else {
		echo "Error al procesar el huésped: " . mysqli_error($conexion);
	}
}
?>