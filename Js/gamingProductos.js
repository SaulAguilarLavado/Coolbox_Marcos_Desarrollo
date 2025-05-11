const productos = [
  {
    nombre: "Audífonos Gamer",
    imagen: "../img/gamer-img/audifonos_gamer.jpg",
    precio: "S/. 299.90"
  },
  {
    nombre: "Cooler para PC",
    imagen: "../img/gamer-img/cooler.jpg",
    precio: "S/. 149.90"
  },
  {
    nombre: "Mando Gamer",
    imagen: "../img/gamer-img/mando.jpg",
    precio: "S/. 199.90"
  },
  {
    nombre: "Mouse Gamer",
    imagen: "../img/gamer-img/mouse_gamer.jpg",
    precio: "S/. 129.90"
  },
  {
    nombre: "Mouse Pad Gamer",
    imagen: "../img/gamer-img/mouse_pad.jpg",
    precio: "S/. 49.90"
  },
  {
    nombre: "Silla Gamer",
    imagen: "../img/gamer-img/silla_gamer.jpg",
    precio: "S/. 899.90"
  },
  {
    nombre: "Teclado Gamer",
    imagen: "../img/gamer-img/teclado_gamer.jpg",
    precio: "S/. 249.90"
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
