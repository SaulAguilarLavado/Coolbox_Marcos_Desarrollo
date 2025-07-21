const productos = [
  {
    nombre: "Cámara 360°",
    imagen: "../img/camarasYdrones-img/camara_360.jpg",
    precio: "S/. 899.90"
  },
  {
    nombre: "Cámara de Acción",
    imagen: "../img/camarasYdrones-img/camara_accion.jpg",
    precio: "S/. 699.90"
  },
  {
    nombre: "Cámara Instantánea",
    imagen: "../img/camarasYdrones-img/camara_instantanea.jpg",
    precio: "S/. 499.90"
  },
  {
    nombre: "Cámara Profesional",
    imagen: "../img/camarasYdrones-img/camara.jpg",
    precio: "S/. 2499.90"
  },
  {
    nombre: "Drone Básico",
    imagen: "../img/camarasYdrones-img/drone_1.jpg",
    precio: "S/. 1299.90"
  },
  {
    nombre: "Drone Avanzado",
    imagen: "../img/camarasYdrones-img/drone_2.jpg",
    precio: "S/. 1999.90"
  },
  {
    nombre: "GoPro",
    imagen: "../img/camarasYdrones-img/GoPro.jpg",
    precio: "S/. 1599.90"
  }
];

const container = document.getElementById("productos-container");

productos.forEach(producto => {
  const col = document.createElement("div");
  col.className = "col-12 col-md-6 col-lg-4 col-xl-2 mb-4";

  col.innerHTML = `
    <div class="card h-100 border-0 shadow-sm text-center">
      <img src="${producto.imagen}" class="card-img-top p-3" alt="${producto.nombre}">
      <div class="card-body">
        <h6 class="card-title fw-bold">${producto.nombre}</h6>
        <p class="card-text text-center">${producto.precio}</p>
      </div>
      <div class="text-center mb-3">
        <a href="#" class="btn btn-danger btn-sm">Ver producto</a>
      </div>
    </div>
  `;

  container.appendChild(col);
});
