<?php
session_start();
include('../config/conexion.php');

if (!isset($_SESSION['empleado_id'])) {
	header("Location: ../index.php");
	exit();
}

$sql = "SELECT f.*, h.nombre, h.apellidos 
		FROM Facturas f
		JOIN Reservas r ON f.id_reserva = r.id_reserva
		JOIN Huespedes h ON r.dni_pasaporte = h.dni_pasaporte
		ORDER BY f.fecha_emision DESC";
$resultado = mysqli_query($conexion, $sql);
?>

<!DOCTYPE html>
<html lang="es">
	<head>
		<meta charset="UTF-8">
		<title>Historial de Facturación</title>
		<link rel="icon" type="image/png" href="../css/favicon.ico">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
		<link rel="stylesheet" href="../css/rooms.css"> </head>
	<body class="bg-light">
		<nav class="navbar-custom d-flex justify-content-between align-items-center shadow-sm">
			<a href="dashboard.php" class="back-link">
				<i class="bi bi-arrow-left"></i> Volver al Panel
			</a>
			<span class="page-title">Control de Ingresos / Facturación</span>
		</nav>

		<div class="container mt-4">
			<div class="d-flex justify-content-between align-items-center mb-4">
				<h4 class="fw-bold"><i class="bi bi-receipt-cutoff me-2"></i>Facturas Emitidas</h4>
				<div>
					<button class="btn btn-outline-primary btn-sm me-2" onclick="window.print()">
						<i class="bi bi-printer"></i> Imprimir Reporte
					</button>
				</div>
			</div>

			<div class="row mb-4">
				<div class="col-md-6 col-lg-4 ms-auto">
					<div class="input-group shadow-sm">
						<span class="input-group-text bg-white border-end-0 text-muted">
							<i class="bi bi-search"></i>
						</span>
						<input type="text" id="inputBuscarFactura" class="form-control border-start-0 ps-1" placeholder="Buscar factura por cliente...">
					</div>
				</div>
			</div>

			<div class="card shadow-sm border-0">
				<div class="card-body p-0">
					<table class="table table-hover mb-0">
						<thead class="table-dark">
							<tr>
								<th class="px-4">ID Factura</th>
								<th>Fecha y Hora</th>
								<th>Huésped</th>
								<th>Método de Pago</th>
								<th class="text-end px-4">Total Pagado</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<?php 
							if(mysqli_num_rows($resultado) > 0):
								while($factura = mysqli_fetch_assoc($resultado)): 
							?>
							<tr class="align-middle">
								<td class="px-4 text-muted">#<?php echo $factura['id_factura']; ?></td>
								<td><?php echo date('d/m/Y H:i', strtotime($factura['fecha_emision'])); ?></td>
								<td class="nombre-huesped"><strong><?php echo $factura['nombre'] . " " . $factura['apellidos']; ?></strong></td>
								<td><span class="badge bg-info text-dark"><?php echo $factura['metodo_pago_token']; ?></span></td>
								<td class="text-end fw-bold"><?php echo number_format($factura['total_pagado'], 2, ',', '.'); ?> €</td>
								<td class="text-center">
									<a href="imprimir_factura.php?id=<?php echo $factura['id_factura']; ?>" class="btn btn-sm btn-outline-dark" target="_blank">
										<i class="bi bi-printer"></i>
									</a>
								</td>
							</tr>
							<?php 
								endwhile; 
							else:
							?>
							<tr class="fila-vacia">
								<td colspan="6" class="text-center py-5 text-muted">
									<i class="bi bi-exclamation-circle" style="font-size: 2rem;"></i><br>
									No se han emitido facturas todavía.
								</td>
							</tr>
							<?php endif; ?>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<script>
			document.getElementById('inputBuscarFactura').addEventListener('input', function() {
				let termino = this.value.toLowerCase().trim();
				let filas = document.querySelectorAll('tbody tr');

				filas.forEach(function(fila) {
					if (fila.classList.contains('fila-vacia')) return;

					let celdaNombre = fila.querySelector('.nombre-huesped');
					if (celdaNombre) {
						let nombreTexto = celdaNombre.textContent.toLowerCase();
						if (nombreTexto.includes(termino)) {
							fila.style.setProperty('display', '', 'important');
						} else {
							fila.style.setProperty('display', 'none', 'important');
						}
					}
				});
			});
		</script>
	</body>
</html>