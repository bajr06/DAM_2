document.getElementById('inputBuscar').addEventListener('input', function() {
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