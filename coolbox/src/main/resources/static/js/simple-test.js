// Script de prueba simple para verificar el sistema de timeout
console.log('🧪 PRUEBA SIMPLE DEL SISTEMA DE TIMEOUT');

// Función simple para verificar si Bootstrap está disponible
function checkBootstrap() {
    console.log('🔍 Verificando Bootstrap...');
    if (typeof bootstrap !== 'undefined') {
        console.log('✅ Bootstrap está disponible');
        console.log('Modal disponible:', typeof bootstrap.Modal !== 'undefined');
        console.log('Toast disponible:', typeof bootstrap.Toast !== 'undefined');
        return true;
    } else {
        console.log('❌ Bootstrap NO está disponible');
        return false;
    }
}

// Función simple para verificar si hay usuario logueado
function checkUserLoggedIn() {
    console.log('🔍 Verificando usuario logueado...');
    
    const logoutButton = document.querySelector('form[action="/logout"]');
    const welcomeMessage = document.querySelector('[data-user-logged="true"]');
    const navbarText = document.querySelector('.navbar .text-white');
    const hasWelcomeText = navbarText && navbarText.textContent.includes('Bienvenido');
    
    console.log('Elementos encontrados:', {
        logoutButton: logoutButton !== null,
        welcomeMessage: welcomeMessage !== null,
        hasWelcomeText: hasWelcomeText,
        navbarText: navbarText ? navbarText.textContent.substring(0, 50) + '...' : 'No encontrado'
    });
    
    const isLoggedIn = logoutButton !== null || welcomeMessage !== null || hasWelcomeText;
    console.log('Usuario logueado:', isLoggedIn);
    return isLoggedIn;
}

// Función simple para crear un modal de prueba
function createTestModal() {
    console.log('🔧 Creando modal de prueba...');
    
    const modal = document.createElement('div');
    modal.id = 'testModal';
    modal.className = 'modal fade';
    modal.innerHTML = `
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Prueba de Modal</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    <p>Este es un modal de prueba para verificar que Bootstrap funciona.</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                </div>
            </div>
        </div>
    `;
    
    document.body.appendChild(modal);
    
    try {
        const modalInstance = new bootstrap.Modal(modal);
        modalInstance.show();
        console.log('✅ Modal de prueba mostrado correctamente');
        
        // Remover modal después de cerrarse
        modal.addEventListener('hidden.bs.modal', () => {
            modal.remove();
        });
        
        return true;
    } catch (error) {
        console.error('❌ Error al mostrar modal:', error);
        modal.remove();
        return false;
    }
}

// Función para probar el sistema completo
function runSimpleTest() {
    console.log('🚀 Iniciando prueba simple...');
    
    // Verificar Bootstrap
    const bootstrapOk = checkBootstrap();
    
    // Verificar usuario
    const userOk = checkUserLoggedIn();
    
    // Probar modal
    const modalOk = createTestModal();
    
    console.log('📊 Resultados de la prueba:');
    console.log('- Bootstrap:', bootstrapOk ? '✅ OK' : '❌ FALLA');
    console.log('- Usuario logueado:', userOk ? '✅ OK' : '❌ FALLA');
    console.log('- Modal:', modalOk ? '✅ OK' : '❌ FALLA');
    
    // Mostrar resultados en pantalla
    const resultsDiv = document.createElement('div');
    resultsDiv.style.cssText = `
        position: fixed;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        background: white;
        border: 2px solid #333;
        padding: 20px;
        border-radius: 10px;
        z-index: 10001;
        font-family: monospace;
        font-size: 14px;
        box-shadow: 0 10px 30px rgba(0,0,0,0.3);
    `;
    
    resultsDiv.innerHTML = `
        <h4>🧪 Resultados de Prueba</h4>
        <p><strong>Bootstrap:</strong> ${bootstrapOk ? '✅ OK' : '❌ FALLA'}</p>
        <p><strong>Usuario logueado:</strong> ${userOk ? '✅ OK' : '❌ FALLA'}</p>
        <p><strong>Modal:</strong> ${modalOk ? '✅ OK' : '❌ FALLA'}</p>
        <button onclick="this.parentElement.remove()" style="margin-top: 10px; padding: 5px 10px;">Cerrar</button>
    `;
    
    document.body.appendChild(resultsDiv);
    
    return { bootstrapOk, userOk, modalOk };
}

// Función para probar timeout inmediatamente
function testTimeoutImmediately() {
    console.log('⚡ Probando timeout inmediatamente...');
    
    if (typeof showTimeoutWarning === 'function') {
        console.log('✅ Función showTimeoutWarning encontrada');
        showTimeoutWarning();
    } else {
        console.log('❌ Función showTimeoutWarning NO encontrada');
        alert('Error: La función showTimeoutWarning no está disponible');
    }
}

// Hacer funciones globales
window.runSimpleTest = runSimpleTest;
window.testTimeoutImmediately = testTimeoutImmediately;
window.checkBootstrap = checkBootstrap;
window.checkUserLoggedIn = checkUserLoggedIn;

// Ejecutar prueba automáticamente después de 3 segundos
setTimeout(() => {
    console.log('🔄 Ejecutando prueba automática...');
    runSimpleTest();
}, 3000);

console.log('✅ Script de prueba simple cargado');
console.log('Comandos disponibles:');
console.log('- runSimpleTest(): Ejecutar prueba completa');
console.log('- testTimeoutImmediately(): Probar timeout inmediatamente');
console.log('- checkBootstrap(): Verificar Bootstrap');
console.log('- checkUserLoggedIn(): Verificar usuario'); 