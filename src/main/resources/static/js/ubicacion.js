document.addEventListener('DOMContentLoaded', function() {
    const ciudadSelect = document.getElementById('ciudad');
    const distritoSelect = document.getElementById('distrito');

    if (ciudadSelect && distritoSelect) {
        // Cargar ciudades al iniciar
        fetch('/api/ciudades')
            .then(response => response.json())
            .then(ciudades => {
                ciudades.forEach(ciudad => {
                    const option = document.createElement('option');
                    option.value = ciudad;
                    option.textContent = ciudad;
                    ciudadSelect.appendChild(option);
                });
            });

        // Actualizar distritos cuando se selecciona una ciudad
        ciudadSelect.addEventListener('change', function() {
            const ciudad = this.value;
            distritoSelect.innerHTML = '<option value="" selected disabled>Seleccione un distrito</option>';
            
            if (ciudad) {
                fetch(`/api/distritos?ciudad=${encodeURIComponent(ciudad)}`)
                    .then(response => response.json())
                    .then(distritos => {
                        distritos.forEach(distrito => {
                            const option = document.createElement('option');
                            option.value = distrito;
                            option.textContent = distrito;
                            distritoSelect.appendChild(option);
                        });
                    });
            }
        });
    }
}); 