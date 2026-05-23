<?php
session_start();
include('../config/conexion.php');

if (!isset($_SESSION['empleado_id'])) {
	header("Location: ../index.php");
	exit();
}

$hoy = date('Y-m-d');

$sql_hoy = "SELECT r.*, h.nombre, h.apellidos 
			FROM Reservas r 
			JOIN Huespedes h ON r.dni_pasaporte = h.dni_pasaporte 
			WHERE r.fecha_checkin = '$hoy' AND r.estado_reserva = 'PENDIENTE'";
$res_hoy = mysqli_query($conexion, $sql_hoy);

$sql_salidas = "SELECT r.*, h.nombre, h.apellidos, rh.numero_habitacion 
				FROM Reservas r 
				JOIN Huespedes h ON r.dni_pasaporte = h.dni_pasaporte 
				LEFT JOIN reserva_habitacion rh ON r.id_reserva = rh.id_reserva
				WHERE r.fecha_checkout = '$hoy' AND r.estado_reserva = 'ACTIVA'";
$res_salidas = mysqli_query($conexion, $sql_salidas);

$sql_todas = "SELECT r.*, h.nombre, h.apellidos 
			  FROM Reservas r 
			  JOIN Huespedes h ON r.dni_pasaporte = h.dni_pasaporte 
			  ORDER BY FIELD(r.estado_reserva, 'PENDIENTE', 'ACTIVA', 'FINALIZADA') ASC, h.nombre ASC, h.apellidos ASC";
$res_todas = mysqli_query($conexion, $sql_todas);
?>

<!DOCTYPE html>
<html lang="es">
	<head>
		<meta charset="UTF-8">
		<title>Gestión de Reservas</title>
		<link rel="icon" type="image/png" href="../css/favicon.ico">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
		<link rel="stylesheet" href="../css/rooms.css">
	</head>
	<body class="bg-light">
		<nav class="navbar-custom d-flex justify-content-between align-items-center shadow-sm">
			<a href="dashboard.php" class="back-link">
				<i class="bi bi-arrow-left"></i> Volver al Panel
			</a>
			<span class="page-title">Centro de Reservas y Llegadas</span>
		</nav>

		<?php if(isset($_GET['msg'])): ?>
			<div class="container mt-3">
				<?php if($_GET['msg'] == 'actualizado' || $_GET['msg'] == 'asignacion_ok' || $_GET['msg'] == 'reserva_creada' || $_GET['msg'] == 'checkout_ok'): ?>
					<div class="alert alert-success alert-dismissible fade show" role="alert">
						<strong><i class="bi bi-check-circle"></i> Éxito:</strong> Operación realizada correctamente.
						<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
					</div>
				<?php endif; ?>
			</div>
		<?php endif; ?>

		<div class="container mt-4">
			
			<div class="row mb-4 align-items-center">
				<div class="col-md-6 col-lg-4 ms-auto">
					<div class="input-group shadow-sm">
						<span class="input-group-text bg-white border-end-0 text-muted">
							<i class="bi bi-search"></i>
						</span>
						<input type="text" id="inputBuscar" class="form-control border-start-0 ps-1" placeholder="Buscar huésped por nombre o apellido...">
					</div>
				</div>
			</div>

			<ul class="nav nav-tabs mb-4" id="misPestanas" role="tablist">
				<li class="nav-item" role="presentation">
					<button class="nav-link active fw-bold text-primary" id="llegadas-tab" data-bs-toggle="tab" data-bs-target="#llegadas" type="button" role="tab">
						<i class="bi bi-bell-fill me-2"></i>Llegadas de Hoy (Check-in)
					</button>
				</li>
				<li class="nav-item" role="presentation">
					<button class="nav-link fw-bold text-danger" id="salidas-tab" data-bs-toggle="tab" data-bs-target="#salidas" type="button" role="tab">
						<i class="bi bi-door-closed-fill me-2"></i>Salidas de Hoy (Check-out)
					</button>
				</li>
				<li class="nav-item" role="presentation">
					<button class="nav-link fw-bold text-dark" id="todas-tab" data-bs-toggle="tab" data-bs-target="#todas" type="button" role="tab">
						<i class="bi bi-calendar-check me-2"></i>Histórico Completo
					</button>
				</li>
			</ul>

			<div class="tab-content border bg-white shadow-sm p-4 rounded-bottom" id="contenidoPestanas">
				
				<div class="tab-pane fade show active" id="llegadas" role="tabpanel">
					<h4 class="mb-3 text-primary">Recepción de Huéspedes Esperados</h4>
					<div class="table-responsive">
						<table class="table table-hover align-middle">
							<thead class="table-light">
								<tr>
									<th>Titular de la Reserva</th>
									<th>DNI / Pasaporte</th>
									<th>Salida Prevista</th>
									<th class="text-end">Acción</th>
								</tr>
							</thead>
							<tbody>
								<?php if(mysqli_num_rows($res_hoy) > 0): ?>
									<?php while($r = mysqli_fetch_assoc($res_hoy)): ?>
									<tr>
										<td class="fw-bold nombre-huesped"><?php echo $r['nombre']." ".$r['apellidos']; ?></td>
										<td><?php echo $r['dni_pasaporte']; ?></td>
										<td><?php echo date('d/m/Y', strtotime($r['fecha_checkout'])); ?></td>
										<td class="text-end">
											<form action="verificar_huespedes.php" method="GET" class="d-inline">
												<input type="hidden" name="reserva_id" value="<?php echo $r['id_reserva']; ?>">
												<button type="submit" class="btn btn-success btn-sm">Iniciar Check-in</button>
											</form>
										</td>
									</tr>
									<?php endwhile; ?>
								<?php else: ?>
									<tr class="fila-vacia"><td colspan="4" class="text-center text-muted py-3">No hay llegadas pendientes para el día de hoy.</td></tr>
								<?php endif; ?>
							</tbody>
						</table>
					</div>
				</div>

				<div class="tab-pane fade" id="salidas" role="tabpanel">
					<h4 class="mb-3 text-danger">Salidas de Huéspedes (Check-out)</h4>
					<div class="table-responsive">
						<table class="table table-hover align-middle">
							<thead class="table-light">
								<tr>
									<th>Titular de la Reserva</th>
									<th>DNI / Pasaporte</th>
									<th>Habitación</th>
									<th class="text-end">Acción</th>
								</tr>
							</thead>
							<tbody>
								<?php if(mysqli_num_rows($res_salidas) > 0): ?>
									<?php while($s = mysqli_fetch_assoc($res_salidas)): ?>
									<tr>
										<td class="fw-bold nombre-huesped"><?php echo $s['nombre']." ".$s['apellidos']; ?></td>
										<td><?php echo $s['dni_pasaporte']; ?></td>
										<td>
											<span class="badge bg-info text-dark fw-bold px-3 py-2">
												Hab. <?php echo !empty($s['numero_habitacion']) ? $s['numero_habitacion'] : 'No asignada'; ?>
											</span>
										</td>
										<td class="text-end">
											<?php if(!empty($s['numero_habitacion'])): ?>
												<a href="../procesar_checkout.php?hab=<?php echo $s['numero_habitacion']; ?>" 
												class="btn btn-danger btn-sm px-3" 
												onclick="return confirm('¿Confirmar salida del cliente y liberar la habitación <?php echo $s['numero_habitacion']; ?>?')">
												Realizar Check-out
												</a>
											<?php else: ?>
												<span class="text-muted small">Sin habitación vinculada</span>
											<?php endif; ?>
										</td>
									</tr>
									<?php endwhile; ?>
								<?php else: ?>
									<tr class="fila-vacia"><td colspan="4" class="text-center text-muted py-3">No hay salidas de clientes agendadas para hoy.</td></tr>
								<?php endif; ?>
							</tbody>
						</table>
					</div>
				</div>

				<div class="tab-pane fade" id="todas" role="tabpanel">
					<div class="d-flex justify-content-between align-items-center mb-3">
						<h4 class="text-dark">Listado General (Clasificado por Prioridad de Estado)</h4>
					</div>
					<div class="table-responsive">
						<table class="table table-hover align-middle">
							<thead class="table-light">
								<tr>
									<th>Huésped</th>
									<th>DNI</th>
									<th>Entrada</th>
									<th>Salida</th>
									<th class="text-center">Estado</th>
								</tr>
							</thead>
							<tbody>
								<?php while($res = mysqli_fetch_assoc($res_todas)): 
									$badge_class = "bg-secondary";
									if($res['estado_reserva'] == 'PENDIENTE') $badge_class = "bg-warning text-dark";
									if($res['estado_reserva'] == 'ACTIVA') $badge_class = "bg-success";
									if($res['estado_reserva'] == 'FINALIZADA') $badge_class = "bg-dark";
								?>
								<tr>
									<td class="nombre-huesped"><strong><?php echo $res['nombre'] . " " . $res['apellidos']; ?></strong></td>
									<td><?php echo $res['dni_pasaporte']; ?></td>
									<td><?php echo date('d/m/Y', strtotime($res['fecha_checkin'])); ?></td>
									<td><?php echo date('d/m/Y', strtotime($res['fecha_checkout'])); ?></td>
									<td class="text-center"><span class="badge <?php echo $badge_class; ?>"><?php echo $res['estado_reserva']; ?></span></td>
								</tr>
								<?php endwhile; ?>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>

		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
		<script src="../js/script.js"></script>
	</body>
</html>