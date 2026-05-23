<?php
session_start();
include('../config/conexion.php');

if (!isset($_SESSION['empleado_id'])) {
	header("Location: ../index.php");
	exit();
}

$busqueda = isset($_GET['buscar']) ? mysqli_real_escape_string($conexion, $_GET['buscar']) : '';

$sql = "SELECT * FROM Huespedes";
if ($busqueda != '') {
	$sql .= " WHERE dni_pasaporte LIKE '%$busqueda%' OR nombre LIKE '%$busqueda%' OR apellidos LIKE '%$busqueda%'";
}
$sql .= " ORDER BY apellidos ASC";

$resultado = mysqli_query($conexion, $sql);
?>

<!DOCTYPE html>
<html lang="es">
	<head>
		<meta charset="UTF-8">
		<title>Gestión de Huéspedes - PMS</title>
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
			<span class="page-title">Base de Datos de Huéspedes</span>
		</nav>

		<div class="container mt-4">
			<div class="card shadow-sm border-0">
				<div class="card-body p-4">
					<div class="row mb-4 align-items-center">
						<div class="col-md-6">
							<h4 class="fw-bold"><i class="bi bi-people-fill me-2"></i>Listado de Clientes</h4>
						</div>
						<div class="col-md-6">
							<form action="" method="GET" class="d-flex">
								<input type="text" name="buscar" class="form-control me-2" 
									placeholder="Buscar por DNI o Nombre..." value="<?php echo $busqueda; ?>">
								<button type="submit" class="btn btn-primary">
									<i class="bi bi-search"></i>
								</button>
								<?php if($busqueda != ''): ?>
									<a href="huespedes.php" class="btn btn-outline-secondary ms-2">Limpiar</a>
								<?php endif; ?>
							</form>
						</div>
					</div>

					<div class="table-responsive">
						<table class="table table-hover align-middle">
							<thead class="table-light">
								<tr>
									<th class="ps-3">DNI / Pasaporte</th>
									<th>Nombre Completo</th>
									<th>Email</th>
								</tr>
							</thead>
							<tbody>
								<?php if(mysqli_num_rows($resultado) > 0): ?>
									<?php while($h = mysqli_fetch_assoc($resultado)): ?>
									<tr>
										<td class="ps-3 fw-bold"><?php echo $h['dni_pasaporte']; ?></td>
										<td><?php echo $h['nombre'] . " " . $h['apellidos']; ?></td>
										<td><?php echo $h['email']; ?></td>
									</tr>
									<?php endwhile; ?>
								<?php else: ?>
									<tr>
										<td colspan="4" class="text-center py-4 text-muted">
											No se encontraron huéspedes con ese criterio.
										</td>
									</tr>
								<?php endif; ?>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>