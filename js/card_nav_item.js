//Esperar a que el documento HTML esté completamente cargado
document.addEventListener("DOMContentLoaded", function () {

    // Seleccionar los elementos de la clase 'nav-link'
    const navLinks = document.querySelectorAll('.nav-link');
  
    // Recorrer cada enlace del nav
    navLinks.forEach(link => {
  
      // Agregar un evento para cuando Bootstrap haya mostrado la nueva pestaña
      link.addEventListener('shown.bs.tab', function () {
  
        // Asegurar que todos los tabs sean de color negro
        navLinks.forEach(l => {
          l.classList.remove('text-danger');
          l.classList.add('text-black');
        });
  
        // El tab que fue clickeado (this) se le cambia el color a rojo
        this.classList.remove('text-black');
        this.classList.add('text-danger');
      });
  
    });
  
  });
  