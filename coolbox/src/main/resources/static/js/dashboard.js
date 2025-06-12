let tipoActual = 'sugerencias'; // o 'reclamaciones'
let editModal;

// Cargar datos al iniciar
document.addEventListener('DOMContentLoaded', function() {
    editModal = new bootstrap.Modal(document.getElementById('editModal'));
    cargarDatos('sugerencias');
    cargarDatos('reclamaciones');
});

// Cambiar entre pestañas
document.querySelectorAll('button[data-bs-toggle="tab"]').forEach(tab => {
    tab.addEventListener('shown.bs.tab', function (e) {
        tipoActual = e.target.id.split('-')[0];
    });
});

// Buscar sugerencia por ID
async function buscarSugerenciaPorId() {
    const id = document.getElementById('sugerenciaId').value;
    if (!id) {
        alert('Por favor ingrese un ID');
        return;
    }

    try {
        const response = await fetch(`/api/sugerencias/${id}`);
        if (!response.ok) {
            throw new Error('Sugerencia no encontrada');
        }
        const data = await response.json();

        const detallesDiv = document.getElementById('sugerenciaDetalles');
        detallesDiv.innerHTML = `
            <p><strong>ID:</strong> ${data.id}</p>
            <p><strong>Nombre:</strong> ${data.name}</p>
            <p><strong>DNI:</strong> ${data.dni}</p>
            <p><strong>Categoría:</strong> ${data.categoria}</p>
            <p><strong>Sugerencia:</strong> ${data.suggestion}</p>
            <p><strong>Fecha:</strong> ${new Date(data.fechaCreacion).toLocaleDateString()}</p>
            <div class="mt-3">
                <button class="btn btn-primary btn-sm" onclick="editar(${data.id})">Editar</button>
                <button class="btn btn-danger btn-sm" onclick="eliminar(${data.id})">Eliminar</button>
            </div>
        `;

        document.getElementById('sugerenciaIndividual').classList.remove('d-none');
    } catch (error) {
        console.error('Error:', error);
        alert('Error al buscar la sugerencia');
    }
}

// Buscar reclamación por ID
async function buscarReclamacionPorId() {
    const id = document.getElementById('reclamacionId').value;
    if (!id) {
        alert('Por favor ingrese un ID');
        return;
    }

    try {
        const response = await fetch(`/api/reclamaciones/${id}`);
        if (!response.ok) {
            throw new Error('Reclamación no encontrada');
        }
        const data = await response.json();

        const detallesDiv = document.getElementById('reclamacionDetalles');
        detallesDiv.innerHTML = `
            <p><strong>ID:</strong> ${data.id}</p>
            <p><strong>Nombre:</strong> ${data.name}</p>
            <p><strong>DNI:</strong> ${data.dni}</p>
            <p><strong>Categoría:</strong> ${data.categoria}</p>
            <p><strong>Reclamación:</strong> ${data.complaint}</p>
            <p><strong>Fecha:</strong> ${new Date(data.fechaCreacion).toLocaleDateString()}</p>
            <div class="mt-3">
                <button class="btn btn-primary btn-sm" onclick="editar(${data.id})">Editar</button>
                <button class="btn btn-danger btn-sm" onclick="eliminar(${data.id})">Eliminar</button>
            </div>
        `;

        document.getElementById('reclamacionIndividual').classList.remove('d-none');
    } catch (error) {
        console.error('Error:', error);
        alert('Error al buscar la reclamación');
    }
}

// Cargar datos
async function cargarDatos(tipo) {
    try {
        const response = await fetch(`/api/${tipo}`);
        const data = await response.json();
        const tbody = document.getElementById(`${tipo}TableBody`);
        tbody.innerHTML = '';

        // Ocultar la vista individual si está visible
        if (tipo === 'sugerencias') {
            document.getElementById('sugerenciaIndividual').classList.add('d-none');
        } else {
            document.getElementById('reclamacionIndividual').classList.add('d-none');
        }

        data.forEach(item => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${item.id}</td>
                <td>${item.name}</td>
                <td>${item.dni}</td>
                <td>${item.categoria}</td>
                <td>${tipo === 'sugerencias' ? item.suggestion : item.complaint}</td>
                <td>${new Date(item.fechaCreacion).toLocaleDateString()}</td>
                <td>
                    <button class="btn btn-sm btn-primary" onclick="editar(${item.id})">Editar</button>
                    <button class="btn btn-sm btn-danger" onclick="eliminar(${item.id})">Eliminar</button>
                </td>
            `;
            tbody.appendChild(tr);
        });
    } catch (error) {
        console.error('Error:', error);
        alert('Error al cargar los datos');
    }
}

// Editar
async function editar(id) {
    try {
        const response = await fetch(`/api/${tipoActual}/${id}`);
        const data = await response.json();

        document.getElementById('editId').value = data.id;
        document.getElementById('editName').value = data.name;
        document.getElementById('editDni').value = data.dni;
        document.getElementById('editCategoria').value = data.categoria;
        document.getElementById('editDescripcion').value =
            tipoActual === 'sugerencias' ? data.suggestion : data.complaint;

        editModal.show();
    } catch (error) {
        console.error('Error:', error);
        alert('Error al cargar los datos para editar');
    }
}

// Guardar cambios
async function guardarCambios() {
    const id = document.getElementById('editId').value;
    const data = {
        name: document.getElementById('editName').value,
        dni: document.getElementById('editDni').value,
        categoria: document.getElementById('editCategoria').value
    };

    if (tipoActual === 'sugerencias') {
        data.suggestion = document.getElementById('editDescripcion').value;
    } else {
        data.complaint = document.getElementById('editDescripcion').value;
    }

    try {
        const response = await fetch(`/api/${tipoActual}/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        if (response.ok) {
            editModal.hide();
            cargarDatos(tipoActual);
            alert('Cambios guardados correctamente');
        } else {
            throw new Error('Error al guardar');
        }
    } catch (error) {
        console.error('Error:', error);
        alert('Error al guardar los cambios');
    }
}

// Eliminar
async function eliminar(id) {
    if (confirm('¿Estás seguro de que deseas eliminar este registro?')) {
        try {
            const response = await fetch(`/api/${tipoActual}/${id}`, {
                method: 'DELETE'
            });

            if (response.ok) {
                cargarDatos(tipoActual);
                alert('Registro eliminado correctamente');
            } else {
                throw new Error('Error al eliminar');
            }
        } catch (error) {
            console.error('Error:', error);
            alert('Error al eliminar el registro');
        }
    }
}