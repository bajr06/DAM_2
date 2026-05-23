<?php
include('config/conexion.php');
session_start();

if (!isset($_SESSION['rol']) || $_SESSION['rol'] !== 'ADMINISTRADOR') {
	header("Location: vistas/empleados.php?error=permisos");
	exit();
}

if ($_SERVER["REQUEST_METHOD"] == "POST") {
	$dni = mysqli_real_escape_string($conexion, $_POST['dni']);
	$nombre = mysqli_real_escape_string($conexion, $_POST['nombre']);
	$apellidos = mysqli_real_escape_string($conexion, $_POST['apellidos']);
	$email = mysqli_real_escape_string($conexion, $_POST['email']);
	$password_hash = password_hash($_POST['password'], PASSWORD_BCRYPT);
	$rol = 'RECEPCIONISTA'; 

	mysqli_begin_transaction($conexion);

	try {
		$sql_trabajador = "INSERT INTO trabajadores (dni_trabajador, nombre, apellidos, email) 
						   VALUES ('$dni', '$nombre', '$apellidos', '$email')";
		mysqli_query($conexion, $sql_trabajador);

		$sql_cuenta = "INSERT INTO Cuentas_empleado (dni_empleado, email_acceso, password_hash, rol) 
					   VALUES ('$dni', '$email', '$password_hash', '$rol')";
		mysqli_query($conexion, $sql_cuenta);

		mysqli_commit($conexion);
		header("Location: vistas/empleados.php?msg=ok");

	} catch (mysqli_sql_exception $exception) {
		mysqli_rollback($conexion);
		echo "Error en el registro del empleado: " . $exception -> getMessage();
	}
}
?>