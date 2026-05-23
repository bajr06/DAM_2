<?php
session_start();
include('../config/conexion.php');

if (!isset($_SESSION['empleado_id'])) {
	header("Location: ../index.php");
	exit();
}

$es_admin = (isset($_SESSION['rol']) && $_SESSION['rol'] === 'ADMINISTRADOR');

$sql = "SELECT * FROM Cuentas_empleado";
$resultado = mysqli_query($conexion, $sql);
?>

<!DOCTYPE html>
<html lang="es">
	<head>
		<meta charset="UTF-8">
		<title>Gestión de Staff - PMS</title>
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
			<span class="page-title">Administración de Personal</span>
		</nav>

		<div class="container mt-4">
			<?php if (isset($_GET['error']) && $_GET['error'] == 'permisos'): ?>
				<div class="alert alert-danger alert-dismissible fade show shadow-sm" role="alert">
					<i class="bi bi-exclamation-triangle-fill me-2"></i> Acceso denegado: No tienes permisos de Administrador para registrar personal.
				</div>
			<?php endif; ?>
			
			<?php if (isset($_GET['msg']) && $_GET['msg'] == 'ok'): ?>
				<div class="alert alert-success alert-dismissible fade show shadow-sm" role="alert">
					<i class="bi bi-check-circle-fill me-2"></i> Empleado registrado y cuenta de acceso creada correctamente.
				</div>
			<?php endif; ?>

			<div class="row">
				<?php if ($es_admin): ?>
				<div class="col-md-4">
					<div class="card shadow-sm border-0">
						<div class="card-body">
							<h5 class="fw-bold mb-3"><i class="bi bi-person-plus-fill me-2"></i>Nuevo Usuario</h5>
							<form action="../procesar_empleado.php" method="POST">
								<div class="mb-3">
									<label class="form-label small fw-bold">DNI EMPLEADO</label>
									<input type="text" name="dni" class="form-control" required placeholder="Ej: 12345678A">
								</div>
								<div class="mb-3">
									<label class="form-label small fw-bold">NOMBRE</label>
									<input type="text" name="nombre" class="form-control" required placeholder="Ej: Juan">
								</div>
								<div class="mb-3">
									<label class="form-label small fw-bold">APELLIDOS</label>
									<input type="text" name="apellidos" class="form-control" required placeholder="Ej: Pérez Gómez">
								</div>
								<div class="mb-3">
									<label class="form-label small fw-bold">EMAIL DE ACCESO</label>
									<input type="email" name="email" class="form-control" required placeholder="staff@hotel.com">
								</div>
								<div class="mb-3">
									<label class="form-label small fw-bold">CONTRASEÑA</label>
									<input type="password" name="password" class="form-control" required>
									<div class="form-text">Se encriptará automáticamente antes de guardarse.</div>
								</div>
								<button type="submit" class="btn btn-primary w-100 mt-2">Crear Cuenta de Acceso</button>
							</form>
						</div>
					</div>
				</div>
				<?php endif; ?>

				<div class="<?php echo $es_admin ? 'col-md-8' : 'col-md-12'; ?>">
					<div class="card shadow-sm border-0">
						<div class="card-body">
							<h5 class="fw-bold mb-3">Usuarios con Acceso</h5>
							<div class="table-responsive">
								<table class="table table-hover align-middle">
									<thead class="table-light">
										<tr>
											<th>DNI</th>
											<th>Email</th>
											<th class="text-center">Estado</th>
										</tr>
									</thead>
									<tbody>
										<?php while($user = mysqli_fetch_assoc($resultado)): ?>
										<tr>
											<td><?php echo $user['dni_empleado']; ?></td>
											<td><?php echo $user['email_acceso']; ?></td>
											<td class="text-center">
												<span class="badge bg-success">Activo</span>
											</td>
										</tr>
										<?php endwhile; ?>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>