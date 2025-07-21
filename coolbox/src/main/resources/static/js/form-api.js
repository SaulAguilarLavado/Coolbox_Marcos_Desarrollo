// Funciones para manejar Sugerencias
async function getAllSugerencias() {
    try {
        const response = await fetch('/api/sugerencias');
        if (!response.ok) throw new Error('Error al obtener sugerencias');
        return await response.json();
    } catch (error) {
        console.error('Error:', error);
        throw error;
    }
}

async function getSugerenciaById(id) {
    try {
        const response = await fetch(`/api/sugerencias/${id}`);
        if (!response.ok) throw new Error('Error al obtener la sugerencia');
        return await response.json();
    } catch (error) {
        console.error('Error:', error);
        throw error;
    }
}

async function updateSugerencia(id, sugerenciaData) {
    try {
        const response = await fetch(`/api/sugerencias/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(sugerenciaData)
        });
        if (!response.ok) throw new Error('Error al actualizar la sugerencia');
        return await response.json();
    } catch (error) {
        console.error('Error:', error);
        throw error;
    }
}

// Funciones para manejar Reclamaciones
async function getAllReclamaciones() {
    try {
        const response = await fetch('/api/reclamaciones');
        if (!response.ok) throw new Error('Error al obtener reclamaciones');
        return await response.json();
    } catch (error) {
        console.error('Error:', error);
        throw error;
    }
}

async function getReclamacionById(id) {
    try {
        const response = await fetch(`/api/reclamaciones/${id}`);
        if (!response.ok) throw new Error('Error al obtener la reclamación');
        return await response.json();
    } catch (error) {
        console.error('Error:', error);
        throw error;
    }
}

async function updateReclamacion(id, reclamacionData) {
    try {
        const response = await fetch(`/api/reclamaciones/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(reclamacionData)
        });
        if (!response.ok) throw new Error('Error al actualizar la reclamación');
        return await response.json();
    } catch (error) {
        console.error('Error:', error);
        throw error;
    }
}


// Obtener todas las sugerencias
getAllSugerencias()
    .then(sugerencias => console.log('Sugerencias:', sugerencias))
    .catch(error => console.error('Error:', error));

// Obtener una sugerencia específica
getSugerenciaById(1)
    .then(sugerencia => console.log('Sugerencia:', sugerencia))
    .catch(error => console.error('Error:', error));

// Actualizar una sugerencia
const sugerenciaData = {
    name: "Nuevo Nombre",
    dni: "12345678",
    suggestion: "Nueva sugerencia",
    categoria: "producto"
};
updateSugerencia(1, sugerenciaData)
    .then(updatedSugerencia => console.log('Sugerencia actualizada:', updatedSugerencia))
    .catch(error => console.error('Error:', error));
