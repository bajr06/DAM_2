<?php
$host = "localhost";
$user = "root";
$pass = "songoku"
$db = "hotel_gestion_tfg";

$conexion = mysqli_connection($host, $user, $pass, $db)

if(!$conexion) {
	die("Error de conexión: " . mysqli_connect_error());
}
?>