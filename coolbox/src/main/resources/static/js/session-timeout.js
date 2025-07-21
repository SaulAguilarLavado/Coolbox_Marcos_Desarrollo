// Sistema de Timeout de Sesión con Bearer Token y Listener Robusto
console.log('🚀 Sistema de timeout iniciado - Con Bearer Token');

// Variables globales
let inactivityTimer = null;
let warningTimer = null;
let countdownTimer = null;
let lastActivity = Date.now();

// Configuración de tiempos (1 minuto)
const TIMEOUT_DURATION = 60000; // 1 minuto
const WARNING_DURATION = 45000; // 45 segundos
const COUNTDOWN_DURATION = 15000; // 15 segundos
const CHECK_INTERVAL = 10000; // Verificar cada 10 segundos

// Eventos que resetean el timer
const RESET_EVENTS = [
    'mousedown', 'mousemove', 'keypress', 'scroll', 'touchstart', 'click',
    'focus', 'blur', 'input', 'change', 'submit', 'wheel', 'drag', 'drop'
];

// Función para obtener el Bearer Token
function getBearerToken() {
    // Buscar token en localStorage
    const token = localStorage.getItem('jwt_token') || 
                  sessionStorage.getItem('jwt_token') ||
                  getCookie('jwt_token');
    
    console.log('🔑 Bearer Token encontrado:', token ? 'Sí' : 'No');
    return token;
}

// Función para obtener cookie
function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
    return null;
}

// Función para verificar si hay usuario logueado usando Bearer Token
function isUserLoggedIn() {
    const token = getBearerToken();
    const logoutForm = document.querySelector('form[action="/logout"]');
    const isLoggedIn = token !== null || logoutForm !== null;
    
    console.log('🔍 Usuario logueado (Bearer):', isLoggedIn);
    return isLoggedIn;
}

// Función para crear el modal de advertencia
function createWarningModal() {
    const existingModal = document.getElementById('sessionWarningModal');
    if (existingModal) {
        existingModal.remove();
    }
    
    const modal = document.createElement('div');
    modal.id = 'sessionWarningModal';
    modal.className = 'modal fade';
    modal.setAttribute('data-bs-backdrop', 'static');
    modal.setAttribute('data-bs-keyboard', 'false');
    modal.innerHTML = `
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header bg-warning">
                    <h5 class="modal-title">
                        <i class="bi bi-exclamation-triangle-fill me-2"></i>
                        Sesión por expirar
                    </h5>
                </div>
                <div class="modal-body text-center">
                    <p class="mb-3">Tu sesión expirará en <strong id="countdownTimer">15</strong> segundos por inactividad.</p>
                    <div class="progress mb-3">
                        <div id="countdownProgress" class="progress-bar bg-warning" role="progressbar" style="width: 100%"></div>
                    </div>
                    <p class="text-muted small">¿Deseas continuar con tu sesión?</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" onclick="logoutDueToInactivity()">
                        <i class="bi bi-box-arrow-right me-1"></i>
                        Cerrar sesión
                    </button>
                    <button type="button" class="btn btn-primary" onclick="extendSession()">
                        <i class="bi bi-arrow-clockwise me-1"></i>
                        Continuar sesión
                    </button>
                </div>
            </div>
        </div>
    `;
    
    document.body.appendChild(modal);
    return modal;
}

// Función para mostrar la advertencia
function showTimeoutWarning() {
    console.log('⚠️ Mostrando advertencia de timeout');
    
    const modal = createWarningModal();
    const modalInstance = new bootstrap.Modal(modal);
    modalInstance.show();
    
    let timeLeft = COUNTDOWN_DURATION / 1000;
    const countdownElement = document.getElementById('countdownTimer');
    const progressElement = document.getElementById('countdownProgress');
    
    countdownTimer = setInterval(() => {
        timeLeft--;
        console.log('⏰ Countdown:', timeLeft);
        
        if (countdownElement) {
            countdownElement.textContent = timeLeft;
        }
        if (progressElement) {
            const progress = (timeLeft / (COUNTDOWN_DURATION / 1000)) * 100;
            progressElement.style.width = progress + '%';
        }
        
        if (timeLeft <= 0) {
            console.log('🚨 Countdown terminado, cerrando sesión');
            clearInterval(countdownTimer);
            modalInstance.hide();
            logoutDueToInactivity();
        }
    }, 1000);
}

// Función para extender la sesión
function extendSession() {
    console.log('✅ Extendiendo sesión');
    
    if (countdownTimer) {
        clearInterval(countdownTimer);
    }
    
    const modal = document.getElementById('sessionWarningModal');
    if (modal) {
        const modalInstance = bootstrap.Modal.getInstance(modal);
        if (modalInstance) {
            modalInstance.hide();
        }
    }
    
    // Actualizar actividad
    lastActivity = Date.now();
    startTimers();
    alert('Sesión extendida exitosamente');
}

// Función para cerrar sesión con Bearer Token
function logoutDueToInactivity() {
    console.log('🚪 Cerrando sesión por inactividad');
    
    if (countdownTimer) {
        clearInterval(countdownTimer);
    }
    
    const modal = document.getElementById('sessionWarningModal');
    if (modal) {
        const modalInstance = bootstrap.Modal.getInstance(modal);
        if (modalInstance) {
            modalInstance.hide();
        }
    }
    
    alert('Tu sesión ha expirado por inactividad. Serás redirigido al inicio.');
    
    // Limpiar token
    localStorage.removeItem('jwt_token');
    sessionStorage.removeItem('jwt_token');
    document.cookie = 'jwt_token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;';
    
    // Enviar petición de logout con Bearer Token
    const token = getBearerToken();
    const headers = {
        'Content-Type': 'application/json'
    };
    
    if (token) {
        headers['Authorization'] = `Bearer ${token}`;
    }
    
    fetch('/logout', {
        method: 'POST',
        credentials: 'same-origin',
        headers: headers
    }).then(() => {
        console.log('✅ Logout exitoso');
        window.location.href = '/?expired=true';
    }).catch((error) => {
        console.error('❌ Error en logout:', error);
        window.location.href = '/?expired=true';
    });
}

// Listener robusto para detectar actividad del usuario
function setupActivityListener() {
    console.log('👂 Configurando listener de actividad...');
    
    // Función para registrar actividad
    function registerActivity() {
        const now = Date.now();
        const timeSinceLastActivity = now - lastActivity;
        
        if (timeSinceLastActivity > 1000) { // Solo registrar si han pasado más de 1 segundo
            console.log('🖱️ Actividad detectada, reseteando timers');
            lastActivity = now;
            resetTimers();
        }
    }
    
    // Agregar listeners para todos los eventos
    RESET_EVENTS.forEach(event => {
        document.addEventListener(event, registerActivity, true);
        window.addEventListener(event, registerActivity, true);
    });
    
    // Listener para cambios de visibilidad de la página
    document.addEventListener('visibilitychange', () => {
        if (!document.hidden) {
            console.log('👁️ Página visible, verificando actividad');
            registerActivity();
        }
    });
    
    // Listener para focus/blur de la ventana
    window.addEventListener('focus', registerActivity);
    window.addEventListener('blur', registerActivity);
    
    // Listener para cambios de tamaño de ventana
    window.addEventListener('resize', registerActivity);
    
    console.log('✅ Listener de actividad configurado');
}

// Función para iniciar los timers
function startTimers() {
    console.log('⏱️ Iniciando timers de inactividad');
    
    // Limpiar timers existentes
    clearTimeout(inactivityTimer);
    clearTimeout(warningTimer);
    if (countdownTimer) {
        clearInterval(countdownTimer);
    }
    
    // Solo iniciar si hay usuario logueado
    if (isUserLoggedIn()) {
        console.log('✅ Usuario logueado detectado, iniciando timers');
        
        // Timer de advertencia (45 segundos)
        warningTimer = setTimeout(() => {
            console.log('⏰ Timer de advertencia activado');
            showTimeoutWarning();
        }, WARNING_DURATION);
        
        // Timer de logout (1 minuto)
        inactivityTimer = setTimeout(() => {
            console.log('⏰ Timer de logout activado');
            logoutDueToInactivity();
        }, TIMEOUT_DURATION);
        
        console.log(`⏱️ Timers configurados: Warning en ${WARNING_DURATION/1000}s, Logout en ${TIMEOUT_DURATION/1000}s`);
    } else {
        console.log('❌ No hay usuario logueado, timers no iniciados');
    }
}

// Función para resetear los timers
function resetTimers() {
    console.log('🔄 Reseteando timers por actividad del usuario');
    startTimers();
}

// Función para verificar estado periódicamente
function checkSessionState() {
    const now = Date.now();
    const timeSinceLastActivity = now - lastActivity;
    
    console.log(`🔍 Verificando estado de sesión - Última actividad: ${Math.round(timeSinceLastActivity/1000)}s`);
    
    // Si no hay usuario logueado, limpiar timers
    if (!isUserLoggedIn()) {
        console.log('❌ Usuario no logueado, limpiando timers');
        clearTimeout(inactivityTimer);
        clearTimeout(warningTimer);
        if (countdownTimer) {
            clearInterval(countdownTimer);
        }
        return;
    }
    
    // Si hay usuario logueado pero no hay timers activos, iniciarlos
    if (!inactivityTimer && !warningTimer) {
        console.log('✅ Usuario logueado sin timers, iniciando...');
        startTimers();
    }
    
    // Verificar si ha habido actividad reciente
    if (timeSinceLastActivity > TIMEOUT_DURATION) {
        console.log('⚠️ Demasiado tiempo sin actividad, cerrando sesión');
        logoutDueToInactivity();
    }
}

// Inicialización cuando se carga la página
document.addEventListener('DOMContentLoaded', function() {
    console.log('📄 Página cargada, inicializando sistema de timeout con Bearer Token');
    
    // Configurar listener de actividad
    setupActivityListener();
    
    // Detectar cuando se envían formularios de login
    const loginForms = document.querySelectorAll('form[action="/login"], form[action="/registro"]');
    loginForms.forEach(form => {
        form.addEventListener('submit', () => {
            console.log('📝 Formulario enviado, reseteando timers en 2 segundos');
            setTimeout(startTimers, 2000);
        });
    });
    
    // Iniciar timers iniciales
    setTimeout(startTimers, 1000);
    
    // Verificar estado cada 10 segundos
    setInterval(checkSessionState, CHECK_INTERVAL);
    
    console.log('✅ Sistema de timeout con Bearer Token inicializado correctamente');
});

// Limpiar timers cuando se va la página
window.addEventListener('beforeunload', function() {
    console.log('🧹 Limpiando timers al salir');
    clearTimeout(inactivityTimer);
    clearTimeout(warningTimer);
    if (countdownTimer) {
        clearInterval(countdownTimer);
    }
});

// Función de debug global
window.debugSessionTimeout = function() {
    console.log('🔍 DEBUG - Estado del sistema:');
    console.log('Usuario logueado:', isUserLoggedIn());
    console.log('Bearer Token:', getBearerToken() ? 'Presente' : 'No encontrado');
    console.log('Última actividad:', Math.round((Date.now() - lastActivity)/1000) + ' segundos');
    console.log('Timers activos:', {
        inactivityTimer: inactivityTimer !== null,
        warningTimer: warningTimer !== null,
        countdownTimer: countdownTimer !== null
    });
    console.log('Configuración:', {
        TIMEOUT_DURATION: TIMEOUT_DURATION + 'ms (' + (TIMEOUT_DURATION/1000/60) + ' minutos)',
        WARNING_DURATION: WARNING_DURATION + 'ms (' + (WARNING_DURATION/1000/60) + ' minutos)',
        COUNTDOWN_DURATION: COUNTDOWN_DURATION + 'ms (' + (COUNTDOWN_DURATION/1000) + ' segundos)',
        CHECK_INTERVAL: CHECK_INTERVAL + 'ms'
    });
};

// Función para probar inmediatamente
window.testTimeoutNow = function() {
    console.log('🧪 Probando timeout inmediatamente');
    showTimeoutWarning();
};

console.log('✅ Sistema de timeout con Bearer Token cargado - 1 minuto de inactividad');