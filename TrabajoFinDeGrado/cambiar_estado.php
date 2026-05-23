<?php
include('config/conexion.php');
session_start();

if (isset($_GET['id']) && isset($_GET['nuevo_estado'])) {
	$id = $_GET['id'];
	$estado = $_GET['nuevo_estado'];

	$id = mysqli_real_escape_string($conexion, $id);
	$estado = mysqli_real_escape_string($conexion, $estado);

	$sql = "UPDATE habitaciones SET estado_limpieza = '$estado' WHERE numero_habitacion = '$id'";
	
	if (isset($_GET['id']) && isset($_GET['nuevo_estado'])) {
		$id = mysqli_real_escape_string($conexion, $_GET['id']);
		$estado = mysqli_real_escape_string($conexion, $_GET['nuevo_estado']);
		
		if(isset($_GET['tipo']) && $_GET['tipo'] == 'mantenimiento'){
			$sql = "UPDATE habitaciones SET estado_disponibilidad = '$estado' WHERE numero_habitacion = '$id'";
		} else {
			$sql = "UPDATE habitaciones SET estado_limpieza = '$estado' WHERE numero_habitacion = '$id'";
		}
	}
	
	if (mysqli_query($conexion, $sql)) {
		header("Location: vistas/habitaciones.php?msg=actualizado");
	} else {
		echo "Error actualizando: " . mysqli_error($conexion);
	}
}
?>