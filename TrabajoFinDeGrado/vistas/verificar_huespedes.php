<?php
session_start();
include('../config/conexion.php');

if (!isset($_SESSION['empleado_id'])) {
	header("Location: ../index.php");
	exit();
}

$hab_seleccionada = isset($_GET['hab']) ? htmlspecialchars($_GET['hab']) : '';
$num_personas = isset($_GET['personas']) ? intval($_GET['personas']) : 0;
$fecha_checkout = isset($_GET['out']) ? htmlspecialchars($_GET['out']) : '';
$reserva_id = isset($_GET['reserva_id']) ? intval($_GET['reserva_id']) : null;
$datos_huesped = null;

if ($reserva_id) {
	$sql_reserva = "SELECT r.*, h.* FROM Reservas r 
					JOIN Huespedes h ON r.dni_pasaporte = h.dni_pasaporte 
					WHERE r.id_reserva = $reserva_id";
	$res_reserva = mysqli_query($conexion, $sql_reserva);
	
	if ($res_reserva && mysqli_num_rows($res_reserva) > 0) {
		$datos_huesped = mysqli_fetch_assoc($res_reserva);
		
		if ($num_personas <= 0) {
			$num_personas = 1;
		}
		if (empty($fecha_checkout)) {
			$fecha_checkout = $datos_huesped['fecha_checkout'];
		}
	}
}

$mostrar_formulario_huespedes = (!empty($hab_seleccionada) && $num_personas > 0);
?>

<!DOCTYPE html>
<html lang="es">
	<head>
		<meta charset="UTF-8">
		<title>Registro de Huéspedes</title>
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
			<span class="page-title">Módulo de Check-in</span>
		</nav>
		
		<div class="container py-4">

			<?php if (!$mostrar_formulario_huespedes): ?>
				<div class="row justify-content-center">
					<div class="col-md-6">
						<div class="card shadow border-0 mt-4">
							<div class="card-header bg-dark text-white text-center py-3">
								<h5 class="mb-0"><i class="bi bi-door-open-fill me-2"></i>Paso 1: Configurar Estancia</h5>
							</div>
							<div class="card-body p-4">
								<form action="verificar_huespedes.php" method="GET">
									<?php if ($reserva_id): ?>
										<input type="hidden" name="reserva_id" value="<?php echo $reserva_id; ?>">
									<?php endif; ?>

									<div class="mb-3">
										<label class="form-label fw-bold small">SELECCIONAR HABITACIÓN DISPONIBLE *</label>
										<select name="hab" class="form-select" required>
											<option value="">-- Seleccione una habitación libre --</option>
											<?php
											// Consultamos únicamente las habitaciones que no estén ocupadas en este momento
											$sql_libres = "SELECT numero_habitacion FROM habitaciones WHERE ocupada_bool = 0 AND estado_disponibilidad = 'DISPONIBLE' ORDER BY numero_habitacion ASC";
											$res_libres = mysqli_query($conexion, $sql_libres);
											if ($res_libres) {
												while ($hab = mysqli_fetch_assoc($res_libres)) {
													echo "<option value='".htmlspecialchars($hab['numero_habitacion'])."'>Habitación ".htmlspecialchars($hab['numero_habitacion'])."</option>";
												}
											}
											?>
										</select>
									</div>

									<div class="mb-3">
										<label class="form-label fw-bold small">NÚMERO TOTAL DE OCUPANTES *</label>
										<input type="number" name="personas" class="form-control" min="1" max="4" value="1" required>
									</div>

									<div class="mb-3">
										<label class="form-label fw-bold small">FECHA DE SALIDA (CHECK-OUT) *</label>
										<input type="date" name="out" class="form-control" value="<?php echo $fecha_checkout; ?>" required>
									</div>

									<button type="submit" class="btn btn-primary w-100 py-2 fw-bold mt-2">
										Continuar al Formulario de Datos <i class="bi bi-arrow-right ms-1"></i>
									</button>
								</form>
							</div>
						</div>
					</div>
				</div>

			<?php else: ?>
				<div class="d-flex justify-content-between align-items-center mb-4">
					<h3><i class="bi bi-people-fill me-2"></i>Paso 2: Registro de Fichas de Ocupantes</h3>
					<span class="badge bg-primary fs-6 p-2 shadow-sm">
						Habitación: <?php echo $hab_seleccionada; ?> | <?php echo $num_personas; ?> Ocupante(s)
					</span>
				</div>

				<form action="../procesar_multi_checkin.php" method="POST">
					<input type="hidden" name="numero_habitacion" value="<?php echo $hab_seleccionada; ?>">
					<input type="hidden" name="fecha_checkin" value="<?php echo date('Y-m-d'); ?>">
					<input type="hidden" name="fecha_checkout" value="<?php echo $fecha_checkout; ?>">
					<input type="hidden" name="reserva_id" value="<?php echo $reserva_id; ?>">

					<?php for ($i = 0; $i < $num_personas; $i++):
						$es_titular_reserva = ($i === 0 && $datos_huesped !== null);
						
						$val_dni    = $es_titular_reserva ? htmlspecialchars($datos_huesped['dni_pasaporte']) : '';
						$val_nom    = $es_titular_reserva ? htmlspecialchars($datos_huesped['nombre']) : '';
						$val_ape    = $es_titular_reserva ? htmlspecialchars($datos_huesped['apellidos']) : '';
						$val_email  = $es_titular_reserva ? htmlspecialchars($datos_huesped['email']) : '';
						$val_tel    = $es_titular_reserva ? htmlspecialchars($datos_huesped['telefono']) : '';
						$val_sexo   = $es_titular_reserva ? $datos_huesped['sexo'] : '';
						$val_nac    = $es_titular_reserva ? $datos_huesped['fecha_nacimiento'] : '';
						$val_pais   = $es_titular_reserva ? htmlspecialchars($datos_huesped['pais_procedencia']) : '';
						$val_ciud   = $es_titular_reserva ? htmlspecialchars($datos_huesped['ciudad']) : '';
						$val_cp     = $es_titular_reserva ? htmlspecialchars($datos_huesped['codigo_postal']) : '';
					?>
						<div class="card shadow-sm mb-4 border-0">
							<div class="card-header bg-primary text-white fw-bold">
								HUÉSPED #<?php echo ($i + 1); ?> <?php echo ($i == 0) ? '(TITULAR)' : ''; ?>
							</div>
							<div class="card-body">
								<div class="row g-3">
									<div class="col-md-3">
										<label class="form-label small fw-bold">DNI / PASAPORTE *</label>
										<input type="text" name="dni_pasaporte[]" class="form-control" value="<?php echo $val_dni; ?>" required>
									</div>
									<div class="col-md-3">
										<label class="form-label small fw-bold">NOMBRE *</label>
										<input type="text" name="nombre[]" class="form-control" value="<?php echo $val_nom; ?>" required>
									</div>
									<div class="col-md-6">
										<label class="form-label small fw-bold">APELLIDOS *</label>
										<input type="text" name="apellidos[]" class="form-control" value="<?php echo $val_ape; ?>" required>
									</div>

									<div class="col-md-3">
										<label class="form-label small fw-bold">SEXO</label>
										<select name="sexo[]" class="form-control">
											<option value="">Seleccionar...</option>
											<option value="MASCULINO" <?php echo $val_sexo === 'MASCULINO' ? 'selected' : ''; ?>>Masculino</option>
											<option value="FEMENINO" <?php echo $val_sexo === 'FEMENINO' ? 'selected' : ''; ?>>Femenino</option>
											<option value="OTRO" <?php echo $val_sexo === 'OTRO' ? 'selected' : ''; ?>>Otro</option>
										</select>
									</div>
									<div class="col-md-3">
										<label class="form-label small fw-bold">FECHA NACIMIENTO</label>
										<input type="date" name="fecha_nacimiento[]" class="form-control" value="<?php echo $val_nac; ?>">
									</div>
									<div class="col-md-3">
										<label class="form-label small fw-bold">TELÉFONO</label>
										<input type="text" name="telefono[]" class="form-control" value="<?php echo $val_tel; ?>">
									</div>
									<div class="col-md-3">
										<label class="form-label small fw-bold">EMAIL</label>
										<input type="email" name="email[]" class="form-control" value="<?php echo $val_email; ?>">
									</div>

									<div class="col-md-4">
										<label class="form-label small fw-bold">PAÍS DE PROCEDENCIA</label>
										<input type="text" name="pais_procedencia[]" class="form-control" value="<?php echo $val_pais; ?>">
									</div>
									<div class="col-md-5">
										<label class="form-label small fw-bold">CIUDAD</label>
										<input type="text" name="ciudad[]" class="form-control" value="<?php echo $val_ciud; ?>">
									</div>
									<div class="col-md-3">
										<label class="form-label small fw-bold">CÓDIGO POSTAL</label>
										<input type="text" name="codigo_postal[]" class="form-control" value="<?php echo $val_cp; ?>">
									</div>
								</div>
							</div>
						</div>
					<?php endfor; ?>
					<button type="submit" class="btn btn-success btn-lg w-100 shadow mb-5"><i class="bi bi-check-circle me-2"></i>Completar Check-in y Activar Habitación</button>
				</form>
			<?php endif; ?>
		</div>
	</body>
</html>
