<?php
session_start();
include('config/conexion.php');

if (!isset($_SESSION['empleado_id'])) {
	header("Location: index.php");
	exit();
}

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
	$dni_pasaporte = mysqli_real_escape_string($conexion, $_POST['dni_pasaporte']);
	$nombre = mysqli_real_escape_string($conexion, $_POST['nombre']);
	$apellidos = mysqli_real_escape_string($conexion, $_POST['apellidos']);
	$email = mysqli_real_escape_string($conexion, $_POST['email']);
	$telefono = mysqli_real_escape_string($conexion, $_POST['telefono']);
	$pais_procedencia = mysqli_real_escape_string($conexion, $_POST['pais_procedencia']);
	$ciudad = mysqli_real_escape_string($conexion, $_POST['ciudad']);
	$codigo_postal = mysqli_real_escape_string($conexion, $_POST['codigo_postal']);
	
	$fecha_nacimiento = !empty($_POST['fecha_nacimiento']) ? "'" . mysqli_real_escape_string($conexion, $_POST['fecha_nacimiento']) . "'" : "NULL";
	$sexo = !empty($_POST['sexo']) ? "'" . mysqli_real_escape_string($conexion, $_POST['sexo']) . "'" : "NULL";

	$numero_habitacion = mysqli_real_escape_string($conexion, $_POST['numero_habitacion']);
	$fecha_checkin = mysqli_real_escape_string($conexion, $_POST['fecha_checkin']);
	$fecha_checkout = mysqli_real_escape_string($conexion, $_POST['fecha_checkout']);

	$sql_huesped = "INSERT INTO Huespedes (dni_pasaporte, nombre, apellidos, fecha_nacimiento, sexo, pais_procedencia, ciudad, codigo_postal, telefono, email) 
					VALUES ('$dni_pasaporte', '$nombre', '$apellidos', $fecha_nacimiento, $sexo, '$pais_procedencia', '$ciudad', '$codigo_postal', '$telefono', '$email')
					ON DUPLICATE KEY UPDATE 
					nombre='$nombre', apellidos='$apellidos', fecha_nacimiento=$fecha_nacimiento, sexo=$sexo, 
					pais_procedencia='$pais_procedencia', ciudad='$ciudad', codigo_postal='$codigo_postal', 
					telefono='$telefono', email='$email'";
	
	mysqli_query($conexion, $sql_huesped);

	$sql_reserva = "INSERT INTO Reservas (fecha_checkin, fecha_checkout, estado_reserva, dni_pasaporte) 
					VALUES ('$fecha_checkin', '$fecha_checkout', 'ACTIVA', '$dni_pasaporte')";
	mysqli_query($conexion, $sql_reserva);
	
	$id_reserva = mysqli_insert_id($conexion);

	$sql_asignacion = "INSERT INTO reserva_habitacion (id_reserva, numero_habitacion) 
					   VALUES ($id_reserva, '$numero_habitacion')";
	mysqli_query($conexion, $sql_asignacion);

	$sql_estado = "UPDATE habitaciones SET ocupada_bool = 1 WHERE numero_habitacion = '$numero_habitacion'";
	mysqli_query($conexion, $sql_estado);

	header("Location: vistas/dashboard.php?msg=reserva_creada");
	exit();
}
?>