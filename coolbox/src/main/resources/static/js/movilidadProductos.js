const productos = [
  {
    nombre: "Bicicleta Eléctrica",
    imagen: "../img/movilidad-img/bicicleta_electrica.jpg",
    precio: "S/. 2999.90"
  },
  {
    nombre: "Bicimoto",
    imagen: "../img/movilidad-img/bicimoto.jpg",
    precio: "S/. 2499.90"
  },
  {
    nombre: "Candado de Seguridad",
    imagen: "../img/movilidad-img/candado.jpg",
    precio: "S/. 99.90"
  },
  {
    nombre: "Casco de Protección",
    imagen: "../img/movilidad-img/casco.jpg",
    precio: "S/. 199.90"
  },
  {
    nombre: "Espejo Retrovisor",
    imagen: "../img/movilidad-img/espejo.jpg",
    precio: "S/. 49.90"
  },
  {
    nombre: "Moto Eléctrica",
    imagen: "../img/movilidad-img/moto_electrica.jpg",
    precio: "S/. 4999.90"
  },
  {
    nombre: "Scooter Eléctrico",
    imagen: "../img/movilidad-img/scooter_electrico.jpg",
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
