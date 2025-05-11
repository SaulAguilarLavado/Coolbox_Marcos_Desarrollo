const productos = [
  {
    nombre: "Altavoz Inteligente",
    imagen: "../img/smartHome-img/altavoz_inteligente.jpg",
    precio: "S/. 199.90"
  },
  {
    nombre: "Aspiradora Inteligente",
    imagen: "../img/smartHome-img/aspiradora.jpg",
    precio: "S/. 599.90"
  },
  {
    nombre: "Cámara de Seguridad",
    imagen: "../img/smartHome-img/camara_seguridad.jpg",
    precio: "S/. 399.90"
  },
  {
    nombre: "Cerradura Smart",
    imagen: "../img/smartHome-img/cerradura_smart.jpg",
    precio: "S/. 499.90"
  },
  {
    nombre: "Convertidor para TV",
    imagen: "../img/smartHome-img/convertidor_tv.jpg",
    precio: "S/. 299.90"
  },
  {
    nombre: "Foco Inteligente",
    imagen: "../img/smartHome-img/foco_inteligente.jpg",
    precio: "S/. 99.90"
  },
  {
    nombre: "Monitor de Video",
    imagen: "../img/smartHome-img/monitor_video.jpg",
    precio: "S/. 699.90"
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
