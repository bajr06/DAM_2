<?php
session_start();
include('config/conexion.php');

if (!isset($_SESSION['empleado_id'])) {
	header("Location: index.php");
	exit();
}

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
	$numero_habitacion = mysqli_real_escape_string($conexion, $_POST['numero_habitacion']);
	$fecha_checkin = mysqli_real_escape_string($conexion, $_POST['fecha_checkin']);
	$fecha_checkout = mysqli_real_escape_string($conexion, $_POST['fecha_checkout']);
	$reserva_id = !empty($_POST['reserva_id']) ? intval($_POST['reserva_id']) : null;

	$dnis = $_POST['dni_pasaporte'];
	$nombres = $_POST['nombre'];
	$apellidos = $_POST['apellidos'];
	$sexos = $_POST['sexo'];
	$fechas_nac = $_POST['fecha_nacimiento'];
	$paises = $_POST['pais_procedencia'];
	$ciudades = $_POST['ciudad'];
	$cps = $_POST['codigo_postal'];
	$telefonos = $_POST['telefono'];
	$emails = $_POST['email'];

	mysqli_begin_transaction($conexion);

	try {
		foreach ($dnis as $i => $dni) {
			if (empty($dni)) continue;

			$d = mysqli_real_escape_string($conexion, $dni);
			$n = mysqli_real_escape_string($conexion, $nombres[$i]);
			$a = mysqli_real_escape_string($conexion, $apellidos[$i]);
			$sexo_val = !empty($sexos[$i]) ? "'" . mysqli_real_escape_string($conexion, $sexos[$i]) . "'" : "NULL";
			$fecha_val = !empty($fechas_nac[$i]) ? "'" . mysqli_real_escape_string($conexion, $fechas_nac[$i]) . "'" : "NULL";
			$p = mysqli_real_escape_string($conexion, $paises[$i]);
			$c = mysqli_real_escape_string($conexion, $ciudades[$i]);
			$cp = mysqli_real_escape_string($conexion, $cps[$i]);
			$tel = mysqli_real_escape_string($conexion, $telefonos[$i]);
			$em = mysqli_real_escape_string($conexion, $emails[$i]);

			$sql_huesped = "INSERT INTO Huespedes 
				(dni_pasaporte, nombre, apellidos, sexo, fecha_nacimiento, pais_procedencia, ciudad, codigo_postal, telefono, email) 
				VALUES ('$d', '$n', '$a', $sexo_val, $fecha_val, '$p', '$c', '$cp', '$tel', '$em')
				ON DUPLICATE KEY UPDATE 
				nombre='$n', apellidos='$a', sexo=$sexo_val, fecha_nacimiento=$fecha_val, 
				pais_procedencia='$p', ciudad='$c', codigo_postal='$cp', telefono='$tel', email='$em'";
			
			if (!mysqli_query($conexion, $sql_huesped)) {
				throw new Exception("Error al mapear la ficha del huésped (" . $d . "): " . mysqli_error($conexion));
			}
		}

		$titular = mysqli_real_escape_string($conexion, $dnis[0]);

		if ($reserva_id) {
			$sql_res = "UPDATE Reservas 
						SET estado_reserva = 'ACTIVA', dni_pasaporte = '$titular', fecha_checkout = '$fecha_checkout' 
						WHERE id_reserva = $reserva_id";
			
			if (!mysqli_query($conexion, $sql_res)) {
				throw new Exception("Error al actualizar la reserva existente: " . mysqli_error($conexion));
			}
			$id_reserva_final = $reserva_id;
		} else {
			$sql_res = "INSERT INTO Reservas (fecha_checkin, fecha_checkout, estado_reserva, dni_pasaporte) 
						VALUES ('$fecha_checkin', '$fecha_checkout', 'ACTIVA', '$titular')";

			if (!mysqli_query($conexion, $sql_res)) {
				throw new Exception("Error al crear la reserva principal: " . mysqli_error($conexion));
			}
			$id_reserva_final = mysqli_insert_id($conexion);
		}

		$num_ocupantes_reales = count(array_filter($dnis));
		
		$sql_asignacion = "INSERT INTO reserva_habitacion (id_reserva, numero_habitacion, numero_ocupantes_reales) 
						   VALUES ($id_reserva_final, '$numero_habitacion', $num_ocupantes_reales)
						   ON DUPLICATE KEY UPDATE numero_habitacion = '$numero_habitacion', numero_ocupantes_reales = $num_ocupantes_reales";
		
		if (!mysqli_query($conexion, $sql_asignacion)) {
			throw new Exception("Error al mapear la relación reserva-habitación: " . mysqli_error($conexion));
		}

		$sql_update_hab = "UPDATE habitaciones SET ocupada_bool = 1 WHERE numero_habitacion = '$numero_habitacion'";
		if (!mysqli_query($conexion, $sql_update_hab)) {
			throw new Exception("Error al actualizar el indicador de estado de la habitación: " . mysqli_error($conexion));
		}

		mysqli_commit($conexion);

		header("Location: vistas/habitaciones.php?msg=checkin_ok");
		exit();

	} catch (Exception $e) {
		mysqli_rollback($conexion);
		die("Fallo crítico en el proceso de Check-in múltiple: " . $e->getMessage());
	}
}
?>