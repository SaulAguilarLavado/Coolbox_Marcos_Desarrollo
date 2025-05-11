const productos = [
  {
    nombre: "Cargador Inalámbrico",
    imagen: "../img/pilasYcargadores-img/cargador_inalambrico.jpg",
    precio: "S/. 199.90"
  },
  {
    nombre: "Cargador Universal",
    imagen: "../img/pilasYcargadores-img/cargador.jpg",
    precio: "S/. 149.90"
  },
  {
    nombre: "Pilas Duracell",
    imagen: "../img/pilasYcargadores-img/duracell.jpg",
    precio: "S/. 49.90"
  },
  {
    nombre: "Estabilizador de Voltaje",
    imagen: "../img/pilasYcargadores-img/estabilizador.jpg",
    precio: "S/. 299.90"
  },
  {
    nombre: "Extensión Eléctrica",
    imagen: "../img/pilasYcargadores-img/extension.jpg",
    precio: "S/. 99.90"
  },
  {
    nombre: "Pilas RadioShack AA",
    imagen: "../img/pilasYcargadores-img/radiosharck_pila_2A.jpg",
    precio: "S/. 29.90"
  },
  {
    nombre: "Pilas RadioShack AAA",
    imagen: "../img/pilasYcargadores-img/radiosharck_pila_3A.jpg",
    precio: "S/. 29.90"
  },
  {
    nombre: "Supresor de Picos",
    imagen: "../img/pilasYcargadores-img/supresor.jpg",
    precio: "S/. 199.90"
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
