<?php
include('config/conexion.php');
session_start();

if ($_SERVER["REQUEST_METHOD"] == "POST") {
	$email = mysqli_real_escape_string($conexion, $_POST['email']);
	$password = $_POST['password']; 

	$sql = "SELECT c.dni_empleado, c.email_acceso, c.password_hash, c.rol, t.nombre 
			FROM Cuentas_empleado c
			INNER JOIN trabajadores t ON c.dni_empleado = t.dni_trabajador
			WHERE c.email_acceso = '$email'";
			
	$resultado = mysqli_query($conexion, $sql);

	if ($empleado = mysqli_fetch_assoc($resultado)) {
		if (password_verify($password, $empleado['password_hash'])) {
			date_default_timezone_set('Europe/Madrid');
			$_SESSION['empleado_id'] = $empleado['dni_empleado'];
			$_SESSION['email_empleado'] = $empleado['email_acceso'];
			$_SESSION['rol'] = $empleado['rol']; 
			$_SESSION['nombre_empleado'] = $empleado['nombre'];
			$_SESSION['hora_login'] = date('H:i');
			
			header("Location: vistas/dashboard.php");
			exit();
		} else {
			header("Location: index.php?error=1");
			exit();
		}
	} else {
		header("Location: index.php?error=1");
		exit();
	}
}
?>
