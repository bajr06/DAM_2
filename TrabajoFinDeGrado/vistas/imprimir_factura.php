<?php
session_start();
include('../config/conexion.php');

if (!isset($_SESSION['empleado_id'])) {
	die("Acceso denegado");
}

$id_factura = $_GET['id'];
$sql = "SELECT f.*, r.fecha_checkin, r.fecha_checkout, h.nombre, h.apellidos, h.dni_pasaporte 
		FROM Facturas f
		JOIN Reservas r ON f.id_reserva = r.id_reserva
		JOIN Huespedes h ON r.dni_pasaporte = h.dni_pasaporte
		WHERE f.id_factura = '$id_factura'";
$res = mysqli_query($conexion, $sql);
$f = mysqli_fetch_assoc($res);
?>

<!DOCTYPE html>
<html lang="es">
	<head>
		<meta charset="UTF-8">
		<link rel="icon" type="image/png" href="../css/favicon.ico">
		<title>Factura_<?php echo $f['id_factura']; ?></title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
		<link rel="stylesheet" href="../css/rooms.css">
	</head>
	<body onload="window.print()"> <div class="container my-5 invoice-box">
			<div class="row">
				<div class="col-6">
					<h1 class="display-5 fw-bold">HOTEL TFG</h1>
					<p>NIF: B-12345678<br>Calle Falsa 123, Madrid</p>
				</div>
				<div class="col-6 text-end">
					<h2 class="text-muted">FACTURA</h2>
					<p class="mb-0"><b>Número:</b> <?php echo $f['id_factura']; ?></p>
					<p><b>Fecha:</b> <?php echo date('d/m/Y', strtotime($f['fecha_emision'])); ?></p>
				</div>
			</div>
			<hr>
			<div class="row my-4">
				<div class="col-6">
					<p class="mb-1 text-muted fw-bold">CLIENTE:</p>
					<h5><?php echo $f['nombre'] . " " . $f['apellidos']; ?></h5>
					<p>DNI: <?php echo $f['dni_pasaporte']; ?></p>
				</div>
			</div>
			<table class="table table-bordered">
				<thead class="table-light">
					<tr>
						<th>Descripción</th>
						<th class="text-center">Total</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Estancia Hotelera (<?php echo date('d/m/Y', strtotime($f['fecha_checkin'])); ?> al <?php echo date('d/m/Y', strtotime($f['fecha_checkout'])); ?>)</td>
						<td class="text-center"><?php echo $f['total_pagado']; ?>€</td>
					</tr>
				</tbody>
			</table>
			<div class="text-end mt-4">
				<h3>TOTAL: <?php echo $f['total_pagado']; ?>€</h3>
			</div>
			<div class="no-print mt-5 text-center">
				<button class="btn btn-primary" onclick="window.print()">Imprimir de nuevo</button>
				<a href="facturas.php" class="btn btn-secondary">Volver</a>
			</div>
		</div>
	</body>
</html>