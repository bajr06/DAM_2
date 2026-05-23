<?php
session_start();
include('../config/conexion.php');

if (!isset($_SESSION['empleado_id'])) {
	header("Location: ../index.php");
	exit();
}

$fecha_hoy = date('Y-m-d');
$mes_actual = date('m');
$anio_actual = date('Y');

$sql_llegadas = "SELECT COUNT(*) as total FROM Reservas WHERE fecha_checkin = '$fecha_hoy' AND estado_reserva != 'ACTIVA'";
$res_llegadas = mysqli_query($conexion, $sql_llegadas);
$datos_llegadas = mysqli_fetch_assoc($res_llegadas);

$sql_salidas = "SELECT COUNT(*) as total FROM Reservas WHERE fecha_checkout = '$fecha_hoy' AND estado_reserva = 'ACTIVA'";
$res_salidas = mysqli_query($conexion, $sql_salidas);
$datos_salidas = mysqli_fetch_assoc($res_salidas);

$sql_ingresos = "SELECT SUM(total_pagado) as total_mes FROM Facturas 
				 WHERE MONTH(fecha_emision) = '$mes_actual' AND YEAR(fecha_emision) = '$anio_actual'";
$res_ingresos = mysqli_query($conexion, $sql_ingresos);
$datos_ingresos = mysqli_fetch_assoc($res_ingresos);
$ingresos_mes = $datos_ingresos['total_mes'] ?? 0;

$res_total_hab = mysqli_query($conexion, "SELECT COUNT(*) as total FROM Habitaciones");
$total_hab = mysqli_fetch_assoc($res_total_hab)['total'];

$res_hab_ocu = mysqli_query($conexion, "SELECT COUNT(*) as total FROM Habitaciones WHERE ocupada_bool = 1");
$hab_ocu = mysqli_fetch_assoc($res_hab_ocu)['total'];
$porcentaje_ocupacion = ($total_hab > 0) ? round(($hab_ocu / $total_hab) * 100) : 0;

$nombre_empleado = isset($_SESSION['nombre_empleado']) ? htmlspecialchars($_SESSION['nombre_empleado']) : 'Empleado';
$hora_acceso = isset($_SESSION['hora_login']) ? htmlspecialchars($_SESSION['hora_login']) : date('H:i');
?>

<!DOCTYPE html>
<html lang="es">
	<head>
		<meta charset="UTF-8">
		<title>Panel de Principal</title>
		<link rel="icon" type="image/png" href="../css/favicon.ico">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
		<link rel="stylesheet" href="css\model.css">
	</head>
	<body class="bg-light">
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow">
			<div class="container-fluid">
				<a class="navbar-brand" href="#">🛎️ Hotel ME Melia</a>
				<div class="d-flex align-items-center">
					<span class="navbar-text text-white me-3">
						<i class="bi bi-person-circle"></i> <?php echo htmlspecialchars($_SESSION['email_empleado']); ?>
					</span>
					<a href="../logout.php" class="btn btn-outline-danger btn-sm">Cerrar Sesión</a>
				</div>
			</div>
		</nav>
		
		<?php if(isset($_GET['msg'])): ?>
			<div class="container mt-3">
				<?php if($_GET['msg'] == 'actualizado' || $_GET['msg'] == 'asignacion_ok' || $_GET['msg'] == 'reserva_creada' || $_GET['msg'] == 'checkin_ok'): ?>
					<div class="alert alert-success alert-dismissible fade show" role="alert">
						<strong><i class="bi bi-check-circle"></i> Éxito:</strong> Operación realizada correctamente.
						<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
					</div>
				<?php endif; ?>
			</div>
		<?php endif; ?>
		
		<?php if(isset($_GET['error'])): ?>
			<div class="container mt-3">
				<div class="alert alert-danger alert-dismissible fade show" role="alert">
					<strong><i class="bi bi-exclamation-triangle"></i> Error:</strong> 
					<?php echo ($_GET['error'] == 'sin_disponibilidad') ? 'No hay habitaciones disponibles para esas fechas.' : 'Hubo un problema al procesar la solicitud.'; ?>
					<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
				</div>
			</div>
		<?php endif; ?>
		
		<div class="container mt-4">
			<div class="row mb-4">
				<div class="col-12">
					<div class="bg-white p-4 rounded-3 shadow-sm d-flex align-items-center justify-content-between">
						<div>
							<h3 class="fw-bold text-dark mb-1">Bienvenido, <?php echo $nombre_empleado; ?> 👋</h3>
							<p class="text-muted mb-0 font-monospace small">
								<i class="bi bi-calendar3 me-1"></i> <?php echo date('d/m/Y'); ?> 
								<span class="mx-2 text-secondary">|</span> 
								<i class="bi bi-box-arrow-in-right me-1"></i> Acceso al sistema: <?php echo $hora_acceso; ?>
							</p>
						</div>
						<div class="fs-1 text-primary"><i class="bi bi-building-gear"></i></div>
					</div>
				</div>
			</div>

			<div class="row g-4">
				<div class="col-md-3">
					<div class="card shadow-sm border-0 card-counter" style="border-left-color: #0d6efd;">
						<div class="card-body">
							<h6 class="text-muted small fw-bold text-uppercase">Ocupación Actual</h6>
							<h2 class="display-5 fw-bold"><?php echo $porcentaje_ocupacion; ?>%</h2>
							<div class="progress" style="height: 5px;">
								<div class="progress-bar" role="progressbar" style="width: <?php echo $porcentaje_ocupacion; ?>%"></div>
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-3">
					<div class="card shadow-sm border-0 card-counter" style="border-left-color: #198754;">
						<div class="card-body">
							<h6 class="text-muted small fw-bold text-uppercase">Ingresos de <?php echo date('F'); ?></h6>
							<h2 class="display-5 fw-bold text-success"><?php echo number_format($ingresos_mes, 0, ',', '.'); ?>€</h2>
							<small class="text-muted"><i class="bi bi-graph-up"></i> Facturación acumulada</small>
						</div>
					</div>
				</div>

				<div class="col-md-3">
					<div class="card shadow-sm border-0 card-counter" style="border-left-color: #ffc107;">
						<div class="card-body">
							<h6 class="text-muted small fw-bold text-uppercase">Llegadas Hoy</h6>
							<h2 class="display-5 fw-bold"><?php echo $datos_llegadas['total']; ?></h2>
							<a href="reservas.php" class="text-decoration-none small">Ver listado &rarr;</a>
						</div>
					</div>
				</div>

				<div class="col-md-3">
					<div class="card shadow-sm border-0 card-counter" style="border-left-color: #dc3545;">
						<div class="card-body">
							<h6 class="text-muted small fw-bold text-uppercase">Salidas Hoy</h6>
							<h2 class="display-5 fw-bold text-danger"><?php echo $datos_salidas['total']; ?></h2>
							<a href="reservas.php" class="text-decoration-none small text-danger">Gestionar llaves &rarr;</a>
						</div>
					</div>
				</div>
			</div>

			<div class="row mt-5">
				<div class="col-md-4">
					<a href="habitaciones.php?accion=checkin" class="btn btn-primary w-100 p-3 shadow-sm rounded-3">
						<i class="bi bi-person-plus fs-3"></i><br>Nuevo Check-in
					</a>
				</div>
				<div class="col-md-4">
					<a href="reservas.php" class="btn btn-info w-100 p-3 shadow-sm rounded-3 text-white">
						<i class="bi bi-calendar-event fs-3"></i><br>Reservas
					</a>
				</div>
				<div class="col-md-4">
					<a href="facturas.php" class="btn btn-dark w-100 p-3 shadow-sm rounded-3">
						<i class="bi bi-currency-euro fs-3"></i><br>Facturación
					</a>
				</div>
				<div class="row mt-3">
					<div class="col-md-4">
						<a href="nueva_reserva.php" class="btn btn-success w-100 p-3 shadow-sm rounded-3">
							<i class="bi bi-calendar-plus fs-3"></i><br>Nueva Reserva
						</a>
					</div>

					<div class="col-md-4">
						<a href="huespedes.php" class="btn btn-warning w-100 p-3 shadow-sm rounded-3 text-dark fw-bold">
							<i class="bi bi-people fs-3"></i><br>Huéspedes
						</a>
					</div>

					<div class="col-md-4">
						<a href="empleados.php" class="btn btn-outline-secondary w-100 p-3 shadow-sm rounded-3">
							<i class="bi bi-person-badge fs-3"></i><br>Staff
						</a>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
