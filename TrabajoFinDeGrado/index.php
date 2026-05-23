<!DOCTYPE html>
<html lang="es">
	<head>
		<meta charset="UTF-8">
		<title>Login - Gestión Hotelera</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
		<link rel="stylesheet" href="css/model.css">
	</head>
	<body>
		<div class="login-card card shadow">
			<div class="card-body">
				<h3 class="text-center mb-4">Acceso Recepción</h3>
				<form action="validar.php" method="POST">
					<div class="mb-3">
						<label class="form-label">Correo Electrónico</label>
						<input type="email" name="email" class="form-control" required>
					</div>
					<div class="mb-3">
						<label class="form-label">Contraseña</label>
						<input type="password" name="password" class="form-control" required>
					</div>
					<button type="submit" class="btn btn-primary w-100">Entrar</button>
				</form>
			</div>
		</div>
	</body>
</html>