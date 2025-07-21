// Script de debug temporal para verificar el sistema de timeout
console.log('🔧 SCRIPT DE DEBUG ACTIVADO');

// Función para simular inactividad inmediatamente
function testTimeout() {
    console.log('🧪 Simulando timeout inmediatamente...');
    
    // Limpiar todos los timers existentes
    if (window.inactivityTimer) {
        clearTimeout(window.inactivityTimer);
        console.log('✅ Timer de inactividad limpiado');
    }
    if (window.warningTimer) {
        clearTimeout(window.warningTimer);
        console.log('✅ Timer de advertencia limpiado');
    }
    if (window.countdownTimer) {
        clearInterval(window.countdownTimer);
        console.log('✅ Timer de countdown limpiado');
    }
    
    // Activar la advertencia inmediatamente
    if (typeof showTimeoutWarning === 'function') {
        console.log('🚀 Activando advertencia de timeout...');
        showTimeoutWarning();
    } else {
        console.log('❌ ERROR: Función showTimeoutWarning no encontrada');
    }
}

// Función para mostrar información del estado
function showStatus() {
    console.log('📊 ESTADO ACTUAL DEL SISTEMA:');
    console.log('Usuario logueado:', typeof isUserLoggedIn === 'function' ? isUserLoggedIn() : 'Función no disponible');
    console.log('Timers activos:', {
        inactivityTimer: window.inactivityTimer !== undefined,
        warningTimer: window.warningTimer !== undefined,
        countdownTimer: window.countdownTimer !== undefined
    });
    
    // Mostrar información visual en la página
    const statusDiv = document.createElement('div');
    statusDiv.id = 'debugStatus';
    statusDiv.style.cssText = `
        position: fixed;
        top: 10px;
        left: 10px;
        background: rgba(0,0,0,0.9);
        color: white;
        padding: 15px;
        border-radius: 8px;
        font-family: monospace;
        font-size: 12px;
        z-index: 10000;
        max-width: 350px;
        border: 2px solid #ff6b6b;
    `;
    
    const isLoggedIn = typeof isUserLoggedIn === 'function' ? isUserLoggedIn() : 'N/A';
    statusDiv.innerHTML = `
        <div style="margin-bottom: 10px;">
            <strong style="color: #ff6b6b;">🔧 DEBUG TIMEOUT</strong>
        </div>
        <div style="margin-bottom: 5px;">
            <span style="color: #74c0fc;">Usuario:</span> ${isLoggedIn ? '✅ Logueado' : '❌ No logueado'}
        </div>
        <div style="margin-bottom: 5px;">
            <span style="color: #74c0fc;">Timers:</span> ${window.inactivityTimer ? '✅ Activo' : '❌ Inactivo'}
        </div>
        <div style="margin-bottom: 10px;">
            <span style="color: #74c0fc;">Config:</span> 3 min timeout
        </div>
        <div style="display: flex; gap: 5px;">
            <button onclick="testTimeout()" style="padding: 5px 10px; background: #ff6b6b; color: white; border: none; border-radius: 3px; font-size: 10px; cursor: pointer;">
                🧪 Test Timeout
            </button>
            <button onclick="showStatus()" style="padding: 5px 10px; background: #74c0fc; color: white; border: none; border-radius: 3px; font-size: 10px; cursor: pointer;">
                🔄 Refresh
            </button>
            <button onclick="this.parentElement.parentElement.remove()" style="padding: 5px 10px; background: #868e96; color: white; border: none; border-radius: 3px; font-size: 10px; cursor: pointer;">
                ❌ Cerrar
            </button>
        </div>
    `;
    
    // Remover panel existente
    const existingPanel = document.getElementById('debugStatus');
    if (existingPanel) {
        existingPanel.remove();
    }
    
    document.body.appendChild(statusDiv);
}

// Función para activar modo debug
function enableDebugMode() {
    console.log('🔧 Modo debug activado');
    
    // Hacer funciones globales
    window.testTimeout = testTimeout;
    window.showStatus = showStatus;
    window.enableDebugMode = enableDebugMode;
    
    // Mostrar panel de debug
    showStatus();
    
    // Agregar botón flotante
    const debugButton = document.createElement('button');
    debugButton.textContent = '🔧 Debug';
    debugButton.style.cssText = `
        position: fixed;
        bottom: 20px;
        right: 20px;
        background: #ff6b6b;
        color: white;
        border: none;
        padding: 12px 16px;
        border-radius: 8px;
        cursor: pointer;
        z-index: 10000;
        font-size: 14px;
        font-weight: bold;
        box-shadow: 0 4px 12px rgba(0,0,0,0.3);
    `;
    debugButton.onclick = showStatus;
    document.body.appendChild(debugButton);
    
    console.log('✅ Panel de debug activado');
    console.log('Comandos disponibles:');
    console.log('- testTimeout(): Simula timeout inmediatamente');
    console.log('- showStatus(): Muestra estado actual');
    console.log('- debugSessionTimeout(): Debug del sistema original');
}

// Activar automáticamente en desarrollo
if (window.location.hostname === 'localhost' || window.location.hostname === '127.0.0.1') {
    setTimeout(enableDebugMode, 2000);
}

console.log('Para activar debug manualmente, ejecuta: enableDebugMode()'); 