<?php
session_start();
if (!isset($_SESSION['empleado_id'])) {
	header("Location: ../index.php");
	exit();
}

$hab_seleccionada = isset($_GET['hab']) ? $_GET['hab'] : '';
$fecha_hoy = date('Y-m-d');
?>

<!DOCTYPE html>
<html lang="es">
	<head>
		<meta charset="UTF-8">
		<title>Nuevo Check-in</title>
		<link rel="icon" type="image/png" href="../css/favicon.ico">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
		<link rel="stylesheet" href="../css/rooms.css">
	</head>
	<body class="bg-light">
		<nav class="navbar-custom d-flex justify-content-between align-items-center shadow-sm">
			<a href="javascript:history.back()" class="back-link">
				<i class="bi bi-arrow-left"></i> Volver al Panel
			</a>
			<span class="page-title">Registro de Nuevo Huésped (Walk-in)</span>
		</nav>
		<div class="container mt-4">
			<div class="card shadow-sm border-0">
				<div class="card-body p-4">
					<h4 class="fw-bold mb-4"><i class="bi bi-person-lines-fill me-2"></i>Ficha de Registro</h4>
					
					<form action="../procesar_checkin.php" method="POST">
						
						<h5 class="text-primary border-bottom pb-2 mb-3">Datos Personales</h5>
						<div class="row g-3 mb-4">
							<div class="col-md-3">
								<label class="form-label fw-bold small">DNI / PASAPORTE *</label>
								<input type="text" name="dni_pasaporte" class="form-control" required>
							</div>
							<div class="col-md-3">
								<label class="form-label fw-bold small">NOMBRE *</label>
								<input type="text" name="nombre" class="form-control" required>
							</div>
							<div class="col-md-6">
								<label class="form-label fw-bold small">APELLIDOS *</label>
								<input type="text" name="apellidos" class="form-control" required>
							</div>
							<div class="col-md-3">
								<label class="form-label fw-bold small">FECHA NACIMIENTO</label>
								<input type="date" name="fecha_nacimiento" class="form-control">
							</div>
							<div class="col-md-3">
								<label class="form-label fw-bold small">SEXO</label>
								<select name="sexo" class="form-select">
									<option value="">Seleccionar...</option>
									<option value="MASCULINO">Masculino</option>
									<option value="FEMENINO">Femenino</option>
									<option value="OTRO">Otro</option>
								</select>
							</div>
						</div>

						<h5 class="text-primary border-bottom pb-2 mb-3">Contacto y Procedencia</h5>
						<div class="row g-3 mb-4">
							<div class="col-md-4">
								<label class="form-label fw-bold small">EMAIL</label>
								<input type="email" name="email" class="form-control">
							</div>
							<div class="col-md-4">
								<label class="form-label fw-bold small">TELÉFONO</label>
								<input type="text" name="telefono" class="form-control">
							</div>
							<div class="col-md-4">
								<label class="form-label fw-bold small">PAÍS DE PROCEDENCIA</label>
								<input type="text" name="pais_procedencia" class="form-control">
							</div>
							<div class="col-md-6">
								<label class="form-label fw-bold small">CIUDAD</label>
								<input type="text" name="ciudad" class="form-control">
							</div>
							<div class="col-md-6">
								<label class="form-label fw-bold small">CÓDIGO POSTAL</label>
								<input type="text" name="codigo_postal" class="form-control">
							</div>
						</div>

						<h5 class="text-primary border-bottom pb-2 mb-3">Datos de Estancia</h5>
						<div class="row g-3 mb-4">
							<div class="col-md-4">
								<label class="form-label fw-bold small">HABITACIÓN ASIGNADA *</label>
								<input type="number" name="numero_habitacion" class="form-control bg-light" value="<?php echo htmlspecialchars($hab_seleccionada); ?>" readonly required>
							</div>
							<div class="col-md-4">
								<label class="form-label fw-bold small">FECHA ENTRADA</label>
								<input type="date" name="fecha_checkin" class="form-control bg-light" value="<?php echo $fecha_hoy; ?>" readonly>
							</div>
							<div class="col-md-4">
								<label class="form-label fw-bold small">FECHA SALIDA *</label>
								<input type="date" name="fecha_checkout" class="form-control" required min="<?php echo date('Y-m-d', strtotime('+1 day')); ?>">
							</div>
						</div>

						<div class="text-end mt-4">
							<a href="habitaciones.php" class="btn btn-secondary me-2">Cancelar</a>
							<button type="submit" class="btn btn-success px-4"><i class="bi bi-check2-circle me-2"></i>Registrar Check-in</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>