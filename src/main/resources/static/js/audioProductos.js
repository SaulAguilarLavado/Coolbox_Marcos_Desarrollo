const productos = [
  {
    nombre: "Asistente de Voz",
    imagen: "../img/audio-img/asistente_voz.jpg",
    precio: "S/. 249.90"
  },
  {
    nombre: "Audifonos con cable",
    imagen: "../img/audio-img/audifono_cable.jpg",
    precio: "S/. 99.90"
  },
  {
    nombre: "Audifonos inalambricos",
    imagen: "../img/audio-img/audifono_inalambrico.jpg",
    precio: "S/. 299.90"
  },
  {
    nombre: "Audifonos de Membrana",
    imagen: "../img/audio-img/audifono_membrana.jpg",
    precio: "S/. 149.90"
  },
  {
    nombre: "Microfono",
    imagen: "../img/audio-img/microfono.jpg",
    precio: "S/. 199.90"
  },
  {
    nombre: "Organo",
    imagen: "../img/audio-img/organo.jpg",
    precio: "S/. 799.90"
  },
  {
    nombre: "Parlante",
    imagen: "../img/audio-img/parlante.jpg",
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