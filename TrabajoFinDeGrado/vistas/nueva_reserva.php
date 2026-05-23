<?php
session_start();
include('../config/conexion.php');
if (!isset($_SESSION['empleado_id'])) { header("Location: ../index.php"); exit(); }
?>

<!DOCTYPE html>
<html lang="es">
	<head>
		<meta charset="UTF-8">
		<title>Nueva Reserva</title>
		<link rel="icon" type="image/png" href="../css/favicon.ico">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
		<link rel="stylesheet" href="../css/rooms.css">
	</head>
	<body class="bg-light">
		<nav class="navbar-custom d-flex justify-content-between align-items-center shadow-sm">
			<a href="javascript:history.back()" class="back-link">
				<i class="bi bi-arrow-left"></i> Cancelar y Volver
			</a>
			<span class="page-title">Entrada de Reserva Manual</span>
		</nav>

		<div class="container mt-5">
			<div class="row justify-content-center">
				<div class="col-md-8">
					<div class="card shadow-sm border-0">
						<div class="card-body p-4">
							<h4 class="mb-4"><i class="me-2 text-primary"></i>Datos del Cliente</h4>
							
							<form action="../procesar_reserva.php" method="POST">
								<div class="row g-3">
									<div class="col-md-4">
										<label class="form-label small fw-bold">DNI / PASAPORTE</label>
										<input type="text" name="dni" class="form-control" required>
									</div>
									<div class="col-md-4">
										<label class="form-label small fw-bold">NOMBRE</label>
										<input type="text" name="nombre" class="form-control" required>
									</div>
									<div class="col-md-4">
										<label class="form-label small fw-bold">APELLIDOS</label>
										<input type="text" name="apellidos" class="form-control" required>
									</div>
									<div class="col-md-12">
										<label class="form-label small fw-bold">EMAIL</label>
										<input type="email" name="email" class="form-control">
									</div>

									<hr class="my-4">
									<h4 class="mb-2"><i class="bi bi-calendar-check me-2 text-primary"></i>Fechas de Estancia</h4>

									<div class="col-md-6">
										<label class="form-label small fw-bold">ENTRADA</label>
										<input type="date" name="fecha_in" class="form-control" required min="<?php echo date('Y-m-d'); ?>">
									</div>
									<div class="col-md-6">
										<label class="form-label small fw-bold">SALIDA</label>
										<input type="date" name="fecha_out" class="form-control" required min="<?php echo date('Y-m-d', strtotime('+1 day')); ?>">
									</div>

									<div class="col-12 mt-4">
										<button type="submit" class="btn btn-primary w-100 p-3 fw-bold shadow-sm">
											Registrar Reserva Pendiente
										</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>