document.getElementById("loginForm").addEventListener("submit", function(event) {
    event.preventDefault();

    const user = document.getElementById("user").value;
    const password = document.getElementById("password").value;
    const error = document.getElementById("error");

    // EXEMPLO DE LOGIN FIXO (você pode substituir por validação no backend)
    if (user === "admin" && password === "1234") {
        window.location.href = "estoque.html"; // redireciona para o sistema
    } else {
        error.textContent = "Usuário ou senha incorretos!";
    }
});
window.addEventListener("load", () => {
    const container = document.querySelector(".login-container");
    container.classList.add("show");
});

document.getElementById("loginForm").addEventListener("submit", function(event) {
    event.preventDefault();

    const user = document.getElementById("user").value;
    const password = document.getElementById("password").value;
    const error = document.getElementById("error");
    const form = document.querySelector(".login-container");

    // SIMULA LOGIN (troque depois por validação real)
    if (user === "admin" && password === "1234") {
        error.textContent = "";
        
        // Animação de fade-out ao entrar
        form.style.transition = "0.6s";
        form.style.opacity = "0";
        form.style.transform = "scale(0.9)";

        setTimeout(() => {
            window.location.href = "dashboard.html";
        }, 600);

    } else {
        error.textContent = "Usuário ou senha incorretos!";

        // Animação shake
        form.classList.remove("shake");
        void form.offsetWidth; // força o repaint para resetar a animação
        form.classList.add("shake");
    }
});