const modal = document.getElementById("videoModal");
const video = document.getElementById("introVideo");

// Ocultar el modal cuando el video termina
video.addEventListener("ended", () => {
    modal.classList.add("hidden");
});

// Ocultar el modal si se hace clic fuera del video
modal.addEventListener("click", (event) => {
    if (event.target === modal) {
        modal.classList.add("hidden");
    }
});