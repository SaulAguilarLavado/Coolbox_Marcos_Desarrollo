let inactivityTimer;
let warningTimer;
const TIMEOUT_DURATION = 60000; // 1 minuto en milisegundos
const WARNING_DURATION = 45000; // Mostrar advertencia a los 45 segundos

// Eventos que resetean el timer de inactividad
const events = ['mousedown', 'mousemove', 'keypress', 'scroll', 'touchstart', 'click'];

// Función para verificar si hay un usuario logueado
function isUserLoggedIn() {
    // Buscar elementos que indiquen que hay un usuario logueado
    const logoutButton = document.querySelector('form[action="/logout"]');
    const welcomeMessage = document.querySelector('[data-user-logged="true"]');
    const userNameSpan = document.querySelector('.text-white span');
    
    // También verificar en el texto si aparece "Bienvenido"
    const navbarText = document.querySelector('.navbar .text-white');
    const hasWelcomeText = navbarText && navbarText.textContent.includes('Bienvenido');
    
    return logoutButton !== null || welcomeMessage !== null || hasWelcomeText;
}

// Función para mostrar advertencia
function showTimeoutWarning() {
    if (confirm('Tu sesión expirará en 15 segundos por inactividad. ¿Deseas continuar?')) {
        resetInactivityTimer();
    }
}

// Función para cerrar sesión por inactividad
function logoutDueToInactivity() {
    alert('Tu sesión ha expirado por inactividad. Serás redirigido al inicio.');
    
    // Enviar petición de logout
    fetch('/logout', {
        method: 'POST',
        credentials: 'same-origin',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        }
    }).then(() => {
        window.location.href = '/?expired=true';
    }).catch(() => {
        window.location.href = '/?expired=true';
    });
}

// Función para resetear el timer
function resetInactivityTimer() {
    // Limpiar timers existentes
    clearTimeout(inactivityTimer);
    clearTimeout(warningTimer);
    
    // Solo activar si hay un usuario logueado
    if (isUserLoggedIn()) {
        console.log('Usuario logueado detectado - Iniciando timer de inactividad');
        
        // Configurar advertencia a los 45 segundos
        warningTimer = setTimeout(showTimeoutWarning, WARNING_DURATION);
        
        // Configurar logout a los 60 segundos
        inactivityTimer = setTimeout(logoutDueToInactivity, TIMEOUT_DURATION);
    } else {
        console.log('No hay usuario logueado - Timer de inactividad desactivado');
    }
}

// Función para detectar cambios en el estado de login (cuando se loguea via modal)
function checkLoginStateChange() {
    resetInactivityTimer();
}

// Inicializar cuando se carga la página
document.addEventListener('DOMContentLoaded', function() {
    console.log('Session timeout script iniciado');
    
    // Agregar event listeners para actividad del usuario
    events.forEach(event => {
        document.addEventListener(event, resetInactivityTimer, true);
    });
    
    // Detectar cuando se envían formularios de login
    const loginForms = document.querySelectorAll('form[action="/login"], form[action="/registro"]');
    loginForms.forEach(form => {
        form.addEventListener('submit', () => {
            // Delay para permitir que la página se recargue y detecte el nuevo estado
            setTimeout(checkLoginStateChange, 1000);
        });
    });
    
    // Iniciar el timer
    resetInactivityTimer();
    
    // Verificar estado cada 10 segundos (por si hay cambios dinámicos)
    setInterval(checkLoginStateChange, 10000);
});

// Limpiar timers cuando se va la página
window.addEventListener('beforeunload', function() {
    clearTimeout(inactivityTimer);
    clearTimeout(warningTimer);
});

// Función de debug (puedes quitarla después)
window.debugSessionTimeout = function() {
    console.log('Usuario logueado:', isUserLoggedIn());
    console.log('Timer activo:', inactivityTimer !== undefined);
    console.log('Warning timer activo:', warningTimer !== undefined);
};