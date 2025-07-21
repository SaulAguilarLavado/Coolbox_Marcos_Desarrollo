// Script para verificar errores específicos en el sistema de timeout
console.log('🔍 VERIFICADOR DE ERRORES ACTIVADO');

// Verificar errores de JavaScript
window.addEventListener('error', function(e) {
    console.error('🚨 ERROR DE JAVASCRIPT DETECTADO:', {
        message: e.message,
        filename: e.filename,
        lineno: e.lineno,
        colno: e.colno,
        error: e.error
    });
});

// Verificar errores de recursos
window.addEventListener('error', function(e) {
    if (e.target && e.target.tagName) {
        console.error('🚨 ERROR DE RECURSO:', {
            tagName: e.target.tagName,
            src: e.target.src,
            href: e.target.href
        });
    }
}, true);

// Verificar si las funciones del sistema de timeout están disponibles
function checkTimeoutFunctions() {
    console.log('🔍 Verificando funciones del sistema de timeout...');
    
    const functions = [
        'isUserLoggedIn',
        'showTimeoutWarning',
        'extendSession',
        'logoutDueToInactivity',
        'resetInactivityTimer',
        'createWarningModal',
        'showToast'
    ];
    
    const results = {};
    
    functions.forEach(funcName => {
        const isAvailable = typeof window[funcName] === 'function';
        results[funcName] = isAvailable;
        console.log(`${funcName}: ${isAvailable ? '✅ Disponible' : '❌ No disponible'}`);
    });
    
    return results;
}

// Verificar variables globales del sistema
function checkGlobalVariables() {
    console.log('🔍 Verificando variables globales...');
    
    const variables = [
        'inactivityTimer',
        'warningTimer',
        'countdownTimer',
        'TIMEOUT_DURATION',
        'WARNING_DURATION',
        'COUNTDOWN_DURATION'
    ];
    
    const results = {};
    
    variables.forEach(varName => {
        const isAvailable = window[varName] !== undefined;
        const value = window[varName];
        results[varName] = { available: isAvailable, value: value };
        console.log(`${varName}: ${isAvailable ? '✅ Disponible' : '❌ No disponible'} = ${value}`);
    });
    
    return results;
}

// Verificar elementos del DOM necesarios
function checkDOMElements() {
    console.log('🔍 Verificando elementos del DOM...');
    
    const elements = [
        { selector: 'form[action="/logout"]', name: 'Formulario de logout' },
        { selector: '[data-user-logged="true"]', name: 'Elemento de usuario logueado' },
        { selector: '.navbar .text-white', name: 'Texto de bienvenida' },
        { selector: 'body', name: 'Elemento body' }
    ];
    
    const results = {};
    
    elements.forEach(element => {
        const found = document.querySelector(element.selector);
        results[element.name] = found !== null;
        console.log(`${element.name}: ${found !== null ? '✅ Encontrado' : '❌ No encontrado'}`);
        if (found) {
            console.log(`  - Texto: "${found.textContent.substring(0, 50)}..."`);
        }
    });
    
    return results;
}

// Función principal de verificación
function runErrorCheck() {
    console.log('🚀 Iniciando verificación de errores...');
    
    const functionResults = checkTimeoutFunctions();
    const variableResults = checkGlobalVariables();
    const domResults = checkDOMElements();
    
    // Mostrar resumen
    console.log('📊 RESUMEN DE VERIFICACIÓN:');
    console.log('Funciones disponibles:', Object.values(functionResults).filter(Boolean).length + '/' + Object.keys(functionResults).length);
    console.log('Variables disponibles:', Object.values(variableResults).filter(r => r.available).length + '/' + Object.keys(variableResults).length);
    console.log('Elementos DOM encontrados:', Object.values(domResults).filter(Boolean).length + '/' + Object.keys(domResults).length);
    
    // Crear panel de resultados
    const panel = document.createElement('div');
    panel.id = 'errorCheckPanel';
    panel.style.cssText = `
        position: fixed;
        top: 20px;
        right: 20px;
        background: #f8f9fa;
        border: 2px solid #dee2e6;
        padding: 15px;
        border-radius: 8px;
        font-family: monospace;
        font-size: 12px;
        z-index: 10002;
        max-width: 400px;
        max-height: 80vh;
        overflow-y: auto;
    `;
    
    const functionStatus = Object.values(functionResults).filter(Boolean).length + '/' + Object.keys(functionResults).length;
    const variableStatus = Object.values(variableResults).filter(r => r.available).length + '/' + Object.keys(variableResults).length;
    const domStatus = Object.values(domResults).filter(Boolean).length + '/' + Object.keys(domResults).length;
    
    panel.innerHTML = `
        <h5 style="margin: 0 0 10px 0; color: #495057;">🔍 Verificación de Errores</h5>
        <div style="margin-bottom: 8px;">
            <strong>Funciones:</strong> ${functionStatus} ✅
        </div>
        <div style="margin-bottom: 8px;">
            <strong>Variables:</strong> ${variableStatus} ✅
        </div>
        <div style="margin-bottom: 8px;">
            <strong>Elementos DOM:</strong> ${domStatus} ✅
        </div>
        <div style="margin-top: 10px;">
            <button onclick="testTimeoutImmediately()" style="padding: 4px 8px; margin-right: 5px; font-size: 10px;">
                🧪 Test Timeout
            </button>
            <button onclick="this.parentElement.parentElement.remove()" style="padding: 4px 8px; font-size: 10px;">
                ❌ Cerrar
            </button>
        </div>
    `;
    
    // Remover panel existente
    const existingPanel = document.getElementById('errorCheckPanel');
    if (existingPanel) {
        existingPanel.remove();
    }
    
    document.body.appendChild(panel);
    
    return { functionResults, variableResults, domResults };
}

// Hacer función global
window.runErrorCheck = runErrorCheck;

// Ejecutar verificación automáticamente
setTimeout(() => {
    console.log('🔄 Ejecutando verificación automática de errores...');
    runErrorCheck();
}, 2000);

console.log('✅ Verificador de errores cargado');
console.log('Comando disponible: runErrorCheck()'); 