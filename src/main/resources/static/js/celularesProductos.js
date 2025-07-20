const productos = [
  {
    nombre: "Cable de Cargador",
    imagen: "../img/celularesYAccesorios-img/cable.jpg",
    precio: "S/. 49.90"
  },
  {
    nombre: "Case para celulares",
    imagen: "../img/celularesYAccesorios-img/case.jpg",
    precio: "S/. 39.90"
  },
  {
    nombre: "iPhone",
    imagen: "../img/celularesYAccesorios-img/iphone.jpg",
    precio: "S/. 4999.90"
  },
  {
    nombre: "Mica para celulares",
    imagen: "../img/celularesYAccesorios-img/micas.jpg",
    precio: "S/. 19.90"
  },
  {
    nombre: "Motorola",
    imagen: "../img/celularesYAccesorios-img/motorola.jpg",
    precio: "S/. 1299.90"
  },
  {
    nombre: "Samsung",
    imagen: "../img/celularesYAccesorios-img/samsung.jpg",
    precio: "S/. 2999.90"
  },
  {
    nombre: "Xiaomi",
    imagen: "../img/celularesYAccesorios-img/xiaomi.jpg",
    precio: "S/. 1999.90"
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