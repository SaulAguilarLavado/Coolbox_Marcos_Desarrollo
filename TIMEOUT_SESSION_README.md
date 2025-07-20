# Sistema de Timeout de Sesión - Coolbox

## Descripción
Este sistema implementa un timeout de sesión de 1 minuto con alertas emergentes elegantes para mejorar la experiencia del usuario.

## Características

### ⏰ Timeout de 2 minutos
- La sesión expira automáticamente después de 2 minutos de inactividad
- Configurado en `application.properties`:
  ```properties
  server.servlet.session.timeout=2m
  spring.session.timeout=120s
  server.servlet.session.cookie.max-age=120
  ```

### 🚨 Alertas Emergentes
- **Advertencia a los 1 minuto 45 segundos**: Modal elegante con countdown de 15 segundos
- **Barra de progreso visual**: Muestra el tiempo restante
- **Botones de acción**: "Continuar sesión" o "Cerrar sesión"
- **Notificaciones toast**: Confirmaciones de acciones

### 🎯 Detección Inteligente
- Solo se activa cuando hay un usuario logueado
- Detecta automáticamente el estado de autenticación
- Se resetea con cualquier actividad del usuario (mouse, teclado, scroll, etc.)

## Archivos Implementados

### JavaScript Principal
- `src/main/resources/static/js/session-timeout.js` - Lógica principal del sistema

### Estilos CSS
- `src/main/resources/static/style/session-timeout.css` - Estilos para modales y toasts



## Funcionamiento

### 1. Inicialización
```javascript
// Se ejecuta automáticamente al cargar la página
document.addEventListener('DOMContentLoaded', function() {
    // Configura event listeners para actividad del usuario
    // Inicia el timer de inactividad
});
```

### 2. Detección de Usuario
```javascript
function isUserLoggedIn() {
    // Busca elementos que indiquen usuario logueado:
    // - Botón de logout
    // - Mensaje de bienvenida
    // - Atributo data-user-logged
}
```

### 3. Timer de Inactividad
- **1 minuto 45 segundos**: Muestra advertencia con modal
- **2 minutos**: Cierra sesión automáticamente

### 4. Modal de Advertencia
- Countdown visual de 15 segundos
- Barra de progreso animada
- Botones para extender o cerrar sesión
- Diseño responsive y elegante

## Configuración

### Spring Security
El `SecurityConfig.java` ya está configurado correctamente:
```java
.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
.maximumSessions(1)
.maxSessionsPreventsLogin(false)
.sessionFixation().migrateSession()
.invalidSessionUrl("/?expired=true")
```

### Mensajes de Estado
Los mensajes se muestran en la página principal:
- `/?success=true` - Login exitoso
- `/?error=true` - Error de credenciales
- `/?expired=true` - Sesión expirada
- `/?logout=true` - Logout manual

## Pruebas

El sistema funciona automáticamente cuando hay un usuario logueado. Para probar:

1. **Inicia sesión** en la aplicación
2. **Espera 1 minuto 45 segundos** sin actividad
3. **Verás el modal** con countdown de 15 segundos
4. **Elige una opción**: "Continuar sesión" o "Cerrar sesión"

## Personalización

### Cambiar Tiempos
En `session-timeout.js`:
```javascript
const TIMEOUT_DURATION = 120000;   // 2 minutos
const WARNING_DURATION = 105000;   // 1 minuto 45 segundos
const COUNTDOWN_DURATION = 15000;  // 15 segundos
```

### Cambiar Estilos
En `session-timeout.css`:
- Colores del modal
- Animaciones
- Responsive design
- Estilos de botones

## Eventos que Resetean el Timer
- `mousedown` - Clic del mouse
- `mousemove` - Movimiento del mouse
- `keypress` - Presionar tecla
- `scroll` - Scroll de página
- `touchstart` - Toque en dispositivos táctiles
- `click` - Clic en cualquier elemento

## Compatibilidad
- ✅ Chrome, Firefox, Safari, Edge
- ✅ Dispositivos móviles
- ✅ Bootstrap 5.x
- ✅ Spring Boot 3.x

## Notas Importantes

1. **No modificar SecurityConfig**: El sistema funciona sin cambios en la configuración de seguridad
2. **Dependencias**: Requiere Bootstrap 5 y Bootstrap Icons (ya incluidos)
3. **Funcionamiento automático**: Se activa solo cuando hay usuario logueado
4. **Sin logs de debug**: Sistema optimizado para producción

## Solución de Problemas

### Modal no aparece
1. Verificar que Bootstrap JS esté cargado
2. Revisar consola para errores JavaScript
3. Confirmar que hay un usuario logueado

### Timer no se resetea
1. Verificar event listeners
2. Comprobar función `isUserLoggedIn()`
3. Verificar que hay un usuario logueado

### Estilos no se aplican
1. Verificar que `session-timeout.css` esté incluido
2. Comprobar que Bootstrap CSS esté cargado
3. Revisar conflictos de CSS

## Ejemplo de Uso

```html
<!-- El sistema se activa automáticamente -->
<!-- No requiere configuración adicional -->
<!-- Solo funciona cuando hay usuario logueado -->
```

¡El sistema está listo para usar! 🚀 