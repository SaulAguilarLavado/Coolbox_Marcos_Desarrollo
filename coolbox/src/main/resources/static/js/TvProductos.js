const productos = [
  {
    nombre: "Antena para TV",
    imagen: "../img/tvYvideo-img/antena.jpg",
    precio: "S/. 99.90"
  },
  {
    nombre: "Control Remoto Universal",
    imagen: "../img/tvYvideo-img/control.jpg",
    precio: "S/. 49.90"
  },
  {
    nombre: "Proyector",
    imagen: "../img/tvYvideo-img/proyector.jpg",
    precio: "S/. 1299.90"
  },
  {
    nombre: "Rack para TV",
    imagen: "../img/tvYvideo-img/rack_tv.jpg",
    precio: "S/. 399.90"
  },
  {
    nombre: "Televisor 50 pulgadas",
    imagen: "../img/tvYvideo-img/tv_50.jpg",
    precio: "S/. 1999.90"
  },
  {
    nombre: "Televisor 65 pulgadas",
    imagen: "../img/tvYvideo-img/tv_65.jpg",
    precio: "S/. 2999.90"
  },
  {
    nombre: "Televisor 85 pulgadas",
    imagen: "../img/tvYvideo-img/tv_85.jpg",
    precio: "S/. 4999.90"
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
