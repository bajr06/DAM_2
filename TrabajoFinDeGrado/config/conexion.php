<?php
$servidor = "localhost";
$usuario  = "root";
$password = "songoku";
$db		  = "hotel_gestion_tfg";

$conexion = mysqli_connect($servidor, $usuario, $password, $db);

if (!$conexion) {
	die("Fallo en la conexión: " . mysqli_connect_error());
} 
?>