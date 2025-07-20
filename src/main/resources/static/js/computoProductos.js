const productos = [
  {
    nombre: "Camara Web",
    imagen: "../img/computo-img/camara_web.jpg",
    precio: "S/. 199.90"
  },
  {
    nombre: "Impresora",
    imagen: "../img/computo-img/impresora.jpg",
    precio: "S/. 499.90"
  },
  {
    nombre: "Procesador Marca Intel", 
    imagen: "../img/computo-img/intel.jpg",
    precio: "S/. 199.90"
  },
  {
    nombre: "Kit Teclado y Mouse",
    imagen: "../img/computo-img/kit_teclado_mouse.jpg",
    precio: "S/. 149.90"
  },
  {
    nombre: "Router",
    imagen: "../img/computo-img/router.jpg",
    precio: "S/. 299.90"
  },
  {
    nombre: "Procesador Marca Ryzen",
    imagen: "../img/computo-img/ryzen.jpg",
    precio: "S/. 209.90"
  },
  {
    nombre: "Unidad de Estado Solido",
    imagen: "../img/computo-img/ssd.jpg",
    precio: "S/. 399.90"
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