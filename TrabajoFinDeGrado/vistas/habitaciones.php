<?php
session_start();
include('../config/conexion.php');

if (!isset($_SESSION['empleado_id'])) {
	header("Location: ../index.php");
	exit();
}

$modo_checkin = isset($_GET['accion']) && $_GET['accion'] == 'checkin';

if ($modo_checkin) {
	$sql = "SELECT * FROM habitaciones WHERE ocupada_bool = 0 AND estado_limpieza = 'LIMPIA' ORDER BY numero_habitacion ASC";
} else {
	$sql = "SELECT * FROM habitaciones ORDER BY numero_habitacion ASC";
}
$resultado = mysqli_query($conexion, $sql);


$reserva_id_asignando = isset($_GET['reserva_id']) ? $_GET['reserva_id'] : null;
?>

<!DOCTYPE html>
<html lang="es">
	<head>
		<meta charset="UTF-8">
		<title>Estado de Habitaciones - PMS</title>
		<link rel="icon" type="image/png" href="../css/favicon.ico">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
		<link rel="stylesheet" href="../css/rooms.css">
	</head>
	<body>
		<nav class="navbar-custom d-flex justify-content-between align-items-center shadow-sm">
			<a href="dashboard.php" class="back-link">
				<i class="bi bi-arrow-left"></i> Volver al Panel
			</a>
			<span class="page-title">Gestión de Planta</span>
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
		<div class="container mt-5">
			<div class="accordion accordion-flush" id="accordionHabitaciones">
				<?php 
				$piso_actual = 0;
				$primera_vez = true;
				
				while($room = mysqli_fetch_assoc($resultado)): 
					$piso_de_la_habitacion = floor($room['numero_habitacion'] / 100);
					
				if($piso_actual != $piso_de_la_habitacion):
					if(!$primera_vez) echo '</div></div></div>'; 
				
				$piso_actual = $piso_de_la_habitacion;
				$show_class = ($piso_actual == 1) ? 'show' : '';
				$collapsed_class = ($piso_actual == 1) ? '' : 'collapsed';
				?>
				
				<div class="accordion-item border-0 mb-2">
					<h2 class="accordion-header" id="heading<?php echo $piso_actual; ?>">
						<button class="accordion-button <?php echo $collapsed_class; ?> border-bottom px-0" type="button" data-bs-toggle="collapse" data-bs-target="#collapse<?php echo $piso_actual; ?>">
							<i class="bi bi-layers me-2 text-muted"></i> <strong>Planta <?php echo $piso_actual; ?></strong>
						</button>
					</h2>
					<div id="collapse<?php echo $piso_actual; ?>" class="accordion-collapse collapse <?php echo $show_class; ?>" data-bs-parent="#accordionHabitaciones">
						<div class="accordion-body p-0">
			
							<?php 
							$primera_vez = false;
							endif; 

							if($room['estado_disponibilidad'] == 'INDISPONIBLE') {
								$status_text = "Fuera de Servicio (Bloqueada)"; 
								$status_class = "bg-warning";
							} elseif($room['ocupada_bool']) {
								$status_text = "Ocupada"; 
								$status_class = "bg-occupied";
							} elseif($room['estado_limpieza'] == 'SUCIA') {
								$status_text = "Sucia"; 
								$status_class = "bg-dirty";
							} else {
								$status_text = "Disponible"; 
								$status_class = "bg-available";
							}
							?>
			
							<div class="row room-row mx-0 align-items-center">
								<div class="col-md-4">
									<span class="hab-label">Hab.</span>
									<span class="hab-number"><?php echo $room['numero_habitacion']; ?></span>
									<span class="hab-type"><?php echo $room['categoria']; ?></span>
								</div>
								<div class="col-md-4 text-center">
									<span class="status-dot <?php echo $status_class; ?>"></span>
									<small class="text-secondary fw-medium"><?php echo $status_text; ?></small>
								</div>
								<div class="col-md-4 text-end">
									<span class="text-muted me-3"><i class="bi bi-people"></i> Cap: <?php echo $room['capacidad_max']; ?></span>
									
									<?php if($room['ocupada_bool']): ?>
										<a href="../procesar_checkout.php?hab=<?php echo $room['numero_habitacion']; ?>" 
										class="btn btn-danger btn-sm px-3" 
										onclick="return confirm('¿Confirmar salida del cliente y liberar habitación?')">
										Realizar Check-out
										</a>
									<?php elseif($room['estado_limpieza'] == 'SUCIA' && $room['estado_disponibilidad'] == 'DISPONIBLE'): ?>
										<a href="../cambiar_estado.php?id=<?php echo $room['numero_habitacion']; ?>&nuevo_estado=LIMPIA" 
										class="btn btn-success btn-sm px-3">
										Marcar Limpia
										</a>
									<?php elseif($room['estado_disponibilidad'] == 'DISPONIBLE'): ?>
										<a href="nuevo_checkin.php?hab=<?php echo $room['numero_habitacion']; ?>" 
										class="btn btn-primary btn-sm px-4">
										Check-in
										</a>
									<?php endif; ?>

									<?php if($room['estado_disponibilidad'] == 'DISPONIBLE' && !$room['ocupada_bool']): ?>
										<a href="../cambiar_estado.php?id=<?php echo $room['numero_habitacion']; ?>&nuevo_estado=INDISPONIBLE&tipo=mantenimiento" 
										class="btn btn-outline-warning btn-sm"
										onclick="return confirm('¿Está seguro de que desea bloquear la habitación <?php echo $room['numero_habitacion']; ?> por mantenimiento? Pasará a estar Indisponible.')">
										<i class="bi bi-tools"></i> Bloquear
										</a>
									<?php elseif($room['estado_disponibilidad'] == 'INDISPONIBLE'): ?>
										<a href="../cambiar_estado.php?id=<?php echo $room['numero_habitacion']; ?>&nuevo_estado=DISPONIBLE&tipo=mantenimiento" 
										class="btn btn-warning btn-sm text-dark"
										onclick="return confirm('¿Está seguro de que desea desbloquear y habilitar la habitación <?php echo $room['numero_habitacion']; ?>? Pasará a estar Disponible.')">
										<i class="bi bi-check-circle"></i> Habilitar
										</a>
									<?php endif; ?> </div>
							</div>
						<?php endwhile; ?>
					</div>
				</div></div>
			</div>
		</div>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	</body>
</html>