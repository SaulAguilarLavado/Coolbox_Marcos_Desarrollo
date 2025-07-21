const productos = [
  {
    nombre: "Calculadora",
    imagen: "../img/Oficina-img/calculadora.jpg",
    precio: "S/. 99.90"
  },
  {
    nombre: "Control Biométrico",
    imagen: "../img/Oficina-img/control_biometrico.jpg",
    precio: "S/. 499.90"
  },
  {
    nombre: "Cortadora de Papel",
    imagen: "../img/Oficina-img/cortadora_papel.jpg",
    precio: "S/. 299.90"
  },
  {
    nombre: "Escritorio de Oficina",
    imagen: "../img/Oficina-img/escritorio_oficina.jpg",
    precio: "S/. 899.90"
  },
  {
    nombre: "Rack para Oficina",
    imagen: "../img/Oficina-img/rack.jpg",
    precio: "S/. 399.90"
  },
  {
    nombre: "Silla Gerencial",
    imagen: "../img/Oficina-img/silla_gerencial.jpg",
    precio: "S/. 699.90"
  },
  {
    nombre: "Triturador de Papel",
    imagen: "../img/Oficina-img/triturador_papel.jpg",
    precio: "S/. 599.90"
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
